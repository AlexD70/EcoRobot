package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slider {
    public enum Height{
        DOWN,
        HIGH
    }

    public DcMotor _motor;

    public final int ticks = 2000;

    private HardwareMap hardwareMap;

    public Height rn = Height.DOWN;

    public Object lock = new Object();

    public boolean flag = false;

    public double maxHeight = 2000;

    public Slider(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;

        _motor = hardwareMap.get(DcMotor.class, "glisiera");

        _motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        _motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void position(Height height){
        new Thread(() -> {
            synchronized (lock){
                //so that you can t access multiple threads at the same time
                int ticksRN = _motor.getCurrentPosition();
                int ticksdown = ticksRN;
                flag = false;

                if(height == Height.DOWN) _motor.setDirection(DcMotorSimple.Direction.REVERSE);
                {

                    while(ticksRN < ticksdown*2){
                        _motor.setPower(0.7);
                        ticksRN = _motor.getCurrentPosition();
                    }

                    if(ticksRN==ticksdown)flag = true;
                }

                if(height == Height.HIGH){

                    _motor.setDirection(DcMotorSimple.Direction.FORWARD);

                    while(ticksRN < ticks ){
                        _motor.setPower(0.7);
                        ticksRN = _motor.getCurrentPosition();
                    }
                }


                if(flag==true)_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                _motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

        }).start();
    }
}

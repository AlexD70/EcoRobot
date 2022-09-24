package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Glisiera {
    public enum Height{
        DOWN,
        HIGH
    }

    public DcMotor glisiera;

    public final int ticks = 2000;

    private HardwareMap hardwareMap;

    public Height rn = Height.DOWN;

    public Object lock = new Object();

    public boolean flag = false;

    public double maxHeight = 2000;

    public Glisiera(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;

        glisiera = hardwareMap.get(DcMotor.class, "glisiera");

        glisiera.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        glisiera.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void position(Height height){
        new Thread(() -> {
            synchronized (lock){
                //so that you can t access multiple threads at the same time
                int ticksRN = glisiera.getCurrentPosition();
                int ticksdown = ticksRN;
                flag = false;

                if(height == Height.DOWN) glisiera.setDirection(DcMotorSimple.Direction.REVERSE);
                {

                    while(ticksRN < ticksdown*2){
                        glisiera.setPower(0.7);
                        ticksRN = glisiera.getCurrentPosition();
                    }

                    if(ticksRN==ticksdown)flag = true;
                }

                if(height == Height.HIGH){

                    glisiera.setDirection(DcMotorSimple.Direction.FORWARD);

                    while(ticksRN < ticks ){
                        glisiera.setPower(0.7);
                        ticksRN = glisiera.getCurrentPosition();
                    }
                }


                if(flag==true)glisiera.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                glisiera.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

        }).start();
    }
}

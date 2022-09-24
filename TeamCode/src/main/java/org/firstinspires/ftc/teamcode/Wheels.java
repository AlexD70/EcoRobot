package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

public class Wheels {


    public DcMotor LF;
    public DcMotor LB;
    public DcMotor RF;
    public DcMotor RB;
    public DcMotor[] wheels = {LF, LB, RF, RB};

    private HardwareMap hardwareMap;

    public Wheels(HardwareMap hardwareMap){

        this.hardwareMap = hardwareMap;

        LF = hardwareMap.get(DcMotor.class, "LF");
        LB = hardwareMap.get(DcMotor.class, "LB");
        RF = hardwareMap.get(DcMotor.class, "RF");
        RB = hardwareMap.get(DcMotor.class, "RB");

        for(DcMotor motor : wheels){
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        LF.setDirection(DcMotorSimple.Direction.REVERSE);
        LB.setDirection(DcMotorSimple.Direction.REVERSE);
        RF.setDirection(DcMotorSimple.Direction.FORWARD);
        RB.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    //voiam sa extend MecanumDrive :/
    public void setMotorPowers(double v, double v1, double v2, double v3) {
        LF.setPower(v);
        RF.setPower(v1);
        LF.setPower(v2);
        RB.setPower(v3);
    }
}

package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Wheels {


    public DcMotor LF; // left front
    public DcMotor LB; // left back
    public DcMotor RF; // right front
    public DcMotor RB; // right back

    private HardwareMap hardwareMap;

    // constructor handles motor initialisation
    public Wheels(@NonNull HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;

        LF = hardwareMap.get(DcMotor.class, "LF");
        LB = hardwareMap.get(DcMotor.class, "LB");
        RF = hardwareMap.get(DcMotor.class, "RF");
        RB = hardwareMap.get(DcMotor.class, "RB");

        LF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LF.setDirection(DcMotorSimple.Direction.FORWARD);
        LB.setDirection(DcMotorSimple.Direction.FORWARD);
        RF.setDirection(DcMotorSimple.Direction.REVERSE);
        RB.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    //"voiam sa extend MecanumDrive :/    Vlad: Nu, nu voiati sa extindeti nimic"
    public void setMotorPowers(double v1, double v2, double v3, double v4) {
        LF.setPower(v1);
        RF.setPower(v2);
        LB.setPower(v3);
        RB.setPower(v4);
    }
}

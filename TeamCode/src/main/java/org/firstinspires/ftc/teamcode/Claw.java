package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {

    public Servo _servo;

    private HardwareMap hardwareMap;

    public double openPos = 0;
    public double closedPos = 0.8;

    public Claw(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        _servo = hardwareMap.get(Servo.class, "claw");
        _servo.setPosition(openPos);
    }

    public void open() {
        _servo.setPosition(openPos);
    }

    public void close() {
        _servo.setPosition(closedPos);
    }

}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw{

    public Servo _servo;

    private HardwareMap hardwareMap;

    public Object lock = new Object();

    public double initPos;
    public double openPos;
    public double closePos;

    public Gheara(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        _servo = hardwareMap.get(Servo.class, "gheara");

    }

    public void open(){
        new Thread(() -> {
            synchronized (lock) {
                _servo.setDirection(Servo.Direction.FORWARD);
                _servo.setPosition(openPos);
            }}
        ).start();
    }

    public void close(){
        new Thread(() -> {
            synchronized(lock){
             _servo.setDirection(Servo.Direction.REVERSE);
             _servo.setPosition(closePos);
        }}
        ).start();
    }

}
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Gheara{

    public Servo gheara;

    private HardwareMap hardwareMap;

    public Object lock = new Object();

    public double initPos;
    public double openPos;
    public double closePos;

    public Gheara(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        gheara = hardwareMap.get(Servo.class, "gheara");

    }

    public void open(){
        new Thread(() -> {
            synchronized (lock) {
                gheara.setDirection(Servo.Direction.FORWARD);
                gheara.setPosition(openPos);
            }}
        ).start();
    }

    public void close(){
        new Thread(() -> {
            synchronized(lock){
             gheara.setDirection(Servo.Direction.REVERSE);
             gheara.setPosition(closePos);
        }}
        ).start();
    }

}

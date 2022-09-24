package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class Drive extends LinearOpMode {

    public ControllerInput controller;

    private Wheels wheels;
    private Glisiera slider; //funny story am uitat cum se spune la glisiera in engleza
    private Gheara claw;

    @Override
    public void runOpMode() throws InterruptedException {

        controller = new ControllerInput(gamepad1);

        wheels = new Wheels(hardwareMap);
        slider = new Glisiera(hardwareMap);
        claw = new Gheara(hardwareMap);

        waitForStart();

        while(opModeIsActive()){
            controller.update();

            //ridica glisiera in teorie
            while(controller.right_trigger != 0){
                slider.position(Glisiera.Height.HIGH);
            }

            //coboara glisiera in teorie
            while(controller.left_trigger != 0){
                slider.position(Glisiera.Height.DOWN);
            }

            //deschide gheara in teorie
            if(controller.AOnce()){
                claw.open();
            }

            //in teorie inchide gheara
            if(controller.BOnce()){
                claw.close();
            }

            move();

        }
    }

    public void move(){

        double forward = -controller.right_stick_y;
        double strafe = controller.right_stick_x;
        double turn = controller.left_stick_x;

        double v = forward + strafe + turn;
        double v1 = forward - strafe - turn;
        double v2 = forward - strafe + turn;
        double v3 = forward + strafe - turn;

        wheels.setMotorPowers(Range.clip(v, -1, 1),
                              Range.clip(v1, -1,1),
                              Range.clip(v2, -1,1),
                              Range.clip(v3, -1,1));

    }
}

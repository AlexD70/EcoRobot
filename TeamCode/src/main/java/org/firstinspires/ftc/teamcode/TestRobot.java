package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class TestRobot extends LinearOpMode {
    public DcMotorEx lifter;
    public double triggerL, triggerR;
    private Wheels wheels;
    public ControllerInput controller;
    public Claw claw;

    @Override
    public void runOpMode() throws InterruptedException {
//        lifter = hardwareMap.get(DcMotorEx.class, "lifter");
//        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        lifter.setDirection(DcMotorSimple.Direction.REVERSE);
//        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        wheels = new Wheels(hardwareMap);
        controller = new ControllerInput(gamepad1);
        claw = new Claw(hardwareMap);

        waitForStart();
//        while (opModeIsActive()) {
//            telemetry.addLine("Started");
//            telemetry.update();
//
//            wheels.RF.setPower(0.8);
//            sleep(5000);
//            wheels.RF.setPower(0.0);
//
//            telemetry.log().clear();
//            telemetry.update();
//
//            sleep(5000);
//
//            wheels.RB.setPower(0.8);
//            sleep(5000);
//            wheels.RB.setPower(0.0);
//
//            sleep(5000);
//
//            wheels.LF.setPower(0.8);
//            sleep(5000);
//            wheels.LF.setPower(0.0);
//
//            sleep(5000);
//
//            wheels.LB.setPower(0.8);
//            sleep(5000);
//            wheels.LB.setPower(0.0);
//
//            sleep(5000);
//        }

        while (opModeIsActive()) {
            controller.update();
            triggerR = gamepad1.right_trigger;
            triggerL = gamepad1.left_trigger;

            move();
            lifter();

            if(controller.AOnce()) {
                telemetry.addData("HEre", " xddd");
                telemetry.update();
                claw.close();
            }

            if(controller.BOnce()) {
                claw.open();
            }

            telemetry.addData("left", triggerL);
            telemetry.addData("right", triggerR);
            telemetry.addData("ticks" ,lifter.getCurrentPosition());
            telemetry.update();

//            if (triggerR > 0.05 && triggerL < 0.05) { //go up
//                lifter.setPower(triggerR);
//            }
//            else if(triggerL > 0.05 && triggerR < 0.05) { //go down
//                lifter.setPower(-triggerL);
//            }
//            else lifter.setPower(0.0);
        }
    }

    public void move(){

        double delta = 0.7;

        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        double v = forward + strafe + turn;
        double v1 = forward - strafe - turn;
        double v2 = forward - strafe + turn;
        double v3 = forward + strafe - turn;

        wheels.setMotorPowers(Range.clip(v * delta, -1, 1),
                Range.clip(v1 * delta, -1,1),
                Range.clip(v2 * delta, -1,1),
                Range.clip(v3 * delta, -1,1));
    }

    public void lifter(){
        double maxTicks = 6800;

        lifter = hardwareMap.get(DcMotorEx.class, "lifter");
        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lifter.setDirection(DcMotorSimple.Direction.REVERSE);
        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        if(lifter.getCurrentPosition() > maxTicks && triggerR > 0.05)lifter.setPower(0.0);
        else if(lifter.getCurrentPosition() < 0 && triggerL > 0.05)lifter.setPower(0.0);
        else if (triggerR > 0.05 && triggerL < 0.05) { //go up
            lifter.setPower(triggerR);
        }
        else if(triggerL > 0.05 && triggerR < 0.05) { //go down
            lifter.setPower(-triggerL);
        }
        else lifter.setPower(0.0);


    }
}

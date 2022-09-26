package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TestLifter extends LinearOpMode {
    public DcMotorEx lifter;
    public double triggerL, triggerR;

    @Override
    public void runOpMode() throws InterruptedException {
        lifter = hardwareMap.get(DcMotorEx.class, "lifter");
        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lifter.setDirection(DcMotorSimple.Direction.REVERSE);
        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        while (opModeIsActive()) {
            triggerR = gamepad1.right_trigger;
            triggerL = gamepad1.left_trigger;

            telemetry.addData("left", triggerL);
            telemetry.addData("right", triggerR);
            telemetry.update();

            if (triggerR > 0.05 && triggerL < 0.05) { //go up
                lifter.setPower(triggerR);
            }
            else if(triggerL > 0.05 && triggerR < 0.05) { //go down
                lifter.setPower(-triggerL);
            }
            else lifter.setPower(0.0);

        }
    }
}

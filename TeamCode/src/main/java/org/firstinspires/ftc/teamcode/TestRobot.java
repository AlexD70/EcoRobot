package org.firstinspires.ftc.teamcode;

// imports
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

    // see Wheels.java
    private Wheels wheels;

    // see ControllerInput.java
    public ControllerInput controller;

    // see Claw.java
    public Claw claw;
    private final int max = 6800, min = -50;

    @Override
    public void runOpMode() throws InterruptedException {
        // lifter initialisation
	lifter = hardwareMap.get(DcMotorEx.class, "lifter");
	lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	lifter.setPower(0d);
        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lifter.setDirection(DcMotorSimple.Direction.FORWARD);
        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        
        // other initialisations
        wheels = new Wheels(hardwareMap);
        controller = new ControllerInput(gamepad1);
        claw = new Claw(hardwareMap);

        waitForStart();

	// loop
        while (opModeIsActive()) {
            // get input
            controller.update();
            triggerR = gamepad1.right_trigger;
            triggerL = gamepad1.left_trigger;
            
	    // handle mecanum wheels
            move();

	    // handle lifter
            lifter();

	    // handle claw
            if(controller.AOnce()) {
                claw.close();
            }

            if(controller.BOnce()) {
                claw.open();
            }
	
	    // some data we use for debugging
            telemetry.addData("left", triggerL);
            telemetry.addData("right", triggerR);
            telemetry.addData("ticks", lifter.getCurrentPosition());
            telemetry.update();
        }
    }

    // extremely simple mecanum implementation
    public void move(){

        double coefficient = 0.7; // P coefficient

        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        double v1 = forward + strafe + turn;
        double v2 = forward - strafe - turn;
        double v3 = forward - strafe + turn;
        double v4 = forward + strafe - turn;

        wheels.setMotorPowers(
	   // (k > max)?(max):((k < min)?(min):(k))
	   Range.clip(v1 * coefficient, -1, 1),
           Range.clip(v2 * coefficient, -1, 1),
           Range.clip(v3 * coefficient, -1, 1),
           Range.clip(v4 * coefficient, -1, 1)
	);
    }

    public void lifter(){
	
        double coefficient = 0.6d;
	double sensitivity_threshold = 0.5d;

	if (gamepad1.right_trigger > sensitivity_threshold && gamepad1.left_trigger > sensitivity_threshold){
	    ;; // pass
	} else if (lifter.getCurrentPosition() < this.max && gamepad1.right_trigger > sensitivity_threshold) {
	    lifter.setPower(gamepad1.right_trigger * coefficient);
	    return;
	} else if (lifter.getCurrentPosition() > this.min && gamepad1.left_trigger > sensitivity_threshold) {
	    lifter.setPower(gamepad1.left_trigger * (-coefficient));
	    return;
	}

	lifter.setPower(0d);
    }
}

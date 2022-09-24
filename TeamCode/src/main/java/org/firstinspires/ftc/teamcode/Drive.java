import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

class Drive extends LinearOpMode {

    DcMotor lf, rf, lb, rb; // we should make an array but i cant do it rn cause i dont hv access to a laptop atm

    public boolean _init() { 
	this.lf = this.hardwareMap.get(DcMotor.class, "lf");
	this.rf = this.hardwareMap.get(DcMotor.class, "rf");
	this.lb = this.hardwareMap.get(DcMotor.class, "lb");
	this.rb = this.hardwareMap.get(DcMotor.class, "rb");
	// encoders
	// runmodes (brake/float)
	// TODO remeber to check for errors, print telemetry/robot logs and return true
	// set power 0 to everything, get lifter and claw to position 0
    }

    @Override
    public void runOpMode(){
         _init();

	 waitForStart();

	 while (opModeIsActive()) {
              // handleDriving();
	      // handleMechanisms();
	 }
    }

    public void handleDriving(){}
    public void handleMechanisms(){}
}

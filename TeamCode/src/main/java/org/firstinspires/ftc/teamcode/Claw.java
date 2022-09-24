import com.qualcomm.hardware.Servo;
// import telemetry ???

public class Claw {

    private double down = 0, up = 1;
    private Servo _servo;

    private void _init(){
         // go to position 0
    }

    public Claw(Servo __servo){
        this._servo = __servo;
	_init();
    }

    public void togglePosition(){}
}

import com.qualcomm.hardware.DcMotor;
// import telemetry???


public class Lifter {
    //private int ticksFrom0ToMax ???
    private DcMotor _motor;

    public Lifter(DcMotor __motor) {
        this._motor = __motor;
        // returnTo0();
    } 

    public void gotoPos(/* some public enum member param */) {}
}

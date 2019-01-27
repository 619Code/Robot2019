package frc.robot;
public class RobotMap {

    // CAN IDs
    public static final int LEFT_FRONT = 10;
    public static final int LEFT_MIDDLE = 11;
    public static final int LEFT_REAR = 12;

    public static final int RIGHT_FRONT = 13;
    public static final int RIGHT_MIDDLE = 14;
    public static final int RIGHT_REAR = 15;

    public static final int LEFT_INTAKE = 20;
    public static final int RIGHT_INTAKE = 21;

    public static final int INTAKE1 = 30;
    public static final int INTAKE2 = 31;

    // pneumatics TODO: FIGURE THESE OUT WHEN THE BOARD IS READY
    public static final int PCM_CAN_ID = 10000000; 
    public static final int INTAKE_WRIST_CHANNEL = 0;

    // ramp constant (only works if in coast)
    public static final double RAMP_RATE = 0.5;
    public static final double DEADZONE = 0.07;

    // motor outputs
    public static final double DRIVE_OUTPUT_MAX = 0.6;
    public static final double DRIVE_ROT_MAX = 0.5;
    public static final double DRIVE_SPEED_MAX = 1.0;

    public static final double INTAKE_SPEED = 0.5;

    // controller public static finals
    public static final int SPEED_AXIS = 1;
    public static final int ROT_AXIS = 4;
    public static final int GTA_ROT_AXIS = 0;
    public static final int LEFT_TRIGGER = 2;
    public static final int RIGHT_TRIGGER = 3;

    // pathfinder constants
    public static final double DT = 0.05;
    public static final double MAX_VELOCITY = 1;
    public static final double MAX_ACCEL = 0.5;
    public static final double MAX_JERK = 40.0;
    public static final double WHEEL_DIAMETER = 3.940;
    public static final int ENCODER_TICK_PER_REV = 6;

    // Encoder PID
    public static final double kP = 1.0;
    public static final double kI = 0.0;
    public static final double kD = 0.0;

    //Lift PID
    public static final double liftkP = 0;
	public static final double liftkI = 0;
	public static final double liftkD = 0;
	public static final double liftkF = 0;
	public static final int liftkIzone = 0;
    public static final double liftkPeakOutput = 0;
    
    //Motion Magic Constants
    public static final int kSlotIdx = 0;
    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 30;
    

}
package frc.robot;

public class RobotMap {

    // CAN IDs
    public static final int LEFT_FRONT = 10;
    public static final int LEFT_MIDDLE = 11;
    public static final int LEFT_REAR = 12;

    public static final int RIGHT_FRONT = 13;
    public static final int RIGHT_MIDDLE = 14;
    public static final int RIGHT_REAR = 15;

    // ramp constant (only works if in coast)
    public static final double RAMP_RATE = 0.5;
    public static final double DEADZONE = 0.07;
    // drive public static finals
    public static final double OUTPUT_MAX = 0.2;
    public static final double ROT_MAX = 0.5;
    public static final double SPEED_MAX = 1.0;

    // controller public static finals
    public static final int SPEED_AXIS = 1;
    public static final int ROT_AXIS = 4;
    public static final int GTA_ROT_AXIS = 0;
    public static final int LEFT_TRIGGER = 2;
    public static final int RIGHT_TRIGGER = 3;

}
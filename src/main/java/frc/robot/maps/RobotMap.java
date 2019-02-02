package frc.robot.maps;

import edu.wpi.first.wpilibj.GenericHID.Hand;

public class RobotMap {

    public enum Subsystem {
        LIFT, ARM, INTAKE, HATCH, GRABBER, CLIMB;
    }

    public enum AutoType {
        LINE;
    }
    public enum LIFT_TARGETS{
        LOWER(0),MIDDLE(1),HIGH(2),NULL_POSITION(-1);
        private final int rotations;
        LIFT_TARGETS(int rotations){this.rotations = rotations;}
        public int getValue() {return rotations;}

    }
    public enum ARM_TARGETS{
        BACK(0),LOWER(1),MIDDLE(2),HIGH(3),NULL_POSITION(-1);
        private final int rotations;
        ARM_TARGETS(int rotations){this.rotations = rotations;}
        public int getValue() {return rotations;}
    }
    // CAN IDs
    public static final int LEFT_FRONT = 10;
    public static final int LEFT_MIDDLE = 11;
    public static final int LEFT_REAR = 12;

    public static final int RIGHT_FRONT = 13;
    public static final int RIGHT_MIDDLE = 14;
    public static final int RIGHT_REAR = 15;

    public static final int LEFT_LIFT = 41;
    public static final int RIGHT_LIFT = 40;

    public static final int INTAKE = 30;

    public static final int ARM = 35;

    public static final int LEFT_CLIMB = 50;
    public static final int RIGHT_CLIMB = 51;

    public static final int LEFT_GRABBER = 60;
    public static final int RIGHT_GRABBER = 61;

    public static final int LEFT_MIDDLE_CLIMB_SWITCH = 0;
    public static final int LEFT_END_CLIMB_SWITCH = 1;
    public static final int RIGHT_MIDDLE_CLIMB_SWITCH = 2;
    public static final int RIGHT_END_CLIMB_SWITCH = 3;

    // pneumatics TODO: FIGURE THESE OUT WHEN THE BOARD IS READY
    public static final int PCM_CAN_ID = 10000000; 
    public static final int INTAKE_WRIST_CHANNEL = 10;
    public static final int[] HATCH_GRABBER_CHANNEL = {0, 1};
    public static final int[] HATCH_EXTEND_CHANNEL = {2, 3};

    // ramp constant (only works if in coast)
    public static final double RAMP_RATE = 0.5;
    public static final double DEADZONE = 0.07;

    // motor outputs
    public static final double DRIVE_OUTPUT_MAX = 0.6;
    public static final double DRIVE_ROT_MAX = 0.5;
    public static final double DRIVE_SPEED_MAX = 1.0;

    public static final double INTAKE_SPEED = 0.5;

    // controller public static finals
    public static final Hand SPEED_HAND = Hand.kLeft;
    public static final Hand ROT_HAND = Hand.kRight;
    public static final Hand RIGHT_HAND = Hand.kRight;
    public static final Hand LEFT_HAND = Hand.kLeft;

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
    public static final double LIFT_kP = 0;
	public static final double LIFT_kI = 0;
	public static final double LIFT_kD = 0;
	public static final double LIFT_kF = 0;
	public static final int LIFT_kIZONE = 0;
    public static final double LIFE_PEAK_OUTPUT = 0;
    
    //Arm PID
    public static final double ARM_kP = 0;
	public static final double ARM_kI = 0;
	public static final double ARM_kD = 0;
    public static final int ARM_kIZONE = 0;
    public static final double ARM_KFF = 0;
    public static final double ARM_MAXOUTPUT = 0;
    public static final double ARM_MINOUTPUT = 0;

    //Motion Magic Constants
    public static final int kSLOTIDX = 0;
    public static final int kPIDLOOPIDX = 0;
    public static final int kTIMEOUT_MS = 30;

    //gear ratios
    public static final int RATIO_LIFT = 70;
    public static final int RATIO_ARM = 81;

    //encoder ticks per rotation
    public static final int TICKSPERROT_VERSAPLANETARY_ENC = 1024;
    public static final int TICKSPERROT_NEO_ENC = 42;
}
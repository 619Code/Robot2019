package frc.robot.maps;

public class VisionMap{
    public static final double BLUR_RADIUS = 0.44;
    public static final double RADUS = BLUR_RADIUS + 0.5;
    public static final double KERNEL_SIZE = 6 * RADUS + 1;

    public static final double[] HSV_THRESHOLD_HUE = {75.0, 100.0};
    public static final double[] HSV_THRESHOLD_SATURATION = {90.0, 255.0};
    public static final double[] HSV_THRESHOLD_VALUE = {100.0, 150.0};

    public static final double CV_CANNY_THRESHOLD_1 = 0.0;
    public static final double CV_CANNY_THRESHOLD_2 = 0.5;
    public static final int CV_CANNY_APERTURE_SIZE = 5;
    public static final boolean CV_CANNY_L2_GRADIENT = false;
}
package frc.robot.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import frc.robot.vision.GRIPVision.BlurType;

public class VisionProcess{
    private Mat resizeImageOutput = new Mat();
	private Mat blurOutput = new Mat();
	private Mat hsvThresholdOutput = new Mat();
	private Mat cvCannyOutput = new Mat();
    public VisionProcess(){
    
    }
    public Mat process(Mat source0) {

		// Step Resize_Image0:
		Mat resizeImageInput = source0;
		double resizeImageWidth = 640;
		double resizeImageHeight = 480;
		int resizeImageInterpolation = Imgproc.INTER_NEAREST;
        Imgproc.resize(resizeImageInput, resizeImageOutput, new Size(resizeImageWidth, resizeImageHeight), 0.0, 0.0, resizeImageInterpolation);
		// Step Blur0:
		Mat blurInput = resizeImageOutput;
        double blurRadius = 7.0;
        int radius = (int)(blurRadius + 0.5);
        int kernelSize = 6 * radius + 1;
		Imgproc.GaussianBlur(resizeImageOutput,blurOutput, new Size(kernelSize, kernelSize), radius);


		// Step HSV_Threshold0:
		Mat hsvThresholdInput = blurOutput;
		double[] hsvThresholdHue = {70.0, 150.0};
		double[] hsvThresholdSaturation = {70.0, 255.0};
		double[] hsvThresholdValue = {160.0, 255.0};
        Imgproc.cvtColor(hsvThresholdInput, hsvThresholdOutput, Imgproc.COLOR_BGR2HSV);
		Core.inRange(hsvThresholdOutput, new Scalar(hsvThresholdHue[0], hsvThresholdSaturation[0], hsvThresholdValue[0]),
			new Scalar(hsvThresholdSaturation[1], hsvThresholdSaturation[1], hsvThresholdValue[1]), hsvThresholdOutput);
		// Step CV_Canny0:
		Mat cvCannyImage = hsvThresholdOutput;
		double cvCannyThreshold1 = 0.0;
		double cvCannyThreshold2 = 1.0;
		double cvCannyAperturesize = 3.0;
		boolean cvCannyL2gradient = false;
        Imgproc.Canny(cvCannyImage, cvCannyOutput, cvCannyThreshold1, cvCannyThreshold2, (int)cvCannyAperturesize, cvCannyL2gradient);
        return cvCannyOutput;
    }
}
package frc.robot.vision;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import frc.robot.maps.VisionMap;

public class VisionProcess{
    private Mat brightImage = new Mat();
	private Mat blurImage = new Mat();
	private Mat hsvImage = new Mat();
	private Mat cannyImage = new Mat();
	Mat hierarchy;

	List<MatOfPoint> contours;

	private Random rng = new Random(12345);

    public VisionProcess(){
	}
	
    public Mat process(Mat source0) {
		brightImage = new Mat(source0.rows(), source0.cols(), source0.type());
		source0.convertTo(brightImage, -1, 1, -100);

		//Blur
		Imgproc.blur(brightImage, blurImage, new Size(VisionMap.KERNEL_SIZE, VisionMap.KERNEL_SIZE));
		//Imgproc.GaussianBlur(brightImage, blurImage, new Size(VisionMap.KERNEL_SIZE, VisionMap.KERNEL_SIZE), VisionMap.RADUS);


		//HSV
        Imgproc.cvtColor(blurImage, hsvImage, Imgproc.COLOR_BGR2HSV);
		Core.inRange(hsvImage, new Scalar(VisionMap.HSV_THRESHOLD_HUE[0], VisionMap.HSV_THRESHOLD_SATURATION[0], VisionMap.HSV_THRESHOLD_VALUE[0]),
			new Scalar(VisionMap.HSV_THRESHOLD_HUE[1], VisionMap.HSV_THRESHOLD_SATURATION[1], VisionMap.HSV_THRESHOLD_VALUE[1]), 
			hsvImage);

		//Canny
        Imgproc.Canny(hsvImage, cannyImage, VisionMap.CV_CANNY_THRESHOLD_1, VisionMap.CV_CANNY_THRESHOLD_2, VisionMap.CV_CANNY_APERTURE_SIZE, VisionMap.CV_CANNY_L2_GRADIENT);

		contours = new ArrayList<>();
		hierarchy = new Mat();
		Imgproc.findContours(cannyImage, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

		contours = filterContours(contours);

		//System.out.println(contours.size());
		for(int i = 0; i < contours.size(); i++){
			 Rect contourRect = Imgproc.boundingRect(contours.get(i));
			 if(i == 1)  Imgproc.rectangle(source0, contourRect.tl(), contourRect.br(), new Scalar(0, 255, 0), 2);
			 else if(i == 2)  Imgproc.rectangle(source0, contourRect.tl(), contourRect.br(), new Scalar(255, 0, 0), 2); 
			 else Imgproc.rectangle(source0, contourRect.tl(), contourRect.br(), new Scalar(0, 0, 255), 2);
		}

		return source0;
	}
	
	//remove overlapping contours
	//remove small contours with height less than 20
	public List<MatOfPoint> filterContours(List<MatOfPoint> contours){
		List<MatOfPoint> filteredContours = new ArrayList<>();
		for(int i= 0; i < contours.size(); i++){
			if(contours.get(i).height() > 20){
				Rect currentRect = Imgproc.boundingRect(contours.get(i));
				if(!isOverlap(currentRect, i) && !isDuplicate(currentRect, filteredContours)){
					filteredContours.add(contours.get(i));
				}
			}
		}
		return filteredContours;
	}

	public boolean isOverlap(Rect currentRect, int rectIdx){
		for(int j = 0; j < contours.size(); j++){
			Rect compareRect = Imgproc.boundingRect(contours.get(j));
			//System.out.println("i x: " + i + " " + currentRect.tl().x + "j x:" + j + " " + compareRect.tl().x);
			if(rectIdx != j && currentRect.tl().x > compareRect.tl().x && currentRect.tl().y > compareRect.tl().y &&
			currentRect.br().x < compareRect.br().x && currentRect.br().y < compareRect.br().y){
				return true;
			}
		}
		return false; 
	}

	public boolean isDuplicate(Rect currentRect, List<MatOfPoint> filteredContours){
		for(int i = 0; i < filteredContours.size(); i++){
			Rect contourRect = Imgproc.boundingRect(filteredContours.get(i));
			if(currentRect.tl().equals(contourRect.tl()) && currentRect.br().equals(contourRect.br())){
				return true;
			}
		}
		return false;
	}
}
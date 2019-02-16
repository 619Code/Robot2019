package frc.robot.vision;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import frc.robot.threading.RobotThread;
import frc.robot.threading.ThreadManager;

public class VisionThread extends RobotThread {

    VideoCapture cam;
    Mat frame;

    public VisionThread(ThreadManager threadManager) {
        super(threadManager);
        cam = new VideoCapture(0);
        start();
    }

    @Override
    protected void cycle() {
        cam.read(frame);
    }
} 
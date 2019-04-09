package frc.robot.vision;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.threading.RobotThread;
import frc.robot.threading.ThreadManager;

public class VisionThread extends RobotThread {

    VideoCapture cam;
    Mat frame;
    Mat source;
    Mat output;
    CvSink cvSink;
    CvSource outputStream;
    VisionProcess visionProcess;

    boolean _outputing;

    public VisionThread(ThreadManager threadManager, boolean outputing) {
        super(threadManager);

        source = new Mat();
        output = new Mat();
        cvSink = CameraServer.getInstance().getVideo();
        outputStream = CameraServer.getInstance().putVideo("Vision", 640, 480);
        visionProcess = new VisionProcess();

        _outputing = outputing;
        start();
    }

    @Override
    protected void cycle() {
        cvSink.grabFrame(source);
        visionProcess.process(source);
        if(_outputing){
            output = visionProcess.getImage();
            outputStream.putFrame(output);
        }
        //System.out.println("Rectangles: " + getRectangles());
    }

    public int getRectangles(){
        return visionProcess.getRectangles();
    }
} 
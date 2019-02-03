package frc.robot.auto.lidar;

import roboticinception.rplidar.*;

/**
 * Prints out raw scans from the low level driver
 *
 * @author Peter Abeles
 */
public class SpewOutMeasurements implements RpLidarListener {

	@Override
	public void handleMeasurement(RpLidarMeasurement measurement) {
		double deg = measurement.angle / 64.0;
		double r = measurement.distance / 4.0;
		if (measurement.start)
			System.out.println();
		if( deg > 350 )
			System.out.printf(measurement.start + " %3d   theta = %6.2f r = %10.2f\n", measurement.quality, deg, r);
//		System.out.printf(measurement.start + " %3d   theta = %4d r = %5d\n", measurement.quality, measurement.angle, measurement.distance);
	}

	@Override
	public void handleDeviceHealth(RpLidarHeath health) {
		health.print();
	}

	@Override
	public void handleDeviceInfo(RpLidarDeviceInfo info) {
		System.out.println("Got device info packet");
		info.print();
    }
    
	public static void main(String[] args) throws Exception {
		//System.loadLibrary("rxtxSerial");
		//System.loadLibrary("rxtxParallel");
		System.out.println("ez clap");
		RpLidarLowLevelDriver driver = new RpLidarLowLevelDriver("COM5", new SpewOutMeasurements());
		driver.setVerbose(false);
		driver.sendReset();
		driver.pause(100);

		driver.sendGetInfo(100);
		driver.sendGetHealth(100);
		//for v2 only - I guess this command is ignored by v1
		driver.sendStartMotor(660);
		driver.sendScan(50);
		driver.pause(100);
		driver.sendReset();
		driver.pause(100);
		driver.shutdown();
		driver.pause(100);
		System.exit(0);
	}
}
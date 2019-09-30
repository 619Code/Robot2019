# Robot 2019

This is Team 619's 2019 FRC robot code for Armstrong. The code is written in Java and is based off of WPILib's Java control system.

The code is divided into several pacakges that correlate to a specific function of Armstrong.

## Code Highlights
### Path Planning and Following
Used [Tervay's EasyPath library](https://github.com/tervay/EasyPath) to score hatches during sandstorm period.  
### Multiple Autonomous Modes
8 Autonomous modes to place a hatch on the side and front of the ship, place a hatch on the side of the ship, or place a hatch on the rocket and go to the loading station on both the right and left sides of the ship and rocket.
### Custom PID
[Custom PID algorithm](https://github.com/619Code/Robot2019/blob/master/src/main/java/frc/robot/helper/PID.java) to position the arm in the optimal position for collection and scoring of cargo.
### Vision Allignment
Used [OpenCV](https://github.com/opencv/opencv) library to align to retroreflective tape placed on the ship, rocket, and loading station.
## Packages
* [```frc.robot.auto```](https://github.com/619Code/Robot2019/tree/master/src/main/java/frc/robot/auto)<br/>
Handles the execution of autonomous routines
* [```frc.robot.drive```](https://github.com/619Code/Robot2019/tree/master/src/main/java/frc/robot/drive)<br/>
Contains the control for the West Coast drive.
* [```frc.robot.hardware```](https://github.com/619Code/Robot2019/tree/master/src/main/java/frc/robot/hardware)<br/>
Contiains custrom hardware classes such as the controller and limit switches.
* [```frc.robot.helper```](https://github.com/619Code/Robot2019/tree/master/src/main/java/frc/robot/helper)<br/>
Contains miscellaneous functions that assist other functions of the robot
* [```frc.robot.maps```](https://github.com/619Code/Robot2019/tree/master/src/main/java/frc/robot/maps)<br/>
Contains all variables and controller key mapping.
* [```frc.robot.subsystems```](https://github.com/619Code/Robot2019/tree/master/src/main/java/frc/robot/subsystems)<br/>
Contains susbsystem for each manipulator. Arm, climb, grabber, hatch, intake, and lift are handled within this package.
* [```frc.robot.teleop```](https://github.com/619Code/Robot2019/tree/master/src/main/java/frc/robot/teleop)<br/>
Contains main teleop thread
* [```frc.robot.threading```](https://github.com/619Code/Robot2019/tree/master/src/main/java/frc/robot/threading)<br/>
Contains the thread manager and robot thread
* [```frc.robot.vision```](https://github.com/619Code/Robot2019/tree/master/src/main/java/frc/robot/vision)<br/>
Contains the vision pipeline and vision processing to locate and align to retroreflective tape placed on the ship, rocket, and loading station.

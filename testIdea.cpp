#include "Robot.h"
#include "RobotMap.hpp"
#include "rev/CANSparkMax.h"

#include <iostream>
#include <vector>

#include <frc/smartdashboard/SmartDashboard.h>
#include <frc/Joystick.h>
#include <frc/SpeedControllerGroup.h>
#include <frc/drive/DifferentialDrive.h>

using namespace frc;
using namespace rev;

CANTalon talon{/*Put code here*/};

void closedLoop() {
    talon.SetPID(0.3,0,0,0.025); /* P=0.3, F=0.025, everything is 0 */
    talon.SetClosedLoopRampRate(0.0);
    talon.SetIzone(0.0);
}
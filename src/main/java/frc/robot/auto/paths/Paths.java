package frc.robot.auto.paths;

import easypath.Path;
import easypath.PathUtil;

public class Paths{
    public static Path AUTOTOHATCHLVL2 = new Path(t -> 
    /* {"start":{"x":19,"y":212},"mid1":{"x":189,"y":208},"mid2":{"x":134,"y":169},"end":{"x":230,"y":172}} */
    (231 * Math.pow(t, 2) + -210 * t + -12) / (1128 * Math.pow(t, 2) + -1350 * t + 510),
    217.291);
    public static Path TESTCURVEYPATH = new Path(t -> 
    /* {"start":{"x":114,"y":209},"mid1":{"x":188,"y":210},"mid2":{"x":127,"y":174},"end":{"x":212,"y":174}} */
    (219 * Math.pow(t, 2) + -222 * t + 3) / (843 * Math.pow(t, 2) + -810 * t + 222),
    110.682);

    public static Path GRABHATCHAFTERBACKUP = new Path(t -> 
    /* {"start":{"x":188,"y":174},"mid1":{"x":116,"y":174},"mid2":{"x":221,"y":297},"end":{"x":28,"y":297}} */
    (-738 * Math.pow(t, 2) + 738 * t + 0) / (-1425 * Math.pow(t, 2) + 1062 * t + -216),
    232.376);

    public static Path getStraightLinePath(double inches){
        return PathUtil.createStraightPath(inches);
    }
}
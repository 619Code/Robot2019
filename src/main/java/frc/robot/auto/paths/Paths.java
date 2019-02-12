package frc.robot.auto.paths;

import easypath.Path;
import easypath.PathUtil;

public class Paths{
    public static Path AUTOTOHATCHLVL2 = new Path(t -> 
    /* {"start":{"x":19,"y":212},"mid1":{"x":189,"y":208},"mid2":{"x":134,"y":169},"end":{"x":230,"y":172}} */
    (231 * Math.pow(t, 2) + -210 * t + -12) / (1128 * Math.pow(t, 2) + -1350 * t + 510),
    217.291);
    public static Path TESTCURVEYPATH = new Path(t -> 
    /* {"start":{"x":114,"y":209},"mid1":{"x":188,"y":210},"mid2":{"x":127,"y":174},"end":{"x":217,"y":174}} */
    (219 * Math.pow(t, 2) + -222 * t + 3) / (858 * Math.pow(t, 2) + -810 * t + 222),
    114.995);

    public static Path GRABHATCHAFTERBACKUP = new Path(t -> 
    /* {"start":{"x":181,"y":174},"mid1":{"x":83,"y":174},"mid2":{"x":160,"y":14},"end":{"x":46,"y":21}} */
    (981 * Math.pow(t, 2) + -960 * t + 0) / (-1098 * Math.pow(t, 2) + 1050 * t + -294),
    230.562);

    public static Path getStraightLinePath(double inches){
        return PathUtil.createStraightPath(inches);
    }
}
package frc.robot.auto.variables;

import easypath.Path;
import easypath.PathUtil;

public class Paths{
    public static Path AUTOTOHATCHLVL2 = new Path(t -> 
    /* {"start":{"x":19,"y":212},"mid1":{"x":189,"y":208},"mid2":{"x":134,"y":169},"end":{"x":230,"y":172}} */
    (231 * Math.pow(t, 2) + -210 * t + -12) / (1128 * Math.pow(t, 2) + -1350 * t + 510),
    217.291);
    // public static Path TESTCURVEPATH = new Path(t -> 
    // /* {"start":{"x":114,"y":209},"mid1":{"x":188,"y":210},"mid2":{"x":127,"y":174},"end":{"x":212,"y":174}} */
    // (219 * Math.pow(t, 2) + -222 * t + 3) / (843 * Math.pow(t, 2) + -810 * t + 222),
    // 110.682);

    public static Path TESTCURVEPATH = new Path(t -> 
    /* {"start":{"x":114,"y":209},"mid1":{"x":188,"y":210},"mid2":{"x":129,"y":176},"end":{"x":212,"y":176}} */
    (207 * Math.pow(t, 2) + -210 * t + 3) / (825 * Math.pow(t, 2) + -798 * t + 222),
    109.307);

    //works for v2 0.04 kP
    // public static Path GRABHATCHAFTERBACKUP = new Path(t -> 
    // /* {"start":{"x":188,"y":174},"mid1":{"x":116,"y":174},"mid2":{"x":221,"y":295},"end":{"x":20,"y":295}} */
    // (-726 * Math.pow(t, 2) + 726 * t + 0) / (-1449 * Math.pow(t, 2) + 1062 * t + -216),
    // 236.916);

    public static Path GRABHATCHAFTERBACKUP = new Path(t -> 
    /* {"start":{"x":200,"y":174},"mid1":{"x":128,"y":174},"mid2":{"x":116,"y":184},"end":{"x":131,"y":-38}} */
    (-726 * Math.pow(t, 2) + 60 * t + 0) / (-99 * Math.pow(t, 2) + 360 * t + -216),
    266.736);

    public static Path TESTTURN = new Path(t -> 
    /* {"start":{"x":200,"y":174},"mid1":{"x":109,"y":173},"mid2":{"x":109,"y":184},"end":{"x":98,"y":30}} */
    (-531 * Math.pow(t, 2) + 72 * t + -3) / (-306 * Math.pow(t, 2) + 546 * t + -273),
    217.188);

    public static Path getStraightLinePath(double inches){
        return PathUtil.createStraightPath(inches);
    }
}
package frc.robot.auto.paths;

import java.util.function.Function;

public class Speeds{
    public static final Function<Double, Double> DEFAULT = x -> {
        return 0.2;
    };

    public static final Function<Double, Double> REVERSE_DEFAULT = x -> {
        return -0.2;
    };

    public static final Function<Double, Double> SECOND_HATCH_SPEED = x -> {
        if(x > 0.5) return 0.2;
        else return 0.3;
    };

    public static Function<Double, Double> setSpeed(double speed){
        return x -> {return speed;};
    }
}
package frc.robot.auto.paths;

import java.util.function.Function;

public class Speeds{
    public static final Function<Double, Double> DEFAULT = x -> {
        return 0.2;
    };

    public static final Function<Double, Double> REVERSE_DEFAULT = x -> {
        return -0.2;
    };

    public static Function<Double, Double> setSpeed(double speed){
        return x -> {return speed;};
    }
}
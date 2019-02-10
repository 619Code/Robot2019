package frc.robot.auto.paths;

import java.util.function.Function;

public class Speeds{
    public static final Function<Double, Double> DEFAULT = x -> {
        return 0.2;
    };
}
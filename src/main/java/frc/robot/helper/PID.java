package frc.robot.helper;

public class PID{
    double Kp, Ki, Kd, p_error, i_error, d_error, sum;
    int i;
    int[] window;
    final int WINDOW_SIZE = 20;
    public PID(double Kp, double Ki, double Kd) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
        
        p_error = 0;
        i_error = 0;
        d_error = 0;

        window = new int[WINDOW_SIZE];
        i = WINDOW_SIZE -1;
        sum = 0;
    }

    public void updateError(double cte, double dt){
        d_error = (cte - p_error) / dt;
        p_error = cte;
        i_error = add_i(cte * dt);
    }
    public double totalError(){
        return Kp * p_error + Ki * i_error + Kd * d_error;
    }
    public double add_i(double err){
        i = (i+1) % WINDOW_SIZE;
        sum = sum - window[i] + err;
        return sum;
    }


}
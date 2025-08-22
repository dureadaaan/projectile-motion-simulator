package physics;
import java.awt.Point;
public class DefaultProjectile extends Projectile implements ISimulator {

    public DefaultProjectile(double angle, double velocity) {
        super(angle, velocity, 9.8, 0.0, 0.0);
    }

    @Override
    public double calculateRange() {
        double time = calculateTimeOfFlight();
        return velocity * Math.cos(angle) * time;
    }

    @Override
    public double calculateTimeOfFlight() {
        double vy = velocity * Math.sin(angle);
        return (2 * vy) / gravity;
    }

    @Override
    public double calculatePeakTime() {
        return (velocity * Math.sin(angle)) / gravity;
    }

    @Override
    public Point[] calculateTrajectory() {
        int steps = 20;//50;//300; //100;
        double tMax = calculateTimeOfFlight();
        Point[] path = new Point[steps];
        for (int i = 0; i < steps; i++) {
            double t = tMax * i / (steps - 1);
            double x = velocity * Math.cos(angle) * t;
            double y = velocity * Math.sin(angle) * t - 0.5 * gravity * t * t;
            path[i] = new Point((int)x, (int)y);
        }
        return path;
    }

    @Override
    public SimulationResult simulate() {
        return new SimulationResult(
            calculateRange(),
            calculateTimeOfFlight(),
            calculatePeakTime(),
            calculateTrajectory()
        );
    }
}


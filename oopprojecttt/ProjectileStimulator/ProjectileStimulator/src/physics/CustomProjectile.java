package physics;
import java.awt.Point;
public class CustomProjectile extends Projectile implements ISimulator {

    public CustomProjectile(double angle, double velocity, double gravity, double resistance, double height) {
        super(angle, velocity, gravity, resistance, height);
    }

    @Override
    public double calculateRange() {
        return velocity * Math.cos(angle) * calculateTimeOfFlight(); // Simplified (no resistance)
    }

    @Override
    public double calculateTimeOfFlight() {
        double vy = velocity * Math.sin(angle);
        double discriminant = vy * vy + 2 * gravity * height;
        return (vy + Math.sqrt(discriminant)) / gravity;
    }

    @Override
    public double calculatePeakTime() {
        return (velocity * Math.sin(angle)) / gravity;
    }

    @Override
    public Point[] calculateTrajectory() {
        int steps = 20;   //50;  //100;  //300;
        double tMax = calculateTimeOfFlight();
        Point[] path = new Point[steps];
        for (int i = 0; i < steps; i++) {
            double t = tMax * i / (steps - 1);
            double x = velocity * Math.cos(angle) * t;
            double y = height + velocity * Math.sin(angle) * t - 0.5 * gravity * t * t;
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

package physics;
import java.awt.Point;
public class SimulationResult {
	private double range;
    private double timeOfFlight;
    private double peakTime;
    private Point[] trajectoryPoints;

    public SimulationResult(double range, double timeOfFlight, double peakTime, Point[] trajectoryPoints) {
        this.range = range;
        this.timeOfFlight = timeOfFlight;
        this.peakTime = peakTime;
        this.trajectoryPoints = trajectoryPoints;
    }

    public double getRange() {
        return range;
    }

    public double getTimeOfFlight() {
        return timeOfFlight;
    }

    public double getPeakTime() {
        return peakTime;
    }

    public Point[] getTrajectoryPoints() {
        return trajectoryPoints;
    }

    @Override
    public String toString() {
        return String.format("Range: %.2f, Time of Flight: %.2f, Peak Time: %.2f", range, timeOfFlight, peakTime);
    }
}


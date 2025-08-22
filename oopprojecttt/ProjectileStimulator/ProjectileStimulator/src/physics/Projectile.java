package physics;
import java.awt.Point;
public abstract class Projectile {

	    protected double angle;
	    protected double velocity;
	    protected double gravity;
	    protected double resistance;
	    protected double height;

	    public Projectile(double angle, double velocity, double gravity, double resistance, double height) {
	        this.angle = Math.toRadians(angle);
	        this.velocity = velocity;
	        this.gravity = gravity;
	        this.resistance = resistance;
	        this.height = height;
	    }

	    public abstract double calculateRange();
	    public abstract double calculateTimeOfFlight();
	    public abstract double calculatePeakTime();
	    public abstract Point[] calculateTrajectory();
	}


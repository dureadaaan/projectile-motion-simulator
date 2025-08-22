package gui;

public class InputData {
	
	private double angle;
    private double velocity;
    private double height;
    private double gravity;
    private double resistance;
    private boolean isCustom;

    // Constructor to initialize the fields
    public InputData(double angle, double velocity, double height, double gravity, double resistance,boolean isCustom) {
        this.angle = angle;
        this.velocity = velocity;
        this.height = height;
        this.gravity = gravity;
        this.resistance = resistance;
        this.isCustom = isCustom;
    }

    // Getters for each field
    public double getAngle() {
        return angle;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getHeight() {
        return height;
    }

    public double getGravity() {
        return gravity;
    }

    public double getResistance() {
        return resistance;
    }
    
    public boolean isCustom() {
        return isCustom;
    }
    
 // Setters
    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    public void setCustom(boolean isCustom) {
        this.isCustom = isCustom;
    }
}

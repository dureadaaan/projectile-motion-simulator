package physics;
import gui.InputData;
public class PhysicsEngine {
	public static SimulationResult simulate(InputData input) {
        ISimulator simulator;
        if (input.isCustom()) {
            simulator = new CustomProjectile(
                input.getAngle(),
                input.getVelocity(),
                input.getGravity(),
                input.getResistance(),
                input.getHeight()
            );
        } else {
            simulator = new DefaultProjectile(
                input.getAngle(),
                input.getVelocity()
            );
        }
        return simulator.simulate();
    }
}

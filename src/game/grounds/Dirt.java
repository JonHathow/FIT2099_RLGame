package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	/**
	 * Dirt constructor. Dirt has the fertile capability, which means that
	 * it has the capability to grow new trees, warp pipes, and actors.
	 */
	public Dirt() {
		super('.');
		this.addCapability(Status.FERTILE);
	}
}

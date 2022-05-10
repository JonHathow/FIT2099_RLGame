package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents fiery hot lava.
 */
public class Lava extends Ground {

	int fireDamage;

	/**
	 * Lava constructor. Lava has the capability "BLAZING", which makes it
	 * deal damage to the actor if he stands on it.
	 */
	public Lava() {
		super('L');
		this.addCapability(Status.BLAZING);
		this.setFireDamage(15);
	}

	/**
	 * Getter for the damage dealt by the lava ground.
	 * @return the fire damage inflicted by the lava.
	 */
	public int getFireDamage() {
		return fireDamage;
	}

	/**
	 * Setter for the damage dealt by the lava ground.
	 * @param fireDamage the fire damage inflicted by the lava.
	 */
	public void setFireDamage(int fireDamage) {
		this.fireDamage = fireDamage;
	}
}

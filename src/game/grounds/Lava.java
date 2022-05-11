package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

import java.awt.*;

/**
 * The Lava class represents lava ground that enemies will avoid, and will burn the player if the player
 * stands on it.
 *
 * @author How Yu Chern
 * @version 1.0.0
 */
public class Lava extends Ground {

	/**
	 * Instance of display class to display lava's damage message onto the console.
	 */
	Display display = new Display();

	/**
	 * Fire damage integer, which represents the damage the lava deals each tick it burns mario.
	 */
	int fireDamage;

	/**
	 * Lava constructor.
	 */
	public Lava() {
		super('L');
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
	 * Setter for the damage dealt by the lava ground. Will check if fire damage is more than 0
	 * (as negative damage is equivalent to healing the player, and lava ground is intended to damage the player).
	 * @param fireDamage fireDamage the fire damage inflicted by the lava.
	 * @return true if fire damage is more than zero, false otherwise.
	 */
	public boolean setFireDamage(int fireDamage) {
		boolean flag = false;
		if (fireDamage > 0){
			this.fireDamage = fireDamage;
			flag = true;
		}
		return flag;
	}

	/**
	 * Tick method that makes lava deal it's fire damage to the player each tick.
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		super.tick(location);

		//Damage player every turn
		//Player is the only actor with the hostile to enemy capability
		if (location.containsAnActor() && location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
			location.getActor().hurt(getFireDamage());
			display.println("The floor is lava! Mario is on fire, and takes " + getFireDamage() + " damage.");
		}
	}

	/**
	 * Override of canActorEnter method that prevents enemies from entering Lava Ground.
	 * @param actor the Actor to check
	 * @return true if actor can enter, false otherwise.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		boolean valid = true;
		//Check if an actor is an enemy.
		if (actor.hasCapability(Status.ENEMY)) {
			valid =  false;
		}
		return valid;
	}
}

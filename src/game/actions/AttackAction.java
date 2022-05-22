package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;

/**
 * AttackAction class is the class which handles one Actor attacking other Actor.
 * @author Eugene Fan Kah Chun
 * @version 3.0
 */

public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 * @param direction the direction of attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * @return direction - the direction of attack
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @return target - the actor getting attacked
	 */
	public Actor getTarget() {
		return target;
	}

	/**
	 * Execution method of AttackAction.
	 *
	 * @param actor the Actor executing the attack
	 * @param map the current map of execution
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		//gets the weapon currently in used by the actor
		Weapon weapon = actor.getWeapon();

		//simulates probability of hitting target
		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		//get weapon's damage
		int damage = weapon.damage();

		//if the target is invincible, current damage is nil
		if (target.hasCapability(Status.INVINCIBLE)){
			damage = 0;
		}
		String result = "";

		//if the actor who is attacking is not invincible, its attack are normal
		if (!actor.hasCapability(Status.INVINCIBLE)){
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
			target.hurt(damage);
			if (!target.isConscious()) {
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor
				map.removeActor(target);
				result += System.lineSeparator() + target + " is killed.";
			}
		}

		//if the actor who is attacking is invincible, its attack becomes instant kill
		else if (actor.hasCapability(Status.INVINCIBLE)){
			// drop all items
			ActionList dropActions = new ActionList();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			map.removeActor(target);
			result += actor + " insta-killed " + target;
		}
		return result;
	}

	/**
	 * Prepares the menu description of AttackAction.
	 *
	 * @param actor the Actor executing the attack
	 */
	@Override
	public String menuDescription(Actor actor) {
		String ret = actor + " attacks " + target + " at " + direction;

		//if it is dormant, you can destroy it instead of attacking it
		if (target.hasCapability(Status.DORMANT)){
			ret = actor + " destroys " + target + "(dormant) at " + direction;
		}
		return ret;
	}
}

package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.items.Coin;

/**
 * Wall class represents a Wall. This Wall class will have all the necessary methods and attributes for a
 * Wall to function in the game.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class Wall extends Ground implements Jumpable{
	/**
	 * An instance of the jumpAction
	 */
	private JumpAction jumpAction;

	/**
	 * The success rate of an actor jumping onto the Wall
	 */
	private int successRate;

	/**
	 * The fall damage inflicted an actor failing to jump onto the Wall
	 */
	private int fallDamage;

	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
		setFallDamage(20);
		setSuccessRate(80);
	}

	@Override
	public void tick(Location location) {
		if (location.containsAnActor() && location.getActor().hasCapability(Status.INVINCIBLE)){
			location.setGround(new Dirt());
			location.addItem(new Coin(5));
		}
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		//if an actor can fly ar is invincible
		if (actor.hasCapability(Status.INVINCIBLE) || actor.hasCapability(Status.CAN_FLY)){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction) {
		ActionList actions = new ActionList();
		if (location.getActor() != actor && !actor.hasCapability(Status.INVINCIBLE)){
			JumpAction jumpAction1 = new JumpAction(location,direction,this);
			setJumpAction(jumpAction1);
			actions.add(getJumpAction());
		}
		return actions;
	}

	//setters and getters
	@Override
	public void setFallDamage(int fallDamage) {
		this.fallDamage = fallDamage;
	}

	@Override
	public void setSuccessRate(int successRate) {
		this.successRate = successRate;
	}

	@Override
	public int getSuccessRate() {
		return successRate;
	}

	@Override
	public int getFallDamage() {
		return fallDamage;
	}

	@Override
	public void setJumpAction(JumpAction jumpAction) {
		this.jumpAction = jumpAction;
	}

	@Override
	public JumpAction getJumpAction() {
		return jumpAction;
	}

	@Override
	public String toString() {
		return "Wall";
	}
}

package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.items.Coin;

public class Wall extends Ground implements Jumpable{
	private JumpAction jumpAction;
	private int successRate;
	private int fallDamage;

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
	public void setFallDamage(int fallDamage) {
		this.fallDamage = fallDamage;
	}

	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction) {
		ActionList actions = new ActionList();
		if (location.getActor() != actor && !actor.hasCapability(Status.INVINCIBLE)){
			JumpAction jumpAction1 = new JumpAction(actor,location,direction,this);
			setJumpAction(jumpAction1);
			actions.add(getJumpAction());
		}
		return actions;
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

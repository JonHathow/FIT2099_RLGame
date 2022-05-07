package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;

public class Wall extends Ground implements Jumpable{

	private int successRate;
	private int fallDamage;
	private JumpAction jumpAction;
	private boolean jumpAttempt;
	public Wall() {
		super('#');
	}

	public Action setJumpAttempt(Location location, String direction, boolean canJump){
		this.jumpAttempt = canJump;
		if (jumpAttempt){
			return new MoveActorAction(location, direction);
		}
		else{
			return new DoNothingAction();
		}
	}

	public JumpAction getJumpAction() {
		return jumpAction;
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
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
		JumpAction jumpAction1 = new JumpAction(actor,location,direction,this);
		this.jumpAction = jumpAction1;
		actions.add(jumpAction1);
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
}

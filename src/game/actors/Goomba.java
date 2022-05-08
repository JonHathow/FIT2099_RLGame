package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.*;
import game.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Goomba class is a little fungus guy. Contains all the necessary methods and attributes for Goomba
 * to work in a game.
 * @author Eugene Fan Kah Chun
 * @version 5.0
 */
public class Goomba extends Enemy {

	/**
	 * A boolean flag to denote whether the reset has been called
	 */
	private boolean resetDone = false;

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		//lets Goomba to wander around
		this.addBehaviour(10, new WanderBehaviour());
		this.addBehaviour(3, new DrinkBehaviour());
		//register the reset instance into ResetManager
		this.registerInstance();
	}

	/**
	 * Replaces the Intrinsic Weapon to kick ability of 10 damage
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "kicks");
	}

	/**
	 * Sets what happens when reset is called by ResetManager
	 */
	@Override
	public void resetInstance() {
		resetDone = true;
	}

	/**
	 * Allows itself to be attacked by Player. And adds behaviours so that it can also start following and
	 * attacking player
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();

		// it can be attacked only by the HOSTILE opponent
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this,direction));
			//can start attacking player if close
			this.addBehaviour(1, new AttackBehaviour(otherActor,direction));

			//can start following player once battle has begun
			this.addBehaviour(2, new FollowBehaviour(otherActor));
		}
		return actions;
	}

	/**
	 * Figure out what to do next for NPC's like Goomba
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		//10% chance of suicide for Goomba, and if the reset has been done, suicide too
		if(Math.random() <= 0.1 || resetDone == true){
			map.removeActor(this);
			return new DoNothingAction();
		}

		//loops through the behaviours hashmap and see what to do next
		for(Behaviour behaviour : this.getBehaviours().values()) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

}

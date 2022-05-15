package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.actors.Koopa;
import game.actors.PiranhaPlant;
import game.resets.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * The WarpPipe class represents a warp pipe from the mario game, that will allow the player to
 * warp in between maps. A warp pipe will send mario to another warp pipe in a different location.
 *
 * @author How Yu Chern
 * @version 2.0.0
 */
public class WarpPipe extends Ground implements Jumpable, Resettable {

	/**
	 * The success rate of the player jumping onto the warp pipe.
	 */
	private int successRate;

	/**
	 * The fall damage received if the player fails to jump onto the warp pipe.
	 */
	private int fallDamage;

	/**
	 * The jump action for the player to jump onto the warp pipe.
	 */
	private JumpAction jumpAction;

	/**
	 * The tick counter, to keep track of the number of ticks (how much time has passed).
	 */
	private int counter;

	/**
	 * The source location of the current warp pipe that other warp pipes can send mario to.
	 */
	private Location source;

	/**
	 * Reset flag to let the warp pipe know if a reset has occurred.
	 */
	private boolean resetFlag;

	private List<WarpLink> warpLinks = new ArrayList<>();

	/**
	 * The Move Action of the warp pipe, to send the player to another warp pipe that this warp pipe
	 * links to. The warp pipe's destination will be handled by warp.
	 */
	private MoveActorAction warp;

	private WarpPipeManager warpPipeManager = new WarpPipeManager();

	//Methods
	/**
	 * Warp Pipe constructor.
	 */
	public WarpPipe() {
		super('C');
		setFallDamage(0);
		setSuccessRate(100);
		setCounter(0);
		setResetFlag(false);
		this.registerInstance();
		warpPipeManager.registerPipe(this);
	}

	/**
	 * Allowable Actions method that implements the move actor action. Gives the player the
	 * option to warp using the warp pipe he is standing on.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return A list of actions with the move actor action present in it if the action is available to the player, or absent from it otherwise.
	 */
	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction) {
		ActionList actions = new ActionList();
		if (location.getActor() == actor){
			actions.add(warp);
		}
		else{
			actions.remove(warp);
		}
		return actions;
	}

	/**
	 * Tick method that helps a warp pipe keep track of it's source location.
	 * Also enables a piranha plant to spawn on top of the warp pipe on the second turn.
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		/* Ping this warp pipe's source location at regular intervals.
		The getter getSource() will be used to broadcast the warp pipe's location for
		other warp pipes to link to it.*/
		setSource(location);

		//Spawn Piranha Plant on the second turn
		if (counter == 2 && !(location.containsAnActor())){
			location.addActor(new PiranhaPlant());
		}

		//If a reset has been triggered
		//Note: Existing Piranha Plants will reset it's hp on it's own.
		if (getResetFlag() && !(location.containsAnActor())){
			location.addActor(new PiranhaPlant());
			//Reset Done
			setResetFlag(false);
		}
	}

	/**
	 * Reset Instance method from implementing resettable.
	 */
	@Override
	public void resetInstance() {
		setResetFlag(true);
	}


	//Setters
	/**
	 * Setter for counter.
	 * @param counter the integer of counter.
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void setWarp(MoveActorAction warp) {
		this.warp = warp;
	}

	/**
	 * Setter for the source location of this warp pipe.
	 * It is made private as other warp pipes should not be able to change this warp pipe's source location.
	 * Only this warp pipe controls it's source location.
	 *
	 * @param source the source location of the warp pipe.
	 */
	private void setSource(Location source) {
		this.source = source;
	}

	/**
	 * Setter for reset flag to indicate if a reset has occurred.
	 * @param resetFlag true if a reset has occurred, false if not or if the reset is done.
	 */
	public void setResetFlag(boolean resetFlag) {
		this.resetFlag = resetFlag;
	}

	/**
	 * Setter for the fall damage that the player receives from failing to jump onto the warp pipe.
	 * @param fallDamage the fall damage taken.
	 */
	@Override
	public void setFallDamage(int fallDamage) {
		this.fallDamage = fallDamage;
	}

	/**
	 * Setter for success rate of the player jumping onto the warp pipe.
	 * @param successRate the success rate.
	 */
	@Override
	public void setSuccessRate(int successRate) {
		this.successRate = successRate;
	}

	/**
	 * Setter for the player's jump action.
	 * @param jumpAction the jump action.
	 */
	@Override
	public void setJumpAction(JumpAction jumpAction) {
		this.jumpAction = jumpAction;
	}

	//Getters
	/**
	 * Getter for the warp pipe's source location, for other warp pipes to be able to send the player
	 * to it.
	 * @return the source location, for another warp pipe to link to it.
	 */
	public Location getSource() {
		return source;
	}

	/**
	 * Getter of the reset flag's value.
	 * @return the reset flag's value (the status of the reset).
	 */
	public boolean getResetFlag(){
		return this.resetFlag;
	}

	/**
	 * Getter for the success rate of the player jumping onto the warp pipe.
	 * @return the success rate.
	 */
	@Override
	public int getSuccessRate() {
		return successRate;
	}

	/**
	 * Getter for the fall damage for failing to jump onto the warp pipe.
	 * @return the fall damage.
	 */
	@Override
	public int getFallDamage() {
		return fallDamage;
	}

	/**
	 * Getter for the jump action for the player to jump onto the warp pipe.
	 * @return the jump action.
	 */
	@Override
	public JumpAction getJumpAction() {
		return jumpAction;
	}

}

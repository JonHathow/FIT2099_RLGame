package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.ResetAction;
import game.items.Bottle;
import game.items.ConsumeCapable;
import game.resets.Resettable;

/**
 * Class representing the Player. Contains all the necessary methods and attributes for Player
 * to work in a game.
 * @author Eugene Fan Kah Chun
 * @version 5.0
 */
public class Player extends Actor implements WalletCapable, Resettable {

	/**
	 * A Menu instance
	 */
	private final Menu menu = new Menu();

	/**
	 * A boolean flag to denote whether the reset has been called
	 */
	private boolean resetDone;

	/**
	 * A boolean flag to denote whether the reset action has been added
	 */
	private boolean resetAdded;

	private int intrinsicBooster;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);

		//add HOSTILE_TO_ENEMY capability
		this.addCapability(Status.HOSTILE_TO_ENEMY);

		//register the reset instance into ResetManager
		this.registerInstance();

		this.setResetDone(false);
		this.setResetAdded(false);
		this.setIntrinsicBooster(0);

	}
	/**
	 * @param item The Item to add.
	 */
	@Override
	public void addItemToInventory(Item item) {
		// if item can be consumed
		if (item instanceof ConsumeCapable){
			//change the portability so that Consumable Items added to inventory cannot be dropped
			item.togglePortability();
		}
		super.addItemToInventory(item);
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		if (this.hasCapability(Status.POWER_UP)){
			this.setIntrinsicBooster(this.getIntrinsicBooster()+15);
			this.removeCapability(Status.POWER_UP);
		}
		return new IntrinsicWeapon(super.getIntrinsicWeapon().damage()+this.getIntrinsicBooster(),super.getIntrinsicWeapon().verb());
	}

	/**
	 *
	 * @param points number of hitpoints to deduct.
	 */
	@Override
	public void hurt(int points) {
		//if Player is not invincible
		if (!this.hasCapability(Status.INVINCIBLE)){
			//get hurt
			super.hurt(points);
			//if Player was tall, remove capability
			if (this.hasCapability(Status.TALL)){
				this.removeCapability(Status.TALL);
			}
		}
	}

	/**
	 * Sets what happens when reset is called by ResetManager
	 */
	@Override
	public void resetInstance() {
		//Removing all capabilities
		for (Enum capability:this.capabilitiesList()){
			if (!(capability == Status.HOSTILE_TO_ENEMY)){
				this.removeCapability(capability);
			}
		}
		//heal up to max health
		this.heal(this.getMaxHp());
		this.setResetDone(true);
	}

	/**
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 *
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		ResetAction resetAction = new ResetAction();

		//If player health is 0 after taking any other external damage (Such as fire damage), player is defeated.
		if (!this.isConscious()) {
			// remove actor
			map.removeActor(this);
			display.println(System.lineSeparator() + this + " has been killed.");
			return new DoNothingAction();
		}


		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		//return/print the console menu
		String printing = "";

		//gets the health of current actor
		printing += this.name + this.printHp();

		//gets the coordinates of current actor
		printing += " at (" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ")";

		//gets the Wallet value of current actor
		printing += "\nwallet: $" + getWallet();

		if (this.hasCapability(Status.INVINCIBLE)){
			printing += "\nMARIO IS INVINCIBLE!";
		}
		//Resetting option
		if (this.isResetDone() == false) {
			actions.add(resetAction);
			this.setResetAdded(true);
		}
		else if (this.isResetDone() == true && this.isResetAdded() == true){
			actions.remove(resetAction);
		}
		display.println(printing);
		return menu.showMenu(this, actions, display);
	}

	//setters and getters
	/**
	 * Returns the resetDone
	 */
	public int getIntrinsicBooster() {
		return intrinsicBooster;
	}

	/**
	 * Sets the intrinsicBooster with a new intrinsicBooster
	 * @param intrinsicBooster
	 */
	public void setIntrinsicBooster(int intrinsicBooster) {
		this.intrinsicBooster = intrinsicBooster;
	}

	/**
	 * Returns the resetDone
	 */
	public boolean isResetDone() {
		return resetDone;
	}

	/**
	 * Sets the resetDone with a new resetDone
	 * @param resetDone
	 */
	public void setResetDone(boolean resetDone) {
		this.resetDone = resetDone;
	}

	/**
	 * Returns the resetDone
	 */
	public boolean isResetAdded() {
		return resetAdded;
	}

	/**
	 * Sets the resetAdded with a new resetAdded
	 * @param resetAdded
	 */
	public void setResetAdded(boolean resetAdded) {
		this.resetAdded = resetAdded;
	}

	/**
	 * Returns the character used to depict actor in console
	 * @return char
	 */
	@Override
	public char getDisplayChar(){
		//if Player is tall, return 'M'; else return 'm'
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

}

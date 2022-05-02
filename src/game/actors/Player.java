package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.Status;
import game.actions.ResetAction;
import game.items.Coin;
import game.items.ConsumeCapable;
import game.items.TradeCapable;
import game.resets.ResetManager;
import game.resets.Resettable;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements WalletCapable, Resettable {

	private final Menu menu = new Menu();

	private boolean resetDone = false;
	private boolean resetAdded = false;

//	private int wallet;
	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.registerInstance();
//		this.wallet = 0;
	}

//	public void updateWallet(int val){
//		this.wallet += val;
//	}

	@Override
	public void addItemToInventory(Item item) {
		if (item instanceof ConsumeCapable){
			item.togglePortability();
		}
		super.addItemToInventory(item);
	}

//	public int getWallet() {
//		return wallet;
//	}

	@Override
	public void hurt(int points) {
		if (!this.hasCapability(Status.INVINCIBLE)){
			super.hurt(points);
			if (this.hasCapability(Status.TALL)){
				this.removeCapability(Status.TALL);
			}
		}
	}

	@Override
	public void resetInstance() {
		//Removing all capabilities
		for (Enum capability:this.capabilitiesList()){
			if (!(capability == Status.HOSTILE_TO_ENEMY)){
				this.removeCapability(capability);
			}
		}
		this.heal(this.getMaxHp());
		resetDone = true;
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		ResetAction resetAction = new ResetAction();

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		// return/print the console menu
		String printing = "";
		printing += this.name + this.printHp();
		printing += " at (" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ")";
		printing += "\nwallet: $" + getWallet();
		if (this.hasCapability(Status.INVINCIBLE)){
			printing += "\nMARIO IS INVINCIBLE!";
		}

		//Resetting option
		if (resetDone == false) {
			actions.add(resetAction);
			resetAdded = true;
		}
		else if (resetDone == true && resetAdded == true){
			actions.remove(resetAction);
		}

		System.out.println(printing);

		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}



}

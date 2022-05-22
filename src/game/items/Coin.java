package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpCoinAction;
import game.resets.Resettable;

/**
 * Coin class represents a coin. This coin class will have all the necessary methods and attributes for a coin
 * to function in the game.
 * @author Eugene Fan Kah Chun
 * @version 2.0
 */
public class Coin extends Item implements Resettable {

    /**
     * Integer representing the value of the coin
     */
    private int value;

    /**
     * A boolean flag to denote whether the reset has been called
     */
    private boolean resetDone = false;

    /***
     * Constructor.
     *  @param value the value of the coin
     */
    public Coin(int value) {
        super("Coin", '$', true);
        setValue(value);

        //register the reset instance into ResetManager
        this.registerInstance();
    }

    /**
     * Sets the value of the coin with value
     * @param value value used to set the value of the coin
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Returns the value of the coin
     */
    public int getValue() {
        return this.value;
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
     * Returns the PickUpAction of the coin
     * @param actor actor who is picking up the coin
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        //returns PickUpCoinAction instead of PickUpAction
        return new PickUpCoinAction(this);
    }

    /**
     * Sets what happens when reset is called by ResetManager
     */
    @Override
    public void resetInstance() {
        this.setResetDone(true);
    }

    /**
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        //if reset is executed, remove coin from the map
        if (this.isResetDone() == true){
            currentLocation.removeItem(this);
        }
    }
}

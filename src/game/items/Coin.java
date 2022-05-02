package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpCoinAction;
import game.resets.Resettable;

public class Coin extends Item implements Resettable {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    private int value;
    private boolean resetDone = false;
    public Coin(int value) {
        super("Coin", '$', true);
        setValue(value);
        this.registerInstance();
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpCoinAction(this);
    }

    @Override
    public void resetInstance() {
        resetDone = true;
    }

    @Override
    public void tick(Location currentLocation) {
        if (resetDone == true){
            currentLocation.removeItem(this);
        }
    }
}

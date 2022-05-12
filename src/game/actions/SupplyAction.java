package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;

/**
 * SupplyAction class is the class which handles one Actor getting supplies from the Toad.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class SupplyAction extends Action {

    /**
     * The instance of the item supplied
     */
    private Item item;

    /**
     * Constructor.
     * @param item the item supplied by Toad
     */
    public SupplyAction(Item item){
        setItem(item);
    }

    /**
     * Execution method of SupplyAction.
     * @param actor The actor potentially receiving the supply.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean hasBottle = false;
        for (Item item:actor.getInventory()){
            if (item instanceof Bottle){
                hasBottle = true;
                break;
            }
        }
        if (!hasBottle){
            actor.addItemToInventory(getItem());
            return menuDescription(actor);
        }
        else{
            return actor + " already has a bottle";
        }
    }

    //setter and getter
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Prepares the menu description of SupplyAction.
     *
     * @param actor the Actor executing the SupplyAction with Toad
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " gets " + getItem() + " from Toad";
    }
}

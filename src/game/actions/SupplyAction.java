package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;

public class SupplyAction extends Action {
    /**
     * The Actor that is to be supplied with
     */
    protected Actor target;

    /**
     * The direction of supplying.
     */
    protected String direction;

    /**
     * The instance of an item
     */
    private Item item;
    public SupplyAction(Actor target, String direction, Item item){
        this.target = target;
        this.direction = direction;
        this.item = item;
    }

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
            actor.addItemToInventory(item);
            return menuDescription(actor);
        }
        else{
            return actor + " already has a bottle";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " gets " + item + " from Toad";
    }
}

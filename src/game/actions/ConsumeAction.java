package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ConsumeCapable;

/**
 * ConsumeAction class is the class which handles one Actor consuming a Consumable Item.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class ConsumeAction extends Action {
    /**
     * The instance of a ConsumeCapableItem
     */
    private ConsumeCapable consumeCapableItem;

    /**
     * Constructor.
     *
     * @param consumeCapableItem the item of consumption
     */
    public ConsumeAction(ConsumeCapable consumeCapableItem){
        this.consumeCapableItem = consumeCapableItem;
    }

    /**
     * Execution method of ConsumeAction.
     *
     * @param actor the Actor consuming the item
     * @param map the current map of execution
     */
    public String execute(Actor actor, GameMap map) {
        this.consumeCapableItem.consume(actor,map);
        return menuDescription(actor);
    }

    /**
     * Prepares the menu description of ConsumeAction.
     *
     * @param actor the Actor consuming the item
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.consumeCapableItem;
    }
}

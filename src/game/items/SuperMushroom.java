package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;
import game.Status;

/**
 * SuperMushroom class represents a SuperMushroom. This SuperMushroom class will have all the
 * necessary methods and attributes for a SuperMushroom to function in the game.
 * @author Eugene Fan Kah Chun
 * @version 5.0
 */
public class SuperMushroom extends Item implements ConsumeCapable, TradeCapable {

    /**
     * represents the trade value of SuperMushroom
     */
    private int tradeVal;

    /**
     * The consume action used at the constructor
     */
    private ConsumeAction consumeAction;

    /**
     * Constructor
     */
    public SuperMushroom(){
        super("Super Mushroom", '^',true);

        //allows other Actors to consume this item
        this.addConsumeAction();
        setTradeVal(400);
    }


    /**
     *
     * @param actor actor that consumes item
     * @param map map of where actor is at
     */
    @Override
    public void consume(Actor actor, GameMap map) {
        boolean inInventory = false;

        //loops through the actor's inventory,
        for (Item item: actor.getInventory()){
            //if the item is in actor's inventory, remove it from inventory
            if (item == this){
                actor.removeItemFromInventory(this);
                inInventory = true;
                break;
            }
        }

        //if the item is not in actor's inventory, remove item based on the location of actor
        if (!inInventory){
            map.locationOf(actor).removeItem(this);
        }

        //add Tall capability to actor
        actor.addCapability(Status.TALL);

        //increases maxHP of actors
        actor.increaseMaxHp(50);

        //handle jump stuff

    }

    /**
     *
     */
    @Override
    public void addConsumeAction() {
        //creates a consumeAction
        ConsumeAction consumeAction = new ConsumeAction(this);
        this.addAction(consumeAction);
        setConsumeAction(consumeAction);
    }

    /**
     *
     * @param consumeAction
     */
    @Override
    public void setConsumeAction(ConsumeAction consumeAction) {
        this.consumeAction = consumeAction;
    }

    /**
     *
     *
     */
    @Override
    public ConsumeAction getConsumeAction() {
        return consumeAction;
    }

    /**
     * Return the trade value of SuperMushroom
     *
     */
    public int getTradeVal() {
        return tradeVal;
    }

    /**
     * Sets the trade value of SuperMushroom
     * @param tradeVal
     */
    @Override
    public void setTradeVal(int tradeVal) {
        this.tradeVal = tradeVal;
    }
}

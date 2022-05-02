package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.Status;

/**
 * PowerStar class represents a PowerStar. This PowerStar class will have all the
 * necessary methods and attributes for a PowerStar to function in the game.
 * @author Eugene Fan Kah Chun
 * @version 5.0
 */
public class PowerStar extends Item implements TradeCapable, ConsumeCapable {
    /**
     * Represents the fading counter
     */
    private int counterFade;

    /**
     * Represents the invinciblity counter
     */
    private int counterInvincible;

    /**
     * The consume action used at the constructor
     */
    private ConsumeAction consumeAction;

    /**
     * represents the trade value of SuperMushroom
     */
    private int tradeVal;

    /**
     * Constructor
     */
    public PowerStar() {
        super("Power Star", '*', true);
        //Initialize the counters
        counterFade = 10;
        counterInvincible = -1;

        //allows other Actors to consume this item
        this.addConsumeAction();
        setTradeVal(600);
    }

    /**
     * If the item is on the ground, update counter and removeItem if meets certain requirements
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        //reduces fade counter
        counterFade -= 1;
        //if invincible counter started, then start reducing it
        if (counterInvincible>0){
            counterInvincible -=1;
        }
        //if fade counter drops to zero, remove item from ground
        if (counterFade == 0) {
            currentLocation.removeItem(this);
        }
    }

    /**
     * If the item is in Actor's inventory, update counter and removeItem if meets certain requirements
     * @param currentLocation The location of the ground on which we lie.
     * @param actor actor that has the item in the inventory
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        //reduces fade counter
        counterFade -= 1;
        //if invincible counter started, then start reducing it
        if (counterInvincible>0){
            counterInvincible -=1;
        }
        //if fade counter drops to zero, remove item from ground/inventory
        if (counterFade == 0) {
            currentLocation.removeItem(this);
            actor.removeItemFromInventory(this);
        }
        //if invincible counter drops to zero, remove actor's capability
        if (counterInvincible == 0){
            actor.removeCapability(Status.INVINCIBLE);
        }
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
            //if the item is in actor's inventory, change inInventory to true
            if (item == this){
                inInventory = true;
                break;
            }
        }
        //if it is not in inventory
        if (!inInventory){
            //remove the consumeAction from item
            this.removeAction(this.getConsumeAction());
            //add item to actor's inventory so actor can get the perks
            actor.addItemToInventory(this);
            //remove item from the ground
            map.locationOf(actor).removeItem(this);
        }
        //removing the action
        this.removeAction(this.getConsumeAction());
        actor.heal(200);
        actor.addCapability(Status.INVINCIBLE);
        counterInvincible = 10;
        counterFade = -1;
        //handle jump stuff
    }

    /**
     *
     */
    @Override
    public void addConsumeAction() {
        ConsumeAction consumeAction = new ConsumeAction(this);
        this.addAction(consumeAction);
        setConsumeAction(consumeAction);
    }

    /**
     *
     */
    @Override
    public void setConsumeAction(ConsumeAction consumeAction) {
        this.consumeAction = consumeAction;
    }

    /**
     *
     */
    @Override
    public ConsumeAction getConsumeAction() {
        return consumeAction;
    }

    /**
     *Return the trade value of PowerStar
     */
    public int getTradeVal() {
        return tradeVal;
    }

    /**
     * Change the toString method of PowerStar so that it will also state how many turns
     * left before fading
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + " - " + counterFade + " turns left";
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
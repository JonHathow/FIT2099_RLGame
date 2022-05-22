package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.WalletCapable;
import game.items.TradeCapable;

/**
 * TradeAction class is the class which handles one Actor trading items with the Toad.
 * @author Eugene Fan Kah Chun
 * @version 2.0
 */
public class TradeAction extends Action {
    /**
     * The Actor that is to be traded with
     */
    protected Actor target;

    /**
     * The direction of trading.
     */
    protected String direction;

    /**
     * The instance of a tradeCapable item
     */
    private TradeCapable item;

    /**
     * Constructor.
     *
     * @param target the Actor to trade
     * @param direction the direction of trade
     * @param item the item that is up for trading
     */
    public TradeAction(Actor target, String direction, TradeCapable item) {
        this.setTarget(target);
        this.setDirection(direction);
        this.setItem(item);
    }

    /**
     * Execution method of TradeAction.
     *
     * @param actor the Actor executing the trading
     * @param map the current map of execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // if the actor has a wallet via WalletCapable
        if (actor instanceof WalletCapable){

            //check whether the actor's funds are sufficient to trade for the item
            if (sufficientFunds((WalletCapable) actor, this.getItem().getTradeVal())){
                //update the wallet of actor, by reducing the wallet with item value
                ((WalletCapable) actor).updateWallet(-this.getItem().getTradeVal());

                //add the item to the inventory
                actor.addItemToInventory((Item) this.getItem());
                return actor + " obtained " + this.getItem();
            }
            //if insufficient funds
            else{
                return actor + " doesn't have enough coins!";
            }
        }
        else{
            return actor + "doesn't have a wallet!";
        }
    }

    /**
     * Checks whether actor has sufficient funds to trade for the item
     * @param actor actor who is trading
     * @param val value of item
     * @return a Boolean stating whether actor has sufficient funds to trade for the item
     */
    public boolean sufficientFunds(WalletCapable actor, int val){
        boolean ret = false;
        if (actor.getWallet() >= val){
            ret = true;
        }
        return ret;
    }

    //setters and getters
    /**
     * Returns the target
     */
    public Actor getTarget() {
        return target;
    }

    /**
     * Sets the target with a new target
     * @param target
     */
    public void setTarget(Actor target) {
        this.target = target;
    }

    /**
     * Returns the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the direction with a new direction
     * @param direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Returns the item
     */
    public TradeCapable getItem() {
        return item;
    }

    /**
     * Sets the item with a new item
     * @param item
     */
    public void setItem(TradeCapable item) {
        this.item = item;
    }

    /**
     * Prepares the menu description of TradeAction.
     *
     * @param actor the Actor executing the trade
     */
    @Override
    public String menuDescription(Actor actor) {
        String ret = actor + " buys " + this.getItem();
        ret += " ($" + this.getItem().getTradeVal() + ")";
        return ret;
    }
}

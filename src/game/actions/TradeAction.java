package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.items.TradeCapable;


public class TradeAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;
    private TradeCapable item;
    public TradeAction(Actor target, String direction, TradeCapable item) {
        this.target = target;
        this.direction = direction;
        this.item = item;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        if (sufficientFunds((Player)actor, item.getTradeVal())){
            ((Player) actor).updateWallet(-item.getTradeVal());
            actor.addItemToInventory((Item) item);
            return actor + " obtained " + item;
        }
        else{
            return "Mario doesn't have enough coins!";
        }

    }

    public boolean sufficientFunds(Player actor, int val){
        boolean ret = false;
        if (actor.getWallet() >= val){
            ret = true;
        }
        return ret;
    }
    @Override
    public String menuDescription(Actor actor) {
        String ret = actor + " buys " + this.item;
        ret += " ($" + item.getTradeVal() + ")";
        return ret;
    }
}

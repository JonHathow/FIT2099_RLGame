package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class TradeAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;
    public TradeAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

//    public boolean sufficientFunds(Actor actor, int val){
//        boolean ret = false;
//        if (actor){
//            ret = false;
//        }
//        return ret;
//    }
    @Override
    public String menuDescription(Actor actor) {

//        some description
//
//        superMushroom $50
//        ...
//
        // see exit.getHotKey() , maybe can get hotkey selection
        return null;
    }
}

package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TradeAction;
import game.behaviours.Behaviour;

import java.util.HashMap;
import java.util.Map;

public class Toad extends Npc{
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    public Toad() {
        super("Toad", 'O', 99999);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        actions.add(new TradeAction(this,direction));
        return actions;
    }
}

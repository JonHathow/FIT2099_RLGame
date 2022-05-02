package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.TradeAction;
import game.behaviours.Behaviour;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;

import java.util.HashMap;
import java.util.Map;

public class Toad extends Actor{

    public Toad() {
        super("Toad", 'O', 99999);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new TradeAction(this,direction, new SuperMushroom()));
        actions.add(new TradeAction(this,direction, new PowerStar()));
        actions.add(new TradeAction(this,direction, new Wrench()));
        return actions;
    }
}

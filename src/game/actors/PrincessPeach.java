package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.SavePrincessAction;


public class PrincessPeach extends Actor {

    public PrincessPeach() {
        super("Princess Peach", 'P', 99999);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.SAVE_PRINCESS) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new SavePrincessAction());
        }
        return actions;
    }
}

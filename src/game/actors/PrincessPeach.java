package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.SavePrincessAction;

/**
 * PrincessPeach class is a friendly actor. Saving the Princess will win the game. Contains all the
 * necessary methods and attributes for PrincessPeach to work in a game.
 *
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class PrincessPeach extends Actor {

    /**
     * Constructor
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 99999);
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.SAVE_PRINCESS) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new SavePrincessAction());
        }
        return actions;
    }
}

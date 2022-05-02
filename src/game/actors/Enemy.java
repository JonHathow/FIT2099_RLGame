package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.resets.Resettable;

/**
 * Enemy class is an abstract class used to seperate enemy actors with non-enemy actors.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */

public abstract class Enemy extends Actor implements Resettable {

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     */
    @Override
    public abstract Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display);

    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     */
    @Override
    public abstract ActionList allowableActions(Actor otherActor, String direction, GameMap map);
}

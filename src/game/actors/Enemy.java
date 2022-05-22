package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.behaviours.Behaviour;
import game.resets.Resettable;

import java.util.HashMap;
import java.util.Map;

/**
 * Enemy class is an abstract class used to seperate enemy actors with non-enemy actors.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */

public abstract class Enemy extends Actor implements Resettable {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * Adds the behaviour into behaviours hashmap
     * @param key used to keep track on where the behaviour is in the hash map
     * @param behaviour the behaviour to be added
     */
    public void addBehaviour(int key, Behaviour behaviour){
        this.getBehaviours().put(key,behaviour);
    }

    /**
     * Clears every behaviour from the behaviours hash map
     */
    public void clearBehaviour(){
        this.getBehaviours().clear();
    }

    /**
     * Returns the behaviours hashmap
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.ENEMY);
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

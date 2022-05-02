package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public abstract class Enemy extends Actor {
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

    public String printingHp(){
        return super.printHp();
    }
    @Override
    public abstract Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display);
    @Override
    public abstract ActionList allowableActions(Actor otherActor, String direction, GameMap map);
}

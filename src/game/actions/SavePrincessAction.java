package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * SavePrincessAction class is the class which handles one Actor saving the princess.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class SavePrincessAction extends Action {
    /**
     *
     * @param actor The actor saving the princess.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //removing the actor from the map will end the game
        map.removeActor(actor);
        return "Victory! " + actor + " has saved the princess!";
    }

    /**
     * Prepares the menu description of SavePrincessAction.
     *
     * @param actor the Actor saving the princess
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " saves the princess";
    }
}

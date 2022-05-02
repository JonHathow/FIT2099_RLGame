package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.resets.ResetManager;

/**
 * ResetAction class is the class which handles one Actor initiating the global reset of a game.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class ResetAction extends Action {

    /**
     * Execution method of ResetAction.
     *
     * @param actor the actor who executes the reset
     * @param map the current map of execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        //run the ResetManager to start globally resetting the game
        ResetManager.getInstance().run();
        return actor + " has chosen to reset the game!";
    }

    /**
     * Prepares the menu description of ResetAction.
     *
     * @param actor the actor who executes the reset
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game";
    }

    /**
     * Sets the hotkey for the menuDescription of ResetAction
     *
     */
    @Override
    public String hotkey() {
        return "r";
    }
}

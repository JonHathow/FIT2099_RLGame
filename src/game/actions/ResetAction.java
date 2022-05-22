package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Goomba;
import game.actors.Koopa;
import game.grounds.Sprout;
import game.populators.ActorRandomPopulator;
import game.populators.GroundRandomPopulator;
import game.resets.ResetManager;

/**
 * ResetAction class is the class which handles one Actor initiating the global reset of a game.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class ResetAction extends Action {

    /**
     * Instance of Ground Random Populator.
     */
    GroundRandomPopulator grp = new GroundRandomPopulator();

    /**
     * Instance of Actor Random Populator.
     */
    ActorRandomPopulator arp = new ActorRandomPopulator();

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

        //Spawn some random trees
        try {
            grp.populate(map, 5, 10, new Sprout());
        }catch (Exception e){
           e.printStackTrace();
        }

        //Spawn some Goombas
        try {
            arp.populate(map, 2, 4, new Goomba());
        }catch (Exception e){
            e.printStackTrace();
        }

        //Spawn some Koopas
        try {
            arp.populate(map, 2, 4, new Koopa());
        }catch (Exception e){
            e.printStackTrace();
        }

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

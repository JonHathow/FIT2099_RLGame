package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.RefillAction;
import game.grounds.Fountain;

/**
 * DrinkBehaviour is a class used by Npc. DrinkBehaviour is called when NPCs are on the fountain. It allows
 * Npc to drink from the fountain
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class DrinkBehaviour implements Behaviour {

    /**
     * Constructor
     */
    public DrinkBehaviour(){}

    /**
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        //loops through all the exits
        if (map.locationOf(actor).getGround() instanceof Fountain){
            //drinks from the fountain if the actor in on the fountain
            return new RefillAction((Fountain) map.locationOf(actor).getGround());
        }
        return null;
    }

}

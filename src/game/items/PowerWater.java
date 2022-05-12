package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;


/**
 * PowerWater class represents a PowerWater. This PowerWater class will have all the necessary methods and attributes for a
 * PowerWater to function in the game.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class PowerWater extends Water{

    @Override
    public void consume(Actor actor, GameMap map) {
        actor.addCapability(Status.POWER_UP);
    }

    /**
     * Change the toString method of PowerWater
     */
    @Override
    public String toString() {
        return "Power Water";
    }
}

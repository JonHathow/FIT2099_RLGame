package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * HealthWater class represents a HealthWater. This HealthWater class will have all the necessary methods and attributes for a
 * HealthWater to function in the game.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class HealthWater extends Water{
    @Override
    public void consume(Actor actor, GameMap map) {
        //heals actor by 50 points
        actor.heal(50);
    }

    /**
     * Change the toString method of HealthWater
     */
    @Override
    public String toString() {
        return "Health Water";
    }
}

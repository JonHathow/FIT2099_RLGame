package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class HealthWater extends Water{
    @Override
    public void consume(Actor actor, GameMap map) {
        actor.heal(50);
    }

    @Override
    public String toString() {
        return "Health Water";
    }
}

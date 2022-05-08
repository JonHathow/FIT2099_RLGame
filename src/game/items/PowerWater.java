package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

public class PowerWater extends Water{

    @Override
    public void consume(Actor actor, GameMap map) {
        actor.addCapability(Status.POWER_UP);
    }

    @Override
    public String toString() {
        return "Power Water";
    }
}

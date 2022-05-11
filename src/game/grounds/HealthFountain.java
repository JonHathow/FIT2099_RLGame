package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Bottle;
import game.items.HealthWater;

import static java.lang.Math.max;


public class HealthFountain extends Fountain {

    public HealthFountain() {
        super('H');
        setWater(new HealthWater());
    }

    @Override
    public void refill(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (Item item:actor.getInventory()){
                if (item instanceof Bottle){
                    ((Bottle) item).addContent(this.getWater());
                    this.reduceCapacity(1);
                    break;
                }
            }
        }
        else if (!actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            this.getWater().consume(actor,map);
            this.reduceCapacity(5);
        }
    }

    @Override
    public String toString() {
        return "Health Fountain (" + max(getCapacity(),0) + "/10)";
    }
}

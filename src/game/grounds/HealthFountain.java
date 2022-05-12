package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Bottle;
import game.items.HealthWater;

import static java.lang.Math.max;

/**
 * The HealthFountain class represents HealthFountain ground that actors can drink and refill bottle from when they
 * stand on it.
 * @author Eugene Fan Kah Chun
 * @version 3.0
 */
public class HealthFountain extends Fountain {

    /**
     * Constructor
     */
    public HealthFountain() {
        super('H');
        setWater(new HealthWater());
    }

    @Override
    public void refill(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            //if the actor has a bottle, add water to the bottle and reduce capacity of fountain
            for (Item item:actor.getInventory()){
                if (item instanceof Bottle){
                    ((Bottle) item).addContent(this.getWater());
                    this.reduceCapacity(1);
                    break;
                }
            }
        }
        else if (!actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            //if the actor is an NPC, just consume from the fountain
            this.getWater().consume(actor,map);
            this.reduceCapacity(5);
        }
    }

    /**
     * Change the toString method of HealthFountain so that it will also state what is the capacity of
     * fountain
     */
    @Override
    public String toString() {
        return "Health Fountain (" + max(getCapacity(),0) + "/10)";
    }
}

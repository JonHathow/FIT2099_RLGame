package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Bottle;
import game.items.PowerWater;

import static java.lang.Math.max;

/**
 * The PowerFountain class represents PowerFountain ground that actors can drink and refill bottle from when they
 * stand on it.
 * @author Eugene Fan Kah Chun
 * @version 3.0
 */
public class PowerFountain extends Fountain {
    /**
     * Constructor
     */
    public PowerFountain() {
        super('A');
        setWater(new PowerWater());
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

    /**
     * Change the toString method of PowerFountain so that it will also state what is the capacity of
     * fountain
     */
    @Override
    public String toString() {
        return "Power Fountain (" + max(getCapacity(),0)  + "/10)";
    }
}

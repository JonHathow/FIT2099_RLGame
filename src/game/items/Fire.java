package game.items;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Bowser;


/**
 * Fire class represents a Fire. This Fire class will have all the
 * necessary methods and attributes for a Fire to function in the game.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class Fire extends Item {
    /**
     * Represents the fading counter
     */
    private int fadeCounter;

    /**
     * Constructor
     */
    public Fire() {
        super("Fire", 'v', false);
        fadeCounter = 3;
    }

    @Override
    public void tick(Location currentLocation) {
        if (fadeCounter == 0){
            //remove the item from the map if the fade counter reaches 0
            currentLocation.removeItem(this);
        }
        else{
            if(currentLocation.containsAnActor()){
                //if there is an actor on the same ground as the fire item, hurt the actor
                if (!(currentLocation.getActor() instanceof Bowser)){
                    currentLocation.getActor().hurt(20);
                    Display display = new Display();
                    display.println("Fire hurt " + currentLocation.getActor());
                }
            }

        }
        fadeCounter--;

    }
}

package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Bowser;

public class Fire extends Item {
    private int fadeCounter;
    public Fire() {
        super("Fire", 'F', false);
        fadeCounter = 3;
    }

    @Override
    public void tick(Location currentLocation) {
        if (fadeCounter == 0){
            currentLocation.removeItem(this);
        }
        else{
            if(currentLocation.containsAnActor()){
                if (!(currentLocation.getActor() instanceof Bowser)){
                    currentLocation.getActor().hurt(20);
                    System.out.println("Fire hurt " + currentLocation.getActor());
                }
            }

        }
        fadeCounter--;

    }
}

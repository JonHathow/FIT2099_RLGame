package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;

public class Sprout extends Tree {

    /**
     * The tick counter, to keep track of the number of ticks (how much time has passed).
     */
    int counter;

    /**
     * Constructor for Sprout.
     * Also intializes a counter to keep track of it's lifetime.
     */
    public Sprout() {
        super('+');
        counter = 0;
    }

    /**
     * Tick method to enable the Sprout to change or perform
     * actions in accordance to time.
     * @param location The location of the Sprout
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        counter += 1;

        //10% chance to Spawn Goomba if there are no other actors standing on it.
        if (Math.random() <= 0.1 && !(location.containsAnActor())){
            location.addActor(new Goomba());
        }

        //Grow into a sapling after 10 turns
        if (counter == 10){
            location.setGround(new Sapling());
        }
    }
}

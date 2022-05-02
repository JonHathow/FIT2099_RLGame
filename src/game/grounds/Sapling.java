package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;
import game.items.Coin;

public class Sapling extends Tree {

    /**
     * The tick counter, to keep track of the number of ticks (how much time has passed).
     */
    int counter;

    /**
     * Constructor for Sprout.
     * Also intializes a counter to keep track of it's lifetime.
     */
    public Sapling() {
        super('t');
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

        //10% chance to Spawn Coins
        if (Math.random() <= 0.1){
            location.addItem(new Coin(20));
        }

        //Grow into a mature after 10 turns
        if (counter == 10){
            location.setGround(new Mature());
        }
    }
}

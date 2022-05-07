package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;
import game.items.Coin;

/**
 * The Sapling class represents a Mature, which is the second stage of growth for a tree.
 * This class manages the attributes and behaviours of a sappling.
 *
 * @author How Yu Chern
 * @version 1.0.0
 */
public class Sapling extends Tree implements Jumpable{

    private int successRate;
    private int fallDamage;
    /**
     * The tick counter, to keep track of the number of ticks (how much time has passed).
     */
    int counter;
    private boolean resetDone = false;

    /**
     * Constructor for Sprout.
     * Also intializes a counter to keep track of it's lifetime.
     */
    public Sapling() {
        super('t');
        counter = 0;
        setFallDamage(20);
        setSuccessRate(80);
        this.registerInstance();
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

        if (resetDone){
            if (Math.random() <= 0.5){
                location.setGround(new Dirt());
            }
        }

        //10% chance to Spawn Coins
        if (Math.random() <= 0.1){
            location.addItem(new Coin(20));
        }

        //Grow into a mature after 10 turns
        if (counter == 10){
            location.setGround(new Mature());
        }

    }

    @Override
    public void setFallDamage(int fallDamage) {
        this.fallDamage = fallDamage;
    }

    @Override
    public void setSuccessRate(int successRate) {
        this.successRate = successRate;
    }

    @Override
    public int getSuccessRate() {
        return successRate;
    }

    @Override
    public int getFallDamage() {
        return fallDamage;
    }

    @Override
    public void resetInstance() {
        resetDone = true;
    }
}

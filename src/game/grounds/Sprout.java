package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.actors.Goomba;
import game.items.Coin;

/**
 * The Sprout class represents a Sprout, which is the first stage of growth for a tree.
 * This class manages the attributes and behaviours of a sprout.
 *
 * @author How Yu Chern
 * @version 1.0.0
 */
public class Sprout extends Tree {

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
    public Sprout() {
        super('+');
        counter = 0;
        setFallDamage(10);
        setSuccessRate(90);
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

        //10% chance to Spawn Goomba if there are no other actors standing on it.
        if (Math.random() <= 0.1 && !(location.containsAnActor())){
            location.addActor(new Goomba());
        }

        if (resetDone){
            if (Math.random() <= 0.5){
                location.setGround(new Dirt());
            }
        }

        if (location.containsAnActor() && location.getActor().hasCapability(Status.INVINCIBLE)){
            location.setGround(new Dirt());
            location.addItem(new Coin(5));
        }

        //Grow into a sapling after 10 turns
        if (counter == 10){
            location.setGround(new Sapling());
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

    @Override
    public String toString() {
        return "Sprout";
    }
}

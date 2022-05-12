package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.actors.Goomba;
import game.actors.Koopa;
import game.items.Coin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * The Mature class represents a Mature, which is the third and final stage of growth for a tree.
 * This class manages the attributes and behaviours of a mature.
 *
 * @author How Yu Chern
 * @version 1.0.0
 */
public class Mature extends Tree {
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
    public Mature() {
        super('T');
        counter = 0;
        setFallDamage(30);
        setSuccessRate(70);
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

        if (location.containsAnActor() && location.getActor().hasCapability(Status.INVINCIBLE)){
            location.setGround(new Dirt());
            location.addItem(new Coin(5));
        }

        //15% chance to Spawn Koopa if there are no other actors standing on it.
        if (Math.random() <= 0.15 && !(location.containsAnActor())){
            location.addActor(new Koopa());
        }

        //Grow a new sprout in one of it's fertile surrounding every 5 turns
        if (counter % 5 == 0){
            spawnSprout(location);
        }
        double chance = 0.2;
        if (resetDone){
            chance = 0.5;
        }
        //Has a 20% chance of withering into dirt each turn.
        if (Math.random() <= chance ){
            location.setGround(new Dirt());
        }
    }

    /**
     * Method which spawns a Sprout randomly in a fertile location adjacent to a Mature.
     * @param location The location of the Mature.
     */
    public void spawnSprout(Location location){
        //Get the neighbouring grounds of the Mature
        Random rand = new Random();
        List<Exit> neighbours = location.getExits();
        List<Exit> fertileNeighbours = new ArrayList();

        //Identify all grounds which are fertile and remember them.
        for (Exit neighbour:neighbours){
            if(neighbour.getDestination().getGround().hasCapability(Status.FERTILE)){
                fertileNeighbours.add(neighbour);
            }
        }

        //Pick a random fertile neighbour ground to grow a new sprout on it.
        //If there are no fertile grounds, do nothing.
        if (fertileNeighbours.size() > 0){
            fertileNeighbours.get(rand.nextInt(fertileNeighbours.size())).getDestination().setGround(new Sprout());
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
        return "Mature";
    }
}

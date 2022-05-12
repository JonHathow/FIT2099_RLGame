package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RefillAction;
import game.items.Water;

/**
 * The Fountain class represents Fountain ground that actors can drink and refill bottle from when they
 * stand on it.
 * @author Eugene Fan Kah Chun
 * @version 3.0
 */
public abstract class Fountain extends Ground {

    /**
     * The counter to auto refill fountain once depleted
     */
    private int autoRefill = -1;

    /**
     * The capacity of the fountain
     */
    private int capacity;

    /**
     * The water that the fountains hold
     */
    private Water water;

    /**
     * An instance of the RefillAction
     */
    private RefillAction refillAction;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);
        resetCapacity();
        RefillAction refillAction1 = new RefillAction(this);
        setRefillAction(refillAction1);
    }

    /**
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {

        //if fountain is empty and the autoRefill counter has not started, start the autoRefill counter
        if (capacity <= 0 && autoRefill == -1){
            autoRefill = 5;
        }

        //if the autoRefill counter has already started, reduce the counter
        else if(autoRefill > 0){
            autoRefill--;
        }

        // if the autoRefill counter started and reached 0, refill the fountain and stop the autoRefill
        // counter
        else if(autoRefill == 0){
            resetCapacity();
            autoRefill = -1;
        }
    }

    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (location.getActor() == actor){ //if the actor is on the fountain, add action
            actions.add(getRefillAction());
        }
        else{
            actions.remove(getRefillAction());
        }
        return actions;
    }

    //setters and getters
    public void setRefillAction(RefillAction refillAction) {
        this.refillAction = refillAction;
    }

    public RefillAction getRefillAction() {
        return refillAction;
    }

    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        this.water = water;
    }

    public void resetCapacity() {
        this.capacity = 10;
    }

    public int getCapacity() {
        return capacity;
    }

    public void reduceCapacity(int val) {
        this.capacity -= val;
    }

    /**
     * This method will handle what needs to be done to the actor when they refill from this
     * fountain
     * @param actor the actor refilling from the fountain
     * @param map the map of execution
     */
    public abstract void refill(Actor actor, GameMap map);


}
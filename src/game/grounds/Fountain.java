package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RefillAction;
import game.actors.Enemy;
import game.items.Water;

public abstract class Fountain extends Ground {
    private int autoRefill = -1;
    private int capacity;
    private Water water;
    private RefillAction refillAction;
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);
        resetCapacity();
        RefillAction refillAction1 = new  RefillAction(this);
        setRefillAction(refillAction1);
    }

    public void setRefillAction(RefillAction refillAction) {
        this.refillAction = refillAction;
    }

    public RefillAction getRefillAction() {
        return refillAction;
    }

    @Override
    public void tick(Location location) {
        if (capacity <= 0 && autoRefill == -1){
            autoRefill = 5;
        }
        else if(autoRefill > 0){
            autoRefill--;
        }
        else if(autoRefill == 0){
            resetCapacity();
            autoRefill = -1;
        }
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (location.getActor() == actor){
            actions.add(getRefillAction());
        }
        else{
            actions.remove(getRefillAction());
        }
        return actions;
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
    public abstract void refill(Actor actor, GameMap map);


}
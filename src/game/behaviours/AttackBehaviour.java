package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

/**
 * AttackBehaviour is a class used by Npc. AttackBehaviour is to Npc what AttackAction is to Player. It allows
 * Npc to attack
 * @author Eugene Fan Kah Chun
 * @version 2.0
 */
public class AttackBehaviour implements Behaviour {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Constructor
     * @param target
     * @param direction
     */
    public AttackBehaviour(Actor target, String direction) {
        this.setTarget(target);
        this.setDirection(direction);
    }

    /**
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        //loops through all the exits
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            //if it contains and actor
            if (destination.containsAnActor()){
                //and if actor is equal to target
                if (destination.getActor().equals(this.getTarget())){
                    //create an attack action towards that target
                    return new AttackAction(this.getTarget(),this.getDirection());
                }

            }
        }
        return null;
    }

    /**
     * Returns the target
     */
    public Actor getTarget() {
        return target;
    }

    /**
     * Sets the target with a new target
     * @param target
     */
    public void setTarget(Actor target) {
        this.target = target;
    }

    /**
     * Returns the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the direction with a new direction
     * @param direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }
}

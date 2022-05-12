package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Jumpable;


import java.util.Random;
/**
 * JumpAction class is the class which handles one Actor jumping to a higher ground.
 * @author Eugene Fan Kah Chun
 * @version 3.0
 */
public class JumpAction extends Action {

    /**
     * Initialized of Random class
     */
    protected Random rand = new Random();

    /**
     * The direction of ground relative to the actor jumping
     */
    protected String direction;

    /**
     * The high ground that actor is trying to jump on
     */
    private Jumpable ground;

    /**
     * The location of the ground in the map
     */
    private Location location;


    /**
     * Constructor.
     * @param location the location of the ground in the map
     * @param direction the direction of ground relative to the actor jumping
     * @param ground the high ground that actor is trying to jump on
     */
    public JumpAction(Location location, String direction, Jumpable ground){
        setGround(ground);
        setLocation(location);
        setDirection(direction);
    }

    /**
     * Execution method of JumpAction.
     *
     * @param actor the Actor trying to jump
     * @param map the current map of execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.TALL) || (rand.nextInt(100) <= getGround().getSuccessRate())){
            //no fall damage
            MoveActorAction moveActorAction = new MoveActorAction(getLocation(), getDirection());
            moveActorAction.execute(actor,map);
            return menuDescription(actor);

        }
        else{
            actor.hurt(getGround().getFallDamage());
            return actor + " fell and got hurt!";
        }
    }

    //setters and getters
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Jumpable getGround() {
        return ground;
    }

    public void setGround(Jumpable ground) {
        this.ground = ground;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Prepares the menu description of JumpAction.
     *
     * @param actor the Actor trying to jump
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to the " + getDirection() + " " + getGround();
    }
}

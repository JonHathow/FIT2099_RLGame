package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.resets.Resettable;

/**
 * The Tree class represents a tree, which is the parent class for the tree variants: Sprout, Sapling and Mature.
 *
 * @author How Yu Chern
 * @version 4.0.0
 */
public abstract class Tree extends Ground implements Jumpable, Resettable {
    /**
     * The jump action for the player to jump onto the tree.
     */
    private JumpAction jumpAction;
    /**
     * Constructor.
     */
    public Tree(Character displayChar) {
        super(displayChar);
    }

    /**
     * Tick Method for tree to enable tree to experience
     * the passage of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
    }

    /**
     * Allowable Actions method of Tree.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (location.getActor() != actor && !actor.hasCapability(Status.INVINCIBLE)){
            JumpAction jumpAction1 = new JumpAction(location,direction,this);
            setJumpAction(jumpAction1);
            actions.add(getJumpAction());
        }
        return actions;
    }

    /**
     * Method to check if an Actor can enter the ground with the tree on it (a.k.a. jump onto the tree).
     * @param actor the Actor to check
     * @return A boolean value representing wether an actor can enter the tree or not (true = actor can enter, false = actor cannot enter).
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        boolean ret = false;
        if ((actor.hasCapability(Status.HOSTILE_TO_ENEMY) && actor.hasCapability(Status.INVINCIBLE)) || actor.hasCapability(Status.CAN_FLY)){
            ret = true;
        }
        return ret;
    }

    /**
     * Setter for the tree's jump action.
     * @param jumpAction the jump action.
     */
    @Override
    public void setJumpAction(JumpAction jumpAction) {
        this.jumpAction = jumpAction;
    }

    /**
     * Getter for the tree's jump action.
     * @return the jump action.
     */
    @Override
    public JumpAction getJumpAction() {
        return jumpAction;
    }
}

package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.*;
import game.Status;
import game.items.SuperMushroom;
import java.util.HashMap;
import java.util.Map;

/**
 * Koopa class is a reptilian mini-trooper of the Koopa Troop. Contains all the necessary methods and attributes for
 * Koopa to work in a game.
 * @author Eugene Fan Kah Chun
 * @version 5.0
 */
public class Koopa extends Enemy {
    /**
     * A hashmap of behaviours for Koopa
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * The otherActor involved in battle
     */
    private Actor otherActor;

    /**
     * A boolean flag to denote whether the reset has been called
     */
    private boolean resetDone = false;

    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        //lets Koopa to wander around
        this.addBehaviour(10, new WanderBehaviour());
        this.addBehaviour(3, new DrinkBehaviour());
        //adds a SuperMushroom into Koopa's inventory so when dead, will drop for others
        this.addItemToInventory(new SuperMushroom());

        //register the reset instance into ResetManager
        this.registerInstance();
    }

    public Koopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        //lets Koopa to wander around
        this.addBehaviour(10, new WanderBehaviour());
        this.addBehaviour(3, new DrinkBehaviour());
        //adds a SuperMushroom into Koopa's inventory so when dead, will drop for others
        this.addItemToInventory(new SuperMushroom());
    }
    /**
     * Replaces the Intrinsic Weapon to punch ability of 30 damage
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }


    /**
     * Sets what happens when reset is called by ResetManager
     */
    @Override
    public void resetInstance() {
        resetDone = true;
    }

    /**
     * Allows itself to be attacked by Player. And adds behaviours so that it can also start following and
     * attacking player
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        this.otherActor = otherActor;
        ActionList actions = new ActionList();

        //if Koopa is not hiding in shell and the otherActor is hostile or if otherActor is Invincible, allows Koopa to be attacked
        if(((!this.hasCapability(Status.DORMANT) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) || otherActor.hasCapability(Status.INVINCIBLE))) {
            //can start attacking player if close
            this.addBehaviour(1, new AttackBehaviour(otherActor,direction));

            //can start following player once battle has begun
            this.addBehaviour(2, new FollowBehaviour(otherActor));

            // it can be attacked by the opponent
            actions.add(new AttackAction(this,direction));
        }
        //if if Koopa is hiding in shell and the otherActor has a Wrench (based on Destroy_Shell capability), allows Koopa to be attacked
        else if (this.hasCapability(Status.DORMANT) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && otherActor.hasCapability(Status.DESTROY_SHELL)){
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * Method to know how Koopa is hurt
     * @param points number of hitpoints to deduct.
     */
    @Override
    public void hurt(int points) {
        //if Koopa is not in dormant state
        if (!this.hasCapability(Status.DORMANT)){
            super.hurt(points);
        }
    }

    /**
     * Checks whether the Koopa is conscious
     * @return a boolean on whether the Koopa is conscious
     */
    @Override
    public boolean isConscious() {
        boolean ret = true;

        //if Koopa is unconcious, set it as Dormant
        if (!super.isConscious() && !this.hasCapability(Status.DORMANT)){
            this.addCapability(Status.DORMANT);

            //Change the display Char of Koopa in the console
            this.setDisplayChar('D');

            //Clear Koopa's behaviours as Koopa is hiding in shell
            this.clearBehaviour();
        }
        //if otherActor has a Wrench, they can break shell
        else if (this.hasCapability(Status.DORMANT) && otherActor.hasCapability(Status.DESTROY_SHELL)){
            ret = super.isConscious();
        }
        return ret;
    }

    /**
     * Figure out what to do next for NPC's like Koopa
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        //if reset has been
        if(resetDone == true){
            //remove actor from the map
            map.removeActor(this);
            return new DoNothingAction();
        }
        //loops through the behaviours hashmap and see what to do next
        for(Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }


}
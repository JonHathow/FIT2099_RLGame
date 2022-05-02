package game.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.Status;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.SuperMushroom;

import java.util.HashMap;
import java.util.Map;
/**
 * A little fungus guy.
 */
public class Koopa extends Enemy {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private Actor otherActor;
    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.behaviours.put(10, new WanderBehaviour());
//        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addItemToInventory(new SuperMushroom());
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }

    /**
     * At the moment, we only make it can be attacked by Player.
     * You can do something else with this method.
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

        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if((!this.hasCapability(Status.DORMANT) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) || otherActor.hasCapability(Status.INVINCIBLE))) {
            this.behaviours.put(1, new AttackBehaviour(otherActor,direction));
            this.behaviours.put(2, new FollowBehaviour(otherActor));
            actions.add(new AttackAction(this,direction));
        }
        else if (this.hasCapability(Status.DORMANT) && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && otherActor.hasCapability(Status.DESTROY_SHELL)){
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    @Override
    public void hurt(int points) {
        //if Koopa is not in dormant state
        if (!this.hasCapability(Status.DORMANT)){
            super.hurt(points);
        }
    }

    @Override
    public boolean isConscious() {
        boolean ret = true;
        if (!super.isConscious() && !this.hasCapability(Status.DORMANT)){
            this.addCapability(Status.DORMANT);
            this.setDisplayChar('D');
            this.behaviours.clear();
        }
        else if (this.hasCapability(Status.DORMANT) && otherActor.hasCapability(Status.DESTROY_SHELL)){
            ret = super.isConscious();
        }
        return ret;
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

}
package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;


/**
 * PiranhaPlant class is a static enemy plant spawned by WarpPipe. Contains all the necessary methods and
 * attributes for PiranhaPlant to work in a game.
 *
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class PiranhaPlant extends Enemy{
    /**
     * A boolean flag to denote whether the reset has been called
     */
    private boolean resetDone = false;

    /**
     * Constructor
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
        this.registerInstance();
    }

    /**
     * Replaces the Intrinsic Weapon to chomp ability of 90 damage
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomps");
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        //loops through the behaviours hashmap and see what to do next
        for(Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
            //can start attacking player if close
            this.addBehaviour(1, new AttackBehaviour(otherActor,direction));
        }
        return actions;
    }

    /**
     * Sets what happens when reset is called by ResetManager
     */
    @Override
    public void resetInstance() {
        this.increaseMaxHp(50);
        resetDone = true;
    }

}

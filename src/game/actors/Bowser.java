package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.DrinkBehaviour;
import game.behaviours.FollowBehaviour;
import game.items.Fire;
import game.items.Key;


/**
 * Bowser class is a powerful enemy. Contains all the necessary methods and attributes for Bowser
 * to work in a game.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class Bowser extends Enemy{

    /**
     * A boolean flag to denote whether the reset has been called
     */
    private boolean resetDone = false;

    /**
     * The start location for Bowser used for reset
     */
    private Location startLocation = null;

    /**
     * Constructor.
     */
    public Bowser() {
        super("Bowser", 'B', 500);
        this.addItemToInventory(new Key());
        this.addBehaviour(3, new DrinkBehaviour());
        this.registerInstance();
    }
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (startLocation == null) {
            startLocation =  map.locationOf(this);
        }
        if (resetDone){
            MoveActorAction moveActorAction = new MoveActorAction(startLocation,null);
            moveActorAction.execute(this,map);
            resetDone = false;
        }
        //loops through the behaviours hashmap and see what to do next
        for(Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null){

                //if it is attackAction(), place fire item at the same ground as attack target
                if (action instanceof AttackAction){
                    map.at(map.locationOf(((AttackAction) action).getTarget()).x(),map.locationOf(((AttackAction) action).getTarget()).y()).addItem(new Fire());
                }
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Replaces the Intrinsic Weapon to punch ability of 80 damage
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches");
    }

    /**
     *
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
            //can start attacking player if close
            this.addBehaviour(1, new AttackBehaviour(otherActor,direction));
            //can start following player once battle has begun
            this.addBehaviour(2, new FollowBehaviour(otherActor));
        }
        return actions;
    }

    /**
     * Sets what happens when reset is called by ResetManager
     */
    @Override
    public void resetInstance() {
        this.heal(this.getMaxHp());
        this.clearBehaviour();
        resetDone = true;
    }
}

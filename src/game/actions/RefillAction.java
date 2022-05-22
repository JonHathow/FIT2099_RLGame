package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.grounds.Fountain;
import game.items.Bottle;


/**
 * RefillAction class is the class which handles one Actor refilling or drinking from a Fountain.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class RefillAction extends Action {
    /**
     * The instance of a Fountain target
     */
    private Fountain fountain;

    /**
     * Constructor.
     *
     * @param fountain the Fountain target
     */
    public RefillAction(Fountain fountain){
        this.setFountain(fountain);
    }

    /**
     * Execution method of RefillAction.
     *
     * @param actor the Actor executing RefillAction
     * @param map the current map of execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        //if the fountain is empty, don't refill
        if (this.getFountain().getCapacity() == 0){
            return this.getFountain() + " is currently empty!";
        }

        //if it is not empty, call the refill method in that particular fountain
        this.getFountain().refill(actor,map);

        //if the actor is a player,
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){

            //checks whether the player has a bottle
            boolean hasBottle = false;
            for (Item item:actor.getInventory()){
                if (item instanceof Bottle){
                    hasBottle = true;
                    break;
                }
            }
            if (!hasBottle){
                return actor + " does not have a bottle!";
            }
        }

        return menuDescription(actor);
    }

    //setters and getters
    /**
     * Returns the fountain
     */
    public Fountain getFountain() {
        return fountain;
    }

    /**
     * Sets the fountain with a new fountain
     * @param fountain
     */
    public void setFountain(Fountain fountain) {
        this.fountain = fountain;
    }

    /**
     * Prepares the menu description of RefillAction.
     *
     * @param actor the Actor refilling from fountain
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills bottle from " + this.getFountain();
    }
}

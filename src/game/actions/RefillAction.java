package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.grounds.Fountain;
import game.items.Bottle;

public class RefillAction extends Action {
    private Fountain fountain;
    public RefillAction(Fountain fountain){
        this.fountain = fountain;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        if (fountain.getCapacity() == 0){
            return fountain + " is currently empty!";
        }
        fountain.refill(actor,map);
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
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

    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills bottle from " + fountain;
    }
}

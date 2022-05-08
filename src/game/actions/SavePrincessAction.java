package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class SavePrincessAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "Victory! " + actor + " has saved the princess!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " saves the princess";
    }
}

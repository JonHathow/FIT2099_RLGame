package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.MagicalItem;

public class ConsumeAction extends Action {
    private MagicalItem magicalItem;
    public ConsumeAction(MagicalItem magicalItem){
        this.magicalItem = magicalItem;
    }

    public String execute(Actor actor, GameMap map) {
        this.magicalItem.consume(actor);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.magicalItem;
    }
}

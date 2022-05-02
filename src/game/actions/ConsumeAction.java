package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ConsumeCapable;

public class ConsumeAction extends Action {
    private ConsumeCapable consumeCapableItem;
    public ConsumeAction(ConsumeCapable consumeCapableItem){
        this.consumeCapableItem = consumeCapableItem;
    }

    public String execute(Actor actor, GameMap map) {
        this.consumeCapableItem.consume(actor);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.consumeCapableItem;
    }
}

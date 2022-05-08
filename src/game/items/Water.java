package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.ConsumeAction;

public abstract class Water implements ConsumeCapable{
    @Override
    public abstract void consume(Actor actor, GameMap map);

    @Override
    public void addConsumeAction() {

    }

    @Override
    public void setConsumeAction(ConsumeAction consumeAction) {

    }

    @Override
    public ConsumeAction getConsumeAction() {
        return null;
    }
}

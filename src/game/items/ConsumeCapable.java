package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.actions.ConsumeAction;


public interface ConsumeCapable {
    ConsumeAction consumeAction = null;
    void consume(Actor actor);

    void addConsumeAction();
    void setConsumeAction(ConsumeAction consumeAction);
    ConsumeAction getConsumeAction();
}

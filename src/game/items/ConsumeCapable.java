package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;

/**
 * ConsumeCapable interface basically helps to let system know which items are capable of being consumed.
 * Prepares necessary methods to be fully coded in classes that implement this method
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public interface ConsumeCapable {

    /**
     * Sets what to do to the actor and item when item is consumed
     * @param actor actor that consumes item
     * @param map map of where actor is at
     */
    void consume(Actor actor, GameMap map);

    /**
     * adds consume action to an attribute(to be reused later)
     */
    void addConsumeAction();

    /**
     * sets ConsumeAction attribute with the consumeAction given
     * @param consumeAction
     */
    void setConsumeAction(ConsumeAction consumeAction);

    /**
     * returns ConsumeAction attribute
     * @return
     */
    ConsumeAction getConsumeAction();
}

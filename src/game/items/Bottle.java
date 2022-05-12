package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;

import java.util.Stack;

/**
 * Bottle class represents a Bottle. This Bottle class will have all the necessary methods and attributes for a
 * Bottle to function in the game.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class Bottle extends Item implements ConsumeCapable {
    /**
     * A ConsumeAction instance
     */
    private ConsumeAction consumeAction;

    /**
     * The stack used to simulate having different types of water in the bottle
     */
    private Stack<Water> content = new Stack<>();

    /**
     * Constructor
     */
    public Bottle() {
        super("Bottle", 'b', true);
        this.addConsumeAction();
    }

    @Override
    public void consume(Actor actor, GameMap map) {
        if (content.size() != 0){
            //drinks the water in the bottle
            Water targetWater = this.removeContent();
            targetWater.consume(actor, map);
        }
    }

    //getter
    public Stack<Water> getContent() {
        return content;
    }

    /**
     *
     * @return the last element in the stack
     */
    public Water removeContent() {
        return content.pop();
    }

    /**
     *
     * @param water water that is added into the stack
     */
    public void addContent(Water water) {
        content.push(water);
    }

    /**
     * Initializer of ConsumeAction
     */
    @Override
    public void addConsumeAction() {
        //creates a consumeAction
        ConsumeAction consumeAction = new ConsumeAction(this);
        this.addAction(consumeAction);
        setConsumeAction(consumeAction);
    }

    /**
     * Setter of ConsumeAction
     */
    @Override
    public void setConsumeAction(ConsumeAction consumeAction) {
        this.consumeAction = consumeAction;
    }

    /**
     * Getter of ConsumeAction
     */
    @Override
    public ConsumeAction getConsumeAction() {
        return consumeAction;
    }


    /**
     * Change the toString method of Bottle so that it will also state the content of the Bottle
     */
    @Override
    public String toString() {
        String contentStr = "[";
        for (int i = 0; i < content.size();i++) {
            contentStr += content.get(i);
            if (i != content.size()-1) {
                contentStr += ", ";
            }
        }
        contentStr += "]";
        return super.toString() + contentStr;
    }
}


package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;

import java.util.Stack;

public class Bottle extends Item implements ConsumeCapable {
    private ConsumeAction consumeAction;
    private Stack<Water> content = new Stack<>();

    public Bottle() {
        super("Bottle", 'b', true);
        this.addConsumeAction();
    }



    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    @Override
    public void consume(Actor actor, GameMap map) {
        if (content.size() != 0){
            Water targetWater = this.removeContent();
            targetWater.consume(actor, map);
        }
    }

    public Stack<Water> getContent() {
        return content;
    }
    public Water removeContent() {
        return content.pop();
    }

    public void addContent(Water water) {
        content.push(water);
    }

    /**
     *
     */
    @Override
    public void addConsumeAction() {
        //creates a consumeAction
        ConsumeAction consumeAction = new ConsumeAction(this);
        this.addAction(consumeAction);
        setConsumeAction(consumeAction);
    }

    /**
     * @param consumeAction
     */
    @Override
    public void setConsumeAction(ConsumeAction consumeAction) {
        this.consumeAction = consumeAction;
    }

    /**
     *
     */
    @Override
    public ConsumeAction getConsumeAction() {
        return consumeAction;
    }

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


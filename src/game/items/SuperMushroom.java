package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;
import game.Status;

public class SuperMushroom extends Item implements ConsumeCapable, TradeCapable {
    private int tradeVal;
    private ConsumeAction consumeAction;
    public SuperMushroom(){
        super("Super Mushroom", '^',true);
        this.addAction(new ConsumeAction(this));
        setTradeVal(400);
    }

    @Override
    public void consume(Actor actor) {
        actor.removeItemFromInventory(this);
        actor.addCapability(Status.TALL);
        //increases maxHP of actors
        actor.increaseMaxHp(50);

        //handle jump stuff
        //handle when effect wears off
    }

    @Override
    public void addConsumeAction() {
        ConsumeAction consumeAction = new ConsumeAction(this);
        this.addAction(consumeAction);
        setConsumeAction(consumeAction);
    }

    @Override
    public void setConsumeAction(ConsumeAction consumeAction) {
        this.consumeAction = consumeAction;
    }

    @Override
    public ConsumeAction getConsumeAction() {
        return consumeAction;
    }

    public int getTradeVal() {
        return tradeVal;
    }

    @Override
    public void setTradeVal(int tradeVal) {
        this.tradeVal = tradeVal;
    }
}

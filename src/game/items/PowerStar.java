package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.Status;

public class PowerStar extends Item implements TradeCapable, ConsumeCapable {
    private int counterFade;
    private int counterInvincible;
    private ConsumeAction consumeAction;
    private int tradeVal;
    public PowerStar() {
        super("Power Star", '*', true);
        counterFade = 10;
        counterInvincible = -1;
        this.addConsumeAction();
        setTradeVal(600);
    }

    @Override
    public void tick(Location currentLocation) {
        counterFade -= 1;
        if (counterInvincible>0){
            counterInvincible -=1;
        }
        if (counterFade == 0) {
            currentLocation.removeItem(this);
        }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        counterFade -= 1;
        if (counterInvincible>0){
            counterInvincible -=1;
        }
        if (counterFade == 0) {
            currentLocation.removeItem(this);
            actor.removeItemFromInventory(this);
        }
        if (counterInvincible == 0){
            actor.removeCapability(Status.INVINCIBLE);
        }
    }

    public void resetCounter(int counter) {
        counter += 10;
    }

    @Override
    public void consume(Actor actor) {
        //maybe try removing the action instead of removing Item
        this.removeAction(this.getConsumeAction());
//        actor.removeItemFromInventory(this);
        actor.heal(200);
        actor.addCapability(Status.INVINCIBLE);
        counterInvincible = 10;
        counterFade = -1;
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
    public String toString() {
        return super.toString() + " - " + counterFade + " turns left";
    }

    @Override
    public void setTradeVal(int tradeVal) {
        this.tradeVal = tradeVal;
    }
}
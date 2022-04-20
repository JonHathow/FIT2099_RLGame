package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.Status;

public class PowerStar extends MagicalItem {
    private int counter;

    public PowerStar() {
        super("Power Star", '*', true);
        counter = 10;
        this.addAction(new ConsumeAction(this));
    }

    @Override
    public void tick(Location currentLocation) {
        counter -= 1;
        if (counter == 0) {
            currentLocation.removeItem(this);
        }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);
    }

    public void resetCounter() {
        counter = 10;
    }

    @Override
    public void consume(Actor actor) {
        //maybe try removing the action instead of removing Item
        this.removeAction(new ConsumeAction(this));
//        actor.removeItemFromInventory(this);
        actor.heal(200);
        actor.addCapability(Status.INVINCIBLE);
        resetCounter();
        //handle jump stuff
        //handle when effect wears off
    }
}
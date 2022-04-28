package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.Status;

public class SuperMushroom extends MagicalItem{

    public SuperMushroom(){
        super("Super Mushroom", '^',true);
        this.addAction(new ConsumeAction(this));
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
}

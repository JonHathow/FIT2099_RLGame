package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.actions.ConsumeAction;
import game.Status;

public class SuperMushroom extends MagicalItem{

    public SuperMushroom(){
        super("Super Mushroom", '^',true);
        this.addCapability(Status.TALL);
        this.addAction(new ConsumeAction(this));
    }

    @Override
    public void consume(Actor actor) {
        actor.removeItemFromInventory(this);
        //increases maxHP of actors
        actor.increaseMaxHp(50);

        //handle jump stuff
        //handle when effect wears off

    }

}

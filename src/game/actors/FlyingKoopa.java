package game.actors;

import game.Status;
import game.behaviours.DrinkBehaviour;

public class FlyingKoopa extends Koopa{

    public FlyingKoopa(){
        super("Flying Koopa",'F',150);
        this.addCapability(Status.CAN_FLY);
        this.addBehaviour(3, new DrinkBehaviour());
        this.registerInstance();
    }
}

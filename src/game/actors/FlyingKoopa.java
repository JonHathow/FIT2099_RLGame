package game.actors;

import game.Status;

public class FlyingKoopa extends Koopa{

    public FlyingKoopa(){
        super("Flying Koopa",'F',150);
        this.addCapability(Status.CAN_FLY);
        this.registerInstance();
    }
}

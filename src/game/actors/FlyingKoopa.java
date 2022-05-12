package game.actors;

import game.Status;

/**
 * FlyingKoopa class represents a type slightly different type to the generic Koopa. Contains all the
 * necessary methods and attributes for FlyingKoopa to work in a game.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class FlyingKoopa extends Koopa{

    /**
     * Constructor
     */
    public FlyingKoopa(){
        super("Flying Koopa",'F',150);
        this.addCapability(Status.CAN_FLY);
        this.registerInstance();
    }
}

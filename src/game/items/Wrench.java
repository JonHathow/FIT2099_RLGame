package game.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

public class Wrench extends WeaponItem {

    public Wrench() {
        super("Wrench", 'w', 50, "batters", 80);
        this.addCapability(Status.DESTROY_SHELL);
    }


}

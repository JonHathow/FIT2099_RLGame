package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;


/**
 * Key class represents a Key. This Key class will have all the
 * necessary methods and attributes for a Key to function in the game. Used to unlock PrincessPeach's handcuff
 * and save her
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class Key extends Item {

    /**
     * Constructor
     */
    public Key() {
        super("Key", 'k', true);
        this.addCapability(Status.SAVE_PRINCESS);
    }
}

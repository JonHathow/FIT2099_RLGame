package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.WalletCapable;
import game.items.Coin;

/**
 * PickUpCoinAction class is the class which handles one Actor picking up a Coin and updating the Actor's wallet.
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class PickUpCoinAction extends PickUpItemAction {

    /**
     * The instance of a Coin
     */
    private Coin coin;


    /**
     * Constructor.
     *
     * @param coin the coin to be picked up
     */
    public PickUpCoinAction(Coin coin) {
        super(coin);
        this.setCoin(coin);
    }

    /**
     * Picks up the coin and updates actor's wallet
     *
     * @param actor the actor who picks up the coin
     * @param map the current map of execution
     */
    public String pickUp(Actor actor, GameMap map) {
        //remove the coin from the map
        map.locationOf(actor).removeItem(this.getCoin());

        //checks whether the actor has a wallet via WalletCapable
        if (actor instanceof WalletCapable){

            //if so, update the wallet of the actor based on coin's value
            ((WalletCapable) actor).updateWallet(this.getCoin().getValue());
        }
        return menuDescription(actor);
    }

    /**
     * Execution method of PickUpCoinAction.
     *
     * @param actor the actor who picks up the coin
     * @param map the current map of execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return this.pickUp(actor,map);
    }

    //setters and getters
    /**
     * Returns the coin
     */
    public Coin getCoin() {
        return coin;
    }

    /**
     * Sets the coin with a new coin
     * @param coin
     */
    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    /**
     * Prepares the menu description of ConsumeAction.
     *
     * @param actor the actor who picks up the coin
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the Coin($" + this.getCoin().getValue() + ")";
    }
}

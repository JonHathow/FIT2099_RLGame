package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;

/**
 * Wrench class represents a Wrench. This Wrench class will have all the
 * necessary methods and attributes for a Wrench to function in the game.
 * @author Eugene Fan Kah Chun
 * @version 2.0
 */
public class Wrench extends Item implements Weapon,TradeCapable {
    /**
     * represents the trade value of Wrench
     */
    private int tradeVal;

    /**
     * Constructor
     */
    public Wrench() {
        super("Wrench", 'w',true);

        //If actor has wrench, they can destroy Koopa's shell
        this.addCapability(Status.DESTROY_SHELL);
        setTradeVal(200);
    }

    /**
     *
     */
    public int getTradeVal() {
        return tradeVal;
    }

    /**
     *
     * @param tradeVal
     */
    @Override
    public void setTradeVal(int tradeVal) {
        this.tradeVal = tradeVal;
    }

    /**
     *
     *
     */
    @Override
    public int damage() {
        return 50;
    }

    /**
     *
     *
     */
    @Override
    public String verb() {
        return "batters";
    }

    /**
     *
     *
     */
    @Override
    public int chanceToHit() {
        return 80;
    }
}

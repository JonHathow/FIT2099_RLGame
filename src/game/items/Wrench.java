package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;

public class Wrench extends Item implements Weapon,TradeCapable {
    private int tradeVal;
    public Wrench() {
        super("Wrench", 'w',true);
        this.addCapability(Status.DESTROY_SHELL);
        setTradeVal(200);
    }

    public int getTradeVal() {
        return tradeVal;
    }

    @Override
    public void setTradeVal(int tradeVal) {
        this.tradeVal = tradeVal;
    }

    @Override
    public int damage() {
        return 50;
    }

    @Override
    public String verb() {
        return "batters";
    }

    @Override
    public int chanceToHit() {
        return 80;
    }
}

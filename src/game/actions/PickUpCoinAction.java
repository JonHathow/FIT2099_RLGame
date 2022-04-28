package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.items.Coin;

public class PickUpCoinAction extends PickUpItemAction {

    private Coin coin;


    public PickUpCoinAction(Coin coin) {
        super(coin);
        this.coin = coin;
    }


    public String pickUp(Player actor, GameMap map) {
        map.locationOf(actor).removeItem(coin);
        actor.updateWallet(coin.getValue());
        return menuDescription(actor);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return this.pickUp((Player)actor,map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the Coin($" + coin.getValue() + ")";
    }
}

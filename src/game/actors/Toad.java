package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TradeAction;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;

/**
 * Class representing the Toad. Contains all the necessary methods and attributes for Toad
 * to work in a game. Toad is used to hold trading actions with other Actors. Hence,
 * Toad acts as a shop, and nearby Actors can buy items from this shop
 * @author Eugene Fan Kah Chun
 * @version 2.0
 */
public class Toad extends Actor{

    /**
     * Constructor
     */
    public Toad() {
        super("Toad", 'O', 99999);
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        //Lets's other actors start trading items with Toad when nearby Toad
        actions.add(new TradeAction(this,direction, new SuperMushroom()));
        actions.add(new TradeAction(this,direction, new PowerStar()));
        actions.add(new TradeAction(this,direction, new Wrench()));
        return actions;
    }
}

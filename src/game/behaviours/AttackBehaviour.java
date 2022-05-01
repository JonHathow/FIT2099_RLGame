package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

import java.util.Random;

public class AttackBehaviour implements Behaviour {

    // TODO: develop and use it to attack the player automatically.
    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    public AttackBehaviour(Actor target, String direction) {
        this.target = target;
        this.direction = direction;

    }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()){
                if (destination.getActor().equals(this.target)){
                    return new AttackAction(this.target,this.direction);
                }

            }
        }
        return null;
    }
}

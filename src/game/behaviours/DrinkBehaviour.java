package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RefillAction;
import game.grounds.Fountain;

public class DrinkBehaviour implements Behaviour {

    public DrinkBehaviour(){}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        //loops through all the exits
        if (map.locationOf(actor).getGround() instanceof Fountain){
            return new RefillAction((Fountain) map.locationOf(actor).getGround());
        }
        return null;
    }

}

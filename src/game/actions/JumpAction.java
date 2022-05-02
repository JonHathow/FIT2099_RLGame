package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Jumpable;
import game.grounds.Wall;

import java.util.Random;

public class JumpAction extends Action {
    /**
     * The Actor that is to be traded with
     */
    protected Actor target;
    protected Random rand = new Random();
    /**
     * The direction of trading.
     */
    protected String direction;
    private Jumpable ground;
    private Location location;


    public JumpAction(Actor target, Location location, String direction, Jumpable ground){
        this.ground = ground;
        this.location = location;
        this.direction = direction;
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.TALL) || (rand.nextInt(100) <= ground.getSuccessRate())){
            //no fall damage
            ((Wall) ground).setJumpAttempt(this.location,this.direction,true);
            return menuDescription(actor);
//            new MoveActorAction(location, direction);
        }
        else if (!(rand.nextInt(100) <= ground.getSuccessRate())){
            actor.hurt(ground.getFallDamage());
            ((Wall) ground).setJumpAttempt(this.location,this.direction,false);
            return actor + " fell and got hurt!";
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to the " + direction + " " + ground;
    }
}

package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.Goomba;
import game.actors.Koopa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mature extends Tree {

    /**
     * The tick counter, to keep track of the number of ticks (how much time has passed).
     */
    int counter;

    /**
     * Constructor for Sprout.
     * Also intializes a counter to keep track of it's lifetime.
     */
    public Mature() {
        super('T');
        counter = 0;
    }

    /**
     * Tick method to enable the Sprout to change or perform
     * actions in accordance to time.
     * @param location The location of the Sprout
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        counter += 1;

        //15% chance to Spawn Koopa if there are no other actors standing on it.
        if (Math.random() <= 0.15 && !(location.containsAnActor())){
            location.addActor(new Koopa());
        }

        //Grow a new sprout in one of it's fertile surrounding every 5 turns
        if (counter % 5 == 0){
            spawnSprout(location);
        }

        //Has a 20% chance of withering into dirt each turn.
        if (Math.random() <= 0.2){
            location.setGround(new Dirt());
        }
    }

    /**
     * Method which spawns a Sprout randomly in a fertile location adjacent to a Mature.
     * @param location The location of the Mature.
     */
    public void spawnSprout(Location location){
        //Get the neighbouring grounds of the Mature
        Random rand = new Random();
        List<Exit> neighbours = location.getExits();
        List<Exit> fertileNeighbours = new ArrayList();

        //Identify all grounds which are fertile and remember them.
        for (Exit neighbour:neighbours){
            if(neighbour.getDestination().getGround().hasCapability(Status.FERTILE)){
                fertileNeighbours.add(neighbour);
            }
        }

        //Pick a random fertile neighbour ground to grow a new sprout on it.
        //If there are no fertile grounds, do nothing.
        if (fertileNeighbours.size() > 0){
            fertileNeighbours.get(rand.nextInt(fertileNeighbours.size())).getDestination().setGround(new Sprout());
        }
    }
}

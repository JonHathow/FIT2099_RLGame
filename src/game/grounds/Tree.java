package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Enemy;

public abstract class Tree extends Ground {

    /**
     * Constructor.
     *
     */
    public Tree(Character displayChar) {
        super(displayChar);
    }

    /**
     * Tick Method for tree to enable tree to experience
     * the passage of time.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
    }

    /**
     * Method to check if an Actor can enter the ground with the tree on it (a.k.a. jump onto the tree).
     * @param actor the Actor to check
     * @return A boolean value representing wether an actor can enter the tree or not (true = actor can enter, false = actor cannot enter).
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !(actor instanceof Enemy);
    }
}

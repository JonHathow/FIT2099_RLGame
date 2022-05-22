package game.populate;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.lang.reflect.Constructor;

public class ActorRandomPopulator extends RandomPopulator {

    public ActorRandomPopulator(){
        super();
    }

    public void randomPopulate(GameMap gameMap, int min, int max) throws Exception {
        super.randomPopulate(gameMap, min, max);
    }

    public void populate(GameMap gameMap, int min, int max, Actor actor) throws Exception {

        randomPopulate(gameMap, min, max);

        Constructor<?> constructor = actor.getClass().getConstructor();

        for (Location location:targetLocations){
            gameMap.at(location.x(), location.y()).addActor((Actor) constructor.newInstance());
        }

        targetLocations.removeAll(targetLocations);
    }
}

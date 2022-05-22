package game.populate;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.lang.reflect.Constructor;

public class GroundRandomPopulator extends RandomPopulator {

    public GroundRandomPopulator(){
        super();
    }

    public void randomPopulate(GameMap gameMap, int min, int max) throws Exception {
        super.randomPopulate(gameMap, min, max);
    }

    public void populate(GameMap gameMap, int min, int max, Ground ground) throws Exception {

        randomPopulate(gameMap, min, max);

        Constructor<?> constructor = ground.getClass().getConstructor();

        for (Location location:targetLocations){
            gameMap.at(location.x(), location.y()).setGround((Ground) constructor.newInstance());
        }

        targetLocations.removeAll(targetLocations);
    }
}

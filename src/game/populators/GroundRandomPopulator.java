package game.populators;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.lang.reflect.Constructor;

/**
 * A variant of Random Populator used to randomly populate game maps with grounds (Trees, Lava, Warp Pipes etc.).
 *
 * @author How Yu Chern
 * @version 1.0.0
 */
public class GroundRandomPopulator extends RandomPopulator {

    /**
     * Constructor
     */
    public GroundRandomPopulator(){
        super();
    }

    /**
     * Overide of findPopulateLocations from Random Populator parent class.
     * @param gameMap The GameMap to populate.
     * @param minCount The minimum count of game objects to put on the GameMap
     * @param maxCount The maximum count of game objects to put on the GameMap
     * @throws Exception when code fails to find the correct number of suitable locations.
     */
    @Override
    public void findPopulateLocations(GameMap gameMap, int minCount, int maxCount) throws Exception {
        super.findPopulateLocations(gameMap, minCount, maxCount);
    }

    /**
     * Populate method that serves as the main auxiliary method to populate the map with a given ground. Like all
     * random populators, it will pick a random number in between a min and max to decide how many grounds to
     * place on the map.
     *
     * @param gameMap The game map to be populated.
     * @param min the minimum amount of grounds.
     * @param max the maximum amount of grounds.
     * @param ground the ground specified to be used to populate the map.
     * @throws Exception when the code fails to populate the map correctly.
     */
    public void populate(GameMap gameMap, int min, int max, Ground ground) throws Exception {

        //Find suitable target locations to populate.
        findPopulateLocations(gameMap, min, max);

        //Get the ground's constructor.
        Constructor<?> constructor = ground.getClass().getConstructor();

        //For each suitable location, create a new instance of the ground there.
        for (Location location:targetLocations){
            gameMap.at(location.x(), location.y()).setGround((Ground) constructor.newInstance());
        }

        //Once done, remove all target locations since they all have already been populated.
        removeAllTargetLocation();
    }
}

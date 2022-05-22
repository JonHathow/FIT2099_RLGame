package game.populators;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Random Populator class is the abstract class that lays the foundation for other Random Populators classes,
 * by defining the system that allows the game map to be randomly populated by game objects.
 * The purpose of a random populator is to randomly populate a game map with game objects, such as actors,
 * grounds, and even items.
 *
 * @author How Yu Chern
 * @version 2.0.0
 */
public abstract class RandomPopulator {

    /**
     * An arrayList of target locations which the random populator has identified as suitable for populating.
     * Any target location still in this arraylist is vacant and available for populating with a game object.
     */
    protected List<Location> targetLocations = new ArrayList<>();

    /**
     * Zero Parameter Constructor
     */
    public RandomPopulator(){}

    /**
     * Method to randomly find a random number of suitable locations on a specified game map that can be populated
     * with a game object. The random number of locations picked (a.k.a the random number of game objects
     * to place on the map, 1 location per game object) falls within a min and max to ensure that the map
     * is not too crowded or too vacant.
     * @param gameMap The GameMap to populate.
     * @param minCount The minimum count of game objects to put on the GameMap
     * @param maxCount The maximum count of game objects to put on the GameMap
     * @throws RuntimeException Throws exception when the code fails to find the correct random amount of suitable locations.
     */
    public void findPopulateLocations(GameMap gameMap, int minCount, int maxCount) throws Exception{
        Random random = new Random();
        int currentTreeCount = 0;

        //Assertions
        //Check if minCount is 1 or more.
        assert minCount < 0 : "The minimum count of game objects allowed on the map must be more than 0.";
        //Check if maxCount exceeds the map's area (width*height)
        assert maxCount > (gameMap.getXRange().max() * gameMap.getYRange().max()) : "The maximum count of game objects allowed cannot exceed the map's total area.";
        //Check if maxCount is less than minCount.
        assert minCount > maxCount : "The minimum count of game objects must be less than the maximum count of trees.";

        //Pick a random amount of game objects to spawn within a constraint.
        //This line of code below is done because random.nextInt(minCount, maxCount) is not working.
        int totalTreeCount = minCount + random.nextInt(maxCount + 1 - minCount);

        //Pick random locations on the map that are capable of being populated.
		/*This loop counter will cause the loop to terminate if it runs to many times whilst failing
		to plant any trees. (fail fast)*/
        int loopCounter = 0;

        while (currentTreeCount < totalTreeCount && loopCounter < totalTreeCount + 50){

            //Pick random location on map.
            int randX = random.nextInt(gameMap.getXRange().max());
            int randY = random.nextInt(gameMap.getYRange().max());

            //Check if the location is fertile ground - available to be populated with game object.
            //If it is fertile, add it to the target locations arrayList. Otherwise, find another suitable location.
            if(!(targetLocations.contains(gameMap.at(randX,randY))) && gameMap.at(randX,randY).getGround().hasCapability(Status.FERTILE) && !gameMap.at(randX,randY).containsAnActor()) {
                addTargetLocation(gameMap.at(randX,randY));
                currentTreeCount++;
            }
            loopCounter++;
        }

        //Throw exception if the loop fails to identify the correct number of suitable locations.
        if (currentTreeCount != totalTreeCount) {
            throw new RuntimeException("Incorrect number of suitable locations found on map. Expected: " + totalTreeCount + ", Trees Initialized: " + currentTreeCount + ".");
        }
    }

    /**
     * Mutator method to dd a target location that is capable of having game objects spawn on it
     * @param location The target location to be added
     */
    public void addTargetLocation(Location location){
        targetLocations.add(location);
    }

    /**
     * Clears the entire arrayList of target locations, after all of them have been populated with game objects.
     */
    public void removeAllTargetLocation(){
        targetLocations.clear();
    }
}

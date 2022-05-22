package game.populate;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.Status;
import game.resets.ResetManager;
import game.resets.Resettable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Random Populator class is used to randomly populate ANY game map with ANYTHING.
 *
 * @author How Yu Chern
 * @version 1.0.0
 */
public class RandomPopulator {
    /**
     * A singleton reset manager instance
     */
    private static RandomPopulator instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static RandomPopulator getInstance(){
        if(instance == null){
            instance = new RandomPopulator();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private RandomPopulator(){}

    /**
     * Method to randomly populate the game map with trees (Sprouts), with several constraints
     * on tree count that apply to ensure that the map is not too crowded.
     * @param gameMap The GameMap to populate with trees
     * @param treeMin The minimum count of trees to put on the GameMap
     * @param treeMax The maximum count of trees to put on the GameMap
     * @throws RuntimeException Throws exception when the code fails to initialize the correct random amount of trees.
     */
    public static void randomPopulate(GameMap gameMap, int treeMin, int treeMax, PopulateCapable entity){
        Random random = new Random();
        int randX = 0;
        int randY = 0;
        int currentTreeCount = 0;
        int totalTreeCount = 0;

        //Assertions
        //Check if treeMin is 1 or more.
        //assert treeMin < 0 : "The minimum count of trees allowed on the map must be more than 0.";
        //Check if treeMax exceeds the map's area (width*height)
        //assert treeMax > (gameMap.getXRange().max() * gameMap.getYRange().max()) : "The maximum count of trees allowed cannot exceed the map's total area.";
        //Check if treeMax is less than treeMin.
        //assert treeMin > treeMax : "The minimum count of trees must be less than the maximum count of trees.";

        //Pick a random amount of trees to spawn within a constraint.
        //This line of code below is done because random.nextInt(treeMin, treeMax) is not working.
        totalTreeCount = treeMin + random.nextInt(treeMax + 1 - treeMin);

        //Pick random locations on the map to spawn the random amount of trees (Sprouts).
		/*This loop counter will cause the loop to terminate if it runs to many times whilst failing
		to plant any trees. (fail fast)*/
        int loopCounter = 0;
        while (currentTreeCount < totalTreeCount && loopCounter < totalTreeCount + 50){
            //Pick random location on map.
            randX = random.nextInt(gameMap.getXRange().max());
            randY = random.nextInt(gameMap.getYRange().max());

            //Check if the location is fertile ground which Sprouts can grow on.
            if(gameMap.at(randX,randY).getGround().hasCapability(Status.FERTILE) && !gameMap.at(randX,randY).containsAnActor()) {
                randomPopulate(gameMap, randX, randY, entity);
                currentTreeCount++;
            }
            loopCounter++;
        }

        //Throw exception if the loop fails to initialize the correct amount of trees.
        if (currentTreeCount != totalTreeCount) {
            throw new RuntimeException("Incorrect number of trees initialized on map. Expected: " + totalTreeCount + ", Trees Initialized: " + currentTreeCount + ".");
        }
    }

    public void populateAt(GameMap gameMap, int x, int y, PopulateCapable entity){
        //gameMap.at().setGround();
        //gameMap.at().addActor();
        //gameMap.at().addItem();
    }

}

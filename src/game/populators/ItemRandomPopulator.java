package game.populators;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.lang.reflect.Constructor;

/**
 * A variant of Random Populator used to randomly populate game maps with items.
 * (Coins, Super Mushrooms, Power Stars, etc.)
 *
 * @author How Yu Chern
 * @version 1.0.0
 */
public class ItemRandomPopulator extends RandomPopulator {

    /**
     * Constructor
     */
    public ItemRandomPopulator(){
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
     * Populate method that serves as the main auxiliary method to populate the map with a given item. Like all
     * random populators, it will pick a random number in between a min and max to decide how many items to
     * randomly place on the map.
     *
     * @param gameMap The game map to be populated.
     * @param min the minimum amount of items.
     * @param max the maximum amount of items.
     * @param item the item specified to be used to populate the map.
     * @throws Exception when the code fails to populate the map correctly.
     */
    public void populate(GameMap gameMap, int min, int max, Item item) throws Exception {

        //Find suitable target locations to populate.
        findPopulateLocations(gameMap, min, max);

        //Get the actor's constructor.
        Constructor<?> constructor = item.getClass().getConstructor();

        //For each suitable location, create a new instance of the actor there
        for (Location location:targetLocations){
            gameMap.at(location.x(), location.y()).addItem((Item) constructor.newInstance());
        }

        //Once done, remove all target locations since they all have already been populated.
        removeAllTargetLocation();
    }
}

package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.demo.mars.DemoCapabilities;
import edu.monash.fit2099.demo.mars.items.MartianItem;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.NumberRange;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Goomba;
import game.actors.Koopa;
import game.actors.Player;
import game.actors.Toad;
import game.grounds.*;
import game.items.Coin;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature());

			List<String> map = Arrays.asList(
				"..........................................##....................................",
				"............................................#...................................",
				"............................................#...................................",
				".............................................##.................................",
				"...............................................#................................",
				"................................................#...............................",
				"..................................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				"........................................+#____####..............................",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				".................................................##.............................",
				"...................................................#............................",
				"....................................................#...........................",
				".....................................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			//Populate GameMap with trees.
			//Minimum 5 trees
			//Maximum 10 trees
			treePopulate(gameMap, 5, 10);

			Actor mario = new Player("Mario", 'm', 600);
			mario.addItemToInventory(new Wrench());
			world.addPlayer(mario, gameMap.at(33, 11));
			gameMap.at(43, 10).addActor(new Toad());
			gameMap.at(44, 11).addItem(new Coin(1000));
//			gameMap.at(36, 11).addItem(new SuperMushroom());
//			gameMap.at(36, 10).addItem(new PowerStar());
			// FIXME: the Goomba should be generated from the Tree
//			mario.addItemToInventory(new PowerStar());
//			mario.addItemToInventory(new SuperMushroom());
			gameMap.at(33, 12).addActor(new Goomba());
			gameMap.at(33, 13).addActor(new Koopa());
			world.run();

	}

	/**
	 * Method to randomly populate the game map with trees (Sprouts), with several constraints
	 * on tree count that apply to ensure that the map is not too crowded.
	 * @param gameMap The GameMap to populate with trees
	 * @param treeMin The minimum count of trees to put on the GameMap
	 * @param treeMax The maximum count of trees to put on the GameMap
	 */
	public static void treePopulate(GameMap gameMap, int treeMin, int treeMax){
		Random random = new Random();
		int randX = 0;
		int randY = 0;
		int currentTreeCount = 0;
		int totalTreeCount = 0;

		//Assertions
		//Check if treeMin and treeMax are suitable - Otherwise throw an assertion
		assert treeMin < 0 : "The minimum count of trees allowed on the map must be more than 0.";
		//The maximum count of trees cannot exceed the map's area (width*height)
		assert treeMax > (gameMap.getXRange().max() * gameMap.getYRange().max()) : "The maximum count of trees allowed cannot exceed the map's total area.";
		assert treeMin > treeMax : "The minimum count of trees must be less than the maximum count of trees.";

		//Pick a random amount of trees to spawn within a constraint.
		//This is done because random.nextInt(treeMin, treeMax) is not working.
		totalTreeCount = treeMin + random.nextInt(treeMax + 1 - treeMin);

		//Pick random locations on the map to spawn Sprouts
		while (currentTreeCount < totalTreeCount){
			//Pick random location on map.
			randX = random.nextInt(gameMap.getXRange().max());
			randY = random.nextInt(gameMap.getYRange().max());

			//Check if the location is fertile ground which Sprouts can grow on.
			if(gameMap.at(randX,randY).getGround().hasCapability(Status.FERTILE)) {
				gameMap.add('+', new NumberRange(randX, 1), new NumberRange(randY, 1));
				currentTreeCount++;
			}
		}
	}

}

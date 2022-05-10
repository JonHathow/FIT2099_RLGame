package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.NumberRange;
import edu.monash.fit2099.engine.positions.World;
import game.actors.*;
import game.grounds.*;
import game.items.Bottle;


/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			//Default Map
			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature(), new Lava(), new WarpPipe());

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
				"............................................C......#............................",
				"....................................................#...........................",
				".....................................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			//Lava Zone
			List<String> lavaMap = Arrays.asList(
				"C____________.._...........###########################################",
				"______________......LLL....#.........................................#",
				"_______####__._.....LLL....#...LLLL...........................LLLL...#",
				"########..__._.............#...LLLL...........................LLLL...#",
				"............_......#########...LLLL...........................LLLL...#",
				".....................................................................#",
				".....................................................................#",
				".....................................................................#",
				"...................#########.........................................#",
				"...........................#.......L........................L........#",
				"....................LLL....#........LLLLLLLLLLLLLLLLLLLLLLLL.........#",
				"....................LLL....#.........................................#",
				"...........................###########################################");

			GameMap lavaZone = new GameMap(groundFactory, lavaMap);
			world.addGameMap(lavaZone);

			/*
			Populate GameMap with trees.
			Minimum 5 trees
			Maximum 10 trees
			*/
			try {
				treePopulate(gameMap, 5, 10);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}

			//Adding Player to the world.
			Actor mario = new Player("Mario", 'm', 600);
//			mario.addItemToInventory(new Wrench());
//			mario.addItemToInventory(new PowerStar());
			world.addPlayer(mario, gameMap.at(23, 12));
			mario.addItemToInventory(new Bottle());
//			gameMap.at(22, 12).addActor(new PiranhaPlant());
//			gameMap.at(23, 11).addItem(new Fire());
			gameMap.at(22, 12).setGround(new HealthFountain());
//			gameMap.at(21, 12).addActor(new PrincessPeach());
//			gameMap.at(22, 12).addActor(new Bowser());
//			gameMap.at(22, 12).setGround(new HealthFountain());
//			gameMap.at(43, 10).addActor(new Toad());
//			gameMap.at(44, 11).addItem(new Coin(1000));
//			gameMap.at(36, 11).addItem(new SuperMushroom());
//			gameMap.at(36, 10).addItem(new PowerStar());

//			mario.addItemToInventory(new PowerStar());
//			mario.addItemToInventory(new SuperMushroom());
//			gameMap.at(33, 12).addActor(new Goomba());
//			gameMap.at(33, 13).addActor(new Koopa());
			world.run();
	}

	/**
	 * Method to randomly populate the game map with trees (Sprouts), with several constraints
	 * on tree count that apply to ensure that the map is not too crowded.
	 * @param gameMap The GameMap to populate with trees
	 * @param treeMin The minimum count of trees to put on the GameMap
	 * @param treeMax The maximum count of trees to put on the GameMap
	 * @throws RuntimeException Throws exception when the code fails to initialize the correct random amount of trees.
	 */
	public static void treePopulate(GameMap gameMap, int treeMin, int treeMax) throws RuntimeException{
		Random random = new Random();
		int randX = 0;
		int randY = 0;
		int currentTreeCount = 0;
		int totalTreeCount = 0;

		//Assertions
		//Check if treeMin is 1 or more.
		assert treeMin < 0 : "The minimum count of trees allowed on the map must be more than 0.";
		//Check if treeMax exceeds the map's area (width*height)
		assert treeMax > (gameMap.getXRange().max() * gameMap.getYRange().max()) : "The maximum count of trees allowed cannot exceed the map's total area.";
		//Check if treeMax is less than treeMin.
		assert treeMin > treeMax : "The minimum count of trees must be less than the maximum count of trees.";

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
			if(gameMap.at(randX,randY).getGround().hasCapability(Status.FERTILE)) {
				gameMap.add('+', new NumberRange(randX, 1), new NumberRange(randY, 1));
				currentTreeCount++;
			}
			loopCounter++;
		}

		//Throw exception if the loop fails to initialize the correct amount of trees.
		if (currentTreeCount != totalTreeCount) {
			throw new RuntimeException("Incorrect number of trees initialized on map. Expected: " + totalTreeCount + ", Trees Initialized: " + currentTreeCount + ".");
		}
	}
}

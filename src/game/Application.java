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
import game.items.Wrench;
import game.populate.GroundRandomPopulator;


/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) throws Exception {

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
				".........................................C.......##.............................",
				"............................................C...##..............................",
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

			GroundRandomPopulator groundRandomPopulator = new GroundRandomPopulator();
			groundRandomPopulator.populate(gameMap, 5, 10, new Sprout());
			groundRandomPopulator.populate(gameMap, 5, 10, new WarpPipe());


			//Lava Zone
			List<String> lavaMap = Arrays.asList(
				"C____________.._...........###########################################",
				"______________......LLL....#.........................................#",
				"_______####__._.....LLL....#...LLLL...........................LLLL...#",
				"########..__._.............#...LLLL...........................LLLL...#",
				"............_......#########...LLLL...........................LLLL...#",
				"................................................_....................#",
				".............................................._____..................#",
				"..............................................._._...................#",
				"...................#########.........................................#",
				"...........................#.......L........................L........#",
				"....................LLL....#........LLLLLLLLLLLLLLLLLLLLLLLL.........#",
				"....................LLL....#.........................................#",
				"...........................###########################################");

			GameMap lavaZone = new GameMap(groundFactory, lavaMap);
			world.addGameMap(lavaZone);

			//Let Warp Pipe Manager know about the Lava Zone map, for identifying the Lava Zone Warp Pipe.
			WarpPipeManager.getInstance().setLavaMap(lavaZone);

			//Initializing Peach and Bowser
			lavaZone.at(45, 6).addActor(new Bowser());
			lavaZone.at(48, 6).addActor(new PrincessPeach());

			//Adding Player to the world.
			Actor mario = new Player("Mario", 'm', 600);

			//Test Items
//			mario.addItemToInventory(new Wrench());
//			mario.addItemToInventory(new Bottle());
//			mario.addItemToInventory(new PowerStar());
//			mario.addItemToInventory(new SuperMushroom());

			//Adding Mario to the world
			world.addPlayer(mario, gameMap.at(43, 10));
//			world.addPlayer(mario, lavaZone.at(40, 6));

			//Adding Friendly Actors
			gameMap.at(45, 12).addActor(new Toad());

			//Adding Hostile Actors
//			gameMap.at(22, 12).addActor(new PiranhaPlant());
//			gameMap.at(23, 11).addItem(new Fire());
// 			gameMap.at(33, 12).addActor(new Goomba());

			//Adding Fountain
			gameMap.at(45, 14).setGround(new HealthFountain());
			gameMap.at(47, 14).setGround(new PowerFountain());

			//Test Items
//			gameMap.at(44, 11).addItem(new Coin(1000));
//			gameMap.at(36, 11).addItem(new SuperMushroom());
//			gameMap.at(36, 10).addItem(new PowerStar());

			world.run();
	}
}

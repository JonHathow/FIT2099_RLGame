package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.demo.mars.DemoCapabilities;
import edu.monash.fit2099.demo.mars.items.MartianItem;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
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
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

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


}

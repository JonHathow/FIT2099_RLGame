package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.Random;

public class AttackBehaviour extends Action implements Behaviour{

    // TODO: develop and use it to attack the player automatically.
    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    public AttackBehaviour(Actor target, String direction) {
        this.target = target;
        this.direction = direction;

    }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        boolean canAttack= true;
        Weapon weapon = actor.getWeapon();

        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            canAttack = false;
        }
        if (canAttack) {
            int damage = weapon.damage();
            System.out.println(actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.");
            target.hurt(damage);
            return this;
        }
        else{
            return null;
        }

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}

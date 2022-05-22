package game.grounds;

import game.actions.JumpAction;

/**
 * Jumpable interface basically helps to let system know which grounds are high grounds.
 * Prepares necessary methods to be fully coded in classes that implement this method
 * @author Eugene Fan Kah Chun
 * @version 2.0
 */
public interface Jumpable {

    //setters and getters

    /**
     * Sets the fallDamage with a new fallDamage
     * @param fallDamage
     */
    void setFallDamage(int fallDamage);

    /**
     * Sets the successRate with a new successRate
     * @param successRate
     */
    void setSuccessRate(int successRate);

    /**
     * Returns the successRate
     */
    int getSuccessRate();

    /**
     * Returns the fallDamage
     */
    int getFallDamage();

    /**
     * sets jumpAction attribute with the jumpAction given
     * @param jumpAction
     */
    void setJumpAction(JumpAction jumpAction);

    /**
     * returns JumpAction attribute
     * @return
     */
    JumpAction getJumpAction();
}

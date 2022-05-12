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
    void setFallDamage(int fallDamage);
    void setSuccessRate(int successRate);
    int getSuccessRate();
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

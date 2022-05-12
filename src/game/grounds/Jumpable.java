package game.grounds;

import game.actions.JumpAction;

public interface Jumpable {
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

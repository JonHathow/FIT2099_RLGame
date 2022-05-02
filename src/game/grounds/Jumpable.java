package game.grounds;

public interface Jumpable {

    void setFallDamage(int fallDamage);
    void setSuccessRate(int successRate);
    int getSuccessRate();
    int getFallDamage();
}

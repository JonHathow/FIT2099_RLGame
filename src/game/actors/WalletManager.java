package game.actors;

/**
 * WalletManager class handles all operations of an actor's wallet
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public class WalletManager {
    /**
     * An integer representing the value of wallet
     */
    private int wallet = 0;

    /**
     * A method used to update the wallet of an Actor, calls setWallet()
     * @param val value used to update actor's wallet
     */
    public void updateWallet(int val){
        setWallet(getWallet()+val);
    }

    /**
     * A method used to set a new value of wallet for an Actor
     * @param wallet the new value for wallet
     */
    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    /**
     * returns an integer which represents the wallet value of an actor
     */
    public int getWallet() {
        return wallet;
    }
}

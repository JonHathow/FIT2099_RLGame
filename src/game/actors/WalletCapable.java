package game.actors;

/**
 * WalletCapable interface basically helps to let system know which actors are capable of having wallets.
 * Prepares necessary methods to be fully coded in classes that implement this method
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public interface WalletCapable {
    /**
     * An instance of WalletManager
     */
    WalletManager walletManager = new WalletManager();

    /**
     * A default method used to update the wallet of an Actor
     * @param val value used to update actor's wallet
     */
    default void updateWallet(int val){
        //calls wallet manager class then update wallet in that class with val
        walletManager.updateWallet(val);
    }

    /**
     * A default method that returns an integer which represents the wallet value of an actor
     */
    default int getWallet(){
        return walletManager.getWallet();
    }
}

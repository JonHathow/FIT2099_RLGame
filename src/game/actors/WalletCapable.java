package game.actors;

public interface WalletCapable {

    WalletManager walletManager = new WalletManager();

    default void updateWallet(int val){
        walletManager.updateWallet(val);
    }
    default int getWallet(){
        return walletManager.getWallet();
    }


}

package game.actors;


public class WalletManager {
    private int wallet = 0;

    public void updateWallet(int val){
        setWallet(getWallet()+val);
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getWallet() {
        return wallet;
    }
}

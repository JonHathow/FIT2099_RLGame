package game.items;

/**
 * TradeCapable interface basically helps to let system know which items are capable of being traded.
 * Prepares necessary methods to be fully coded in classes that implement this method
 * @author Eugene Fan Kah Chun
 * @version 1.0
 */
public interface TradeCapable {

    /**
     * Sets the trade value of item
     * @param tradeVal
     */
    void setTradeVal(int tradeVal);

    /**
     * Return the trade value of item
     *
     */
    int getTradeVal();
}

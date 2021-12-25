package ePortfolio;


/**
 * the mutual class extends the investment class
 */
public class MutualFund extends Investment {

    public MutualFund(String symbol, String name, int quantity, double price) {
        super(symbol, name, quantity, price);
        setBookValue(computeBookValue());
    }

    public MutualFund(MutualFund mutualFund){
        super(mutualFund.getSymbol(), mutualFund.getName(), mutualFund.getQuantity(), mutualFund.getPrice());
    }

    public MutualFund(String symbol, String name, int quantity, double price, double bookValue) {
        super(symbol, name, quantity, price, bookValue);
    }


    @Override
    public double computeBookValue() {
        return getQuantity() * getPrice();
    }

    @Override
    public double computePayment(double price, int quantity) {
        return (price * quantity) - 45.0;
    }

    @Override
    public double computeGain(int quantity, double price) {
        return computePayment(price, quantity) - (getBookValue() * ((double)quantity/getQuantity()));
    }

    /**
     * Formats the mutual fund investment type for file
     */
    @Override
    public String toStringForFile(){
        return String.format("type = \"mutualfund\"\n" +
                "symbol = \"%s\"\n" +
                "name = \"%s\"\n" +
                "quantity = \"%d\"\n" +
                "price = \"%.2f\"\n" +
                "bookValue = \"%.2f\"\n",
                getSymbol(), getName(), getQuantity(), getPrice(), getBookValue());
    }

    @Override
    public String toString() {
        return "MutualFund[" +
                "symbol='" + getSymbol() + '\'' +
                ", name='" + getName() + '\'' +
                ", quantity=" + getQuantity() +
                ", price=" + getPrice() +
                ", bookValue=" + getBookValue() +
                ']';
    }
}

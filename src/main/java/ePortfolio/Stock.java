package ePortfolio;

/**
 * the stock class extends the investment class
 */
public class Stock extends Investment {

    public Stock(String symbol, String name, int quantity, double price) {
        super(symbol, name, quantity, price);
        setBookValue(computeBookValue());
    }

    public Stock(Stock stock){
        super(stock.getSymbol(), stock.getName(), stock.getQuantity(), stock.getPrice());
    }

    public Stock(String symbol, String name, int quantity, double price, double bookValue) {
        super(symbol, name, quantity, price, bookValue);
    }

    @Override
    public double computeBookValue() {
        return getQuantity() * getPrice() + 9.99;
    }

    @Override
    public double computePayment(double price, int quantity) {
        return (price * quantity) - 9.99;
    }

    @Override
    public double computeGain(int quantity, double price) {
        return computePayment(price, quantity) - (getBookValue() * ((double)quantity/getQuantity()));
    }

    /**
     * Formats the stock investment type for file
     */
    @Override
    public String toStringForFile(){
        return String.format("type = \"stock\"\n" +
                        "symbol = \"%s\"\n" +
                        "name = \"%s\"\n" +
                        "quantity = \"%d\"\n" +
                        "price = \"%.2f\"\n" +
                        "bookValue = \"%.2f\"\n",
                getSymbol(), getName(), getQuantity(), getPrice(), getBookValue());
    }

    @Override
    public String toString() {
        return "Stock[" +
                "symbol='" + getSymbol() + '\'' +
                ", name='" + getName() + '\'' +
                ", quantity=" + getQuantity() +
                ", price=" + getPrice() +
                ", bookValue=" + getBookValue() +
                ']';
    }
}

package ePortfolio;

/**
 * investment class defines what an investment is
 */
public abstract class Investment {

    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
    private double gain;

    public Investment(String symbol, String name, int quantity, double price) {
        this(symbol,name,quantity,price,0);
    }

    public Investment(String symbol, String name, int quantity, double price, double bookValue) {
        if(quantity < 0 || price < 0){
            throw new IllegalArgumentException("Quantity and price cannot be negative");
        }
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.bookValue = bookValue;
    }

    /**
     * calculates the bookValue
     * @return the bookValue
     */
    public abstract double computeBookValue();

    /**
     * calculates payment
     * @param price inputted price
     * @param quantity inputted quantity
     * @return payment
     */
    public abstract double computePayment(double price, int quantity);

    /**
     * calculates gain
     * @param quantity inputted quantity
     * @param price inputted price
     * @return gain
     */
    public abstract double computeGain(int quantity, double price);

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0){
            throw new IllegalArgumentException("quantity must be positive");
        }
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price < 0){
            throw new IllegalArgumentException("price must be positive");
        }
        this.price = price;
    }

    public double getBookValue() {
        return bookValue;
    }

    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }

    public abstract String toStringForFile();

    public String gainString(){
        return String.format("%s: %.2f", getSymbol(), getGain());
    }
}

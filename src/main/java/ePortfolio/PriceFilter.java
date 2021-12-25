package ePortfolio;

/**
 * the price filter class contains the calculation for finding the price filter
 */
public class PriceFilter {

    private enum Operation{
        EQUAL,RANGE,MIN,MAX
    }

    private final Double p1;
    private Double p2;
    private final Operation operation;

    /**
     * parses priceFilter and sets the operation and operands (p1 and p2)
     * @param priceFilter the string containing the price filter
     */
    public PriceFilter(String priceFilter) {
        priceFilter = priceFilter.trim();
        if(priceFilter.charAt(0) == '-') {
            operation = Operation.MAX;
            p1 = Double.parseDouble(priceFilter.substring(1));
        }
        else if(priceFilter.charAt(priceFilter.length() - 1) == '-') {
            operation = Operation.MIN;
            p1 = Double.parseDouble(priceFilter.substring(0,priceFilter.length() - 1));
        }
        else if(priceFilter.contains("-")) {
            operation = Operation.RANGE;
            p1 = Double.parseDouble(priceFilter.substring(0,priceFilter.indexOf("-")));
            p2 = Double.parseDouble(priceFilter.substring(priceFilter.indexOf("-") + 1));
        }
        else {
            operation = Operation.EQUAL;
            p1 = Double.parseDouble(priceFilter);
        }
    }

    /**
     * returns whether price matches this priceFilter
     * @param price any price
     * @return true if price matches else false
     */
    public boolean matches(double price) {
        if(operation == Operation.MAX) {
            return price <= p1;
        }
        else if(operation == Operation.MIN) {
            return price >= p1;
        }
        else if(operation == Operation.RANGE) {
            return p1 <= price && price <= p2;
        }
        else {
            return p1 == price;
        }
    }
}

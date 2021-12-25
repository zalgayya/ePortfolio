package ePortfolio;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Allows the Gui interfaces to connect to portfolio
 */
public class Controller {

    private final Portfolio portfolio;

    public Controller(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    /**
     * Connects guiBuy in portfolio to the Buy class
     * @param investmentType type of investment
     * @param name name of investment
     * @param symbol symbol of investment
     * @param price price of investment
     * @param quantity quantity of investment
     * @return an investment
     */
    public Investment buy(String investmentType, String name, String symbol, String price, String quantity){
        double priceDouble = Double.parseDouble(price);
        int quantityInt = Integer.parseInt(quantity);
        Investment investment = portfolio.guiBuy(investmentType, name, symbol, quantityInt, priceDouble);
        return investment;
    }

    /**
     * Connects the guiSell in portfolio to the Sell class
     * @param symbol symbol of investment
     * @param price price of investment
     * @param quantity quantity of investment
     * @return an investment
     */
    public Investment sell(String symbol, String price, String quantity){
        double priceDouble = Double.parseDouble(price);
        int quantityInt = Integer.parseInt(quantity);
        Investment investment = portfolio.guiSell(symbol, quantityInt, priceDouble);
        return investment;
    }

    /**
     * Connects guiQuit in portfolio to the main gui class
     */
    public void quit(){
        portfolio.guiQuit();
    }

    /**
     * Connects guiSearch in portfolio to the Search class
     * @param symbol symbol of investment
     * @param keyWords keywords inside the investments name
     * @param lowPrice lowest price of all investments
     * @param highPrice highest price of all investments
     * @return a list of investments
     */
    public List<Investment> search(String symbol, String keyWords, String lowPrice, String highPrice){
        List<Investment> investments = portfolio.guiSearch(symbol, keyWords, lowPrice.isBlank()? null : Double.parseDouble(lowPrice), highPrice.isBlank()? null : Double.parseDouble(highPrice));
        return investments;
    }

    /**
     * updates the investments price
     * @param investment an investment
     * @param price new price
     * @return update investment
     */
    public Investment update(Investment investment, String price){
        double priceDouble = Double.parseDouble(price);
        investment = portfolio.guiUpdate(investment, priceDouble);
        return investment;
    }

    /**
     * updates the index to move forward
     * @param index the index it's currently at
     * @return investment at that index
     */
    public Investment next(int index){
        Investment investment = portfolio.nextInvestment(index);
        return investment;
    }

    /**
     * updates the index to move backward
     * @param index the index it's currently at
     * @return investment at that index
     */
    public Investment prev(int index){
        Investment investment = portfolio.prevInvestment(index);
        return investment;
    }

    /**
     * gets the size of the investment list
     * @return the size
     */
    public int sizeInvestments(){
        int size = portfolio.sizeOfInvestments();
        return size;
    }

    /**
     * Connects totalGainGui to the gain interface
     * @return the total gain value
     */
    public double gain(){
        return portfolio.totalGainGui();
    }

    /**
     * gets individual gains for each investment
     * @return the formatted gain string
     */
    public String getIndividualGains(){
        return  portfolio.getInvestments().stream()
                .map(Investment::gainString)
                .collect(Collectors.joining("\n"));
    }
}

/*
Author: Zaid Al-gayyali
Course: Cis 2430, Object oriented programming
Professor: Fei Song
date:2021-12-01
 */

package ePortfolio;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * the portfolio class is where all functions are performed
 */
public class Portfolio {

    private final ArrayList<Investment> investments;

    private HashMap<String, List<Integer>> wordMap;

    private final String fileName;

    private double gain;

    public Portfolio(String fileName) {
        this.fileName = fileName;
        wordMap = new HashMap<>();
        investments = new ArrayList<>();
        gain = 0.0;
    }

    public ArrayList<Investment> getInvestments() {
        return investments;
    }

    /**
     * Prompts user for type of investment then symbol, quantity, and price.
     * Creates new investment and adds to appropriate list
     */
    public void buy() {
        String investmentType = getInvestmentType();
        String symbol = getSymbol();
        String[] token = symbol.split("\\s+");

        if(token.length > 1){
            System.out.println("Invalid symbol");
            return;
        }
        String name;

        int quantity = getQuantity();
        if(quantity < 0 ){
            System.out.println("Invalid quantity");
            return;
        }

        double price = getPrice();
        if(price < 0 ){
            System.out.println("Invalid price");
            return;
        }

        if(investmentType.equals("stock")) {
            Optional<Stock> foundStock = investments.stream()
                    .filter(investment -> investment instanceof Stock)
                    .filter((stock) -> stock.getSymbol().equals(symbol))
                    .map(stock -> (Stock) stock)
                    .findFirst();

            if(foundStock.isEmpty()) {
                name = getName();
                double bookValue = price * quantity + 9.99;
                investments.add(new Stock(symbol, name, quantity, price, bookValue));
                addNameToMap(investments.get(investments.size() - 1).getName(), investments.size() - 1);
            }
            else {
                Stock stock = foundStock.get();
                stock.setPrice(price);
                stock.setQuantity(foundStock.get().getQuantity() + quantity);
            }
        }
        else if(investmentType.equals("mutualfund")) {
            Optional<MutualFund> foundMutualFund = investments.stream()
                    .filter(investment -> investment instanceof MutualFund)
                    .filter((mutualFund) -> mutualFund.getSymbol().equals(symbol))
                    .map(mutualFund -> (MutualFund) mutualFund)
                    .findFirst();

            if(foundMutualFund.isEmpty()) {
                name = getName();
                double bookValue = price * quantity;
                investments.add(new MutualFund(symbol, name, quantity, price, bookValue));
                addNameToMap(investments.get(investments.size() - 1).getName(), investments.size() - 1);
            }
            else {
                MutualFund mutualFund = foundMutualFund.get();
                mutualFund.setPrice(price);
                mutualFund.setQuantity(foundMutualFund.get().getQuantity() + quantity);
            }
        }
        else {
            System.out.println("Wrong input");
        }
    }

    /**
     * Does the functionality for the buy gui interface
     * @param investmentType the inputted investment type
     * @param name  the inputted name
     * @param symbol the inputted symbol
     * @param quantity the inputted quantity
     * @param price the inputted price
     * @return an Investment
     */
    public Investment guiBuy(String investmentType, String name, String symbol, int quantity, double price){
        Investment toReturn = null;
        if(symbol.split("\\s+").length > 1){
            throw new IllegalArgumentException("Symbol must be one word");
        }
        if(investmentType.equals("Stock")) {
            Optional<Stock> foundStock = investments.stream()
                    .filter(investment -> investment instanceof Stock)
                    .filter((stock) -> stock.getSymbol().equals(symbol))
                    .map(stock -> (Stock) stock)
                    .findFirst();

            if(foundStock.isEmpty()) {
                if(name == null || name.isBlank()){
                    throw new IllegalArgumentException("Name is required for new investments");
                }
                toReturn = new Stock(symbol, name, quantity, price);
                investments.add(toReturn);
                addNameToMap(investments.get(investments.size() - 1).getName(), investments.size() - 1);
            }
            else {
                toReturn = foundStock.get();
                Stock stock = foundStock.get();
                double bookValue = stock.getBookValue() + (price * quantity + 9.99);
                stock.setBookValue(bookValue);
                stock.setPrice(price);
                stock.setQuantity(foundStock.get().getQuantity() + quantity);
            }
        }
        else if(investmentType.equals("MutualFund")) {
            Optional<MutualFund> foundMutualFund = investments.stream()
                    .filter(investment -> investment instanceof MutualFund)
                    .filter((mutualFund) -> mutualFund.getSymbol().equals(symbol))
                    .map(mutualFund -> (MutualFund) mutualFund)
                    .findFirst();

            if(foundMutualFund.isEmpty()) {
                if(name == null || name.isBlank()){
                    throw new IllegalArgumentException("Name is required for new investments");
                }
                toReturn = new MutualFund(symbol, name, quantity, price);
                investments.add(toReturn);
                addNameToMap(investments.get(investments.size() - 1).getName(), investments.size() - 1);
            }
            else {
                toReturn = foundMutualFund.get();
                MutualFund mutualFund = foundMutualFund.get();
                mutualFund.setPrice(price);
                mutualFund.setQuantity(foundMutualFund.get().getQuantity() + quantity);
            }
        }
        return toReturn;
    }

    /**
     * Does the functionality for the sell gui interface
     * @param symbol the inputted symbol
     * @param quantity the inputted quantity
     * @param price the inputted price
     * @return an Investment
     */
    public Investment guiSell(String symbol, int quantity, double price){
        if(symbol.split("\\s+").length > 1){
            throw new IllegalArgumentException("Symbol must be one word");
        }

        if(quantity < 0){
            throw new IllegalArgumentException("quantity must be positive");
        }

        Optional<Investment> foundInvestment = investments.stream()
                .filter((stock) -> stock.getSymbol().equalsIgnoreCase(symbol))
                .findFirst();

        if(foundInvestment.isEmpty()) {
            throw new IllegalStateException("You do not have this investment");
        }
        Investment investment = foundInvestment.get();


        if(quantity > investment.getQuantity()) {
            throw new IllegalStateException("You do not have enough of this investment. You only have " + investment.getQuantity());
        }
        if(investment.getQuantity() - quantity == 0){
            investment.setGain(investment.computePayment(price, quantity) - investment.getBookValue());
            gain += investment.computePayment(price, quantity) - investment.getBookValue();
            investments.remove(investment);
            removeNameFromMap(investments.indexOf(investment));
            investment.setBookValue(0);
            investment.setQuantity(0);
            investment.setPrice(price);
            return investment;
        }

        double sellBookValue = investment.getBookValue() * ((double)quantity/ (double)investment.getQuantity());
        double newBookValue = investment.getBookValue() - sellBookValue;
        investment.setBookValue(newBookValue);
        investment.setQuantity(investment.getQuantity() - quantity);
        investment.setPrice(price);

        investment.setGain(investment.computeGain(quantity, price));
        gain += investment.computeGain(quantity, price);

        return investment;
    }



    /**
     * Prompts user for symbol, quantity, and price.
     * removes quantity sold and updates price.
     */
    public void sell() {
        String symbol = getSymbol();
        String[] token = symbol.split("\\s+");

        if(token.length > 1){
            System.out.println("Invalid symbol");
            return;
        }
        Optional<Investment> foundInvestment = investments.stream()
                .filter((stock) -> stock.getSymbol().equals(symbol))
                .findFirst();

        if(foundInvestment.isEmpty()) {
            System.out.println("You do not have that investment.");
            return;
        }
        Investment investment = foundInvestment.get();

        int quantity = getQuantity();
        if(quantity < 0 ){
            System.out.println("Invalid quantity");
            return;
        }

        double price = getPrice();
        if(price < 0 ){
            System.out.println("Invalid price");
            return;
        }

        if(quantity > investment.getQuantity()) {
            System.out.println("You do not have enough investments to sell.");
            return;
        }

        double sellBookValue = investment.getBookValue() * ((double)quantity/ (double)investment.getQuantity());
        double newBookValue = investment.getBookValue() - sellBookValue;
        investment.setBookValue(newBookValue);
        investment.setQuantity(investment.getQuantity() - quantity);
        investment.setPrice(price);

        double payment;
        if(investment instanceof Stock) {
            payment = price * quantity - 9.99;
        }
        else {
            payment = price * quantity - 45;
        }
        gain += payment - sellBookValue;
        if(investment.getQuantity() == 0){
            investments.remove(investment);
            removeNameFromMap(investments.indexOf(investment));
        }
    }

    /**
     * For each investment, prompts user to update price.
     */
    public void update() {
        for(Investment investment : investments) {
            System.out.println(investment.getSymbol() + " | " + investment.getName());
            double price = getPrice();
            investment.setPrice(price);
            double newBookValue = price * investment.getQuantity();
            investment.setBookValue(newBookValue);
        }
    }

    /**
     * Does the functionality for the update gui interface
     * @param price the inputted price
     * @return an Investment
     */
    public Investment guiUpdate(Investment investment,double price) {
        if(price < 0) {
            throw new IllegalArgumentException("Invalid price");
        }
        investment.setGain(investment.computeGain(investment.getQuantity(), price));
        gain += investment.computePayment(price, investment.getQuantity()) - investment.getBookValue();
        investment.setPrice(price);
        double newBookValue = price * investment.getQuantity();
        investment.setBookValue(newBookValue);
        return investment;
    }

    /**
     * returns the next investment in the list
     * @param index index of the investment
     * @return investment at that index
     */
    public Investment nextInvestment(int index){
        return investments.get(index);
    }

    /**
     * gets size of the list
     * @return size of list
     */
    public int sizeOfInvestments(){
        return investments.size();
    }

    /**
     * returns the previous investment in the list
     * @param index index of the investment
     * @return investment at that index
     */
    public Investment prevInvestment(int index){
        return investments.get(index);
    }

    /**
     * calculates the total gain for the gain interface
     * @return total gain value
     */
    public double totalGainGui(){
        return gain;
    }

    /**
     * calculates amount gained or lost.
     */
    public void getGain() {
        System.out.printf("Total gain = %.2f\n", gain);
    }

    /**
     * Prompts user for 3 search types, symbolFilter, nameFilter(using hashmap) and priceFilter and
     * prints all investments that match those filters.
     */
    public void searchWithMap(){
        String symbol = getSymbolFilter().toLowerCase(Locale.ROOT);
        String name = getNameFilter().toLowerCase(Locale.ROOT);
        String price = getPriceFilter();

        List<Investment> filteredInvestments = new ArrayList<>(investments);

        if(!symbol.isEmpty()) {
            filteredInvestments = filteredInvestments.stream()
                    .filter(investment -> investment.getSymbol().equals(symbol))
                    .collect(Collectors.toList());
        }

        if (!name.isEmpty()) {
            String[] tokens = name.split("\\s+");
            for (String token : tokens){
                List<Integer> foundIndices = wordMap.getOrDefault(token, Collections.emptyList());
                filteredInvestments = filteredInvestments.stream()
                        .filter(investment -> foundIndices.contains(investments.indexOf(investment)))
                        .collect(Collectors.toList());
            }
        }

        if(!price.isEmpty()){
            PriceFilter priceFilter = new PriceFilter(price);
            filteredInvestments = filteredInvestments.stream()
                    .filter(investment -> priceFilter.matches(investment.getPrice()))
                    .collect(Collectors.toList());
        }

        System.out.println("Matching investments:");
        filteredInvestments.forEach(System.out::println);
    }

    /**
     * Does the functionality for the gui search interface
     * @param symbol inputted symbol
     * @param keyWords inputted keywords
     * @param lowPrice inputted low price
     * @param highPrice inputted high price
     * @return list of all investments found
     */
    public List<Investment> guiSearch(String symbol, String keyWords, Double lowPrice, Double highPrice){
        List<Investment> filteredInvestments = new ArrayList<>(investments);

        if(!symbol.isEmpty()) {
            filteredInvestments = filteredInvestments.stream()
                    .filter(investment -> investment.getSymbol().equalsIgnoreCase(symbol))
                    .collect(Collectors.toList());
        }

        if (!keyWords.isEmpty()) {
            String[] tokens = keyWords.toLowerCase(Locale.ROOT).split("\\s+");
            for (String token : tokens){
                List<Integer> foundIndices = wordMap.getOrDefault(token, Collections.emptyList());
                filteredInvestments = filteredInvestments.stream()
                        .filter(investment -> foundIndices.contains(investments.indexOf(investment)))
                        .collect(Collectors.toList());
            }
        }

        if(lowPrice != null){
            filteredInvestments = filteredInvestments.stream()
                    .filter(investment -> investment.getPrice() >= lowPrice)
                    .collect(Collectors.toList());
        }

        if(highPrice != null){
            filteredInvestments = filteredInvestments.stream()
                    .filter(investment -> investment.getPrice() <= highPrice)
                    .collect(Collectors.toList());
        }

        return filteredInvestments;
    }

    /**
     * Loading information from file
     * @param fileName string containing file name
     */
    public void loadFromFile(String fileName){
        File file = new File(fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Could not create new file");
            }
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String fileInput;
            while((fileInput = reader.readLine()) != null){
                String type = getLineValue(fileInput);

                fileInput = reader.readLine();
                String symbol = getLineValue(fileInput);

                fileInput = reader.readLine();
                String name = getLineValue(fileInput);

                fileInput = reader.readLine();
                String quantity = getLineValue(fileInput);

                fileInput = reader.readLine();
                String price = getLineValue(fileInput);

                fileInput = reader.readLine();
                String bookValue = getLineValue(fileInput);

                int qty = Integer.parseInt(quantity);
                double p = Double.parseDouble(price);
                double bV = Double.parseDouble(bookValue);

                if(type.equals("stock")){
                    investments.add(new Stock(symbol, name, qty, p, bV));
                }
                else{
                    investments.add(new MutualFund(symbol, name, qty, p, bV));
                }
                addNameToMap(investments.get(investments.size() - 1).getName(), investments.size() - 1);
            }
        } catch (IOException e) {
            System.out.println("Could not read from file");
        }
    }

    /**
     *  Saving information from file
     * @param filename string containing file name
     */
    public void saveToFile(String filename){
        File file = new File(filename);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Could not create the file");
                return;
            }
        }
        try (FileWriter writer = new FileWriter(filename)){
            for (Investment investment: investments) {
                writer.write(investment.toStringForFile());
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
    }

    /**
     * Getting the value from the line at each line
     * @param line string containing the entire line from a file
     */
    private String getLineValue(String line){
        return line.substring(line.indexOf('"') + 1, line.lastIndexOf('"'));
    }

    /**
     * Adds name to hashmap
     * @param name string containing the name
     * @param index integer containing the index
     */
    private void addNameToMap(String name, int index){
        String[] tokens = name.toLowerCase(Locale.ROOT).split("\\s+");
        for (String token : tokens){
            if(wordMap.containsKey(token)){
                wordMap.get(token).add(index);
            }
            else{
                wordMap.put(token, new ArrayList<>(List.of(index)));
            }
        }
    }

    /**
     * Removes object at that index from the hashMap
     * @param index Integer containing the index
     */
    private void removeNameFromMap(Integer index){
        for (List<Integer> value : wordMap.values()){
            value.remove(index);
            for (int i = 0; i < value.size(); i++) {
                if (value.get(i) > index) {
                    value.set(i, value.get(i) - 1);
                }
            }
        }
    }

    /**
     * Prompts user to input investment type
     */
    private String getInvestmentType(){
        System.out.print("Which type of investment would you like? (stock or mutualfund)\n> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase(Locale.ROOT);
    }

    /**
     * Prompts user to input symbol
     */
    private String getSymbol(){
        System.out.print("Symbol of investment\n> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase(Locale.ROOT);
    }

    /**
     * Prompts user to input name
     */
    private String getName(){
        System.out.print("What is the name of the investment?\n> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase(Locale.ROOT);
    }

    /**
     * Prompts user to input quantity
     */
    private int getQuantity(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input quantity\n> ");
        return scanner.nextInt();
    }

    /**
     * Prompts user to input price
     */
    private double getPrice(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input price of investment\n> ");
        return scanner.nextDouble();
    }

    /**
     * Prompts user to input symbolFilter
     */
    private String getSymbolFilter(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Filter by symbol\n> ");
        return scanner.nextLine().toLowerCase(Locale.ROOT);
    }

    /**
     * Prompts user to input nameFilter
     */
    private String getNameFilter(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Filter by name\n> ");
        return scanner.nextLine().toLowerCase(Locale.ROOT);
    }

    /**
     * Prompts user to input priceFilter
     */
    private String getPriceFilter(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Filter by price (min-max for range, min- for minimum, -max for maximum)\n> ");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String fileName = null;
        if(args.length >= 1){
            fileName = args[0];
        }
        Portfolio portfolio = new Portfolio(fileName);
        Controller controller = new Controller(portfolio);
        Gui gui = new Gui(controller);
        gui.setVisible(true);
        Menu menu = new Menu();
        if(args.length >= 1){
            portfolio.loadFromFile(args[0]);
        }
        while(true){
            menu.show();
            switch (menu.getSelection().toLowerCase(Locale.ROOT)){
                case "buy": portfolio.buy(); break;
                case "sell": portfolio.sell(); break;
                case "update": portfolio.update(); break;
                case "gain": portfolio.getGain(); break;
                case "search": portfolio.searchWithMap(); break;
                case "q":;
                case "quit": quit(args.length > 0? args[0]:null, portfolio);
                default: System.out.println("Invalid input");
            }
        }
    }

    /**
     * does the functionality for when exitting the gui interface
     */
    public void guiQuit(){
        if (fileName != null) {
            File file = new File(fileName);
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("Could not create file");
                }
            }
            saveToFile(fileName);
        }
        System.exit(0);
    }


    /**
     * Exits the program and creates the file if it does not exist
     */
    public static void quit(String filename, Portfolio portfolio){
        if (filename != null) {
            File file = new File(filename);
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("Could not create file");
                }
            }
            portfolio.saveToFile(filename);
        }
        System.out.println("Goodbye");
        System.exit(0);
    }
}





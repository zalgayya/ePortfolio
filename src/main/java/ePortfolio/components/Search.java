package ePortfolio.components;

import ePortfolio.Controller;
import ePortfolio.Investment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Search class to create the search interface
 */
public class Search extends JPanel implements ActionListener {

    private JTextField textSymbol;
    private JTextField textName;
    private JTextField textLowPrice;
    private JTextField textHighPrice;
    JTextArea messageBox;
    private Controller controller;

    public Search(Controller controller){
        this.controller = controller;
        setLayout(new BorderLayout());
        createSearchInvestmentLabel();
        createSearchButton();
        createResetButton();
        createSymbolLabel();
        createNameKeywordsLabel();
        createLowPriceLabel();
        createSymbolButton();
        createLowPriceButton();
        createHighPriceButton();
        createNameKeywordsButton();
        createHighPriceLabel();
        messageBox();
        messagesLabel();
        fixIssue();
    }

    /**
     * Create search title label
     */
    private void createSearchInvestmentLabel(){
        JLabel label = new JLabel("Searching Investments");
        add(label,BorderLayout.PAGE_START);
    }

    /**
     * Create symbol title label
     */
    private void createSymbolLabel(){
        JLabel label = new JLabel("Symbol");
        label.setBounds(10,0,100,100);
        add(label);
    }

    /**
     * Create name keywords title label
     */
    private void createNameKeywordsLabel(){
        JLabel label = new JLabel("<html>" + "Name" + "<br/>" + "Keywords"+"<html/>");
        label.setBounds(10,30,100,100);
        add(label);
    }

    /**
     * Create low price title label
     */
    private void createLowPriceLabel(){
        JLabel label = new JLabel("Low Price");
        label.setBounds(10,60,100,100);
        add(label);
    }

    /**
     * Create high price title label
     */
    private void createHighPriceLabel(){
        JLabel label = new JLabel("High Price");
        label.setBounds(10,80,100,100);
        add(label);
    }

    /**
     * Create search button
     */
    private void createSearchButton(){
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchButton.setBounds(350,50,80,20);
        add(searchButton);
    }

    /**
     * Create reset button
     */
    private void createResetButton(){
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        resetButton.setBounds(350,100,80,20);
        add(resetButton);
    }

    /**
     * Create symbol text field
     */
    private void createSymbolButton(){
        textSymbol = new JTextField();
        String symbol = textSymbol.getText();
        textSymbol.setBounds(70,40,100,20);
        add(textSymbol);
    }

    /**
     * Create name keywords text field
     */
    private void createNameKeywordsButton(){
        textName = new JTextField();
        String name = textName.getText();
        textName.setBounds(70,70,120,20);
        add(textName);
    }

    /**
     * Create low price text field
     */
    private void createLowPriceButton(){
        textLowPrice = new JTextField();
        String quantity = textLowPrice.getText();
        textLowPrice.setBounds(70,100,100,20);
        add(textLowPrice);
    }

    /**
     * Create high price text field
     */
    private void createHighPriceButton(){
        textHighPrice = new JTextField();
        String price = textHighPrice.getText();
        textHighPrice.setBounds(70,120,100,20);
        add(textHighPrice);
    }

    /**
     * Create message label
     */
    private void messagesLabel(){
        JLabel label = new JLabel("Search results");
        label.setBounds(0,185,100,100);
        add(label);
    }

    /**
     * Create text area box
     */
    private void messageBox(){
        messageBox = new JTextArea();
        messageBox.setLineWrap(true);
        messageBox.setEditable(false);
        JScrollPane pane = new JScrollPane(messageBox, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(0,240,489,199);
        add(pane);
    }

    private void fixIssue(){
        JLabel label = new JLabel("");
        label.setBounds(10,80,100,100);
        add(label);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if(button.equals("Reset")){
            textSymbol.setText("");
            textName.setText("");
            textLowPrice.setText("");
            textHighPrice.setText("");
        }
        if(button.equals("Search")){
            try{
                List<Investment> foundInvestments = null;
                foundInvestments = controller.search(textSymbol.getText(), textName.getText(), textLowPrice.getText(), textHighPrice.getText());
                String investments = foundInvestments.stream()
                        .map(Investment::toStringForFile)
                        .collect(Collectors.joining("\n"));
                messageBox.setText(investments);
            }catch (Exception ex){
                messageBox.setText(ex.getMessage());
            }
        }
    }
}

package ePortfolio.components;

import ePortfolio.Controller;
import ePortfolio.Investment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Sell class to create the Sell interface
 */
public class Sell extends JPanel implements ActionListener {

    private JTextField textSymbol;
    private JTextField textQuantity;
    private JTextField textPrice;
    private JTextArea messageBox;
    private final Controller controller;
    private Gain gainGui;

    public Sell(Controller controller, Gain gainGui) {
        this.controller = controller;
        this.gainGui = gainGui;
        setLayout(new BorderLayout());
        createSellInvestmentLabel();
        createSellButton();
        createResetButton();
        createSymbolLabel();
        createQuantityLabel();
        createSymbolButton();
        createQuantityButton();
        createPriceButton();
        createPriceLabel();
        messageBox();
        messagesLabel();
        fixIssue();
    }

    /**
     * Create sell title label
     */
    private void createSellInvestmentLabel() {
        JLabel label = new JLabel("Selling an investment");
        add(label, BorderLayout.PAGE_START);
    }

    /**
     * Create symbol title label
     */
    private void createSymbolLabel() {
        JLabel label = new JLabel("Symbol");
        label.setBounds(10, 0, 100, 100);
        add(label);
    }

    /**
     * Create create quantity label
     */
    private void createQuantityLabel() {
        JLabel label = new JLabel("Quantity");
        label.setBounds(10, 40, 100, 100);
        add(label);
    }

    /**
     * Create price label
     */
    private void createPriceLabel() {
        JLabel label = new JLabel("Price");
        label.setBounds(10, 80, 100, 100);
        add(label);
    }

    /**
     * Create sell button
     */
    private void createSellButton() {
        JButton buyButton = new JButton("Sell");
        buyButton.addActionListener(this);
        buyButton.setBounds(350, 50, 70, 20);
        add(buyButton);
    }

    /**
     * Create reset button
     */
    private void createResetButton() {
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        resetButton.setBounds(350, 100, 70, 20);
        add(resetButton);
    }

    /**
     * Create symbol text field
     */
    private void createSymbolButton() {
        textSymbol = new JTextField();
        textSymbol.setBounds(60, 40, 100, 20);
        add(textSymbol);
    }

    /**
     * Create quantity text field
     */
    private void createQuantityButton() {
        textQuantity = new JTextField();
        textQuantity.setBounds(60, 80, 100, 20);
        add(textQuantity);
    }

    /**
     * Create price text field
     */
    private void createPriceButton() {
        textPrice = new JTextField();
        textPrice.setBounds(60, 120, 100, 20);
        add(textPrice);
    }

    /**
     * Create message label
     */
    private void messagesLabel(){
        JLabel label = new JLabel("Messages");
        label.setBounds(0,185,100,100);
        add(label);
    }

    /**
     * Create text area box
     */
    private void messageBox() {
        messageBox = new JTextArea();
        messageBox.setLineWrap(true);
        messageBox.setEditable(false);
        JScrollPane pane = new JScrollPane(messageBox, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(0, 240, 489, 199);
        add(pane);
    }

    private void fixIssue() {
        JLabel label = new JLabel("");
        label.setBounds(10, 80, 100, 100);
        add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        if(button.equals("Reset")){
            textSymbol.setText("");
            textQuantity.setText("");
            textPrice.setText("");
        }
        if(button.equals("Sell")){
            try{
                Investment investments = null;
                investments = controller.sell(
                        textSymbol.getText(),
                        textPrice.getText(),
                        textQuantity.getText());
                messageBox.setText("Selling: \n");
                messageBox.append(investments.toStringForFile());
                gainGui.setGainText();
                gainGui.setIndividualGainText();
            }catch (Exception ex){
                messageBox.setText(ex.getMessage());
            }


        }
    }
}
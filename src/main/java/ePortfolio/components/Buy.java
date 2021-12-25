package ePortfolio.components;

import ePortfolio.Controller;
import ePortfolio.Investment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Buy class to create the buy interface
 */
public class Buy extends JPanel implements ActionListener {

    private JTextField textSymbol;
    private JTextField textName;
    private JTextField textQuantity;
    private JTextField textPrice;
    private JComboBox<String> investmentBox;
    private Controller controller;
    private JTextArea messageBox;

    public Buy(Controller controller){
        this.controller = controller;
        setLayout(new BorderLayout());
        createBuyInvestmentLabel();
        createBuyButton();
        createResetButton();
        createInvestmentTypeButton();
        createTypeLabel();
        createSymbolLabel();
        createNameLabel();
        createQuantityLabel();
        createSymbolButton();
        createQuantityButton();
        createPriceButton();
        createNameButton();
        createPriceLabel();
        messagesLabel();
        messageBox();
        fixIssue();
    }

    /**
     * Create buy title label
     */
    private void createBuyInvestmentLabel(){
        JLabel label = new JLabel("Buying an investment");
        add(label,BorderLayout.PAGE_START);
    }

    /**
     * Create type of Investment label
     */
    private void createTypeLabel(){
        JLabel label = new JLabel("Type");
        label.setBounds(10,0,100,100);
        add(label);
    }

    /**
     * Create symbol title label
     */
    private void createSymbolLabel(){
        JLabel label = new JLabel("Symbol");
        label.setBounds(10,20,100,100);
        add(label);
    }

    /**
     * Create name title label
     */
    private void createNameLabel(){
        JLabel label = new JLabel("Name");
        label.setBounds(10,40,100,100);
        add(label);
    }

    /**
     * Create quantity title label
     */
    private void createQuantityLabel(){
        JLabel label = new JLabel("Quantity");
        label.setBounds(10,60,100,100);
        add(label);
    }

    /**
     * Create price title label
     */
    private void createPriceLabel(){
        JLabel label = new JLabel("Price");
        label.setBounds(10,80,100,100);
        add(label);
    }

    /**
     * Create buy button
     */
    private void createBuyButton(){
        JButton buyButton = new JButton("Buy");
        buyButton.addActionListener(this);
        buyButton.setBounds(350,50,70,20);
        add(buyButton);
    }

    /**
     * Create reset button
     */
    private void createResetButton(){
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        resetButton.setBounds(350,100,70,20);
        add(resetButton);
    }

    /**
     * Create combo box with the two type of investments
     */
    private void createInvestmentTypeButton(){
        String[] investmentType = {"Stock", "MutualFund"};
        investmentBox = new JComboBox<>(investmentType);
        investmentBox.addActionListener(this);
        investmentBox.setBounds(60,40,100,20);
        add(investmentBox);
    }

    /**
     * Create symbol text field
     */
    private void createSymbolButton(){
        textSymbol = new JTextField();
        textSymbol.setBounds(60,60,100,20);
        add(textSymbol);
    }

    /**
     * Create name text field
     */
    private void createNameButton(){
        textName = new JTextField();
        textName.setBounds(60,80,100,20);
        add(textName);
    }

    /**
     * Create quantity text field
     */
    private void createQuantityButton(){
        textQuantity = new JTextField();
        textQuantity.addActionListener(e -> textQuantity.setText(textQuantity.getText().replaceAll("[^0-9]", "")));
        textQuantity.setBounds(60,100,100,20);
        add(textQuantity);
    }

    /**
     * Create price text field
     */
    private void createPriceButton(){
        textPrice = new JTextField();
        textPrice.addActionListener(e -> textPrice.setText(textPrice.getText().replaceAll("[^0-9.]", "")));
        textPrice.setBounds(60,120,100,20);
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
        String investmentType = (String) investmentBox.getSelectedItem();
        if(button.equals("Reset")){
            textSymbol.setText("");
            textName.setText("");
            textQuantity.setText("");
            textPrice.setText("");
        }
        if(button.equals("Buy")){
            Investment investment = null;
            try{
                if(investmentType.equals("Stock")){
                    investment = controller.buy("Stock", textName.getText(), textSymbol.getText(), textPrice.getText(), textQuantity.getText());
                    messageBox.setText(investment.toStringForFile());
                }else if(investmentType.equals("MutualFund")){
                    investment = controller.buy("MutualFund", textName.getText(), textSymbol.getText(), textPrice.getText(), textQuantity.getText());
                    messageBox.setText(investment.toStringForFile());
                }
            }catch (Exception ex){
                messageBox.setText(ex.getMessage());
            }
        }
    }
}

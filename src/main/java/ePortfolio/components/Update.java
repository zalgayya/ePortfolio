package ePortfolio.components;

import ePortfolio.Controller;
import ePortfolio.Investment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Update class to create the Update interface
 */
public class Update extends JPanel implements ActionListener{

    private JTextField textSymbol;
    private JTextField textName;
    private JTextField textPrice;
    private JTextArea  messageBox;
    private Controller controller;
    private int index = 0;
    private Investment investment;
    private Gain gainGui;

    public Update(Controller controller, Gain gainGui) {
        this.controller = controller;
        this.gainGui = gainGui;
        setLayout(new BorderLayout());
        createUpdateInvestmentLabel();
        createPrevButton();
        createNextButton();
        createSaveButton();
        createSymbolLabel();
        createNameLabel();
        createSymbolButton();
        createNameButton();
        createPriceButton();
        createPriceLabel();
        messageBox();
        messagesLabel();
        fixIssue();
    }

    /**
     * Create update title label
     */
    private void createUpdateInvestmentLabel() {
        JLabel label = new JLabel("Updating Investments");
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
     * Create name title label
     */
    private void createNameLabel() {
        JLabel label = new JLabel("Name");
        label.setBounds(10, 40, 100, 100);
        add(label);
    }

    /**
     * Create price title label
     */
    private void createPriceLabel() {
        JLabel label = new JLabel("Price");
        label.setBounds(10, 80, 100, 100);
        add(label);
    }

    /**
     * Create previous button
     */
    private void createPrevButton() {
        JButton prevButton = new JButton("Prev");
        prevButton.addActionListener(this);
        prevButton.setBounds(350, 50, 70, 20);
        add(prevButton);
    }

    /**
     * Create next button
     */
    private void createNextButton() {
        JButton resetButton = new JButton("Next");
        resetButton.addActionListener(this);
        resetButton.setBounds(350, 80, 70, 20);
        add(resetButton);
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
     * Create save button
     */
    private void createSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveButton.setBounds(350, 110, 70, 20);
        add(saveButton);
    }

    /**
     * Create symbol text field
     */
    private void createSymbolButton() {
        textSymbol = new JTextField();
        textSymbol.setEditable(false);
        textSymbol.setBounds(60, 40, 100, 20);
        add(textSymbol);
    }

    /**
     * Create name text field
     */
    private void createNameButton() {
        textName = new JTextField();
        textName.setEditable(false);
        textName.setBounds(60, 80, 110, 20);
        add(textName);
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
        String action = e.getActionCommand();
        Investment newInvestment = null;
        if(action.equals("Next")){
            if(index == controller.sizeInvestments()){
                index = 0;
            }
            investment = controller.next(index);
            textSymbol.setText(investment.getSymbol());
            textName.setText(investment.getName());
            index += 1;
        }
        if(action.equals("Save")){
            try{
                newInvestment = controller.update(investment, textPrice.getText());
                messageBox.setText(newInvestment.toStringForFile());
                gainGui.setGainText();
                gainGui.setIndividualGainText();
            }catch (Exception ex){
                messageBox.setText(ex.getMessage());
            }

        }
        if(action.equals("Prev")){
            index -= 1;
            if(index < 0){
                messageBox.setText("You are on the first investment");
                index = 0;
                return;
            }
            investment = controller.prev(index);
            textSymbol.setText(investment.getSymbol());
            textName.setText(investment.getName());
        }
    }
}

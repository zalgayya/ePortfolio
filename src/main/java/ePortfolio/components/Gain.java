package ePortfolio.components;

import ePortfolio.Controller;

import javax.swing.*;
import java.awt.*;


/**
 * Gain class to create the gain interface
 */
public class Gain extends JPanel{

    private JTextArea messageBox;
    private Controller controller;
    private JTextField gainText;

    public Gain(Controller controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        createGainInvestmentsLabel();
        createGainLabel();
        createGainButton();
        messageBox();
        messagesLabel();
        fixIssue();
    }

    /**
     * Create gain total investments title label
     */
    private void createGainInvestmentsLabel() {
        JLabel label = new JLabel("Calculating Total Gain");
        add(label, BorderLayout.PAGE_START);
    }

    /**
     * Create gain title label
     */
    private void createGainLabel() {
        JLabel label = new JLabel("Gain");
        label.setBounds(10, 0, 100, 100);
        add(label);
    }

    /**
     * Create gain text field
     */
    private void createGainButton() {
        gainText = new JTextField();
        gainText.setEditable(false);
        gainText.setBounds(60, 40, 100, 20);
        add(gainText);
    }

    /**
     * sets the gain in the text field
     */
    public void setGainText(){
        String gain = String.format("%.2f", controller.gain());
        gainText.setText(gain);
    }

    /**
     * Create message label
     */
    private void messagesLabel(){
        JLabel label = new JLabel("Individual Gains");
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

    /**
     * sets teh individual gain in the message box
     */
    public void setIndividualGainText(){
        messageBox.setText(controller.getIndividualGains());
    }

    private void fixIssue() {
        JLabel label = new JLabel("");
        label.setBounds(10, 80, 100, 100);
        add(label);
    }

}

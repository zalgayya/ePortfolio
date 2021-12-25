package ePortfolio.components;

import ePortfolio.Constants;
import ePortfolio.Controller;
import ePortfolio.Gui;

import javax.swing.*;

/**
 * class that creates new command menu and sets it menu items
 */
public class CommandMenuBar extends JMenuBar{

    private final Gui parent;
    private final Controller controller;

    /**
     * creates new command menu bar
     * @param parent Main gui class
     * @param controller controller class
     */
    public CommandMenuBar(Gui parent, Controller controller) {
        this.parent = parent;
        this.controller = controller;
        Gain gainGui = new Gain(controller);
        JMenu commandMenu = new JMenu("Commands");
        JMenuItem buy = createMenuItem(Constants.BUY, new Buy(controller));
        JMenuItem sell = createMenuItem(Constants.SELL, new Sell(controller, gainGui));
        JMenuItem update = createMenuItem(Constants.UPDATE, new Update(controller, gainGui));
        JMenuItem gain = createMenuItem(Constants.GAIN, gainGui);
        JMenuItem search = createMenuItem(Constants.SEARCH, new Search(controller));
        commandMenu.add(buy);
        commandMenu.add(sell);
        commandMenu.add(update);
        commandMenu.add(gain);
        commandMenu.add(search);
        add(commandMenu);
    }

    /**
     * creates new menu items
     * @param title Title of item
     * @param target JPanel class
     * @return JMenuItem
     */
    private JMenuItem createMenuItem(String title, JPanel target){
        JMenuItem item = new JMenuItem(title);
        item.addActionListener((e) -> parent.changePanel(target));
        return item;
    }

}

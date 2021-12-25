package ePortfolio;

import ePortfolio.components.CommandMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Main menu for the Gui interface
 */
public class Gui extends JFrame {

    private final Controller controller;
    public Gui(Controller controller){
        this.controller = controller;
        setTitle("ePortfolio");
        setSize(500,500);
        setResizable(false);
        setLayout(new BorderLayout());
        JLabel menu1 = new JLabel("welcome to ePortfolio");
        add(menu1, BorderLayout.NORTH);
        JLabel menu2 = new JLabel(toHtml("Choose a command from the commands menu to buy or sell\nan investment, update prices for all investments, get gain for the\n Portfolio, search for relevant investments, or quit program"));
        add(menu2, BorderLayout.CENTER);
        setJMenuBar(new CommandMenuBar(this,controller));
        addWindowListener(new CloseListener());
    }

    /**
     * changes the panels in the interface
     * @param panel new panel
     */
    public void changePanel(JPanel panel){
        getContentPane().removeAll();
        getContentPane().add(panel);
        update(getGraphics());
        panel.revalidate();
        panel.repaint();
    }

    /**
     * formats the string to html
     * @param s string inputted
     * @return formatted string
     */
    public static String toHtml(String s){
        return  "<html>" + s.replaceAll("\n", "<br/>") + "</html>";
    }

    /**
     * listens to when the gui window is closed
     */
    private class CloseListener extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            controller.quit();
        }
    }

}

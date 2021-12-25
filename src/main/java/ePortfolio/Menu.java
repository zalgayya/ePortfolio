package ePortfolio;

import java.util.Scanner;

/**
 * The menu class displays menu and grabs user input
 */
public class Menu {

    private static final String MENU = "Choose a Menu option:\n" +
            "Type buy to Buy.\n" +
            "Type sell to Sell.\n" +
            "Type update to Update.\n" +
            "Type gain to Get Gain.\n" +
            "Type search to Search.\n" +
            "Type QUIT or Q or q or quit to exit program.\n" +
            "> ";

    /**
     * Prints menu in terminal
     */
    public void show() {
        System.out.print(MENU);
    }

    /**
     * Reads user input for menu selection and exits if user's input is quit or q
     *
     * @return user's input parsed into an integer
     */
    public String getSelection() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}



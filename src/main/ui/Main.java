package ui;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        try {
            ui.options();
        } catch (IllegalStateException e) {
            System.out.println("Error has occurred.");
        }
        System.out.println("Quitting...");
    }
}

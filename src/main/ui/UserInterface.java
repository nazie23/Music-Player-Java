package ui;

import model.*;

import java.util.Scanner;

public class UserInterface {
    Scanner userInput;
    Queue playlist;

    public UserInterface() {
        userInput = new Scanner(System.in);
        playlist = new Queue();
    }

    public void options(boolean runProgram) {
        while (runProgram) {
            System.out.println("Please select an option from the following:"
                    + "\n'view' to view the current songs in queue"
                    + "\n'add' to add a song to the queue"
                    + "\n'remove' to remove a song from the queue"
                    + "\n'skip' to skip a song"
                    + "\n'prev' to go to the previous song"
                    + "\nTo quit, type 'quit':\n");

            String op = userInput.nextLine();
            handleInput(op, userInput, playlist);
        }
        userInput.close();
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void handleInput(String op, Scanner s, Queue q) {
        switch (op) {
            case "view": q.getQueue();
                break;
            case "add":
                System.out.println("Please enter the name of the song you would like to add: ");
                String n1 = s.nextLine();
                System.out.println("Please enter the artist of the song you would like to add: ");
                String art = s.nextLine();
                System.out.println("Please enter the duration of the song you would like to add: ");
                int dur = Integer.parseInt(s.next());
                q.addToQueue(new Song(n1, art, dur));
                break;
            case "remove":
                System.out.println("Please enter the name of the song you would like to remove: ");
                String n2 = s.nextLine();
                q.removeFromQueue(n2);
                break;
            case "skip": q.skipSong();
                break;
            case "prev": q.prevSong();
                break;
            case "quit": options(false);
            default: options(true);
        }

    }

}

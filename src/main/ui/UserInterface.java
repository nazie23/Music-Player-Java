package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private static final String JSON_STORE = "./data/queue.json";
    private Scanner userInput;
    private Queue playlist;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    boolean runProgram;

    public UserInterface() {
        userInput = new Scanner(System.in);
        playlist = new Queue("Untitled");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runProgram = true;
    }

    public void options() {
        while (runProgram) {
            System.out.println("Please select an option from the following:"
                    + "\n'save' to save the current playlist"
                    + "\n'load' to load a saved playlist"
                    + "\n'name' to change the name of a playlist"
                    + "\n'view' to view the current songs in queue"
                    + "\n'add' to add a song to the queue"
                    + "\n'remove' to remove a song from the queue"
                    + "\n'skip' to skip a song"
                    + "\n'prev' to go to the previous song"
                    + "\nTo quit, type 'quit':\t");

            String op = userInput.nextLine();
            handleInput(op);
        }

        userInput.close();
    }

    private void handleInput(String op) {
        switch (op) {
            case "save": saveQueue();
                break;
            case "load": loadQueue();
                break;
            case "name": changeName();
                break;
            case "view": printQueue();
                break;
            case "add": addSong();
                break;
            case "remove": removeSong();
                break;
            case "skip": playlist.skipSong();
                break;
            case "prev": playlist.prevSong();
                break;
            case "quit": runProgram = false;
                break;
            default: runProgram = true;
                break;
        }

    }

    public void saveQueue() {
        try {
            jsonWriter.open();
            jsonWriter.write(playlist);
            jsonWriter.close();
            System.out.println("Saved " + playlist.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public void loadQueue() {
        try {
            playlist = jsonReader.read();
            System.out.println("Loaded " + playlist.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public void changeName() {
        System.out.println("Please enter the new name of the playlist:\t");
        String name = userInput.nextLine();
        playlist.setName(name);
    }

    public void printQueue() {
        if (playlist.getQueue().isEmpty()) {
            System.out.println("The queue is empty!");
        } else {
            for (Song s : playlist.getQueue()) {
                System.out.println(s.getTitle() + " by " + s.getArtist());
            }
        }
    }

    public void addSong() {
        System.out.println("Please enter the name of the song you would like to add:\t");
        String name = userInput.nextLine();
        System.out.println("Please enter the artist of the song you would like to add:\t");
        String art = userInput.nextLine();
        System.out.println("Please enter the duration of the song you would like to add:\t");
        int dur = Integer.parseInt(userInput.next());
        playlist.addToQueue(new Song(name, art, dur));
    }

    public void removeSong() {
        System.out.println("Please enter the name of the song you would like to remove:\t");
        String name = userInput.nextLine();
        playlist.removeFromQueue(name);
    }

}

package ui;

import model.Queue;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class Frame extends JFrame implements ActionListener {
    private JLabel title;
    private JLabel currentSong;
    private Border border = BorderFactory.createLineBorder(Color.black, 1);

    private JButton addSong;
    private JButton removeSong;
    private JButton prevSong;
    private JButton skipSong;
    private JButton savePlaylist;
    private JButton loadPlaylist;
    private JButton viewQueue;
    private ViewQueueScreen viewQueueScreen;

    private JTextField name;
    private JTextField artist;
    private JTextField duration;
    private JButton submit;

    private Queue playlist;
    private static final String JSON_STORE = "./data/queue.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public Frame() {
        instantiateAddButton();
        instantiateRemoveButton();
        instantiateSkipButton();
        instantiatePrevButton();
        instantiateSaveButton();
        instantiateLoadButton();
        instantiateViewQueueButton();

        ImageIcon image = new ImageIcon(Objects.requireNonNull(MainGUI.class.getResource("images/logo.png")));
        this.setIconImage(image.getImage());

        instantiateLabels();
        buildTextFields();
        buildWindow();

        buildFrame();
        this.setVisible(true);

        playlist = new Queue("Untitled");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addSong) {
            getAddSongAttributes();
        } else if (e.getSource() == removeSong) {
            getRemoveSongAttributes();
        } else if (e.getSource() == submit) {
            if (artist.isVisible()) {
                addSong();
            } else {
                removeSong();
            }
        } else if (e.getSource() == prevSong) {
            playlist.prevSong();
            displayCurrentSong();
        } else if (e.getSource() == skipSong) {
            playlist.skipSong();
            displayCurrentSong();
        } else if (e.getSource() == savePlaylist) {
            saveQueue();
        } else if (e.getSource() == loadPlaylist) {
            loadQueue();
        } else if (e.getSource() == viewQueue) {
            viewQueue();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a simple window from which the main app will be run
    private void buildWindow() {
        this.setTitle("Music Player");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(235, 228, 199));
    }

    // MODIFIES: addSong
    // EFFECTS: creates an instance of a button that will be used to add a song
    private void instantiateAddButton() {
        ImageIcon temp = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/add.png")));
        Image plusImg = temp.getImage();
        Image plusImgScale = plusImg.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon plus = new ImageIcon(plusImgScale);
        addSong = new JButton();
        addSong.setBounds(175, 200, 50, 50);
        addSong.setBackground(new Color(235, 228, 199));
        addSong.setBorderPainted(false);
        addSong.addActionListener(this);
        addSong.setFocusable(false);
        addSong.setIcon(plus);
    }

    // MODIFIES: removeSong
    // EFFECTS: creates an instance of a button that will be used to remove a song
    private void instantiateRemoveButton() {
        ImageIcon temp = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/remove.png")));
        Image minusImg = temp.getImage();
        Image minusImgScale = minusImg.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon minus = new ImageIcon(minusImgScale);
        removeSong = new JButton();
        removeSong.setBounds(260, 200, 50, 50);
        removeSong.setBackground(new Color(235, 228, 199));
        removeSong.setBorderPainted(false);
        removeSong.addActionListener(this);
        removeSong.setFocusable(false);
        removeSong.setIcon(minus);
    }

    // MODIFIES: skipSong
    // EFFECTS: creates an instance of a button that will be used to skip a song
    private void instantiateSkipButton() {
        ImageIcon temp = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/skip.png")));
        Image skipImg = temp.getImage();
        Image skipImgScale = skipImg.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon skip = new ImageIcon(skipImgScale);
        skipSong = new JButton();
        skipSong.setBounds(375, 100, 50, 50);
        skipSong.setBackground(new Color(235, 228, 199));
        skipSong.setBorderPainted(false);
        skipSong.addActionListener(this);
        skipSong.setFocusable(false);
        skipSong.setIcon(skip);
    }

    // MODIFIES: prevSong
    // EFFECTS: creates an instance of a button that will be used to go back to a previous song
    private void instantiatePrevButton() {
        ImageIcon temp = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/previous.png")));
        Image prevImg = temp.getImage();
        Image prevImgScale = prevImg.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon prev = new ImageIcon(prevImgScale);
        prevSong = new JButton();
        prevSong.setBounds(75, 100, 50, 50);
        prevSong.setBackground(new Color(235, 228, 199));
        prevSong.setBorderPainted(false);
        prevSong.addActionListener(this);
        prevSong.setFocusable(false);
        prevSong.setIcon(prev);
    }

    // MODIFIES: savePlaylist
    // EFFECTS: creates an instance of a button that will be used to save the state of the
    // application
    private void instantiateSaveButton() {
        savePlaylist = new JButton("Save");
        savePlaylist.setBounds(30, 220, 75, 50);
        savePlaylist.setBackground(Color.white);
        savePlaylist.setBorder(border);
        savePlaylist.addActionListener(this);
        savePlaylist.setFocusable(false);
    }

    // MODIFIES: loadPlaylist
    // EFFECTS: creates an instance of a button that will be used to load a saved state of the
    // application
    private void instantiateLoadButton() {
        loadPlaylist = new JButton("Load");
        loadPlaylist.setBounds(30, 290, 75, 50);
        loadPlaylist.setBackground(Color.white);
        loadPlaylist.setBorder(border);
        loadPlaylist.addActionListener(this);
        loadPlaylist.setFocusable(false);
    }

    // MODIFIES: viewQueue
    // EFFECTS: creates an instance of a button that will be used to view all the songs in the
    // queue
    private void instantiateViewQueueButton() {
        viewQueue = new JButton("View Queue");
        viewQueue.setBounds(370, 200, 85, 40);
        viewQueue.setBackground(Color.white);
        viewQueue.setBorder(border);
        viewQueue.addActionListener(this);
        viewQueue.setFocusable(false);
    }

    // MODIFIES: title, currentSong
    // EFFECTS: creates two labels; one that titles the app and one that displays the current
    // song being played
    private void instantiateLabels() {
        title = new JLabel("Music Player");
        title.setBounds(190, 0, 150, 50);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        currentSong = new JLabel("No song is currently playing.", JLabel.CENTER);
        currentSong.setBounds(150, 100, 200, 50);
        currentSong.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    // MODIFIES: name, artist, duration, submit
    // EFFECTS: creates three text fields set an invisible that will be used when the user is
    // trying to add or remove a song, as well as a submit button
    private void buildTextFields() {
        name = new JTextField();
        name.setBounds(140, 290, 75, 20);
        name.setText("name");
        name.setVisible(false);

        artist = new JTextField();
        artist.setBounds(250, 290, 75, 20);
        artist.setText("artist");
        artist.setVisible(false);

        duration = new JTextField();
        duration.setBounds(360, 290, 75, 20);
        duration.setText("duration");
        duration.setVisible(false);

        submit = new JButton("Submit");
        submit.setBounds(140, 320, 75, 20);
        submit.setVisible(false);
        submit.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: adds all the created buttons, labels and text fields to the frame
    private void buildFrame() {
        this.add(title);
        this.add(currentSong);

        this.add(addSong);
        this.add(removeSong);
        this.add(skipSong);
        this.add(prevSong);
        this.add(savePlaylist);
        this.add(loadPlaylist);
        this.add(viewQueue);

        this.add(name);
        this.add(artist);
        this.add(duration);
        this.add(submit);
    }

    // MODIFIES: currentSong
    // EFFECTS: updates the currentSong label according to which song is currently at the front
    // of the queue
    private void displayCurrentSong() {
        if (playlist.getQueue().isEmpty()) {
            currentSong.setText("No song is currently playing.");
        } else {
            Song curSong = playlist.getQueue().get(0);
            currentSong.setText("<html>" + curSong.getTitle() + " by " + curSong.getArtist()
                    + " <br> " + curSong.getDuration() + " seconds </html>");
        }
    }


    // MODIFIES: name, artist, duration, submit
    // EFFECTS: sets the above labels/button to visible
    private void getAddSongAttributes() {
        name.setVisible(true);
        artist.setVisible(true);
        duration.setVisible(true);
        submit.setVisible(true);
    }

    // MODIFIES: playlist, name, artist, duration, submit
    // EFFECTS: adds a newly created song object to the queue and sets the above labels/button
    // to invisible
    private void addSong() {
        String tempName = name.getText();
        String tempArtist = artist.getText();
        int tempDuration = Integer.parseInt(duration.getText());
        Song tempSong = new Song(tempName, tempArtist, tempDuration);

        playlist.addToQueue(tempSong);
        name.setVisible(false);
        artist.setVisible(false);
        duration.setVisible(false);
        submit.setVisible(false);
        displayCurrentSong();
    }

    // MODIFIES: name, artist, duration, submit
    // EFFECTS: sets name and submit to visible, and artist and duration to invisible
    private void getRemoveSongAttributes() {
        name.setVisible(true);
        artist.setVisible(false);
        duration.setVisible(false);
        submit.setVisible(true);
    }

    // MODIFIES: playlist, name, submit
    // EFFECTS: removes the song according to the name typed into the field, sets name and submit
    // to invisible
    private void removeSong() {
        String tempName = name.getText();
        playlist.removeFromQueue(tempName);
        name.setVisible(false);
        submit.setVisible(false);
        displayCurrentSong();
    }

    private void saveQueue() {
        try {
            jsonWriter.open();
            jsonWriter.write(playlist);
            jsonWriter.close();
            System.out.println("Saved " + playlist.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadQueue() {
        try {
            playlist = jsonReader.read();
            System.out.println("Loaded " + playlist.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        displayCurrentSong();
    }

    private void viewQueue() {
        viewQueueScreen = new ViewQueueScreen(playlist);
    }

}

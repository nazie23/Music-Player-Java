package ui;

import model.Queue;
import model.Song;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JTextField name;
    private JTextField artist;
    private JTextField duration;
    private JButton submit;

    private Queue playlist;

    public Frame() {
        instantiateAddButton();
        instantiateRemoveButton();
        instantiateSkipButton();
        instantiatePrevButton();
        instantiateSaveButton();
        instantiateLoadButton();

        ImageIcon image = new ImageIcon(Objects.requireNonNull(MainGUI.class.getResource("images/logo.png")));
        this.setIconImage(image.getImage());

        instantiateLabels();
        buildTextFields();
        buildWindow();

        buildFrame();
        this.setVisible(true);

        playlist = new Queue("Untitled");
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
            displayCurrentSong();
        } else if (e.getSource() == prevSong) {
            //
        } else if (e.getSource() == skipSong) {
            //
        } else if (e.getSource() == savePlaylist) {
            //
        } else if (e.getSource() == loadPlaylist) {
            //
        }
    }

    private void buildWindow() {
        this.setTitle("Music Player");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 400);
        this.getContentPane().setBackground(new Color(235, 228, 199));
    }

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

    private void instantiateSaveButton() {
        savePlaylist = new JButton("Save");
        savePlaylist.setBounds(30, 220, 75, 50);
        savePlaylist.setBackground(Color.white);
        savePlaylist.setBorder(border);
        savePlaylist.addActionListener(this);
        savePlaylist.setFocusable(false);
    }

    private void instantiateLoadButton() {
        loadPlaylist = new JButton("Load");
        loadPlaylist.setBounds(30, 290, 75, 50);
        loadPlaylist.setBackground(Color.white);
        loadPlaylist.setBorder(border);
        loadPlaylist.addActionListener(this);
        loadPlaylist.setFocusable(false);
    }

    private void instantiateLabels() {
        title = new JLabel("Music Player");
        title.setBounds(190, 0, 150, 50);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        currentSong = new JLabel("No song is currently playing.", JLabel.CENTER);
        currentSong.setBounds(150, 100, 200, 50);
        currentSong.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    private void buildFrame() {
        this.add(title);
        this.add(currentSong);

        this.add(addSong);
        this.add(removeSong);
        this.add(skipSong);
        this.add(prevSong);
        this.add(savePlaylist);
        this.add(loadPlaylist);

        this.add(name);
        this.add(artist);
        this.add(duration);
        this.add(submit);
    }

    private void displayCurrentSong() {
        if (playlist.getQueue().isEmpty()) {
            currentSong.setText("No song is currently playing.");
        } else {
            Song curSong = playlist.getQueue().get(0);
            currentSong.setText("<html>" + curSong.getTitle() + " by " + curSong.getArtist()
                    + " <br> " + curSong.getDuration() + " seconds </html>");
        }
    }

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

    
    // Actions
    private void getAddSongAttributes() {
        name.setVisible(true);
        artist.setVisible(true);
        duration.setVisible(true);
        submit.setVisible(true);
    }

    private void addSong() {
        String tempName = name.getText();
        String tempArtist = artist.getText();
        int tempDuration = Integer.parseInt(duration.getText());
        Song tempSong = new Song(tempName, tempArtist, tempDuration);

        playlist.addToQueue(tempSong);
    }

    private void getRemoveSongAttributes() {
        name.setVisible(true);
        artist.setVisible(false);
        duration.setVisible(false);
        submit.setVisible(true);
    }

    private void removeSong() {
        String tempName = name.getText();

        playlist.removeFromQueue(tempName);
    }

}

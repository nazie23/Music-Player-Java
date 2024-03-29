package ui;

import model.Queue;
import model.Song;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class ViewQueueScreen extends JFrame {
    private Queue playlist;
    private StringBuilder sb;
    private JScrollPane scroll;

    public ViewQueueScreen(Queue playlist) {
        this.playlist = playlist;

        initializeList();
        buildFrame();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a simple window from which the current state of the queue can be viewed
    private void buildFrame() {
        this.setTitle("Queue");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(230, 400);
        this.setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon(Objects.requireNonNull(MainGUI.class.getResource("images/logo.png")));
        this.setIconImage(image.getImage());
    }

    // MODIFIES: sb, scroll, this
    // EFFECTS: adds songs from the queue to a string builder, which is used to instantiate a
    // scroll pane
    private void initializeList() {
        sb = new StringBuilder();
        sb.append("<html>");

        ArrayList<Song> songs = playlist.getQueue();
        for (Song s : songs) {
            String str = s.getTitle() + " by " + s.getArtist() + "<br>";
            sb.append(str);
        }

        sb.append("</html>");
        scroll = new JScrollPane(new JLabel(sb.toString()));
        scroll.setBounds(0, 0, 230, 400);
        this.add(scroll);
    }

}
package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SplashScreen extends JFrame {
    private JProgressBar progressBar;
    private JLabel logo;

    public SplashScreen() {
        buildFrame();
        createLabel();
        addProgressBar();
        this.setVisible(true);
        runningProgressBar();
    }

    // MODIFIES: this
    // EFFECTS: creates a simple window from which a splash screen will run before the main app
    private void buildFrame() {
        this.setLayout(null);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
    }

    // MODIFIES: logo, this
    // EFFECTS: instantiates a welcome message with the app logo and adds it to the frame
    private void createLabel() {
        logo = new JLabel("Welcome to the Music Player!");
        ImageIcon temp = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/logo.png")));
        Image img = temp.getImage();
        Image imgScaled = img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon imgIconScaled = new ImageIcon(imgScaled);
        logo.setIcon(imgIconScaled);
        logo.setVerticalTextPosition(JLabel.BOTTOM);
        logo.setHorizontalTextPosition(JLabel.CENTER);
        logo.setBounds(65, 0, 200, 150);
        this.add(logo);
    }

    // MODIFIES: progressBar, this
    // EFFECTS: instantiates a progress bar and adds it to the frame
    private void addProgressBar() {
        progressBar = new JProgressBar();
        progressBar.setBounds(10, 160, 280, 15);
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.BLACK);
        progressBar.setBorderPainted(true);
        progressBar.setStringPainted(true);
        progressBar.setValue(0);
        this.add(progressBar);
    }

    // MODIFIES: progressBar
    // EFFECTS: runs the progress bar, which incrementally loads from 0% until it reaches 100%
    private void runningProgressBar() {
        int i = 0;

        while (i <= 100) {
            try {
                Thread.sleep(25);
                progressBar.setValue(i);
                i++;
                if (i == 100) {
                    this.dispose();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

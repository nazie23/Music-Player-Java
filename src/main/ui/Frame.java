package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Frame extends JFrame implements ActionListener {

    JButton addSong;
    JButton removeSong;
    JButton prevSong;
    JButton skipSong;

    public Frame() {

        instantiateAddButton();
        instantiateRemoveButton();
        instantiateSkipButton();
        instantiatePrevButton();

        ImageIcon image = new ImageIcon(Objects.requireNonNull(MainGUI.class.getResource("images/logo.png")));
        this.setIconImage(image.getImage());

        JLabel title = new JLabel("Music Player");
        title.setBounds(200, 0, 100, 50);

        this.setTitle("Music Player");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500, 500);
        this.getContentPane().setBackground(new Color(235, 228, 199));

        this.add(title);
        this.add(addSong);
        this.add(removeSong);
        this.add(skipSong);
        this.add(prevSong);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addSong) {
            //
        } else if (e.getSource() == removeSong) {
            //
        } else if (e.getSource() == prevSong) {
            //
        } else if (e.getSource() == skipSong) {
            //
        }
    }

    public void instantiateAddButton() {
        ImageIcon temp = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/add.png")));
        Image plusImg = temp.getImage();
        Image plusImgScale = plusImg.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon plus = new ImageIcon(plusImgScale);
        addSong = new JButton();
        addSong.setBounds(100, 200, 50, 50);
        addSong.addActionListener(this);
        addSong.setFocusable(false);
        addSong.setIcon(plus);
    }

    public void instantiateRemoveButton() {
        ImageIcon temp = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/remove.png")));
        Image minusImg = temp.getImage();
        Image minusImgScale = minusImg.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon minus = new ImageIcon(minusImgScale);
        removeSong = new JButton();
        removeSong.setBounds(200, 200, 50, 50);
        removeSong.addActionListener(this);
        removeSong.setFocusable(false);
        removeSong.setIcon(minus);
    }

    public void instantiateSkipButton() {
        ImageIcon temp = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/skip.png")));
        Image skipImg = temp.getImage();
        Image skipImgScale = skipImg.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon skip = new ImageIcon(skipImgScale);
        skipSong = new JButton();
        skipSong.setBounds(300, 200, 50, 50);
        skipSong.addActionListener(this);
        skipSong.setFocusable(false);
        skipSong.setIcon(skip);
    }

    public void instantiatePrevButton() {
        ImageIcon temp = new ImageIcon(Objects.requireNonNull(getClass().getResource("images/previous.png")));
        Image prevImg = temp.getImage();
        Image prevImgScale = prevImg.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon prev = new ImageIcon(prevImgScale);
        prevSong = new JButton();
        prevSong.setBounds(400, 200, 50, 50);
        prevSong.addActionListener(this);
        prevSong.setFocusable(false);
        prevSong.setIcon(prev);
    }

}

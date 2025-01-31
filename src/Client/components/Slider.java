package client.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import client.MusicPlayer;

public class Slider implements ChangeListener {

    JFrame frame;
    JPanel panel;
    JLabel label;
    JSlider slider;
    MusicPlayer musicPlayer;

    public Slider(MusicPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
        frame = new JFrame("Slider");
        panel = new JPanel();
        label = new JLabel();
        slider = new JSlider(0, 100, 50);

        slider.setPreferredSize(new Dimension(400, 200));

        slider.setPaintTicks(true);
        slider.setMinorTickSpacing(10);

        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(25);

        slider.setPaintLabels(true);
        slider.setFont(new Font("MV Boli", Font.PLAIN, 15));
        label.setFont(new Font("MV Boli", Font.PLAIN, 25));

        // slider.setOrientation(SwingConstants.HORIZONTAL);
        slider.setOrientation(SwingConstants.VERTICAL);

        label.setText("°C = " + slider.getValue());

        slider.addChangeListener(this);

        panel.add(slider);
        panel.add(label);
        frame.add(panel);
        frame.setSize(420, 420);
        frame.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        label.setText("°C = " + slider.getValue());
        this.musicPlayer.setVolume(slider.getValue());
    }

}
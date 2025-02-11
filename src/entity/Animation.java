package entity;

import javax.swing.*;

import client.GamePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Animation extends JPanel implements ActionListener {
    private int frameWidth = 200;
    private int frameHeight = 200;
    private int frameCount = 4;
    private int currentFrame = 0;
    private Timer timer;

    public Animation() {
        timer = new Timer(200, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame = (currentFrame + 1) % frameCount;
        repaint();
    }

    // Component

    public void background(Graphics2D g2, BufferedImage img) {
        int frameWidth = GamePanel.screenWidth / 2;
        int frameHeight = GamePanel.screenHeight / 2;
        int frameX = this.currentFrame * frameWidth;
        g2.drawImage(img, frameWidth * 2, 0, 0, frameHeight * 2, frameX, 0,
                frameX + frameWidth, frameHeight, this);
    }

    // int frameX = currentFrame * frameWidth;
    // int centerX = (GamePanel.screenWidth - frameWidth) / 2; // Horizontal
    // centering
    // int centerY = (GamePanel.screenHeight - frameHeight) / 2; // Vertical
    // centering
    // g2.drawImage(img, centerX, centerY, centerX + frameWidth, centerY +
    // frameHeight,
    // frameX, 0, frameX + frameWidth, frameHeight, this);

    public void P1(Graphics2D g2, BufferedImage img) {
        int frameX = this.currentFrame * frameWidth;
        int centerX = 540 - 430;
        int centerY = 360 - 100;
        g2.drawImage(img, centerX, centerY, frameWidth + centerX + 100, frameHeight + centerY + 100, frameX, 0,
                frameX + frameWidth, frameHeight, this);
    }

    public void P2(Graphics2D g2, BufferedImage img) {
        int frameX = this.currentFrame * frameWidth;
        int centerX = 540 + 330;
        int centerY = 360 - 100;
        g2.drawImage(img, centerX, centerY, frameWidth + centerX + 100, frameHeight + centerY + 100, frameX, 0,
                frameX + frameWidth, frameHeight, this);
    }

    public void centerCharacter(Graphics2D g2, BufferedImage img) {
        int frameX = this.currentFrame * frameWidth;
        int centerX = 540 - 50;
        int centerY = 360 - 100;
        g2.drawImage(img, centerX, centerY, frameWidth + centerX + 100, frameHeight + centerY + 100, frameX, 0,
                frameX + frameWidth, frameHeight, this);
    }

    public void leftCharacter(Graphics2D g2, BufferedImage img) {
        int frameX = this.currentFrame * frameWidth;
        int centerX = 540 - 430;
        int centerY = 360 - 100;
        g2.drawImage(img, centerX, centerY, frameWidth + centerX + 100, frameHeight + centerY + 100, frameX, 0,
                frameX + frameWidth, frameHeight, this);
    }

    public void rightCharacter(Graphics2D g2, BufferedImage img) {
        int frameX = this.currentFrame * frameWidth;
        int centerX = 540 + 330;
        int centerY = 360 - 100;
        g2.drawImage(img, centerX, centerY, frameWidth + centerX + 100, frameHeight + centerY + 100, frameX, 0,
                frameX + frameWidth, frameHeight, this);
    }
}

package entity;

import javax.swing.*;
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
        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentFrame = (currentFrame + 1) % frameCount;
        repaint();
    }


    // Component
    
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

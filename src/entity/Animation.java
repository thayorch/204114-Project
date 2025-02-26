package entity;

import javax.swing.*;
import client.GamePanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Animation extends JPanel implements ActionListener {
    private final int frameWidth = GamePanel.screenWidth / 2;
    private final int frameHeight = GamePanel.screenHeight / 2;
    private final int backgroundFrameCount = 6;
    private int backgroundCurrentFrame = 0;
    private final int characterFrameCount = 4;
    private int characterCurrentFrame = 0;
    private final Timer timer;

    public Animation() {
        timer = new Timer(200, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        backgroundCurrentFrame = (backgroundCurrentFrame + 1) % backgroundFrameCount;
        characterCurrentFrame = (characterCurrentFrame + 1) % characterFrameCount;
        repaint();
    }

    public void drawBackground(Graphics2D g2, BufferedImage img) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int frameX = backgroundCurrentFrame * (GamePanel.screenWidth / 2);
        g2.drawImage(img, 0, 0, GamePanel.screenWidth, GamePanel.screenHeight,
                frameX, 0, frameX + frameWidth, frameHeight, this);
    }

    public void drawBullet(Graphics2D g2, BufferedImage img, boolean reverse) {

        int frameX = backgroundCurrentFrame * (GamePanel.screenWidth / 2);
        if (reverse) {
            g2.drawImage(img, 0, 0, GamePanel.screenWidth, GamePanel.screenHeight + 185,
                    frameX + frameWidth, 0, frameX, frameHeight, this);
        } else {
            g2.drawImage(img, 0, 0, GamePanel.screenWidth, GamePanel.screenHeight + 185,
                    frameX, 0, frameX + frameWidth, frameHeight, this);
        }
        GamePanel.musicPlayer.sfx("pew");
    }

    private void drawCharacter(Graphics2D g2, BufferedImage spriteSheet, int posX, int posY, boolean isFlipped,
            double scale) {
        int frameWidth = 200;
        int frameHeight = 200;
        int spriteX = characterCurrentFrame * frameWidth; // Current frame position in the sprite sheet

        // Calculate new scaled dimensions
        int scaledWidth = (int) (frameWidth * scale);
        int scaledHeight = (int) (frameHeight * scale);

        // Adjust drawing positions based on flipping
        int destX1 = isFlipped ? posX + scaledWidth : posX;
        int destX2 = isFlipped ? posX : posX + scaledWidth;

        // Draw the upscaled/flipped character
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(spriteSheet, destX1, posY, destX2, posY + scaledHeight,
                spriteX, 0, spriteX + frameWidth, frameHeight, this);
    }

    private void drawSparkle(Graphics2D g2, BufferedImage spriteSheet, int posX, int posY, boolean isFlipped,
            double scale) {
        int frameWidth = 640;
        int frameHeight = 380;
        int spriteX = characterCurrentFrame * frameWidth; // Current frame position in the sprite sheet

        // Calculate new scaled dimensions
        int scaledWidth = (int) (frameWidth * scale);
        int scaledHeight = (int) (frameHeight * scale);

        // Adjust drawing positions based on flipping
        int destX1 = isFlipped ? posX + scaledWidth : posX;
        int destX2 = isFlipped ? posX : posX + scaledWidth;

        // Draw the upscaled/flipped character
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(spriteSheet, destX1, posY, destX2, posY + scaledHeight,
                spriteX, 0, spriteX + frameWidth, frameHeight, this);
    }

    public void drawLeftCharacter(Graphics2D g2, BufferedImage img) {
        drawCharacter(g2, img, 120, 320, false, 1.5);
    }

    public void drawCenterCharacter(Graphics2D g2, BufferedImage img) {
        drawCharacter(g2, img, 490, 300, false, 1.5);
    }

    public void drawRightCharacter(Graphics2D g2, BufferedImage img) {
        drawCharacter(g2, img, 870, 320, true, 1.5);
    }

    public void drawP1(Graphics2D g2, BufferedImage img) {
        drawCharacter(g2, img, 170, 300, false, 1.75);
    }
    public void drawP2(Graphics2D g2, BufferedImage img) {
        drawCharacter(g2, img, 750, 300, true, 1.75);
    }
    public void drawWin(Graphics2D g2, BufferedImage img) {
        drawSparkle(g2, img, 760, 110, true, 1.85);
    }
}

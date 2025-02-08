package client;

import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Component {
    public int screenWidth;
    public int screenHeight;
    public float scalingFactor;

    public String fontName = "Jacquard 12";
    public Font font_title = new Font(fontName, Font.PLAIN, 60);
    public Font font_40 = new Font(fontName, Font.PLAIN, 40);
    public Font font_30 = new Font(fontName, Font.PLAIN, 30);
    public Font font_25 = new Font(fontName, Font.PLAIN, 25);
    public Font font_20 = new Font(fontName, Font.PLAIN, 20);
    public Font font_10 = new Font("Consolas", Font.BOLD, 14);

    public Component(GamePanel gamePanel, GameState gameState) {
        this.screenWidth = gamePanel.screenWidth;
        this.screenHeight = gamePanel.screenHeight;
        this.scalingFactor = gamePanel.scalingFactor;
    }

    public BufferedImage img(String FilePath) throws IOException {
        return ImageIO.read(getClass().getResourceAsStream(FilePath));
    }

    public void setBackground(Graphics2D g2, BufferedImage background) {
        g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);
    }

    public void titleCenter(Graphics2D g2, String txt, int y) {
        g2.setColor(Color.black);
        this.setFontScale(g2, this.font_title);
        g2.drawString(txt, getTextCenter(g2, txt), y);
    }

    public void title_25(Graphics2D g2, String txt, int y) {
        g2.setColor(Color.black);
        this.setFontScale(g2, this.font_25);
        g2.drawString(txt, getTextCenter(g2, txt), y);
    }

    public void text_10_alginCenter(Graphics2D g2, String txt, int x, int y) {
        g2.setColor(Color.WHITE);
        g2.setFont(font_10);
        g2.drawString(txt, getTextCenter(g2, txt) + x, y);
    }

    public void text_10_alginLeft(Graphics2D g2, String txt, int x, int y) {
        g2.setColor(Color.WHITE);
        g2.setFont(font_10);
        g2.drawString(txt, x, y);
    }

    public void text_10_alginRight(Graphics2D g2, String txt, int x, int y) {
        g2.setColor(Color.WHITE);
        g2.setFont(font_10);
        g2.drawString(txt, screenWidth - x, y);
    }

    public void textBtn(Graphics2D g2, String txt, BufferedImage button, int y) {
        g2.setColor(Color.decode("#580f00"));
        g2.drawImage(button, (screenWidth / 2) - 155, y - 70, 290, 100, null);
        this.setFontScale(g2, this.font_30);
        g2.drawString(txt, getTextCenter(g2, txt) - 15, y);
    }

    // Scence (Map)

    public void scenceCardleft(Graphics2D g2, String txt, BufferedImage img) {
        g2.drawImage(img, (screenWidth / 2) - (265 * 2), 230, 150 * 2, 200 * 2, null);
        this.setFontScale(g2, this.font_20);
        g2.setColor(Color.white);
        g2.drawString("", getTextCenter(g2, txt) - (190 * 2), 300 * 2);
    }

    public void scenceCardcenter(Graphics2D g2, String txt, BufferedImage img) {
        g2.drawImage(img, (screenWidth / 2) - 150, 230, 150 * 2, 200 * 2, null);
        this.setFontScale(g2, this.font_20);
        g2.setColor(Color.white);
        g2.drawString("", getTextCenter(g2, txt), 300 * 2);
    }

    public void scenceCardright(Graphics2D g2, String txt, BufferedImage img) {
        g2.drawImage(img, (screenWidth / 2) + 115 * 2, 230, 150 * 2, 200 * 2, null);
        this.setFontScale(g2, this.font_20);
        g2.setColor(Color.white);
        g2.drawString("", getTextCenter(g2, txt) + (190 * 2), 300 * 2);
    }

    public void chamberSlot_P1(Graphics2D g2, BufferedImage img, int slot) {
        switch (slot) {
            case 0:
                g2.drawImage(img, 115, screenHeight - 119, 25, 25, null);
                break;
            case 1:
                g2.drawImage(img, 142, screenHeight - 103, 25, 25, null);
                break;
            case 2:
                g2.drawImage(img, 142, screenHeight - 67, 25, 25, null);
                break;
            case 3:
                g2.drawImage(img, 115, screenHeight - 52, 25, 25, null);
                break;
            case 4:
                g2.drawImage(img, 89, screenHeight - 68, 25, 25, null);
                break;
            case 5:
                g2.drawImage(img, 87, screenHeight - 103, 25, 25, null);
                break;

        }
    }

    public void chamberSlot_P2(Graphics2D g2, BufferedImage img, int slot) {
        switch (slot) {
            case 0:
                g2.drawImage(img, 1145, screenHeight - 119, 25, 25, null);
                break;
            case 1:
                g2.drawImage(img, 1172, screenHeight - 103, 25, 25, null);
                break;
            case 2:
                g2.drawImage(img, 1172, screenHeight - 67, 25, 25, null);
                break;
            case 3:
                g2.drawImage(img, 1145, screenHeight - 52, 25, 25, null);
                break;
            case 4:
                g2.drawImage(img, 1119, screenHeight - 68, 25, 25, null);
                break;
            case 5:
                g2.drawImage(img, 1117, screenHeight - 103, 25, 25, null);
                break;

        }
    }

    public void chamberP1(Graphics2D g2, BufferedImage chamber) {
        g2.setColor(Color.WHITE);
        g2.fillRect(125, screenHeight - 100, 50, 50);
        g2.setColor(Color.GRAY);
        g2.fillRect(175, screenHeight - 100, 50, 50);
        g2.setColor(Color.WHITE);
        g2.fillRect(225, screenHeight - 100, 50, 50);
        g2.setColor(Color.GRAY);
        g2.fillRect(275, screenHeight - 100, 50, 50);
        g2.setColor(Color.WHITE);
        g2.fillRect(325, screenHeight - 100, 50, 50);
        g2.setColor(Color.GRAY);
        g2.fillRect(375, screenHeight - 100, 50, 50);
        g2.drawImage(chamber, 75, screenHeight - 125, 100, 100, null);
    }

    public void chamberP2(Graphics2D g2, BufferedImage chamber) {
        g2.setColor(Color.WHITE);
        g2.fillRect(screenWidth - 175, screenHeight - 100, 50, 50);
        g2.setColor(Color.GRAY);
        g2.fillRect(screenWidth - 225, screenHeight - 100, 50, 50);
        g2.setColor(Color.WHITE);
        g2.fillRect(screenWidth - 275, screenHeight - 100, 50, 50);
        g2.setColor(Color.GRAY);
        g2.fillRect(screenWidth - 325, screenHeight - 100, 50, 50);
        g2.setColor(Color.WHITE);
        g2.fillRect(screenWidth - 375, screenHeight - 100, 50, 50);
        g2.setColor(Color.GRAY);
        g2.fillRect(screenWidth - 425, screenHeight - 100, 50, 50);
        g2.drawImage(chamber, screenWidth - 175, screenHeight - 125, 100, 100, null);
    }

    // Bullet

    public void Bullet(Graphics2D g2, int x, int y, Color color) {
        g2.setColor(color);
        g2.fillOval(x, screenHeight - y, 20, 20);
    }
    // Action slot

    public void actionSlot_P1(Graphics2D g2, BufferedImage img, int slot) {
        switch (slot) {
            case 0:
                g2.drawImage(img, 175, screenHeight - 100, 50, 50, null);
                break;
            case 1:
                g2.drawImage(img, 225, screenHeight - 100, 50, 50, null);
                break;
            case 2:
                g2.drawImage(img, 275, screenHeight - 100, 50, 50, null);
                break;
            case 3:
                g2.drawImage(img, 325, screenHeight - 100, 50, 50, null);
                break;
            case 4:
                g2.drawImage(img, 375, screenHeight - 100, 50, 50, null);
                break;
        }
    }

    public void actionSlot_P2(Graphics2D g2, BufferedImage img, int slot) {
        switch (slot) {
            case 0:
                g2.drawImage(img, screenWidth - 425, screenHeight - 100, 50, 50, null);
                break;
            case 1:
                g2.drawImage(img, screenWidth - 375, screenHeight - 100, 50, 50, null);
                break;
            case 2:
                g2.drawImage(img, screenWidth - 325, screenHeight - 100, 50, 50, null);
                break;
            case 3:
                g2.drawImage(img, screenWidth - 275, screenHeight - 100, 50, 50, null);
                break;
            case 4:
                g2.drawImage(img, screenWidth - 225, screenHeight - 100, 50, 50, null);
                break;
        }
    }

    public int getTextCenter(Graphics2D g2, String text) {
        int text_length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = screenWidth / 2 - text_length / 2;
        return x;
    }

    public void setFontScale(Graphics2D g2, Font font) {
        int size = font.getSize();
        g2.setFont(new Font(fontName, Font.PLAIN, (int) (size * scalingFactor)));
    }

}

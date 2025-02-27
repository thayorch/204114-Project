package client;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.FontFormatException;

public class Component {
    public int screenWidth;
    public int screenHeight;
    public float scalingFactor;

    private Font jersey10;
    public String fontName = "Jersey10-Regular";
    public Font font_how = new Font("Consolas", Font.BOLD, 20);
    public Font font_title = new Font("Arial", Font.PLAIN, 40);
    public Font font_40 = new Font("Arial", Font.PLAIN, 40);
    public Font font_30 = new Font("Arial", Font.PLAIN, 30);
    public Font font_25 = new Font("Arial", Font.PLAIN, 25);
    public Font font_20 = new Font("Arial", Font.PLAIN, 20);
    public Font font_10 = new Font("Consolas", Font.BOLD, 14);

    public Component(GamePanel gamePanel, GameState gameState) {
        this.screenWidth = gamePanel.screenWidth;
        this.screenHeight = gamePanel.screenHeight;
        this.scalingFactor = gamePanel.scalingFactor;
        try {
            InputStream is = getClass().getResourceAsStream("/resources/Jersey10-Regular.ttf");
            jersey10 = Font.createFont(Font.TRUETYPE_FONT, is);
            font_title = jersey10.deriveFont(Font.PLAIN, 40);
            font_40 = jersey10.deriveFont(Font.PLAIN, 40);
            font_30 = jersey10.deriveFont(Font.PLAIN, 30);
            font_25 = jersey10.deriveFont(Font.PLAIN, 25);
            font_20 = jersey10.deriveFont(Font.PLAIN, 20);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            font_title = new Font("Arial", Font.PLAIN, 40); // Fallback in case of error
        }
    }

    public BufferedImage img(String FilePath) throws IOException {
        return ImageIO.read(getClass().getResourceAsStream(FilePath));
    }

    public void setBackground(Graphics2D g2, BufferedImage background) {
        g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);
    }

    public void setTitle(Graphics2D g2, BufferedImage img) {
        g2.drawImage(img, (screenWidth / 4) - 190, 10, 500 * 2, 85 * 6, null);
    }

    public void titleCenter(Graphics2D g2, String txt, int y) {
        g2.setColor(Color.decode("#580f00"));
        // g2.setColor(Color.black);
        setFontScale(g2, font_title);
        // this.setFontScale(g2, jersey10.deriveFont(Font.PLAIN, 40)); // Scale directly
        // with jersey10 font
        // g2.setFont(font_title);
        g2.drawString(txt, getTextCenter(g2, txt), y);
    }

    public void PlayerCenter(Graphics2D g2, String txt, int y) {
        g2.setColor(Color.decode("#580f00"));
        // g2.setColor(Color.black);
        setFontScale(g2, font_25);
        // this.setFontScale(g2, jersey10.deriveFont(Font.PLAIN, 40)); // Scale directly
        // with jersey10 font
        // g2.setFont(font_title);
        g2.drawString(txt, getTextCenter(g2, txt), y);
    }

    public void howCenter(Graphics2D g2, String txt, int y) {
        g2.setColor(Color.white);
        g2.setFont(font_how);
        g2.drawString(txt, getTextCenter(g2, txt), y);
    }

    public void title_25(Graphics2D g2, String txt, int y) {
        g2.setColor(Color.white);
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
        g2.drawImage(button, (screenWidth / 2) - 155, y - 60, 290, 100, null);
        this.setFontScale(g2, this.font_30);
        g2.drawString(txt, getTextCenter(g2, txt) - 15, y);
    }

    public void textWin(Graphics2D g2, String txt, int y) {
        g2.setColor(Color.WHITE);
        this.setFontScale(g2, this.font_30);
        g2.drawString(txt, getTextCenter(g2, txt) - 15, y);
    }

    public void text_10(Graphics2D g2, String txt, int x, int y) {
        g2.setColor(Color.WHITE);
        g2.setFont(font_10);
        g2.drawString(txt, x, y);
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
                g2.drawImage(img, 148, screenHeight - 320, 40, 40, null);
                break;
            case 1:
                g2.drawImage(img, 161, screenHeight - 276, 40, 40, null);
                break;
            case 2:
                g2.drawImage(img, 127, screenHeight - 238, 40, 40, null);
                break;
            case 3:
                g2.drawImage(img, 80, screenHeight - 252, 40, 40, null);
                break;
            case 4:
                g2.drawImage(img, 70, screenHeight - 297, 40, 40, null);
                break;
            case 5:
                g2.drawImage(img, 99, screenHeight - 328, 40, 40, null);
                break;

        }
    }

    public void chamberSlot_P2(Graphics2D g2, BufferedImage img, int slot) {
        switch (slot) {
            case 0:
                g2.drawImage(img, 1141, screenHeight - 328, 40, 40, null);
                break;
            case 1:
                g2.drawImage(img, 1172, screenHeight - 296, 40, 40, null);
                break;
            case 2:
                g2.drawImage(img, 1162, screenHeight - 254, 40, 40, null);
                break;
            case 3:
                g2.drawImage(img, 1115, screenHeight - 230, 40, 40, null);
                break;
            case 4:
                g2.drawImage(img, 1080, screenHeight - 277, 40, 40, null);
                break;
            case 5:
                g2.drawImage(img, 1095, screenHeight - 320, 40, 40, null);
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

    public void Bullet(Graphics2D g2, int x, int y, BufferedImage img) {
        g2.drawImage(img, x, screenHeight - y, 40, 40, null);
    }
    // Action slot

    public void actionSlot_P1(Graphics2D g2, BufferedImage img, int slot) {
        switch (slot) {
            case 0:
                g2.drawImage(img, 75, screenHeight - 105, 50, 50, null);
                break;
            case 1:
                g2.drawImage(img, 175, screenHeight - 105, 50, 50, null);
                break;
            case 2:
                g2.drawImage(img, 275, screenHeight - 105, 50, 50, null);
                break;
            case 3:
                g2.drawImage(img, 375, screenHeight - 105, 50, 50, null);
                break;
            case 4:
                g2.drawImage(img, 475, screenHeight - 105, 50, 50, null);
                break;
        }
    }

    public void actionSlot_P2(Graphics2D g2, BufferedImage img, int slot) {
        switch (slot) {
            case 0:
                g2.drawImage(img, screenWidth / 2 + 115, screenHeight - 105, 50, 50, null);
                break;
            case 1:
                g2.drawImage(img, screenWidth / 2 + 215, screenHeight - 105, 50, 50, null);
                break;
            case 2:
                g2.drawImage(img, screenWidth / 2 + 315, screenHeight - 105, 50, 50, null);
                break;
            case 3:
                g2.drawImage(img, screenWidth / 2 + 415, screenHeight - 105, 50, 50, null);
                break;
            case 4:
                g2.drawImage(img, screenWidth / 2 + 515, screenHeight - 105, 50, 50, null);
                break;
        }
    }

    public void healthBar(Graphics2D g2, int health, int x, int y) {
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, 220, 25);
        if (health > 75) {
            g2.setColor(Color.GREEN);
        } else if (health > 10 && health <= 75) {
            g2.setColor(Color.ORANGE);
        } else if (health <= 10) {
            g2.setColor(Color.RED);
        }
        g2.fillRect(x, y, (health + 10) * 2, 25);

        g2.setColor(Color.WHITE);
        g2.setFont(font_10);
        g2.drawString("Health: " + health + "/100", x + 40, y + 15);
    }

    // Style

    public int getTextCenter(Graphics2D g2, String text) {

        int text_length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = screenWidth / 2 - text_length / 2;
        return x;
    }

    public void setFontScale(Graphics2D g2, Font font) {
        int size = font.getSize();
        g2.setFont(font.deriveFont(font.getStyle(), (int) (size * scalingFactor))); // Use the original style and the
                                                                                    // scaled size
    }

}

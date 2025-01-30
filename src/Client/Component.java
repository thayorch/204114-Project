package client;

import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Component {
    public int screenWidth;
    public int screenHeight;
    public float scalingFactor;

    public String fontName = "Arial";
    public Font font_title = new Font(fontName, Font.PLAIN, 60);
    public Font font_40 = new Font(fontName, Font.PLAIN, 40);
    public Font font_30 = new Font(fontName, Font.PLAIN, 30);
    public Font font_20 = new Font(fontName, Font.PLAIN, 20);
    public Font font_10 = new Font(fontName, Font.PLAIN, 10);

    public Component(GamePanel gamePanel, GameState gameSate) {
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

    public void textBtn(Graphics2D g2, String txt, BufferedImage button, int y) {
        g2.setColor(Color.white);
        g2.drawImage(button, (screenWidth / 2) - (int)(77.5 * scalingFactor), y - (int)(35 * scalingFactor), (int)(145 * scalingFactor), (int)(50 * scalingFactor), null); // base value at 640x360
        this.setFontScale(g2, this.font_30);
        g2.drawString(txt, getTextCenter(g2, txt) - (int)(7.5 * scalingFactor), y);
    }

    // Style
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

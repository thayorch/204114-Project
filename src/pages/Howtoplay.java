package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.GamePanel;
import client.GameState;
import client.UI;
import client.Component;

public class Howtoplay {
    protected GamePanel gamePanel;
    protected GameState gameState;
    protected Component component;
    private BufferedImage[] howto = new BufferedImage[5];
    public static int pages = 0;

    public Howtoplay(GamePanel gamePanel, GameState gameState) {
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        this.component = new Component(gamePanel, gameState);
        loadAsset();
    }

    public void render(Graphics2D g2) {
        component.setBackground(g2, Lobby.background);
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        component.setBackground_1(g2, howto[pages]);
        component.howCenter(g2, "Enter to continue, Backspace to return", 700);
    }

    private void loadAsset() {
        try {
            howto[0] = component.img("/resources/howtoplay/howto1_rule.png");
            howto[1] = component.img("/resources/howtoplay/howto2_bullet.png");
            howto[2] = component.img("/resources/howtoplay/howto3_item.png");
            howto[3] = component.img("/resources/howtoplay/howto4_win.png");
            System.out.println("[log: Lobby Image loaded successfully]");
        } catch (IOException e) {
            System.out.println("[error: Failed to load background]");
        }
    }
}

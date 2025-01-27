package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.Component;
import client.GamePanel;
import client.GameState;

public class S_Item {

    protected GamePanel gamePanel;
    protected GameState gameState;
    protected Component component;
    private BufferedImage background;
    protected int screenWidth;
    protected int screenHeight;
    protected float scalingFactor;

    public S_Item(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        loadAsset();
    }

    public void render(Graphics2D g2) {
        component.titleCenter(g2, "Select Item", 100);
        component.setBackground(g2, background);

    }

    public void loadAsset() {
        try {
            background = component.img("/resources/background/Select_Item.png");
            System.out.println("[log: Background loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

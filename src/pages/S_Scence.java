package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.Component;
import client.GamePanel;
import client.GameState;

public class S_Scence {
    
    protected GamePanel gamePanel;
    protected GameState gameState;
    protected Component component;
    private BufferedImage background, card_1;
    protected int screenWidth;
    protected int screenHeight;
    protected float scalingFactor;

    public S_Scence(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        this.screenWidth = gamePanel.screenWidth;
        this.screenHeight = gamePanel.screenHeight;
        this.scalingFactor = gamePanel.scalingFactor;
        loadAsset();
    }

    public void render(Graphics2D g2) {
        component.titleCenter(g2, "Select Scence", 100);
        component.setBackground(g2, background);
        component.scenceCardleft(g2, "Scence L", card_1);
        component.scenceCardcenter(g2, "Scence C", card_1);
        component.scenceCardright(g2, "Scence R", card_1);


    }

    public void loadAsset() {
        try {
            background = component.img("/resources/background/Select_Scence.png");
            card_1 = component.img("/resources/scence/test.png");

            
            System.out.println("[log: Background loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

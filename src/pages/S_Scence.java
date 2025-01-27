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
    private BufferedImage background;
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
        
        // g2.setColor(Color.white);
        // component.setFontScale(g2, component.font_20);
        // String txt = "Hello World" ;
        // int x = component.getXCenter(g2, txt);
        // int y = (int) gamePanel.screenHeight / 3;
        // g2.drawString(txt, x, y);
    }

    public void loadAsset() {
        try {
            background = component.img("/resources/background/Select_Scence.png");
            System.out.println("[log: Background loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

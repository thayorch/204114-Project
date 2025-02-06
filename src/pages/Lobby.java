package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.Component;
import client.GamePanel;
import client.GameState;

public class Lobby {
    protected GamePanel gamePanel;
    protected GameState gameState;
    protected Component component;
    protected int screenWidth;
    protected int screenHeight;
    protected float scalingFactor;
    protected BufferedImage background, button;

    public Lobby(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        this.screenWidth = gamePanel.screenWidth;
        loadAsset();
    }

    public void render(Graphics2D g2) {
        gamePanel.setBackground(Color.white);
        component.setBackground(g2, background);
        component.titleCenter(g2, "Shadow Markmen", 150);
        component.textBtn(g2, "Start", button, 290);
        component.textBtn(g2, "Exit", button, 450);

        
        // component.textBtn(g2, "Option", button, 450);
        // component.textBtn(g2, "Exit", button, 593);

    }

    private void loadAsset() {
        try {
            background = component.img("/resources/lobby/lobby_bg.png");
            button = component.img("/resources/lobby/button.png");
            System.out.println("[log: Lobby Image loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }

}
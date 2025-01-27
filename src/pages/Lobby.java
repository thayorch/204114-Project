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
        loadAsset();
    }

    public void render(Graphics2D g2) {
        gamePanel.setBackground(Color.white);
        // component.setBackground(g2, background);
        component.titleCenter(g2, "GAME TITLE", 150);
        component.textBtn(g2, "START", button, 307);
        component.textBtn(g2, "OPTION", button, 450);
        component.textBtn(g2, "EXIT", button, 593);
    }

    public void loadAsset() {
        try {
            background = component.img("/resources/background/Lobby.png");
            button = component.img("/resources/lobby/button.png");
            System.out.println("[log: Background loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}
package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.Component;
import client.GamePanel;
import client.GameState;

public class Preview {

    protected GamePanel gamePanel;
    protected GameState gameState;
    protected Component component;
    private BufferedImage char_P1, char_P2;
    protected int screenWidth;
    protected int screenHeight;
    protected float scalingFactor;

    public Preview(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        loadAsset();
    }

    public void render(Graphics2D g2) {
        component.setBackground(g2, Lobby.background);
        component.titleCenter(g2, "Preview", 100);

        S_Char.animation.P1(g2, char_P1);
        S_Char.animation.P2(g2, char_P2);

    }

    public void loadAsset() {
        try {
            
            char_P1 = component.img("/resources/character/char"+ Integer.toString(gameState.player1.getCharacter()) + ".png");
            char_P2 = component.img("/resources/character/char"+ Integer.toString(gameState.player2.getCharacter()) + ".png");

            System.out.println("[log: Preview Image loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

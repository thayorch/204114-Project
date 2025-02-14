package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.Component;
import client.GamePanel;
import client.GameState;
import client.UI;
import entity.Dialog;

public class Preview {

    protected GamePanel gamePanel;
    protected GameState gameState;
    protected Component component;
    private BufferedImage char_P1, char_P2;
    protected int screenWidth;
    protected int screenHeight;
    protected float scalingFactor;
    private Dialog di;

    public Preview(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.di = new Dialog(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        loadAsset();
    }

    public void render(Graphics2D g2) {
        // component.setBackground(g2, Lobby.background);
        UI.animation.background(g2, Lobby.background);
        component.titleCenter(g2, "Preview", 100);

        UI.animation.P1(g2, char_P1);
        UI.animation.P2(g2, char_P2);

        if(gameState.player1.duelStatus)
          di.showDialog(g2, gameState.random_x);

        else if(gameState.player2.duelStatus)
          di.showDialog(g2, gameState.random_x);
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

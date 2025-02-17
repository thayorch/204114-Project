package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.Component;
import client.GamePanel;
import client.GameState;
import entity.Player;

public class Victory {

    protected GamePanel gamePanel;
    protected GameState gameState;
    protected Component component;
    protected Player player1;
    protected Player player2;
    private BufferedImage background;

    public Victory(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        player1 = gameState.player1;
        player2 = gameState.player2;
        loadAsset();
    }

    public void render(Graphics2D g2) {
        
        component.setBackground(g2, background);
        component.titleCenter(g2, "Victory", 100);

        if(player1.win && player2.win)
          component.titleCenter(g2, "Draw", 450);

        else if(player1.win)
          component.titleCenter(g2, "P1 Win", 450);

        else if(player2.win)
          component.titleCenter(g2, "P2 Win", 450);
    }

    public void loadAsset() {
        try {
            // background = component.img("/resources/background/Victory.png");
            background = component.img("/resources/lobby/lobby_bg.png");
            System.out.println("[log: Victory Image loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

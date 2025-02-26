package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.Component;
import client.GamePanel;
import client.GameState;
import client.UI;
import entity.Player;

public class Victory {

    protected GamePanel gamePanel;
    protected GameState gameState;
    protected Component component;
    protected Player player1;
    protected Player player2;
    private BufferedImage background,winkle;
    private BufferedImage[] vic = new BufferedImage[3];

    public Victory(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        player1 = gameState.player1;
        player2 = gameState.player2;
        loadAsset();
    }

    public void render(Graphics2D g2) {

        // component.setBackground(g2, background);
        // component.titleCenter(g2, "Victory", 100);

        if (player1.win && player2.win)
            component.titleCenter(g2, "Draw", 450);

        else if (player1.win) {
            // UI.animation.drawWin(g2, S_Item.char_P1[0]);
            component.setBackground(g2, vic[player1.getCharacter()-1]);
            UI.animation.drawWin(g2, winkle);
            component.titleCenter(g2, "P1 Win", 450);
        }

        else if (player2.win) {
            // UI.animation.drawWin(g2, S_Item.char_P2[0]);
            component.setBackground(g2, vic[player2.getCharacter()-1]);
            UI.animation.drawWin(g2, winkle);
            component.titleCenter(g2, "P2 Win", 450);
        }
    }

    public void loadAsset() {
        try {
            // background = component.img("/resources/background/Victory.png");
            vic[0] = component.img("/resources/victoryScene/1.png");
            vic[1] = component.img("/resources/victoryScene/2.png");
            vic[2] = component.img("/resources/victoryScene/3.png");
            background = component.img("/resources/lobby/lobby_bg.png");
            winkle = component.img("/resources/victoryScene/winkle.png");
            System.out.println("[log: Victory Image loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

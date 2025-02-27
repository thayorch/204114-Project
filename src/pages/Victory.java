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
    private BufferedImage background, winkle;
    private BufferedImage[] vic = new BufferedImage[3];
    private BufferedImage[] draw = new BufferedImage[3];

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
            component.setBackground(g2, draw[Lobby.background_num]);

        else if (player1.win) {
            component.setBackground(g2, vic[player1.getCharacter() - 1]);
            UI.animation.drawWin(g2, winkle);
            component.textWin(g2, "player1", 480);
        }

        else if (player2.win) {
            component.setBackground(g2, vic[player2.getCharacter() - 1]);
            UI.animation.drawWin(g2, winkle);
            component.textWin(g2, "player2", 480);
            // g2.drawString("player2", 640, 360);
        }
    }

    public void loadAsset() {
        try {
            // background = component.img("/resources/background/Victory.png");
            vic[0] = component.img("/resources/victoryScene/victory1.png");
            vic[1] = component.img("/resources/victoryScene/victory2.png");
            vic[2] = component.img("/resources/victoryScene/victory3.png");

            draw[0] = component.img("/resources/victoryScene/Draw1.png");
            draw[1] = component.img("/resources/victoryScene/Draw2.png");
            draw[2] = component.img("/resources/victoryScene/Draw3.png");
            background = component.img("/resources/lobby/lobby_bg.png");
            winkle = component.img("/resources/victoryScene/winkle.png");
            System.out.println("[log: Victory Image loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

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
    private BufferedImage background, chamber;
    protected int screenWidth;
    protected int screenHeight;
    protected float scalingFactor;

    Color P1_CHAM1 = Color.BLACK;
    Color P1_CHAM2 = Color.BLACK;
    Color P1_CHAM3 = Color.BLACK;
    Color P1_CHAM4 = Color.BLACK;
    Color P1_CHAM5 = Color.BLACK;
    Color P1_CHAM6 = Color.BLACK;
    
    Color P2_CHAM1 = Color.BLACK;
    Color P2_CHAM2 = Color.BLACK;
    Color P2_CHAM3 = Color.BLACK;
    Color P2_CHAM4 = Color.BLACK;
    Color P2_CHAM5 = Color.BLACK;
    Color P2_CHAM6 = Color.BLACK;

    public S_Item(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        this.screenWidth = gamePanel.screenWidth;
        this.screenHeight = gamePanel.screenHeight;
        loadAsset();
    }

    public void render(Graphics2D g2) {
        component.setBackground(g2, background);
        component.titleCenter(g2, "Select Item", 100);
        component.chamberLeft(g2, chamber);
        component.chamberRight(g2, chamber);

        P1_CHAMBER(g2);
        P2_CHAMBER(g2);

    }

    private void P1_CHAMBER(Graphics2D g2) {
        int[] barrel = gameState.player1.getGun_barrel();
        switch (GameState.currentSlot) {
            case 0:
                if (barrel[0] == 0)
                    P1_CHAM1 = Color.black;
                if (barrel[0] == 1)
                    P1_CHAM1 = Color.YELLOW;
                if (barrel[0] == 2)
                    P1_CHAM1 = Color.GREEN;
                if (barrel[0] == 3)
                    P1_CHAM1 = Color.RED;
                break;
            case 1:
                if (barrel[1] == 0)
                    P1_CHAM2 = Color.black;
                if (barrel[1] == 1)
                    P1_CHAM2 = Color.YELLOW;
                if (barrel[1] == 2)
                    P1_CHAM2 = Color.GREEN;
                if (barrel[1] == 3)
                    P1_CHAM2 = Color.RED;
                break;
            case 2:
                if (barrel[2] == 0)
                    P1_CHAM3 = Color.black;
                if (barrel[2] == 1)
                    P1_CHAM3 = Color.YELLOW;
                if (barrel[2] == 2)
                    P1_CHAM3 = Color.GREEN;
                if (barrel[2] == 3)
                    P1_CHAM3 = Color.RED;
                break;
            case 3:
                if (barrel[3] == 0)
                    P1_CHAM4 = Color.black;
                if (barrel[3] == 1)
                    P1_CHAM4 = Color.YELLOW;
                if (barrel[3] == 2)
                    P1_CHAM4 = Color.GREEN;
                if (barrel[3] == 3)
                    P1_CHAM4 = Color.RED;
                break;
            case 4:
                if (barrel[4] == 0)
                    P1_CHAM5 = Color.black;
                if (barrel[4] == 1)
                    P1_CHAM5 = Color.YELLOW;
                if (barrel[4] == 2)
                    P1_CHAM5 = Color.GREEN;
                if (barrel[4] == 3)
                    P1_CHAM5 = Color.RED;
                break;
            case 5:
                if (barrel[5] == 0)
                    P1_CHAM6 = Color.black;
                if (barrel[5] == 1)
                    P1_CHAM6 = Color.YELLOW;
                if (barrel[5] == 2)
                    P1_CHAM6 = Color.GREEN;
                if (barrel[5] == 3)
                    P1_CHAM6 = Color.RED;
                break;
        }
        component.Bullet(g2, 115, 119, P1_CHAM1);
        component.Bullet(g2, 142, 103, P1_CHAM2);
        component.Bullet(g2, 142, 67, P1_CHAM3);
        component.Bullet(g2, 115, 52, P1_CHAM4);
        component.Bullet(g2, 89, 68, P1_CHAM5);
        component.Bullet(g2, 87, 103, P1_CHAM6);
    }

    private void P2_CHAMBER(Graphics2D g2) {
        int[] barrel = gameState.player2.getGun_barrel();
        switch (GameState.currentSlot) {
            case 0:
                if (barrel[0] == 1)
                    P2_CHAM1 = Color.YELLOW;
                if (barrel[0] == 2)
                    P2_CHAM1 = Color.GREEN;
                if (barrel[0] == 3)
                    P2_CHAM1 = Color.RED;
                break;
            case 1:
                if (barrel[1] == 1)
                    P2_CHAM2 = Color.YELLOW;
                if (barrel[1] == 2)
                    P2_CHAM2 = Color.GREEN;
                if (barrel[1] == 3)
                    P2_CHAM2 = Color.RED;
                break;
            case 2:
                if (barrel[2] == 1)
                    P2_CHAM3 = Color.YELLOW;
                if (barrel[2] == 2)
                    P2_CHAM3 = Color.GREEN;
                if (barrel[2] == 3)
                    P2_CHAM3 = Color.RED;
                break;
            case 3:
                if (barrel[3] == 1)
                    P2_CHAM4 = Color.YELLOW;
                if (barrel[3] == 2)
                    P2_CHAM4 = Color.GREEN;
                if (barrel[3] == 3)
                    P2_CHAM4 = Color.RED;
                break;
            case 4:
                if (barrel[4] == 1)
                    P2_CHAM5 = Color.YELLOW;
                if (barrel[4] == 2)
                    P2_CHAM5 = Color.GREEN;
                if (barrel[4] == 3)
                    P2_CHAM5 = Color.RED;
                break;
            case 5:
                if (barrel[5] == 1)
                    P2_CHAM6 = Color.YELLOW;
                if (barrel[5] == 2)
                    P2_CHAM6 = Color.GREEN;
                if (barrel[5] == 3)
                    P2_CHAM6 = Color.RED;
                break;
        }
        component.Bullet(g2, 1145, 119, P2_CHAM1);
        component.Bullet(g2, 1172, 103, P2_CHAM2);
        component.Bullet(g2, 1172, 67, P2_CHAM3);
        component.Bullet(g2, 1145, 52, P2_CHAM4);
        component.Bullet(g2, 1119, 68, P2_CHAM5);
        component.Bullet(g2, 1117, 103, P2_CHAM6);
    }

    public void loadAsset() {
        try {
            // background = component.img("/resources/background/Select_Item.png");
            background = component.img("/resources/lobby/lobby_bg.png");
            chamber = component.img("/resources/assets/chamber.png");
            System.out.println("[log: Select Item Image loaded successfully]");
        } catch (IOException |

                NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

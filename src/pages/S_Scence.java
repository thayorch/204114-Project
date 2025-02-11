package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.Component;
import client.GamePanel;
import client.GameState;
import client.UI;

public class S_Scence {

    protected GamePanel gamePanel;
    protected GameState gameState;
    protected Component component;
    private BufferedImage card_1, card_2, card_3;
    protected int screenWidth;
    protected int screenHeight;
    protected float scalingFactor;
    public static String Map = "None";

    public S_Scence(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        this.screenWidth = GamePanel.screenWidth;
        this.screenHeight = GamePanel.screenHeight;
        this.scalingFactor = GamePanel.scalingFactor;
        loadAsset();
    }

    public void render(Graphics2D g2) {
        UI.animation.background(g2, Lobby.background);
        // component.setBackground(g2, Lobby.background);
        component.titleCenter(g2, "Select Scence", 150);
        

        switch (GameState.currentScence) {
            case 0:
                component.scenceCardleft(g2, "Scence L", card_2);
                component.scenceCardcenter(g2, "Scence C", card_1);
                component.scenceCardright(g2, "Scence R", card_3);
                Map = "Desert";
                break;
                
                case 1:
                component.scenceCardleft(g2, "Scence L", card_3);
                component.scenceCardcenter(g2, "Scence C", card_2);
                component.scenceCardright(g2, "Scence R", card_1);
                Map = "Doraemon";
                break;
                
                case 2:
                component.scenceCardleft(g2, "Scence L", card_1);
                component.scenceCardcenter(g2, "Scence C", card_3);
                component.scenceCardright(g2, "Scence R", card_2);
                Map = "Luffy";
                break;
        }
        component.title_25(g2, "Enter to continue...", 700);

    }

    public void loadAsset() {
        try {
            card_1 = component.img("/resources/scence/1.png");
            card_2 = component.img("/resources/scence/2.png");
            card_3 = component.img("/resources/scence/3.png");
            System.out.println("[log: Select Scence Image loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

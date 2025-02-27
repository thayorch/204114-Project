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
    private BufferedImage card_1, card_2, card_3, button;
    public static BufferedImage background[] = new BufferedImage[3];
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

    public void render(Graphics2D g2) throws IOException {

        UI.animation.drawBackground(g2, background[GameState.currentScence]);
        component.titleCenter_1(g2, "Select Map", 100, button);
        component.scenceCardleft(g2, "", card_1);
        component.scenceCardcenter(g2, "", card_2);
        component.scenceCardright(g2, "", card_3);

        switch (GameState.currentScence) {
            case 0:
                background[0] = component.img("/resources/background/SpritesheetBG1.png");
                component.scenceCardleft(g2, "", S_Item.frame2);
                Map = "A";
                break;
            case 1:
                background[1] = component.img("/resources/background/SpritesheetBG2.png");
                component.scenceCardcenter(g2, "", S_Item.frame2);
                Map = "B";
                break;
            case 2:
                background[2] = component.img("/resources/background/SpritesheetBG3.png");
                component.scenceCardright(g2, "", S_Item.frame2);
                Map = "C";
                break;
        }
        component.title_25(g2, "Enter to continue...", 700);

    }

    public void loadAsset() {
        try {
            button = component.img("/resources/lobby/button.png");
            card_1 = component.img("/resources/background/BG1_crop.png");
            card_2 = component.img("/resources/background/BG2_crop.png");
            card_3 = component.img("/resources/background/BG3_crop.png");

            System.out.println("[log: Select Scence Image loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

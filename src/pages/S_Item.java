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



    }

    public void loadAsset() {
        try {
            // background = component.img("/resources/background/Select_Item.png");
            background = component.img("/resources/lobby/lobby_bg.png");
            chamber = component.img("/resources/assets/chamber.png");
            System.out.println("[log: Select Item Image loaded successfully]");
        }catch(IOException|

    NullPointerException e)
    {
        e.printStackTrace();
        System.out.println("[error: Failed to load background]");
    }
}}

package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


import client.Component;
import client.GamePanel;
import client.GameState;
import client.UI;
import entity.Dialog;

public class Lobby {
    protected Component component;
    protected int screenWidth;
    protected int screenHeight;
    protected float scalingFactor;
    protected static BufferedImage background;
    private BufferedImage button;
    private Dialog di;
    

    public Lobby(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.screenWidth = GamePanel.screenWidth;
        this.di = new Dialog(gamePanel, gameState);
        loadAsset();
    }

    public void render(Graphics2D g2) {
        UI.animation.background(g2, background);
        component.titleCenter(g2, "Shadow Markmen", 150);
        component.textBtn(g2, "Start", button, 290);
        component.textBtn(g2, "Exit", button, 450);
        
        di.showDialog(g2);
        
        // component.setBackground(g2, background);
        // component.textBtn(g2, "Option", button, 450);
        // component.textBtn(g2, "Exit", button, 593);
    }

    private void loadAsset() {
        try {
            // background = component.img("/resources/lobby/lobby_bg.png");
            background = component.img("/resources/background/SpritesheetBG1.png");
            button = component.img("/resources/lobby/button.png");
            System.out.println("[log: Lobby Image loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }

}
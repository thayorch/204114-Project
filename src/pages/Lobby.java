package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.Component;
import client.GamePanel;
import client.GameState;

public class Lobby {
    protected Component component;
    protected int screenWidth;
    protected int screenHeight;
    protected float scalingFactor;
    public static BufferedImage background;
    private BufferedImage button, title;
    public static int background_num = 0;
    

    public Lobby(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.screenWidth = GamePanel.screenWidth;
        loadAsset();
    }

    public void render(Graphics2D g2) {
        component.setBackground(g2, background);
        component.setTitle(g2, title);
        component.textBtn(g2, "Start", button, 360);
        component.textBtn(g2, "Exit", button, 520);
        component.howCenter(g2, "How to play", 750);
    }

    private void loadAsset() {
        try {
            background = component.img("/resources/lobby/lobby_bg.png");
            title = component.img("/resources/lobby/title.png");
            button = component.img("/resources/lobby/button.png");
            System.out.println("[log: Lobby Image loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }

}

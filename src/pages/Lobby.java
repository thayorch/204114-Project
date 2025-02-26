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
    public static BufferedImage background;
    private BufferedImage button, title, bullet, shoot;
    public static int background_num = 0;
    private Dialog di;
    

    public Lobby(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.screenWidth = GamePanel.screenWidth;
        this.di = new Dialog(gamePanel, gameState);
        loadAsset();
    }

    public void render(Graphics2D g2) {
        // UI.animation.drawBackground(g2, background);
        
        component.setBackground(g2, background);
        component.setTitle(g2, title);
        // component.titleCenter(g2, "Shadow Markmen", 150);
        component.textBtn(g2, "Start", button, 290);
        component.textBtn(g2, "Exit", button, 450);
        component.howCenter(g2, "How to play", 750);
        // di.drawBullet1(g2);
        // di.drawBullet2(g2);
        // di.showDinalog(g2, 123);
        // component.textBtn(g2, "Option", button, 450);
        // component.textBtn(g2, "Exit", button, 593);

        // UI.animation.background(g2, shoot);


    }

    private void loadAsset() {
        try {
            background = component.img("/resources/lobby/lobby_bg.png");
            bullet = component.img("/resources/NBullet_ShootL.png");
            // background = component.img("/resources/background/SpritesheetBG3.png");
            title = component.img("/resources/lobby/title.png");
            button = component.img("/resources/lobby/button.png");
            shoot = component.img("/resources/previewScene/spritesheetBulletL.png");
            System.out.println("[log: Lobby Image loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }

}

package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.Component;
import client.GamePanel;
import client.GameState;
import client.UI;

public class S_Char {

    protected GamePanel gamePanel;
    protected GameState gameState;
    protected Component component;
    private BufferedImage card_1, card_2, card_3, character_1, character_2, character_3;
    protected int screenWidth;
    protected int screenHeight;
    protected float scalingFactor;
    public static int character=1;

    public S_Char(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        loadAsset();
    }

    public void render(Graphics2D g2) {
        UI.animation.background(g2, Lobby.background);
        // component.setBackground(g2, Lobby.background);
        component.titleCenter(g2, "Select Character", 100);

        switch (GameState.currentChar) {
            case 0:
                // component.scenceCardleft(g2, "L", card_2);
                UI.animation.leftCharacter(g2, character_2);

                component.scenceCardcenter(g2, "Select", card_1);
                UI.animation.centerCharacter(g2, character_1);

                // component.scenceCardright(g2, "R", card_3);
                UI.animation.rightCharacter(g2, character_3);
                character = 1;
                break;

            case 1:
                // component.scenceCardleft(g2, "L", card_3);
                UI.animation.leftCharacter(g2, character_3);

                component.scenceCardcenter(g2, "Select", card_2);
                UI.animation.centerCharacter(g2, character_2);

                // component.scenceCardright(g2, "R", card_1);
                UI.animation.rightCharacter(g2, character_1);
                character = 2;
                break;

            case 2:
                // component.scenceCardleft(g2, "L", card_1);
                UI.animation.leftCharacter(g2, character_1);

                component.scenceCardcenter(g2, "Select", card_3);
                UI.animation.centerCharacter(g2, character_3);

                // component.scenceCardright(g2, "R", card_2);
                UI.animation.rightCharacter(g2, character_2);
                character = 3;
                break;
        }

        

    }

    public void loadAsset() {
        try {
            character_1 = component.img("/resources/character/char1.png");
            character_2 = component.img("/resources/character/char2.png");
            character_3 = component.img("/resources/character/char3.png");

            card_1 = component.img("/resources/character/1.png");
            card_2 = component.img("/resources/character/2.png");
            card_3 = component.img("/resources/character/3.png");
            System.out.println("[log: Select Character Image loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

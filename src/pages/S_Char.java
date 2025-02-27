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
        private BufferedImage character_1, character_2, character_3;
        protected int screenWidth;
        protected int screenHeight;
        protected float scalingFactor;
        public static int character = 1;
        public static BufferedImage char_P1[] = new BufferedImage[9];
        public static BufferedImage char_P2[] = new BufferedImage[9];

        public S_Char(GamePanel gamePanel, GameState gameState) {
                this.component = new Component(gamePanel, gameState);
                this.gamePanel = gamePanel;
                this.gameState = gameState;
                loadAsset();
        }

        public void render(Graphics2D g2) throws IOException {
                try {
                        char_P1[0] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[1] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[2] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[3] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[4] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[5] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[6] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[7] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[8] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");

                        char_P2[0] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[1] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[2] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[3] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[4] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[5] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[6] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[7] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[8] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");

                } catch (Exception e) {
                        System.exit(0);
                }

                UI.animation.drawBackground(g2, S_Scence.background[Lobby.background_num]);
                component.titleCenter(g2, "Player " + (GameState.currentPlayer + 1), 100);
                UI.animation.drawLeftCharacter(g2, character_1);
                UI.animation.drawCenterCharacter(g2, character_2);
                UI.animation.drawRightCharacter(g2, character_3);

                switch (GameState.currentChar) {
                        case 0:
                                component.scenceCardleft(g2, "", S_Item.frame2);
                                character = 1;
                                break;

                        case 1:
                                component.scenceCardcenter(g2, "", S_Item.frame2);
                                character = 2;
                                break;

                        case 2:
                                component.scenceCardright(g2, "", S_Item.frame2);
                                character = 3;
                                break;
                }

        }

        public void loadAsset() {
                try {
                        character_1 = component.img("/resources/character/charL1.png");
                        character_2 = component.img("/resources/character/charL2.png");
                        character_3 = component.img("/resources/character/charL3.png");

                        char_P1[0] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[1] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[2] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[3] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[4] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[5] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[6] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[7] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");
                        char_P1[8] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player1.getCharacter()) + ".png");

                        char_P2[0] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[1] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[2] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[3] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[4] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[5] = component
                                        .img("/resources/character/charL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[6] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[7] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");
                        char_P2[8] = component
                                        .img("/resources/character/shootL"
                                                        + Integer.toString(gameState.player2.getCharacter()) + ".png");

                        System.out.println("[log: Select Character Image loaded successfully]");
                } catch (IOException | NullPointerException e) {
                        e.printStackTrace();
                        System.out.println("[error: Failed to load background]");
                }
        }
}

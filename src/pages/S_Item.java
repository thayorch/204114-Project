package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.Component;
import client.GamePanel;
import client.GameState;
import client.UI;
import entity.Player;

public class S_Item {

    protected GamePanel gamePanel;
    protected GameState gameState;
    protected Component component;
    protected int screenWidth;
    protected int screenHeight;
    protected float scalingFactor;

    public static BufferedImage char_P1[] = new BufferedImage[9];
    public static BufferedImage char_P2[] = new BufferedImage[9];
    protected static BufferedImage chamber, frame, construct;
    protected static BufferedImage shied, eva;
    protected static BufferedImage normal_bullet, death_bullet, h_bullet, s_bullet;
    public static BufferedImage P1_ACT1, P1_ACT2, P1_ACT3, P1_ACT4, P1_ACT5;
    public static BufferedImage P2_ACT1, P2_ACT2, P2_ACT3, P2_ACT4, P2_ACT5;
    public static BufferedImage bulletIncham[] = new BufferedImage[5];

    public static BufferedImage P1_CHAM1;
    public static BufferedImage P1_CHAM2;
    public static BufferedImage P1_CHAM3;
    public static BufferedImage P1_CHAM4;
    public static BufferedImage P1_CHAM5;
    public static BufferedImage P1_CHAM6;

    public static BufferedImage P2_CHAM1;
    public static BufferedImage P2_CHAM2;
    public static BufferedImage P2_CHAM3;
    public static BufferedImage P2_CHAM4;
    public static BufferedImage P2_CHAM5;
    public static BufferedImage P2_CHAM6;

    public static void resetItem() {
        P1_ACT1 = null;
        P1_ACT2 = null;
        P1_ACT3 = null;
        P1_ACT4 = null;
        P1_ACT5 = null;

        P2_ACT1 = null;
        P2_ACT2 = null;
        P2_ACT3 = null;
        P2_ACT4 = null;
        P2_ACT5 = null;

        P1_CHAM1 = null;
        P1_CHAM2 = null;
        P1_CHAM3 = null;
        P1_CHAM4 = null;
        P1_CHAM5 = null;
        P1_CHAM6 = null;

        P2_CHAM1 = null;
        P2_CHAM2 = null;
        P2_CHAM3 = null;
        P2_CHAM4 = null;
        P2_CHAM5 = null;
        P2_CHAM6 = null;
    }

    public S_Item(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        this.screenWidth = GamePanel.screenWidth;
        this.screenHeight = GamePanel.screenHeight;
        loadAsset();
    }

    public void render(Graphics2D g2) throws IOException {
        char_P1 = S_Char.char_P1;
        char_P2 = S_Char.char_P2;

        UI.animation.drawBackground(g2, S_Scence.background[Lobby.background_num]);
        component.setBackground(g2, construct);
        component.titleCenter(g2, "Round " + GameState.currentRound, 100);

        component.text_10(g2, "" + gameState.player1.getNormalBullet(), 95, 245);
        component.text_10(g2, "" + gameState.player1.getMagicBullet(), 95, 285);
        component.text_10(g2, "" + gameState.player1.getSilverBullet(), 95, 325);
        component.text_10(g2, "" + gameState.player1.getDeathBullet(), 95, 365);
        component.text_10(g2, ""+ gameState.player1.getBlock(), 135, 610);
        component.text_10(g2, ""+ gameState.player1.getEvade(), 230, 610);
        
        
        component.text_10(g2, "" + gameState.player2.getNormalBullet(), screenWidth-115, 245);
        component.text_10(g2, "" + gameState.player2.getMagicBullet(), screenWidth-115, 285);
        component.text_10(g2, "" + gameState.player2.getSilverBullet(), screenWidth-115, 325);
        component.text_10(g2, "" + gameState.player2.getDeathBullet(), screenWidth-115, 365);
        component.text_10(g2, ""+ gameState.player2.getBlock(), screenWidth-135, 610);
        component.text_10(g2, ""+ gameState.player2.getEvade(), screenWidth-40, 610);


        component.healthBar(g2, gameState.player1.getHealth(), 145, 65);
        component.healthBar(g2, gameState.player2.getHealth(), GamePanel.screenWidth / 2 + 276, 65);

        P1_CHAMBER(g2);
        P2_CHAMBER(g2);

        P1_ACTION(g2);
        P2_ACTION(g2);

        if (GameState.currentType == 0 && GameState.currentPlayer == GameState.PLAYER1) {
            component.chamberSlot_P1(g2, frame, GameState.currentSlot);
        }
        if (GameState.currentType == 0 && GameState.currentPlayer == GameState.PLAYER2) {
            component.chamberSlot_P2(g2, frame, GameState.currentSlot);
        }
        if (GameState.currentType == 1 && GameState.currentPlayer == GameState.PLAYER1) {
            component.actionSlot_P1(g2, frame, GameState.currentSlot);
        }
        if (GameState.currentType == 1 && GameState.currentPlayer == GameState.PLAYER2) {
            component.actionSlot_P2(g2, frame, GameState.currentSlot);
        }

        UI.animation.drawP1(g2, char_P1[0]);
        UI.animation.drawP2(g2, char_P2[0]);
    }

    protected void P1_ACTION(Graphics2D g2) {
        int[] action = gameState.player1.getPlayer_actions();
        switch (GameState.currentSlot) {
            case 0:
                if (action[0] == Player.ACTION_NONE)
                    P1_ACT1 = null;
                if (action[0] == Player.ACTION_SHOOT_L || action[0] == Player.ACTION_SHOOT_M
                        || action[0] == Player.ACTION_SHOOT_R)
                    P1_ACT1 = normal_bullet;
                if (action[0] == Player.ACTION_BLOCK)
                    P1_ACT1 = shied;
                if (action[0] == Player.ACTION_EVADE || action[0] == Player.ACTION_EVADE_L
                        || action[0] == Player.ACTION_EVADE_R)
                    P1_ACT1 = eva;
                break;
            case 1:
                if (action[2] == Player.ACTION_NONE)
                    P1_ACT2 = null;
                if (action[1] == Player.ACTION_SHOOT_L || action[1] == Player.ACTION_SHOOT_M
                        || action[1] == Player.ACTION_SHOOT_R)
                    P1_ACT2 = normal_bullet;
                if (action[1] == Player.ACTION_BLOCK)
                    P1_ACT2 = shied;
                if (action[1] == Player.ACTION_EVADE || action[1] == Player.ACTION_EVADE_L
                        || action[1] == Player.ACTION_EVADE_R)
                    P1_ACT2 = eva;
                break;
            case 2:
                if (action[2] == Player.ACTION_NONE)
                    P1_ACT3 = null;
                if (action[2] == Player.ACTION_SHOOT_L || action[2] == Player.ACTION_SHOOT_M
                        || action[2] == Player.ACTION_SHOOT_R)
                    P1_ACT3 = normal_bullet;
                if (action[2] == Player.ACTION_BLOCK)
                    P1_ACT3 = shied;
                if (action[2] == Player.ACTION_EVADE || action[2] == Player.ACTION_EVADE_L
                        || action[2] == Player.ACTION_EVADE_R)
                    P1_ACT3 = eva;
                break;
            case 3:
                if (action[3] == Player.ACTION_NONE)
                    P1_ACT4 = null;
                if (action[3] == Player.ACTION_SHOOT_L || action[3] == Player.ACTION_SHOOT_M
                        || action[3] == Player.ACTION_SHOOT_R)
                    P1_ACT4 = normal_bullet;
                if (action[3] == Player.ACTION_BLOCK)
                    P1_ACT4 = shied;
                if (action[3] == Player.ACTION_EVADE || action[3] == Player.ACTION_EVADE_L
                        || action[3] == Player.ACTION_EVADE_R)
                    P1_ACT4 = eva;
                break;
            case 4:
                if (action[4] == Player.ACTION_NONE)
                    P1_ACT5 = null;
                if (action[4] == Player.ACTION_SHOOT_L || action[4] == Player.ACTION_SHOOT_M
                        || action[4] == Player.ACTION_SHOOT_R)
                    P1_ACT5 = normal_bullet;
                if (action[4] == Player.ACTION_BLOCK)
                    P1_ACT5 = shied;
                if (action[4] == Player.ACTION_EVADE || action[4] == Player.ACTION_EVADE_L
                        || action[4] == Player.ACTION_EVADE_R)
                    P1_ACT5 = eva;
                break;

        }
        component.actionSlot_P1(g2, P1_ACT1, 0);
        component.actionSlot_P1(g2, P1_ACT2, 1);
        component.actionSlot_P1(g2, P1_ACT3, 2);
        component.actionSlot_P1(g2, P1_ACT4, 3);
        component.actionSlot_P1(g2, P1_ACT5, 4);
    }

    protected void P2_ACTION(Graphics2D g2) {
        int[] action = gameState.player2.getPlayer_actions();
        switch (GameState.currentSlot) {
            case 0:
                if (action[0] == Player.ACTION_NONE)
                    P2_ACT1 = null;
                if (action[0] == Player.ACTION_SHOOT_L || action[0] == Player.ACTION_SHOOT_M
                        || action[0] == Player.ACTION_SHOOT_R)
                    P2_ACT1 = normal_bullet;
                if (action[0] == Player.ACTION_BLOCK)
                    P2_ACT1 = shied;
                if (action[0] == Player.ACTION_EVADE || action[0] == Player.ACTION_EVADE_L
                        || action[0] == Player.ACTION_EVADE_R)
                    P2_ACT1 = eva;
                break;
            case 1:
                if (action[2] == Player.ACTION_NONE)
                    P2_ACT2 = null;
                if (action[1] == Player.ACTION_SHOOT_L || action[1] == Player.ACTION_SHOOT_M
                        || action[1] == Player.ACTION_SHOOT_R)
                    P2_ACT2 = normal_bullet;
                if (action[1] == Player.ACTION_BLOCK)
                    P2_ACT2 = shied;
                if (action[1] == Player.ACTION_EVADE || action[1] == Player.ACTION_EVADE_L
                        || action[1] == Player.ACTION_EVADE_R)
                    P2_ACT2 = eva;
                break;
            case 2:
                if (action[2] == Player.ACTION_NONE)
                    P2_ACT3 = null;
                if (action[2] == Player.ACTION_SHOOT_L || action[2] == Player.ACTION_SHOOT_M
                        || action[2] == Player.ACTION_SHOOT_R)
                    P2_ACT3 = normal_bullet;
                if (action[2] == Player.ACTION_BLOCK)
                    P2_ACT3 = shied;
                if (action[2] == Player.ACTION_EVADE || action[2] == Player.ACTION_EVADE_L
                        || action[2] == Player.ACTION_EVADE_R)
                    P2_ACT3 = eva;
                break;
            case 3:
                if (action[3] == Player.ACTION_NONE)
                    P2_ACT4 = null;
                if (action[3] == Player.ACTION_SHOOT_L || action[3] == Player.ACTION_SHOOT_M
                        || action[3] == Player.ACTION_SHOOT_R)
                    P2_ACT4 = normal_bullet;
                if (action[3] == Player.ACTION_BLOCK)
                    P2_ACT4 = shied;
                if (action[3] == Player.ACTION_EVADE || action[3] == Player.ACTION_EVADE_L
                        || action[3] == Player.ACTION_EVADE_R)
                    P2_ACT4 = eva;
                break;
            case 4:
                if (action[4] == Player.ACTION_NONE)
                    P2_ACT5 = null;
                if (action[4] == Player.ACTION_SHOOT_L || action[4] == Player.ACTION_SHOOT_M
                        || action[4] == Player.ACTION_SHOOT_R)
                    P2_ACT5 = normal_bullet;
                if (action[4] == Player.ACTION_BLOCK)
                    P2_ACT5 = shied;
                if (action[4] == Player.ACTION_EVADE || action[4] == Player.ACTION_EVADE_L
                        || action[4] == Player.ACTION_EVADE_R)
                    P2_ACT5 = eva;
                break;

        }
        component.actionSlot_P2(g2, P2_ACT1, 0);
        component.actionSlot_P2(g2, P2_ACT2, 1);
        component.actionSlot_P2(g2, P2_ACT3, 2);
        component.actionSlot_P2(g2, P2_ACT4, 3);
        component.actionSlot_P2(g2, P2_ACT5, 4);
    }

    protected void P1_CHAMBER(Graphics2D g2) {
        int[] barrel = gameState.player1.getGun_barrel();
        switch (GameState.currentSlot) {
            case 0:
                P1_CHAM1 = bulletIncham[barrel[GameState.currentSlot]];
                break;
            case 1:
                P1_CHAM2 = bulletIncham[barrel[GameState.currentSlot]];
                break;
            case 2:
                P1_CHAM3 = bulletIncham[barrel[GameState.currentSlot]];
                break;
            case 3:
                P1_CHAM4 = bulletIncham[barrel[GameState.currentSlot]];
                break;
            case 4:
                P1_CHAM5 = bulletIncham[barrel[GameState.currentSlot]];
                break;
            case 5:
                P1_CHAM6 = bulletIncham[barrel[GameState.currentSlot]];
                break;
        }

        component.Bullet(g2, 148, 320, P1_CHAM1);
        component.Bullet(g2, 161, 276, P1_CHAM2);
        component.Bullet(g2, 127, 238, P1_CHAM3);
        component.Bullet(g2, 80, 252, P1_CHAM4);
        component.Bullet(g2, 70, 297, P1_CHAM5);
        component.Bullet(g2, 99, 328, P1_CHAM6);

    }

    protected void P2_CHAMBER(Graphics2D g2) {
        int[] barrel = gameState.player2.getGun_barrel();
        switch (GameState.currentSlot) {
            case 0:
                P2_CHAM1 = bulletIncham[barrel[GameState.currentSlot]];
                break;
            case 1:
                P2_CHAM2 = bulletIncham[barrel[GameState.currentSlot]];
                break;
            case 2:
                P2_CHAM3 = bulletIncham[barrel[GameState.currentSlot]];
                break;
            case 3:
                P2_CHAM4 = bulletIncham[barrel[GameState.currentSlot]];
                break;
            case 4:
                P2_CHAM5 = bulletIncham[barrel[GameState.currentSlot]];
                break;
            case 5:
                P2_CHAM6 = bulletIncham[barrel[GameState.currentSlot]];
                break;
        }

        component.Bullet(g2, 1141, 328, P2_CHAM1);
        component.Bullet(g2, 1172, 296, P2_CHAM2);
        component.Bullet(g2, 1162, 254, P2_CHAM3);
        component.Bullet(g2, 1115, 239, P2_CHAM4);
        component.Bullet(g2, 1080, 277, P2_CHAM5);
        component.Bullet(g2, 1095, 320, P2_CHAM6);
    }

    public void loadAsset() {
        try {
            // ACTION_NONE = 0;
            // ACTION_SHOOT = 1;
            // ACTION_BLOCK = 2;
            // ACTION_EVADE = 3;
            // ACTION_EVADE_L = 4;
            // ACTION_EVADE_R = 5;
            // ACTION_SHOOT_L = 6;
            // ACTION_SHOOT_M = 7;
            // ACTION_SHOOT_R = 8;

            char_P1[0] = component
                    .img("/resources/character/charL" + Integer.toString(gameState.player1.getCharacter()) + ".png");
            char_P1[1] = component
                    .img("/resources/character/shootL" + Integer.toString(gameState.player1.getCharacter()) + ".png");
            char_P1[2] = component
                    .img("/resources/character/charL" + Integer.toString(gameState.player1.getCharacter()) + ".png");
            char_P1[3] = component
                    .img("/resources/character/charL" + Integer.toString(gameState.player1.getCharacter()) + ".png");
            char_P1[4] = component
                    .img("/resources/character/charL" + Integer.toString(gameState.player1.getCharacter()) + ".png");
            char_P1[5] = component
                    .img("/resources/character/charL" + Integer.toString(gameState.player1.getCharacter()) + ".png");
            char_P1[6] = component
                    .img("/resources/character/shootL" + Integer.toString(gameState.player1.getCharacter()) + ".png");
            char_P1[7] = component
                    .img("/resources/character/shootL" + Integer.toString(gameState.player1.getCharacter()) + ".png");
            char_P1[8] = component
                    .img("/resources/character/shootL" + Integer.toString(gameState.player1.getCharacter()) + ".png");

            char_P2[0] = component
                    .img("/resources/character/charL" + Integer.toString(gameState.player2.getCharacter()) + ".png");
            char_P2[1] = component
                    .img("/resources/character/shootL" + Integer.toString(gameState.player2.getCharacter()) + ".png");
            char_P2[2] = component
                    .img("/resources/character/charL" + Integer.toString(gameState.player2.getCharacter()) + ".png");
            char_P2[3] = component
                    .img("/resources/character/charL" + Integer.toString(gameState.player2.getCharacter()) + ".png");
            char_P2[4] = component
                    .img("/resources/character/charL" + Integer.toString(gameState.player2.getCharacter()) + ".png");
            char_P2[5] = component
                    .img("/resources/character/charL" + Integer.toString(gameState.player2.getCharacter()) + ".png");
            char_P2[6] = component
                    .img("/resources/character/shootL" + Integer.toString(gameState.player2.getCharacter()) + ".png");
            char_P2[7] = component
                    .img("/resources/character/shootL" + Integer.toString(gameState.player2.getCharacter()) + ".png");
            char_P2[8] = component
                    .img("/resources/character/shootL" + Integer.toString(gameState.player2.getCharacter()) + ".png");

            normal_bullet = component.img("/resources/assets/bullet/NbulletM.png");
            death_bullet = component.img("/resources/assets/bullet/DbulletM.png");
            h_bullet = component.img("/resources/assets/bullet/HbulletM.png");
            s_bullet = component.img("/resources/assets/bullet/SbulletM.png");
            construct = component.img("/resources/previewScene/PreviewPageNew.png");

            bulletIncham[1] = component.img("/resources/assets/bullet/NCircleBullet.png");
            bulletIncham[2] = component.img("/resources/assets/bullet/HCircleBullet.png");
            bulletIncham[3] = component.img("/resources/assets/bullet/SCircleBullet.png");
            bulletIncham[4] = component.img("/resources/assets/bullet/DCircleBullet.png");

            frame = component.img("/resources/assets/frame.png");
            shied = component.img("/resources/assets/shield/shield-slot.png");
            eva = component.img("/resources/assets/evade/evadeL.png");

            System.out.println("[log: Select Item Image loaded successfully]");
        } catch (IOException |

                NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

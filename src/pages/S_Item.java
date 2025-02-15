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
    private BufferedImage char_P1, char_P2;
    private BufferedImage chamber, frame;
    private BufferedImage shied, eva;
    private BufferedImage bullet1;
    private BufferedImage bullet2;
    protected int screenWidth;
    protected int screenHeight;
    protected float scalingFactor;
    public static BufferedImage P1_ACT1, P1_ACT2, P1_ACT3, P1_ACT4, P1_ACT5;
    public static BufferedImage P2_ACT1, P2_ACT2, P2_ACT3, P2_ACT4, P2_ACT5;

    public static Color P1_CHAM1 = Color.BLACK;
    public static Color P1_CHAM2 = Color.BLACK;
    public static Color P1_CHAM3 = Color.BLACK;
    public static Color P1_CHAM4 = Color.BLACK;
    public static Color P1_CHAM5 = Color.BLACK;
    public static Color P1_CHAM6 = Color.BLACK;

    public static  Color P2_CHAM1 = Color.BLACK;
    public static  Color P2_CHAM2 = Color.BLACK;
    public static  Color P2_CHAM3 = Color.BLACK;
    public static  Color P2_CHAM4 = Color.BLACK;
    public static  Color P2_CHAM5 = Color.BLACK;
    public static  Color P2_CHAM6 = Color.BLACK;

    public static void resetItem(){
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


        P1_CHAM1 = Color.BLACK;
        P1_CHAM2 = Color.BLACK;
        P1_CHAM3 = Color.BLACK;
        P1_CHAM4 = Color.BLACK;
        P1_CHAM5 = Color.BLACK;
        P1_CHAM6 = Color.BLACK;

        P2_CHAM1 = Color.BLACK;
        P2_CHAM2 = Color.BLACK;
        P2_CHAM3 = Color.BLACK;
        P2_CHAM4 = Color.BLACK;
        P2_CHAM5 = Color.BLACK;
        P2_CHAM6 = Color.BLACK;
    }

    public S_Item(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        this.screenWidth = GamePanel.screenWidth;
        this.screenHeight = GamePanel.screenHeight;
        loadAsset();
    }
    

    public void render(Graphics2D g2) {




        UI.animation.background(g2, Lobby.background);
        component.titleCenter(g2, "Select Item", 100);
        component.chamberP1(g2, chamber);
        component.chamberP2(g2, chamber);
        
        component.healthBar(g2, gameState.player1.getHealth());

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
        
        try{
            char_P1 = component.img("/resources/character/char"+ Integer.toString(gameState.player1.getCharacter()) + ".png");
            char_P2 = component.img("/resources/character/char"+ Integer.toString(gameState.player2.getCharacter()) + ".png");
        }
        catch (IOException e){
            e.printStackTrace();
        }

        UI.animation.P1(g2, char_P1);
        UI.animation.P2(g2, char_P2);
        
        
        
        
        // component.setBackground(g2, background);
    }

    private void P1_ACTION(Graphics2D g2) {
        int[] action = gameState.player1.getPlayer_actions();
        switch (GameState.currentSlot) {
            case 0:
                if (action[0] == Player.ACTION_NONE)
                    P1_ACT1 = null;
                if (action[0] == Player.ACTION_SHOOT_L || action[0] == Player.ACTION_SHOOT_M
                        || action[0] == Player.ACTION_SHOOT_R)
                    P1_ACT1 = bullet2;
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
                    P1_ACT2 = bullet2;
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
                    P1_ACT3 = bullet2;
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
                    P1_ACT4 = bullet2;
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
                    P1_ACT5 = bullet2;
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

    private void P2_ACTION(Graphics2D g2) {
        int[] action = gameState.player2.getPlayer_actions();
        switch (GameState.currentSlot) {
            case 0:
                if (action[0] == Player.ACTION_NONE)
                    P2_ACT1 = null;
                if (action[0] == Player.ACTION_SHOOT_L || action[0] == Player.ACTION_SHOOT_M
                        || action[0] == Player.ACTION_SHOOT_R)
                    P2_ACT1 = bullet2;
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
                    P2_ACT2 = bullet2;
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
                    P2_ACT3 = bullet2;
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
                    P2_ACT4 = bullet2;
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
                    P2_ACT5 = bullet2;
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
                if (barrel[0] == 0)
                    P2_CHAM1 = Color.black;
                if (barrel[0] == 1)
                    P2_CHAM1 = Color.YELLOW;
                if (barrel[0] == 2)
                    P2_CHAM1 = Color.GREEN;
                if (barrel[0] == 3)
                    P2_CHAM1 = Color.RED;
                break;
            case 1:
                if (barrel[1] == 0)
                    P2_CHAM2 = Color.black;
                if (barrel[1] == 1)
                    P2_CHAM2 = Color.YELLOW;
                if (barrel[1] == 2)
                    P2_CHAM2 = Color.GREEN;
                if (barrel[1] == 3)
                    P2_CHAM2 = Color.RED;
                break;
            case 2:
                if (barrel[2] == 0)
                    P2_CHAM3 = Color.black;
                if (barrel[2] == 1)
                    P2_CHAM3 = Color.YELLOW;
                if (barrel[2] == 2)
                    P2_CHAM3 = Color.GREEN;
                if (barrel[2] == 3)
                    P2_CHAM3 = Color.RED;
                break;
            case 3:
                if (barrel[3] == 0)
                    P2_CHAM4 = Color.black;
                if (barrel[3] == 1)
                    P2_CHAM4 = Color.YELLOW;
                if (barrel[3] == 2)
                    P2_CHAM4 = Color.GREEN;
                if (barrel[3] == 3)
                    P2_CHAM4 = Color.RED;
                break;
            case 4:
                if (barrel[4] == 0)
                    P2_CHAM5 = Color.black;
                if (barrel[4] == 1)
                    P2_CHAM5 = Color.YELLOW;
                if (barrel[4] == 2)
                    P2_CHAM5 = Color.GREEN;
                if (barrel[4] == 3)
                    P2_CHAM5 = Color.RED;
                break;
            case 5:
                if (barrel[5] == 0)
                    P2_CHAM6 = Color.black;
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
            chamber = component.img("/resources/assets/chamber.png");
            bullet1 = component.img("/resources/assets/b1.png");
            bullet2 = component.img("/resources/assets/b2.png");
            frame = component.img("/resources/assets/frame.png");
            shied = component.img("/resources/assets/shied.png");
            eva = component.img("/resources/assets/eva.png");


            char_P1 = component.img("/resources/character/char"+ Integer.toString(gameState.player1.getCharacter()) + ".png");
            char_P2 = component.img("/resources/character/char"+ Integer.toString(gameState.player2.getCharacter()) + ".png");

            System.out.println("[log: Select Item Image loaded successfully]");
        } catch (IOException |

                NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

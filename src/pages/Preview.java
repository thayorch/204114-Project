package pages;

import client.Component;
import client.GamePanel;
import client.GameState;
import client.UI;
import entity.Animation;
import entity.Dialog;
import entity.Player;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Preview {
    protected GamePanel gamePanel;
    protected GameState gameState;
    protected Component component;
    private BufferedImage dmg, shoot, shield_L, shield_R, button;
    private BufferedImage char_P1[];
    private BufferedImage char_P2[];
    protected int screenWidth;
    protected int screenHeight;
    protected float scalingFactor;
    private Dialog di;
    private boolean successDI;

    public Preview(GamePanel gamePanel, GameState gameState) {
        this.component = new Component(gamePanel, gameState);
        this.di = new Dialog(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        loadAsset();
    }

    public void render(Graphics2D g2) throws InterruptedException {
        char_P1 = S_Item.char_P1;
        char_P2 = S_Item.char_P2;

        UI.animation.drawLeftCharacter(g2, shield_L);

        System.out.println(gameState.player1.getPlayer_actions()[GameState.currentSlot]);
        UI.animation.drawBackground(g2, S_Scence.background[Lobby.background_num]);
        component.setBackground(g2, S_Item.construct);
        component.titleCenter_Round(g2, "Round " + GameState.currentRound, 90);

        component.healthBar(g2, gameState.player1.getHealth(), 145, 65);
        component.healthBar(g2, gameState.player2.getHealth(), GamePanel.screenWidth / 2 + 276, 65);

        UI.animation.drawP1(g2, char_P1[gameState.player1.getPlayer_actions()[GameState.currentSlot]]);
        UI.animation.drawP2(g2, char_P2[gameState.player2.getPlayer_actions()[GameState.currentSlot]]);
        component.setBackground(g2, S_Item.closeSlot[1]);
        component.setBackground(g2, S_Item.closeSlot[0]);

        if (gameState.player1.duelStatus) {
            component.PlayerCenter(g2, "Player 1", 150, button);
            di.showDialog(g2, GameState.random_x);
        } else if (gameState.player2.duelStatus) {
            component.PlayerCenter(g2, "Player 2", 150, button);
            di.showDialog(g2, GameState.random_x);
            successDI = true;

        } else {
            if (successDI) {
                if (gameState.player2.QTE(gameState.player1)) {
                    UI.animation.drawBullet(g2, shoot, false);
                    UI.animation.drawRightCharacter(g2, dmg);
                    successDI = false;
                } else {
                    UI.animation.drawBullet(g2, shoot, true);
                    UI.animation.drawLeftCharacter(g2, dmg);
                    successDI = false;
                }
            }

            if (gameState.player1.magicStatus) {
                component.dmgP1(g2, "-1 Sheild -1 Eva");
            }
            if (gameState.player2.magicStatus) {
                component.dmgP2(g2, "-1 Sheild -1 Eva");
            }
            if (gameState.player1.getPlayer_actions()[GameState.currentSlot] == Player.ACTION_SHOOT_M ||
                    gameState.player1.getPlayer_actions()[GameState.currentSlot] == Player.ACTION_SHOOT_L ||
                    gameState.player1.getPlayer_actions()[GameState.currentSlot] == Player.ACTION_SHOOT_R) {
                UI.animation.drawBullet(g2, shoot, false);
                UI.animation.drawRightCharacter(g2, dmg);
            }
            if (gameState.player2.getPlayer_actions()[GameState.currentSlot] == Player.ACTION_SHOOT_M ||
                    gameState.player2.getPlayer_actions()[GameState.currentSlot] == Player.ACTION_SHOOT_L ||
                    gameState.player2.getPlayer_actions()[GameState.currentSlot] == Player.ACTION_SHOOT_R) {
                UI.animation.drawBullet(g2, shoot, true);
                UI.animation.drawLeftCharacter(g2, dmg);
            }
            if (gameState.player1.getPlayer_actions()[GameState.currentSlot] == Player.ACTION_BLOCK) {
                component.drawShield(g2, shield_L, 280, 300);
            }
            if (gameState.player2.getPlayer_actions()[GameState.currentSlot] == Player.ACTION_BLOCK) {
                component.drawShield(g2, shield_R, 790, 300);
            }

            if (gameState.player1.getPlayer_actions()[GameState.currentSlot] == Player.ACTION_EVADE_L) {
                for (int i = 1; i < 50; i++) {
                    Animation.p1_x--;
                }
            }
            if (gameState.player1.getPlayer_actions()[GameState.currentSlot] == Player.ACTION_EVADE_R) {
                for (int i = 1; i < 50; i++) {
                    Animation.p1_x++;
                }
            }

            if (gameState.player2.getPlayer_actions()[GameState.currentSlot] == Player.ACTION_EVADE_L) {
                for (int i = 1; i < 50; i++) {
                    Animation.p2_x--;
                }
            }
            if (gameState.player2.getPlayer_actions()[GameState.currentSlot] == Player.ACTION_EVADE_R) {
                for (int i = 1; i < 50; i++) {
                    Animation.p2_x++;
                }
            }

            Thread.sleep(200);
        }
        if (GameState.currentSlot < 4) {
            GameState.currentSlot++;
            GameState.in_animation = true;
        } else {
            GameState.in_animation = false;
        }
        Thread.sleep(10);
    }

    public void loadAsset() {
        try {
            dmg = component.img("/resources/previewScene/dmg.png");
            shoot = component.img("/resources/previewScene/spritesheetBulletL.png");
            shield_L = component.img("/resources/previewScene/shieldL.png");
            shield_R = component.img("/resources/previewScene/shieldR.png");
            button = component.img("/resources/lobby/button.png");
            System.out.println("[log: Preview Image loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
        }
    }
}

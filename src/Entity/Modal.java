package entity;

import javax.swing.*;

import client.GamePanel;
import client.GameState;
import pages.S_Char;
import pages.S_Scence;
import client.Component;

import java.awt.*;

public class Modal extends JPanel {
    private long lastTimeCheck;
    private int frameCount;
    private int fps;
    private GamePanel gamePanel;
    private GameState gameState;
    private Component component;

    public Modal(GamePanel gamePanel, GameState gameState) {
        lastTimeCheck = System.currentTimeMillis();
        this.component = new Component(gamePanel, gameState);
        this.gamePanel = gamePanel;
        this.gameState = gameState;
    }

    // @Override
    public void log(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // frameCount++;
        // long currentTime = System.currentTimeMillis();
        // if (currentTime - lastTimeCheck >= 1000) {
        //     fps = frameCount;
        //     frameCount = 0;
        //     lastTimeCheck = currentTime;
        // }
        
        // // Set text color
        // g2.setColor(Color.WHITE);
        // g2.setFont(new Font("Consolas", Font.BOLD, 14));
        
        // int y = 30;
        // g2.drawString("Debug Screen - Java", 20, y);
        // g2.drawString("FPS: " + fps, 20, y += 20);
        // g2.drawString("Memory Usage: " + getMemoryUsage() + " MB", 20, y += 20);
        // g2.drawString("Screen Size: " + gamePanel.screenWidth + "x" + gamePanel.screenHeight, 20, y += 20);
        // g2.drawString("Java Version: " + System.getProperty("java.version"), 20, y += 20);

        // // Draw semi-transparent black background for the debug panel
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        component.text_10_alginCenter(g2, "Map : "+ S_Scence.Map, 10,20);
        component.text_10_alginCenter(g2, "Character  : "+ S_Char.character, 10,40);
        
        // characterLog(g2);
        playerLog(g2);


    }

    private void playerLog(Graphics2D g2) {
        final int P1_ID = gameState.player1.getID();
        final int P2_ID = gameState.player2.getID();
        final int P1_CHAR = gameState.player1.getCharacter();
        final int P2_CHAR = gameState.player2.getCharacter();
        final int P1_HP = gameState.player1.getHealth();
        final int P2_HP = gameState.player2.getHealth();
        final int P1_NORMALL = gameState.player1.getNormalBullet();
        final int P2_NORMALL = gameState.player2.getNormalBullet();
        final int P1_SILVER = gameState.player1.getSilverBullet();
        final int P2_SILVER = gameState.player2.getSilverBullet();
        final int P1_MAJIC = gameState.player1.getMagicBullet();
        final int P2_MAJIC = gameState.player2.getMagicBullet();
        final int P1_BLOCK = gameState.player1.getBlock();
        final int P2_BLOCK = gameState.player2.getBlock();
        final int P1_EVA = gameState.player1.getEvade();
        final int P2_EVA = gameState.player2.getEvade();
        final int[] P1_gun_barrel = gameState.player1.getGun_barrel();
        final int[] P2_gun_barrel = gameState.player2.getGun_barrel();
        final int[] P1_ACTIONS = gameState.player1.getPlayer_actions();
        final int[] P2_ACTIONS = gameState.player2.getPlayer_actions();
        final boolean P1_DEATHDOOR = gameState.player1.getDeathDoor();
        final boolean P2_DEATHDOOR = gameState.player2.getDeathDoor();
        final boolean P1_MAJIC_STATUS = gameState.player1.getMagigstatus();
        final boolean P2_MAJIC_STATUS = gameState.player2.getMagigstatus();
        final boolean P1_DUEL_STATUS = gameState.player1.getDuelStatus();
        final boolean P2_DUEL_STATUS = gameState.player2.getDuelStatus();
        final boolean P1_READY = gameState.player1.readyStatus();
        final boolean P2_READY = gameState.player2.readyStatus();

        String type = "";
        if (GameState.currentType == 0)
            type = "Ammo";
        else if (GameState.currentType == 1)
            type = "Actions";

        component.text_10_alginLeft(g2, "Player : " + P1_ID, 10, 20);
        component.text_10_alginRight(g2, "Player : " + P2_ID, 90, 20);

        component.text_10_alginLeft(g2, "Character : " + P1_CHAR, 10, 35);
        component.text_10_alginRight(g2, "Character : " + P2_CHAR, 114, 35);

        component.text_10_alginLeft(g2, "Health : " + P1_HP, 10, 50);
        component.text_10_alginRight(g2, "Health : " + P2_HP, 104, 50);

        component.text_10_alginLeft(g2, "Normal-bullet : " + P1_NORMALL, 10, 65);
        component.text_10_alginRight(g2, "Normal-bullet : " + P2_NORMALL, 150, 65);

        component.text_10_alginLeft(g2, "Magic-bullet : " + P1_MAJIC, 10, 80);
        component.text_10_alginRight(g2, "Magic-bullet : " + P2_MAJIC, 136, 80);

        component.text_10_alginLeft(g2, "Silver-bullet : " + P1_SILVER, 10, 95);
        component.text_10_alginRight(g2, "Silver-bullet : " + P2_SILVER, 142, 95);

        component.text_10_alginLeft(g2, "Block : " + P1_BLOCK + " | EVA : " + P1_EVA, 10, 110);
        component.text_10_alginRight(g2, "Block : " + P2_BLOCK + " | EVA : " + P2_EVA, 158, 110);

        component.text_10_alginLeft(g2, "Bullet : [ " + P1_gun_barrel[0], 10, 125);
        component.text_10_alginLeft(g2, "" + P1_gun_barrel[1], 110, 125);
        component.text_10_alginLeft(g2, "" + P1_gun_barrel[2], 122, 125);
        component.text_10_alginLeft(g2, "" + P1_gun_barrel[3], 134, 125);
        component.text_10_alginLeft(g2, "" + P1_gun_barrel[4], 146, 125);
        component.text_10_alginLeft(g2, "" + P1_gun_barrel[5] + " ]", 158, 125);

        component.text_10_alginRight(g2, "Bullet : [ " + P2_gun_barrel[0], 180, 125);
        component.text_10_alginRight(g2, "" + P2_gun_barrel[1], 80, 125);
        component.text_10_alginRight(g2, "" + P2_gun_barrel[2], 68, 125);
        component.text_10_alginRight(g2, "" + P2_gun_barrel[3], 56, 125);
        component.text_10_alginRight(g2, "" + P2_gun_barrel[4], 43, 125);
        component.text_10_alginRight(g2, "" + P2_gun_barrel[5] + " ]", 30, 125);

        component.text_10_alginLeft(g2, "Action : [ " + P1_ACTIONS[0], 10, 140);
        component.text_10_alginLeft(g2, "" + P1_ACTIONS[1], 110, 140);
        component.text_10_alginLeft(g2, "" + P1_ACTIONS[2], 122, 140);
        component.text_10_alginLeft(g2, "" + P1_ACTIONS[3], 134, 140);
        component.text_10_alginLeft(g2, "" + P1_ACTIONS[4] + " ]", 146, 140);

        component.text_10_alginRight(g2, "Action : [ " + P2_ACTIONS[0], 168, 140);
        component.text_10_alginRight(g2, "" + P2_ACTIONS[1], 68, 140);
        component.text_10_alginRight(g2, "" + P2_ACTIONS[2], 56, 140);
        component.text_10_alginRight(g2, "" + P2_ACTIONS[3], 43, 140);
        component.text_10_alginRight(g2, "" + P2_ACTIONS[4] + " ]", 30, 140);

        component.text_10_alginLeft(g2, "Ready : " + P1_READY, 10, 685);
        component.text_10_alginRight(g2, "Ready : " + P2_READY, 115, 685);

        component.text_10_alginLeft(g2, "Deathdoor : " + P1_DEATHDOOR + " | Magic Status : " + P1_MAJIC_STATUS
                + " | Duel Status : " + P1_DUEL_STATUS, 10, 700);
        component.text_10_alginRight(g2, "Deathdoor : " + P2_DEATHDOOR + " | Magic Status : " + P2_MAJIC_STATUS
                + " | Duel Status : " + P2_DUEL_STATUS, 505, 700);
        component.text_10_alginCenter(g2, "Slot : " + (GameState.currentSlot + 1) + " | " + type + " : Type", 0, 710);

    }

    // private void characterLog(Graphics2D g2){
    //     final int P1_ID = gameState.player1.getID();
    //     final int P2_ID = gameState.player2.getID();
    //     final int P1_CHAR = gameState.player1.getCharacter();
    //     final int P2_CHAR = gameState.player2.getCharacter();
        
    //     if (GameState.currentPlayer == 0) {
    //         component.text_10_alginLeft(g2, "Player : " + P1_ID, 10, 20);
    //         component.text_10_alginRight(g2, "Player : " + P2_ID , 87, 20);

    //         component.text_10_alginLeft(g2, "Character : " + S_Char.character, 10, 35);
    //         component.text_10_alginRight(g2, "Character : " + P2_CHAR, 110, 35);
    //     }else{
    //         component.text_10_alginLeft(g2, "Player : " + P1_ID, 10, 20);
    //         component.text_10_alginRight(g2, "Player : " + P2_ID, 87, 20);

    //         component.text_10_alginLeft(g2, "Character : " + P1_CHAR , 10, 35);
    //         component.text_10_alginRight(g2, "Character : " + S_Char.character, 110, 35);
    //     }
    // }

    private String getMemoryUsage() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = (totalMemory - freeMemory) / (1024 * 1024); // Convert to MB
        return String.valueOf(usedMemory);
    }

    // public static void main(String[] args) {
    // JFrame frame = new JFrame("Minecraft-like Debug Screen");

    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // frame.setSize(800, 600);
    // frame.setContentPane(debugScreen);
    // frame.setVisible(true);

    // Refresh the screen every 16ms (~60FPS)
    // new Timer(16, e -> debugScreen.repaint()).start();
    // }
}

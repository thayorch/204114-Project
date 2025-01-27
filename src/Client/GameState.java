package client;

import java.awt.Graphics2D;

public class GameState {
    public Graphics2D g2;

    GamePanel gamePanel;
    KeyHandler keyHand;

    public GameState(GamePanel gamepanel, KeyHandler keyHand) {
        this.gamePanel = gamepanel;
        this.keyHand = keyHand;
    }

    public GameState() {
    }
}
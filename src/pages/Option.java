package pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import client.GamePanel;
import client.GameState;
import client.Component;

public class Option {
    protected GamePanel gamePanel;
    protected GameState gameState;
    protected Component component;
    private BufferedImage background;

    public final Rectangle sfxSliderRect; // Rectangle for SFX slider
    public final Rectangle musicSliderRect; // Rectangle for music slider
    
    public Option(GamePanel gamePanel, GameState gameState) {
        this.gamePanel = gamePanel;
        this.gameState = gameState;
        this.component = new Component(gamePanel, gameState);
        loadAsset();

        // Initialize slider rectangles
        int sliderWidth = 450;
        int sliderHeight = 40;
        int sliderX = ((gamePanel.getWidth()) / 2) + 410;
        int sfxSliderY = 250;
        int musicSliderY = 350;

        sfxSliderRect = new Rectangle(sliderX, sfxSliderY, sliderWidth, sliderHeight);
        musicSliderRect = new Rectangle(sliderX, musicSliderY, sliderWidth, sliderHeight);
    }

    public void render(Graphics2D g2) {
        component.setBackground(g2, background);
        
        component.titleCenter(g2, "Option", 150);
        // component.Slider(g2, sfxSliderRect, "SFX Volume", sfxVolume);
        // component.Slider(g2, musicSliderRect, "Music Volume", musicVolume);
    }

    

    private void loadAsset() {
        try {
            background = component.img("/resources/lobby/lobby_bg.png");
            System.out.println("[log: Lobby Image loaded successfully]");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("[error: Failed to load background]");
            background = null; // Ensure background is null if loading fails
        }
    }
}
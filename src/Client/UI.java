package client;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.Font;

import javax.imageio.ImageIO;
import java.io.IOException;
	
public class UI extends GameState{
  GameState gameState;
  GamePanel gamePanel;
  Graphics2D g2;
  int screenWidth;
  int screenHeight;
  float scalingFactor;

  // Image Variable
  private BufferedImage Background, Block, Aim, Evade,
  C1, C2, C3, CHAR_P1, CHAR_P2, Slot, Chamber,
  BG1, BG2, BG3, S_CHAR, SL_CHAR, SR_CHAR;
  
  
  
  String fontName = "Arial";

  Font font_20 = new Font(fontName, Font.PLAIN, 20);
  Font font_10 = new Font(fontName, Font.PLAIN, 10);

  public UI(GamePanel gamePanel, GameState gameState){
    this.gamePanel = gamePanel;
    this.gameState = gameState;
    loadAsset();
  }

  // App Router
  
  public void draw(Graphics2D g2) {
    this.g2 = g2;
    switch (gameState.currentState) {
      case(LOBBY_STATE):
        LobbyPage(g2);
      	gameState.lobbyHandler.update();
        break;
      case(S_SCENCE_STATE):
        S_ScencePage(g2);
        break;
      case(S_CHAR_STATE): // p1
        S_CharPage(g2);
        break;
      case(S_ITEM_STATE):
        S_ItemPage(g2);
        break;
      case(PREVIEW_STATE):
        PreviewPage(g2);
        break;
      case(VICTORY_STATE):
        VictoryPage(g2);
        break;
    }
  }

// Page

  private void LobbyPage(Graphics2D g2) {
    setFontScale(font_20); // scaling
    //g2.setFont(font_20);

    g2.setColor(Color.white);
    String txt = "GAME TITLE";
    int x = getCenter(txt);
    int y = (int)gamePanel.screenHeight/3;

    g2.drawString(txt, x, y);
    setFontScale(font_10);
    txt = "Enter to start";
    x = getCenter(txt);

    g2.drawString(txt, x, y + (int)(y/4));
    gamePanel.setBackground(Color.black);
  }

  private void S_ScencePage(Graphics2D g2) {
	int currentBackground = gameState.scenceHandler.currentBackground;
    switch (currentBackground) {
      case 0:
        Background = BG1;
        gamePanel.setBackground(Color.decode("#FF6666"));
        break;
      case 1:
        Background = BG2;
        gamePanel.setBackground(Color.decode("#B2Ff66"));
        break;
      case 2:
        Background = BG3;
        gamePanel.setBackground(Color.decode("#66B2FF"));
        break;
    }

    drawImageScale(Background, 0, 0); // scaling
  }
  
  private void S_CharPage(Graphics2D g2) {
    int currentCharacter = gameState.characterHandler.currentChar;
    int currentPlayer = gameState.characterHandler.currentPlayer;
    
    switch (currentCharacter) {
      case CHAR1:
        SL_CHAR = C3;
        S_CHAR = C1;
        SR_CHAR = C2;
        break;
      case CHAR2:
        SL_CHAR = C1;
        S_CHAR = C2;
        SR_CHAR = C3;
        break;
      case CHAR3:
        SL_CHAR = C2;
        S_CHAR = C3;
        SR_CHAR = C1;
        break;
    }
    
    String txt="";
    switch(currentPlayer) {
    case 0 : txt = "Player 1"; break;
    case 1 : txt = "Player 2"; break;
    }
    
    setFontScale(font_20);
    g2.setColor(Color.black);
    gamePanel.setBackground(Color.decode("#FFFFFF"));
    int x = (int)(getCenter(txt)/6);
    int y = (int)(gamePanel.screenHeight/8.5);
    g2.drawString(txt+" Character Select...", x, y);
    
    double manualScale = 0.5;
    drawImageScale(S_CHAR, manualScale, getCenter(SL_CHAR, manualScale, 2, gamePanel.screenWidth), getCenter(SL_CHAR, manualScale, 4.5, gamePanel.screenHeight)); // scaling
    drawImageScale(SL_CHAR, getCenter(SL_CHAR, 5.98, gamePanel.screenWidth), getCenter(SL_CHAR, 4.5, gamePanel.screenHeight)); // scaling
    drawImageScale(SR_CHAR, getCenter(SR_CHAR, 1.2, gamePanel.screenWidth), getCenter(SR_CHAR, 4.5, gamePanel.screenHeight)); // scaling
  
    x = (int)(getCenter(txt)/6);
    y = (int)(gamePanel.screenHeight-(0.1*gamePanel.screenHeight));
    g2.drawString("Player 1 : "+gamePanel.player1.character,x , y);
    
    x = (int)((gamePanel.screenWidth-(gamePanel.screenWidth)/4));
    y = (int)(gamePanel.screenHeight-(0.1*gamePanel.screenHeight));
    g2.drawString("Player 2 : "+gamePanel.player2.character,x , y);
    
  
  }
  
  private void S_ItemPage(Graphics2D g2) {
    gamePanel.setBackground(Color.black);
    // Ammo screen drawing logic here
  }
  
  private void PreviewPage(Graphics2D g2) {
    drawImageScale(Background, 0, 0);
    gamePanel.setBackground(Color.white);
    // Preview drawing logic here
  }
  
  private void VictoryPage(Graphics2D g2) {
    gamePanel.setBackground(Color.black);
    // Bullet time screen drawing logic here
  }
  
  
  
  
//  STYLE
  // Get center of x
  public int getCenter(String text){ // txt
    screenWidth = gamePanel.screenWidth;

    int text_length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = screenWidth/2 - text_length/2;
    return x;
  }

  public int getCenter(BufferedImage img, double divisor, int lenght){ // img
    int screen;
    screen = gamePanel.screenHeight;
    scalingFactor = gamePanel.scalingFactor;

    if(lenght == gamePanel.screenWidth)
      screen = gamePanel.screenWidth;

    int img_Width = (int)(img.getWidth()*scalingFactor);
    int value = (int)((screen - img_Width)/divisor); // /2 = center
    return value;
  }

  public int getCenter(BufferedImage img, double manualScale, double divisor, int lenght){ // get center with scale
    int screen;
    screen = gamePanel.screenHeight;
    scalingFactor = gamePanel.scalingFactor;
    manualScale += scalingFactor;

    if(lenght == gamePanel.screenWidth)
      screen = gamePanel.screenWidth;

    int img_Width = (int)(img.getWidth() * manualScale);
    int value = (int)((screen - img_Width)/divisor); // /2 = center
    return value;
  }

  // Scaling Image
  public void drawImageScale(BufferedImage img, int x, int y){
    Graphics2D g2 = this.g2;
    scalingFactor = gamePanel.scalingFactor;
    g2.drawImage(img, x, y, (int)(img.getWidth() * scalingFactor), (int)(img.getHeight() * scalingFactor), null); // (x, y, w, h, color)
  }

  public void drawImageScale(BufferedImage img, double manualScale, int x, int y){
    Graphics2D g2 = this.g2;
    scalingFactor = gamePanel.scalingFactor;
    manualScale += scalingFactor;

    g2.drawImage(img, x, y, (int)(img.getWidth() * manualScale), (int)(img.getHeight() * manualScale), null); // (x, y, w, h, color)
  }

  // Scale Image
  public Image scaleImage(BufferedImage img, double manualScale){
    Graphics2D g2 = this.g2;
    scalingFactor = gamePanel.scalingFactor;
    manualScale += scalingFactor;
    return img.getScaledInstance((int)(img.getWidth() * manualScale), (int)(img.getHeight() * manualScale), Image.SCALE_SMOOTH);
  }

  // ScalingFont
  public void setFontScale(Font font){
    Graphics2D g2 = this.g2;
    scalingFactor = gamePanel.scalingFactor;
    int size = font.getSize();
    g2.setFont(new Font(fontName, Font.PLAIN, (int)(size*scalingFactor))); 
  }


  public void loadAsset(){
    try {
      BG1 = ImageIO.read(getClass().getResourceAsStream("/resource/Background/1.png"));
      BG2 = ImageIO.read(getClass().getResourceAsStream("/resource/Background/2.png"));
      BG3 = ImageIO.read(getClass().getResourceAsStream("/resource/Background/3.png"));

      C1 = ImageIO.read(getClass().getResourceAsStream("/resource/character/1.png"));
      C2 = ImageIO.read(getClass().getResourceAsStream("/resource/character/2.png"));
      C3 = ImageIO.read(getClass().getResourceAsStream("/resource/character/3.png"));

      System.out.println("asset has been loaded");
    } catch (IOException e) {
      e.printStackTrace(); // can't find image
    }
  }

}
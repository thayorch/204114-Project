package main;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.Font;

import javax.imageio.ImageIO;
import java.io.IOException;

public class UI extends GameState{
  GameState gs;
  GamePanel gp;
  Graphics2D g2;
  int screenWidth;
  int screenHeight;
  float scalingFactor;

  // Image
  private BufferedImage BackGround, Block, Aim, Evade, C1, C2, C3, CHAR_P1, CHAR_P2, Slot, Chamber, BG1, BG2, BG3, S_CHAR, SL_CHAR, SR_CHAR;

  String fontName = "Arial";

  Font font_20 = new Font(fontName, Font.PLAIN, 20);
  Font font_10 = new Font(fontName, Font.PLAIN, 10);

  public UI(GamePanel gp, GameState gs){
    this.gp = gp;
    this.gs = gs;
    loadAsset();
  }

  // draw
  public void draw(Graphics2D g2) {
    this.g2 = g2;
    // g2.scale(scalingFactor,scalingFactor); // after this set scaling x,y, cause issue with font
    switch (gp.currentState) {
      case(TITLESTATE):
        drawTitleScreen(g2);
        break;
      case(BGSTATE):
        drawBGScreen(g2);
        break;
      case(CHARSTATE):
        drawCharacterScreen(g2);
        break;
      case(AMMOSTATE):
        drawAmmoScreen(g2);
        break;
      case(PREVIEWSTATE):
        drawPreviewScreen(g2);
        break;
      case(BULLETSTATE):
        drawBulletTimeScreen(g2);
        break;
    }
  }

  // Get center of x
  public int getCenter(String text){ // txt
    screenWidth = gp.screenWidth;

    int text_length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = screenWidth/2 - text_length/2;
    return x;
  }

  public int getCenter(BufferedImage img, double divisor, int lenght,char type){ // img
    int screen;
    screen = gp.screenWidth;
    scalingFactor = gp.scalingFactor;

    if(type == 'y')
      screen = gp.screenHeight;

    int img_Width = (int)(img.getWidth()*scalingFactor);
    int value = (int)((screen - img_Width)/divisor); // /2 = center
    return value;
  }

  public int getCenter(BufferedImage img, double manualScale, double divisor, int lenght,char type){ // get center with scale
    int screen;
    screen = gp.screenWidth;
    scalingFactor = gp.scalingFactor;
    manualScale += scalingFactor;

    if(type == 'y')
      screen = gp.screenHeight;

    int img_Width = (int)(img.getWidth() * manualScale);
    int value = (int)((screen - img_Width)/divisor); // /2 = center
    return value;
  }

  // Scaling Image
  public void drawImageScale(BufferedImage img, int x, int y){
    Graphics2D g2 = this.g2;
    scalingFactor = gp.scalingFactor;
    g2.drawImage(img, x, y, (int)(img.getWidth() * scalingFactor), (int)(img.getHeight() * scalingFactor), null); // (x, y, w, h, color)
  }

  public void drawImageScale(BufferedImage img, double manualScale, int x, int y){
    Graphics2D g2 = this.g2;
    scalingFactor = gp.scalingFactor;
    manualScale += scalingFactor;

    g2.drawImage(img, x, y, (int)(img.getWidth() * manualScale), (int)(img.getHeight() * manualScale), null); // (x, y, w, h, color)
  }

  // Scale Image
  public Image scaleImage(BufferedImage img, double manualScale){
    Graphics2D g2 = this.g2;
    scalingFactor = gp.scalingFactor;
    manualScale += scalingFactor;
    return img.getScaledInstance((int)(img.getWidth() * manualScale), (int)(img.getHeight() * manualScale), Image.SCALE_SMOOTH);
  }

  // ScalingFont
  public void setFontScale(Font font){
    Graphics2D g2 = this.g2;
    scalingFactor = gp.scalingFactor;
    int size = font.getSize();
    g2.setFont(new Font(fontName, Font.PLAIN, (int)(size*scalingFactor))); 
  }

  private void drawTitleScreen(Graphics2D g2) {
    setFontScale(font_20); // scaling
    //g2.setFont(font_20);

    g2.setColor(Color.white);
    String txt = "Game Name";
    int x = getCenter(txt);
    int y = (int)gp.screenHeight/3;

    g2.drawString(txt, x, y);
    
    setFontScale(font_10);
    txt = "Enter to start";
    x = getCenter(txt);

    g2.drawString(txt, x, y + (int)(y/4));
    gp.setBackground(Color.black);
  }

  private void drawBGScreen(Graphics2D g2) {

    switch (gs.currentBackGround) {
      case 0:
        BackGround = BG1;
        gp.setBackground(Color.decode("#FF6666"));
        break;
      case 1:
        BackGround = BG2;
        gp.setBackground(Color.decode("#B2Ff66"));
        break;
      case 2:
        BackGround = BG3;
        gp.setBackground(Color.decode("#66B2FF"));
        break;
    }
    //g2.drawImage(BackGround, 0, 0, null);
    drawImageScale(BackGround, 0, 0); // scaling
  }
  
  private void drawCharacterScreen(Graphics2D g2) {
    gp.setBackground(Color.decode("#FFFFFF"));

    switch (gs.currentChar) {
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
    //gp.setBackground(Color.black);

    //int x = getCenter(S_CHAR);
    // Draw from top left
    //S_CHAR = scaleImage(S_CHAR, 1.25);
    //drawImageScale(S_CHAR, getCenter(S_CHAR, 2, gp.screenWidth,'x'), getCenter(S_CHAR, 4.5, gp.screenHeight,'y')); // scaling
    double manualScale = 0.5;
    drawImageScale(S_CHAR, manualScale, getCenter(SL_CHAR, manualScale, 2, gp.screenWidth,'x'), getCenter(SL_CHAR, manualScale, 4.5, gp.screenHeight,'y')); // scaling
    drawImageScale(SL_CHAR, getCenter(SL_CHAR, 5.98, gp.screenWidth,'x'), getCenter(SL_CHAR, 4.5, gp.screenHeight,'y')); // scaling
    drawImageScale(SR_CHAR, getCenter(SR_CHAR, 1.2, gp.screenWidth,'x'), getCenter(SR_CHAR, 4.5, gp.screenHeight,'y')); // scaling
  }
  
  private void drawAmmoScreen(Graphics2D g2) {
    gp.setBackground(Color.white);
    // Ammo screen drawing logic here
  }
  
  private void drawPreviewScreen(Graphics2D g2) {
    drawImageScale(BackGround, 0, 0);
    gp.setBackground(Color.white);
    // Preview drawing logic here
  }
  
  private void drawBulletTimeScreen(Graphics2D g2) {
    gp.setBackground(Color.black);
    // Bullet time screen drawing logic here
  }

  public void loadAsset(){
    try {
      BG1 = ImageIO.read(getClass().getResourceAsStream("/img/bg/test-bg1.png"));
      BG2 = ImageIO.read(getClass().getResourceAsStream("/img/bg/test-bg2.png"));
      BG3 = ImageIO.read(getClass().getResourceAsStream("/img/bg/test-bg3.png"));

      C1 = ImageIO.read(getClass().getResourceAsStream("/img/character/test-char1.png"));
      C2 = ImageIO.read(getClass().getResourceAsStream("/img/character/test-char2.png"));
      C3 = ImageIO.read(getClass().getResourceAsStream("/img/character/test-char3.png"));

      System.out.println("asset has been loaded");
    } catch (IOException e) {
      e.printStackTrace(); // can't find image
    }
  }

}

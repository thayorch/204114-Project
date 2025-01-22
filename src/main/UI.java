package main;

import java.awt.image.BufferedImage;
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

  Font arial_40 = new Font("Arial", Font.PLAIN, 40);
  Font arial_20 = new Font("Arial", Font.PLAIN, 20);

  public UI(GamePanel gp, GameState gs){
    this.gp = gp;
    this.gs = gs;
    screenWidth = gp.screenWidth;
    screenHeight = gp.screenHeight;
    loadAsset();
  }

  // draw
  public void draw(Graphics2D g2) {
    this.g2 = g2;
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
  public int getCenterX(String text){
    int screen_length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    int x = screenWidth/2 - screen_length/2;
    return x;
  }

  private void drawTitleScreen(Graphics2D g2) {
    g2.setFont(arial_40);
    g2.setColor(Color.white);
    String txt = "Game Name";
    int x = getCenterX(txt);
    int y = (int)screenHeight/3;

    g2.drawString(txt, x, y);
    
    g2.setFont(arial_20);
    txt = "Enter to start";
    x = getCenterX(txt);

    g2.drawString(txt, x, y+100);
    gp.setBackground(Color.black);
    // Title screen drawing logic here
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

    g2.drawImage(BackGround, 0, 0, null); // (x, y, w, h, color)
  }
  
  private void drawCharacterScreen(Graphics2D g2) {
    gp.setBackground(Color.black);
    // Character selection screen drawing logic here
  }
  
  private void drawAmmoScreen(Graphics2D g2) {
    gp.setBackground(Color.white);
    // Ammo screen drawing logic here
  }
  
  private void drawPreviewScreen(Graphics2D g2) {
    g2.drawImage(BackGround, 0, 0, null);
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

      System.out.println("asset has been loaded");
    } catch (IOException e) {
      e.printStackTrace(); // can't find image
    }
  }

}

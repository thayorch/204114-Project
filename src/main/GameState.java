package main;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.imageio.ImageIO;
import java.io.IOException;

import entity.Player;

public class GameState {

  // Game State
  public final int TITLESTATE = 0;
  public final int BGSTATE = 1;
  public final int CHARSTATE = 2;
  public final int AMMOSTATE = 3;
  public final int PREVIEWSTATE = 4;
  public final int BULLETSTATE = 5;

  public int currentState = 1;
  public int currentBackGround = 0;
  
  // Image
  protected BufferedImage BackGround, Block, Aim, Evade, C1, C2, C3, P1, P2, Slot, Chamber, BG1, BG2, BG3;

  // Player
  Player p1 = new Player();
  Player p2 = new Player();

  GamePanel gp;
  KeyHandler keyHand;
  Graphics2D g2;
  int screenWidth;
  int screenHeight;

  public GameState(GamePanel gp, KeyHandler keyHand){
    this.gp = gp;
    this.keyHand = keyHand;
    loadAsset();
  }

  public GameState(){
  }

  public int getGameState(){
    return currentState;
  }

  public void setUpdate(GameState gs){
    gs.update();
  }

  // update
  public void update() {
    switch (currentState) {
      case(TITLESTATE):
        titleUpdate();
        break;
      case(BGSTATE):
        bgUpdate();
        break;
      case(CHARSTATE):
        charUpdate();
        break;
      case(AMMOSTATE):
        ammoUpdate();
        break;
      case(PREVIEWSTATE):
        previewUpdate();
        break;
      case(BULLETSTATE):
        bulletUpdate();
        break;
    }
  }

  private void titleUpdate(){

  }

  private void bgUpdate(){
    int total_bg = 3; // n + 1
    if(keyHand.ePressed == true){
      currentBackGround = (currentBackGround + 1)%total_bg;
      keyHand.ePressed = false;
    }

    if(keyHand.qPressed == true){
      currentBackGround = (currentBackGround - 1 + total_bg) % total_bg; 
      keyHand.qPressed = false;
    }

    if(keyHand.enterPressed == true){
      currentState = CHARSTATE;
    }
  }

  private void charUpdate(){

  }

  private void ammoUpdate(){

  }

  private void previewUpdate(){

  }

  private void bulletUpdate(){

  }
  
  // draw
  public void draw(Graphics2D g2) {
    this.g2 = g2;
    switch (currentState) {
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

  private void drawTitleScreen(Graphics2D g2) {
    gp.setBackground(Color.white);
    // Title screen drawing logic here
  }

  private void drawBGScreen(Graphics2D g2) {

    switch (currentBackGround) {
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

    //g2.drawImage(BG1, 0, 0, gp.screenWidth, gp.screenHeight, null); // (x, y, w, h, color) not working
    g2.drawImage(BackGround, 0, 0, null); // (x, y, w, h, color) not working
    // Title screen drawing logic here
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
    gp.setBackground(Color.black);
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

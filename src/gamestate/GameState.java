package gamestate;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.imageio.ImageIO;
import java.io.IOException;

import java.awt.Image;

import main.GamePanel;
import main.KeyHandler;

public class GameState {
  // Game State
  protected String name;
  public final int titleState = 0;
  public final int bgState = 1;
  public final int characterState = 2;
  public final int ammoState = 3;
  public final int previewState = 4;
  public final int bulletTimeState = 5;
  public int currentState = 1;
  public int currentBackGround = 0;
  protected BufferedImage BackGround, Block, Aim, Evade, C1, C2, C3, P1, P2, Slot, Chamber, BG1, BG2, BG3;

  GamePanel gp;
  KeyHandler keyHand;
  Graphics2D g2;
  int screenWidth;
  int screenHeight;

  public GameState(GamePanel gp, KeyHandler keyHand){
    this.gp = gp;
    this.keyHand = keyHand;
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
      case(titleState):
        titleUpdate();
        break;
      case(bgState):
        bgUpdate();
        break;
      case(characterState):
        charUpdate();
        break;
      case(ammoState):
        ammoUpdate();
        break;
      case(previewState):
        previewUpdate();
        break;
      case(bulletTimeState):
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
      currentState = characterState;
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
      case(titleState):
        drawTitleScreen(g2);
        break;
      case(bgState):
        drawBGScreen(g2);
        break;
      case(characterState):
        drawCharacterScreen(g2);
        break;
      case(ammoState):
        drawAmmoScreen(g2);
        break;
      case(previewState):
        drawPreviewScreen(g2);
        break;
      case(bulletTimeState):
        drawBulletTimeScreen(g2);
        break;
    }
  }

  private void drawTitleScreen(Graphics2D g2) {
    gp.setBackground(Color.white);
    // Title screen drawing logic here
  }

  private void drawBGScreen(Graphics2D g2) {
    BackGround = null;
    System.out.println(currentBackGround);
    switch (currentBackGround) {
      case 0:
        BackGround = BG1;
        gp.setBackground(Color.red);
        break;
      case 1:
        BackGround = BG2;
        gp.setBackground(Color.yellow);
        break;
      case 2:
        BackGround = BG3;
        gp.setBackground(Color.blue);
        break;
    }

    //g2.drawImage(BG1, 0, 0, gp.screenWidth, gp.screenHeight, null); // (x, y, w, h, color) not working
    g2.drawImage(BG1, 0, 0, null); // (x, y, w, h, color) not working
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
    } catch (IOException e) {
      e.printStackTrace(); // can't find image
    }
  }
}

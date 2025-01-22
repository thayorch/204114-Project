package main;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.Font;

import javax.imageio.ImageIO;
import java.io.IOException;

import entity.Player;

public class GameState {

  // Game State
  protected final int TITLESTATE = 0;
  protected final int BGSTATE = 1;
  protected final int CHARSTATE = 2;
  protected final int AMMOSTATE = 3;
  protected final int PREVIEWSTATE = 4;
  protected final int BULLETSTATE = 5;

  protected int currentState;
  protected int currentBackGround = 0;
  
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

  Font arial_40 = new Font("Arial", Font.PLAIN, 40);
  Font arial_20 = new Font("Arial", Font.PLAIN, 20);

  public GameState(GamePanel gp, KeyHandler keyHand){
    this.gp = gp;
    this.keyHand = keyHand;
    currentState = gp.currentState;
    screenWidth = gp.screenWidth;
    screenHeight = gp.screenHeight;
    //loadAsset();
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
    gp.currentState = currentState;
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
    if(keyHand.enterPressed == true){
      currentState = BGSTATE;
      keyHand.enterPressed = false;
    }
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
      //currentState = PREVIEWSTATE;
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
}

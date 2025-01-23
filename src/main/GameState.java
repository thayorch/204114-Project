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

  // DEFAULT variable
  protected final int TOTAL_BG = 3; // n + 1
  protected final int TOTAL_CHAR = 3;
  protected final int CHAR1 = 0;
  protected final int CHAR2 = 1;
  protected final int CHAR3 = 2;


  protected int currentState;

  protected int currentBackGround = 0;

  protected int currentChar = 0;

  protected int currentBarrel = 0;
  protected int currentActionSlot = 0;
  

  // Player
  Player player1;
  Player player2;

  GamePanel gp;
  KeyHandler keyHand;
  Graphics2D g2;

  public GameState(GamePanel gp, KeyHandler keyHand){
    this.gp = gp;
    this.keyHand = keyHand;
    player1 = gp.player1;
    player2 = gp.player2;
    currentState = gp.currentState;
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
    if(keyHand.ePressed == true){
      currentBackGround = (currentBackGround + 1)%TOTAL_BG; // backward
      keyHand.ePressed = false;
    }

    if(keyHand.qPressed == true){
      currentBackGround = (currentBackGround - 1 + TOTAL_BG) % TOTAL_BG; //forward
      keyHand.qPressed = false;
    }

    if(keyHand.enterPressed == true){
      currentState = CHARSTATE;
      //currentState = PREVIEWSTATE;
    }
  }

  private void charUpdate(){
    if(keyHand.ePressed == true){
      keyHand.ePressed = false;
      currentChar= (currentChar + 1)%TOTAL_CHAR;
    }

    if(keyHand.qPressed == true){
      keyHand.qPressed = false;
      currentChar= (currentChar - 1 + TOTAL_CHAR)%TOTAL_CHAR;
    }

    if(keyHand.enterPressed == true){
      if(gp.player1.ready && gp.player2.ready){
      currentState = AMMOSTATE;
      }
    }
  }

  private void ammoUpdate(){

  }

  private void previewUpdate(){

  }

  private void bulletUpdate(){

  }
}

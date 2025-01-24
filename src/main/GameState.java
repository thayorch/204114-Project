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

  // Bg
  protected int currentBackGround = 0;

  // Character
  protected int currentChar = 0;
  protected int currentPlayer = 0; // 0 player1, 1 player2

  // Ammo
  protected final int EMPTY_BULLET = 0;
  protected final int NORMAL_BULLET = 1;
  protected final int MAGIC_BULLET = 2; // Reduce Evade & BLock
  protected final int SILVER_BULLET = 3; // Garuntee Hit
  protected final int DEATH_BULLET = 4; // Devil Contact, If it dosen't hit mean the shooter die.

  protected final int ACTION_NONE = 0;
  protected final int ACTION_GUARD = 1;
  protected final int ACTION_SHOOT = 2;
  protected final int ACTION_EVADE = 3;

  protected int currentSlot = 0;
  protected int currentType = 0; // 0 load ammo, 1 select action
  protected int currentAction = 0;
  protected int currentP1Action = 0;
  protected int currentP2Action = 0;
  protected int bulletType = 0;
  protected int round = 1;

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
    if(keyHand.enterPressed){
      currentState = BGSTATE;
      keyHand.enterPressed = false;
    }
  }

  private void bgUpdate(){
    if(keyHand.ePressed){
      currentBackGround = (currentBackGround + 1)%TOTAL_BG; // backward
      keyHand.ePressed = false;
    }

    if(keyHand.qPressed){
      currentBackGround = (currentBackGround - 1 + TOTAL_BG) % TOTAL_BG; //forward
      keyHand.qPressed = false;
    }

    if(keyHand.enterPressed){
      currentState = CHARSTATE;
      keyHand.enterPressed = false;
    }
  }

  private void charUpdate(){

    if(keyHand.ePressed){
      keyHand.ePressed = false;
      currentChar = (currentChar + 1)%TOTAL_CHAR;
    }

    if(keyHand.qPressed){
      keyHand.qPressed = false;
      currentChar = (currentChar - 1 + TOTAL_CHAR)%TOTAL_CHAR;
    }

    if(keyHand.enterPressed == true){

      if(!gp.player1.ready){
        gp.player1.ready = true;
        gp.player1.character = currentChar;
        currentPlayer = 1;
        keyHand.enterPressed = false;
      }

      if(!gp.player2.ready && currentPlayer == 1 && keyHand.enterPressed){
        gp.player2.ready = true;
        gp.player2.character = currentChar;
        currentPlayer = 0;
        //keyHand.enterPressed = false;
      }

      //if(gp.player1.ready && gp.player2.ready && keyHand.enterPressed == true){
      if(gp.player1.ready && gp.player2.ready){
        currentState = AMMOSTATE;
        gp.player1.ready = false;
        gp.player2.ready = false;
        keyHand.enterPressed = false;
      }
    }
  }

  private void checkPlayerAmmo(int bulletType, Player player){ // check player when choose bullet, replace current bullet
    switch (bulletType) {
      case EMPTY_BULLET:
        player.bullet[currentSlot] = EMPTY_BULLET;
        setPlayerAmmoBack(player, bulletType);
        break;

      case NORMAL_BULLET:
        player.bullet[currentSlot] = EMPTY_BULLET;
        if (player.normalBullet > 0 && player.bullet[currentSlot] != NORMAL_BULLET){
          setPlayerAmmoBack(player); // add back bullet type
          player.normalBullet--;
          player.bullet[currentSlot] = bulletType;
        }
        break;

      case MAGIC_BULLET:
        player.bullet[currentSlot] = EMPTY_BULLET;
        if (player.bullet[currentSlot] != MAGIC_BULLET){
          setPlayerAmmoBack(player);
          player.magicBullet--;
          player.bullet[currentSlot] = bulletType;
        }
        break;

      default:
        break;
    }

  }

  private void setPlayerAmmoBack(Player player){ // re add bullet to pool
    switch (player.bullet[currentSlot]) {
      case NORMAL_BULLET:
        player.normalBullet++;
        break;

      case MAGIC_BULLET:
        player.magicBullet++;
        break;

      case SILVER_BULLET:
        player.silverBullet++;
        break;

      case DEATH_BULLET:
        player.deathBullet++;
        break;
    }
  }

  private void setPlayerAmmoBack(Player player, int bulletType){ // re add bullet to pool
    switch (player.bullet[currentSlot]) {

      case EMPTY_BULLET:
        if(bulletType == NORMAL_BULLET) player.normalBullet++;
        if(bulletType == DEATH_BULLET) player.deathBullet++;
        if(bulletType == SILVER_BULLET) player.silverBullet++;
        if(bulletType == MAGIC_BULLET) player.magicBullet++;
        break;

      case NORMAL_BULLET:
        player.normalBullet++;
        break;

      case MAGIC_BULLET:
        player.magicBullet++;
        break;

      case SILVER_BULLET:
        player.silverBullet++;
        break;

      case DEATH_BULLET:
        player.deathBullet++;
        break;
    }
  }

  private void ammoUpdate(){

    int TOTAL_BARREL = 6;
    int TOTAL_ACTION = 5;
    int TOTAL_SLOT = 0;
    int[] SLOT;

    switch (currentType) {
      case 0:
        TOTAL_SLOT = TOTAL_BARREL;
        break;
      case 1:
        TOTAL_SLOT = TOTAL_ACTION;
        break;
    }


    if(keyHand.ePressed){
      keyHand.ePressed = false;
      currentSlot = (currentSlot + 1)%TOTAL_SLOT;
      System.out.printf("P%d, Current Type %d, Current Slot %d, NUM = %d\n", currentPlayer,currentType, currentSlot, keyHand.numPressedNUM);
    }

    if(keyHand.qPressed){
      keyHand.qPressed = false;
      currentSlot = (currentSlot - 1 + TOTAL_SLOT) % TOTAL_SLOT;
      System.out.printf("P%d, Current Type %d, Current Slot %d, NUM = %d\n", currentPlayer,currentType, currentSlot, keyHand.numPressedNUM);
    }

    if(keyHand.spacePressed){
      keyHand.spacePressed = false;
      currentSlot = 0; // reset slot
      if(currentType == 0)
        currentType = 1;
      else
        currentType = 0;
      System.out.printf("P%d, Current Type %d, Current Slot %d, NUM = %d\n", currentPlayer,currentType, currentSlot, keyHand.numPressedNUM);
    }

    if(keyHand.numPressed){
      keyHand.numPressed = false;
      switch (currentPlayer) {
        case 0:
          if(TOTAL_SLOT == TOTAL_BARREL){
            bulletType = keyHand.numPressedNUM; // ammo check
            checkPlayerAmmo(bulletType, gp.player1);
          }
          else
            gp.player1.action[currentSlot] = keyHand.numPressedNUM;
          break;

        case 1:
          if(TOTAL_SLOT == TOTAL_BARREL){
            bulletType = keyHand.numPressedNUM;
            checkPlayerAmmo(bulletType, gp.player2);
          }
          else
            gp.player2.action[currentSlot] = keyHand.numPressedNUM;
          break;
      } 
      System.out.printf("P%d, Current Type %d, Current Slot %d, NUM = %d\n", currentPlayer,currentType, currentSlot, keyHand.numPressedNUM);
      keyHand.numPressedNUM = 0; // reset num
    }

    if(keyHand.enterPressed){

      if(!gp.player1.ready){
        gp.player1.ready = true;
        currentType = 0;
        currentSlot = 0;
        currentPlayer = 1;
        System.out.println("player1 ready");
        keyHand.enterPressed = false;
      }

      if(!gp.player2.ready && keyHand.enterPressed){
        gp.player2.ready = true;
        System.out.println("player2 ready");
      }

      if(gp.player1.ready && gp.player2.ready){
        System.out.println("change stage");
        currentState = PREVIEWSTATE;
        currentPlayer = 0;
        gp.player1.ready = false;
        gp.player2.ready = false;
        keyHand.enterPressed = false;
      }
    }
  }

  private int calculateDamage(int bulletType, Player p1, Player p2){ // Gunner Victim
    int damage = 0;
    switch (bulletType) {
      case NORMAL_BULLET:
        break;
      case MAGIC_BULLET:
        break;
      case SILVER_BULLET:
        break;
      case DEATH_BULLET:
        break;
    }
    return damage;
  }

  private void previewUpdate(){

    int MAX_ACTION = Math.max(gp.player1.actionNum, gp.player2.actionNum);

    if (currentAction < MAX_ACTION)
      currentAction++;

    if(currentAction >= gp.player1.action.length)
      currentP1Action = ACTION_NONE;
    else
      currentP1Action = gp.player1.action[currentAction];

    if(currentAction >= gp.player2.action.length)
      currentP2Action = ACTION_NONE;
    else
      currentP2Action = gp.player2.action[currentAction];

    System.out.printf("P1 %d : P2 %d\n", currentP1Action, currentP2Action);
    
    if(currentP1Action == ACTION_SHOOT){
      System.out.print("Player1 Shoot ");
      switch (currentP2Action) {

        case(ACTION_SHOOT):
          System.out.println("Player2 Shoot");
          //currentState = BULLETSTATE;
          break;

        case(ACTION_EVADE):
          System.out.println("Player2 Evade");
          break;

        case(ACTION_GUARD):
          System.out.println("Player2 Guard");
          break;

        case(ACTION_NONE):
          System.out.println("Player2 Stand");
          break;
      }
    }

    if(currentP2Action == ACTION_SHOOT){
      System.out.print("Player2 Shoot ");
      switch (currentP1Action) {

        case(ACTION_SHOOT):
          System.out.println("Player1 Shoot");
          //currentState = BULLETSTATE;
          break;

        case(ACTION_EVADE):
          System.out.println("Player1 Evade");
          break;

        case(ACTION_GUARD):
          System.out.println("Player1 Guard");
          break;

        case(ACTION_NONE):
          System.out.println("Player1 Stand");
          break;
      }
    }

    if (currentAction == (MAX_ACTION - 1)){

      // GameOver
      if (gp.player1.health <= 0 && gp.player2.health <= 0)
        System.out.println("Tie");
        currentState = TITLESTATE;

      if (gp.player2.health <= 0)
        System.out.println("Player 1 Win");
        currentState = TITLESTATE;

      if (gp.player1.health <= 0)
        System.out.println("Player 2 Win");
        currentState = TITLESTATE;
      
      // Round Over
      if (round > 5){

        if (gp.player1.getTotalBullet() > gp.player2.getTotalBullet())
          System.out.println("Player 1 Win");

        if (gp.player1.getTotalBullet() < gp.player2.getTotalBullet())
          System.out.println("Player 2 Win");

        if (gp.player1.getTotalBullet() == gp.player2.getTotalBullet())
          System.out.println("Tie");

        currentState = TITLESTATE;
      }

      // Alive
      currentState = AMMOSTATE;
      round++;
    }
  }

  private void bulletUpdate(){

  }
}

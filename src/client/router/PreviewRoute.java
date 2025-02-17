package client.router;

import client.GameState;
import entity.Player;

public class PreviewRoute {

  private Router router;
  private GameState gameState;
  private boolean finished = false;
  private boolean finishedAnimation = false;
  private int PLAYER1 = gameState.PLAYER1;
  private int PLAYER2 = gameState.PLAYER2;
  private int currentActionSlot = 0;
  private final int MAX_ROUND = 5;

  private final int ACTION_NONE = 0;
  private final int ACTION_SHOOT = 1;
  private final int ACTION_BLOCK = 2;
  private final int ACTION_EVADE = 3;
  private final int ACTION_SHOOT_L = 4;
  private final int ACTION_SHOOT_M = 5;
  private final int ACTION_SHOOT_R = 6;
  private final int ACTION_EVADE_L = 7;
  private final int ACTION_EVADE_R = 8;

  private final int EMPTY_BULLET = 0;
  private final int NORMAL_BULLET = 1;
  private final int MAGIC_BULLET = 2; // Reduce Evade & BLock
  private final int SILVER_BULLET = 3; // Garuntee Hit
  private final int DEATH_BULLET = 4; // Devil Contact, If it dosen't hit mean the shooter die.
  
  Player player1;
  Player player2;

  public PreviewRoute(Router router, GameState gameState) {
    this.router = router;
    this.gameState = gameState;
    player1 = gameState.player1;
    player2 = gameState.player2;
  }

  private void checkVictory() {
    player1 = gameState.player1;
    player2 = gameState.player2;

    int P1hp = player1.health;
    int P2hp = player2.health;

    // win condition
    if (P1hp <= 0 || P2hp <= 0) {
      if (P1hp == P2hp) {
        gameState.player1.win = true;
        gameState.player2.win = true;
      } 
      else if (P1hp <= 0)
        gameState.player2.win = true;
      else
        gameState.player1.win = true;
      finished = true;
    }

    // run out of bullet
    if (player1.getTotalBullet() == 0 || player2.getTotalBullet() == 0) {
      
      if (player1.getTotalBullet() == player2.getTotalBullet()) { // continue to hp check
        if (P1hp > P2hp)
          gameState.player1.win = true;
        if (P1hp < P2hp)
          gameState.player2.win = true;
        if (P1hp == P2hp)
          gameState.player1.win = true;
          gameState.player2.win = true;
      }

      else if (player1.getTotalBullet() > player2.getTotalBullet())
        gameState.player1.win = true;

      else if (player1.getTotalBullet() < player2.getTotalBullet())
        gameState.player2.win = true;

      finished = true;
    }
  }

  protected void update() {
    
    int currentPlayer = GameState.currentPlayer;
    int currentRound = GameState.currentRound;
    int max_slot = gameState.player1.player_actions.length;

    player1 = gameState.player1;
    player2 = gameState.player2;

    System.out.println(); // for some reason need to print to work at start of new round
    if (!finishedAnimation){
      currentActionSlot = 0;

      while(currentActionSlot < max_slot) {
        gameState.player1.actionCompare(currentActionSlot, gameState.player2);
        gameState.player2.actionCompare(currentActionSlot, gameState.player1);
        currentActionSlot++;

        // dueling
        if (gameState.player1.duelStatus || gameState.player2.duelStatus){

          while (gameState.player1.duelStatus) {
            System.out.println("1 " + gameState.player1.duelStatus); // for some reason need to print output to make it work
            if(router.keyHand.enterPressed){
              router.keyHand.enterPressed = false;
              gameState.player1.bulletPower = gameState.bulletPower;
              gameState.bulletPower = 0; // reset
              gameState.player1.duelStatus = false;
              gameState.random_X(); // set new value
            }
          }

          while (gameState.player2.duelStatus) {
            System.out.println("2 " + gameState.player2.duelStatus);
            if(router.keyHand.enterPressed){
              router.keyHand.enterPressed = false;
              gameState.player2.bulletPower = gameState.bulletPower;
              gameState.bulletPower = 0; // reset
              gameState.player2.duelStatus = false;
              gameState.random_X();
            }
          }

          gameState.player1.duelingQTE(gameState.player2);
          gameState.player2.duelingQTE(gameState.player1);

          //gameState.player1.bulletPower = 0;
          //gameState.player2.bulletPower = 0;

          gameState.player1.rotateBarrel(gameState.player1.currentBarrel);
          gameState.player2.rotateBarrel(gameState.player2.currentBarrel);
        }

        // TODO not finished yet need to wait for animation to finished
        gameState.in_animation = true;
        while (gameState.in_animation) {
            // Optionally, add a small sleep to avoid a busy-wait loop that hogs CPU.
            try {
                Thread.sleep(10); // sleep for 10ms to avoid tight loop
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      }
    }

    // after finished signal this finishedAnimation to be 'true'
    finishedAnimation = true;

    // before try for loop
    //if (currentActionSlot >= max_slot)
    //  currentActionSlot = 0;
    //  finishedAnimation = true;

    //// action check, slot
    //if(!finishedAnimation)
    //  System.out.println("currentAciton : "+ currentActionSlot);
    //  currentActionSlot++;

    checkVictory();

    if (finishedAnimation || finished) {
      //System.out.println("[log : found winner / finished round, enter to continue]");
    }

    if (router.keyHand.enterPressed && (finished || finishedAnimation)) {
      router.keyHand.enterPressed = false;
      if (finished || (GameState.currentRound + 1 > MAX_ROUND)) { // win condition met or reach MAX_ROUND
        finished = false; // reset
        finishedAnimation = false; // reset
        Router.currentRoute = Router.VICTORY_STATE;
      } 

      else if (finishedAnimation) { // next round
        GameState.currentRound++;
        player1.setNewRoundvalues(); // set next round
        player2.setNewRoundvalues();

        finishedAnimation = false; // reset
        Router.currentRoute = Router.S_ITEM_STATE;
      }
    }

    if (router.keyHand.backspacePressed && finished) {
      Router.currentRoute = Router.S_ITEM_STATE;
      router.keyHand.backspacePressed = false;
    }
  }
}

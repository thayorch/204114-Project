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

  public PreviewRoute(Router router, GameState gameState) {
    this.router = router;
    this.gameState = gameState;
  }

  protected void update() {
    
    int currentPlayer = GameState.currentPlayer;
    int currentRound = GameState.currentRound;
    int max_slot = gameState.player1.player_actions.length - 1;

    Player player1 = gameState.player1;
    Player player2 = gameState.player2;
    int P1hp = player1.health;
    int P2hp = player2.health;

    // DEBUG
    // System.out.printf("Round: %d currentActionSlot: %d\n", currentRound, currentActionSlot);
    // System.out.printf("finished: %s finishedAnimation: %s\n", finished, finishedAnimation);
    // gameState.player1.printInventory();
    // gameState.player2.printInventory();
    // System.out.println();

    // action check
    gameState.player1.actionCompare(currentActionSlot, gameState.player2);
    gameState.player2.actionCompare(currentActionSlot, gameState.player1);

    // if shoot & shoot & hit
    if (gameState.player1.duelStatus && gameState.player2.duelStatus) {
      // System.out.println("[log: iniate a duel/quick draw]");
      // TODO: duel some how?
    }

    if (currentActionSlot >= max_slot) {
      currentActionSlot = 0;
      finishedAnimation = true;
    }

    // slot
    else if (currentActionSlot < max_slot)
      currentActionSlot++;

    // win condition
    if (P1hp <= 0 || P2hp <= 0) {
      if (P1hp == P2hp) {
        gameState.player1.win = true;
        gameState.player2.win = true;
      } else if (P1hp <= 0)
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

    if (finishedAnimation || finished) {
      // System.out.println("[log : enter to continue]");
    }

    if (router.keyHand.enterPressed && (finished || finishedAnimation)) {
      router.keyHand.enterPressed = false;
      if (finished) { // win condition met
        finished = false; // reset
        Router.currentRoute = Router.VICTORY_STATE;
        player1.setDefaultvalues();
        player2.setDefaultvalues();
      } else if (finishedAnimation) { // next round
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

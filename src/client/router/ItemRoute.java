package client.router;

import client.GameState;

public class ItemRoute {
  private Router router;
  private GameState gameState;
  private int AMMO = GameState.AMMO;
  private int ACTION = GameState.ACTION;
  private final int PLAYER1 = GameState.PLAYER1;
  private final int PLAYER2 = GameState.PLAYER2;
  private final int ACTION_SHOOT = 1;
  private final int ACTION_EVADE = 3;
  private int type = 0;
  private boolean directionNeed = false;

  public ItemRoute(Router router, GameState gameState) {
    this.router = router;
    this.gameState = gameState;
  }

  protected void update() throws InterruptedException {
    int currentSlot = gameState.currentSlot;
    int currentType = gameState.currentType;
    int currentPlayer = gameState.currentPlayer;
    int TOTAL_BARREL = 5;
    int TOTAL_ACTION = 5;
    int bulletType = 0;
    int actionType = 0;

    // DEBUG
    // System.out.printf("P%d, Current Type %d, Current Slot %d, NUM = %d\n",
    // gameState.currentPlayer + 1, gameState.currentType, gameState.currentSlot,
    // router.keyHand.numPressedNUM); // DEBUG
    // System.out.printf("currentSlot %d currentType %d\n" , currentSlot,
    // currentType);
    // gameState.player1.printInventory();
    // System.out.println();
    // gameState.player2.printInventory();
    // System.out.println();

    int TOTAL_SLOT = TOTAL_BARREL;
    if (currentType == gameState.ACTION)
      TOTAL_SLOT = TOTAL_ACTION;

    int[] SLOT;
    if (router.keyHand.rightPressed) { // e
      router.keyHand.rightPressed = false;
      gameState.currentSlot = (currentSlot + 1) % TOTAL_SLOT;
    }

    if (router.keyHand.leftPressed) { // q
      router.keyHand.leftPressed = false;
      gameState.currentSlot = (currentSlot - 1 + TOTAL_SLOT) % TOTAL_SLOT;
    }

    // switch slot type
    if (router.keyHand.spacePressed || router.keyHand.shiftPressed) { // space
      router.keyHand.spacePressed = false;
      router.keyHand.shiftPressed = false;

      if (currentType == AMMO)
        currentType = ACTION;
      else
        currentType = AMMO;

      type = 0;
      directionNeed = false;
      gameState.currentSlot = 0; // reset slot
      gameState.currentType = currentType;
    }

    if (router.keyHand.rPressed) {
      for (int i = 0; i <= 6; i++) {
        GameState.currentSlot = i;
        Thread.sleep(100);
      }
      GameState.currentSlot = 0;
    }

    if (router.keyHand.numPressed) { // number pressed
      router.keyHand.numPressed = false;

      int numPressedNUM = router.keyHand.numPressedNUM;
      switch (currentPlayer) {
        case PLAYER1:

          if (directionNeed) { // if pressed shoot & evade
            numPressedNUM = gameState.player1.checkDirection(numPressedNUM, type);
            gameState.player1.checkPlayerDirection(numPressedNUM, currentType);
            directionNeed = false;
            type = 0;
          }

          else if ((numPressedNUM == ACTION_EVADE || numPressedNUM == ACTION_SHOOT) && currentType == ACTION
              && !directionNeed) { // check shoot & evade
            type = numPressedNUM; // shoot or evade
            directionNeed = true;
          }

          else if (!directionNeed)
            gameState.player1.checkPlayer(numPressedNUM, currentType);

          break;

        case PLAYER2:

          if (directionNeed) {
            numPressedNUM = gameState.player2.checkDirection(numPressedNUM, type);
            gameState.player2.checkPlayerDirection(numPressedNUM, currentType);
            directionNeed = false;
            type = 0;
          }

          else if ((numPressedNUM == ACTION_EVADE || numPressedNUM == ACTION_SHOOT) && currentType == ACTION
              && !directionNeed) {
            type = numPressedNUM;
            directionNeed = true;
          }

          else if (!directionNeed)
            gameState.player2.checkPlayer(numPressedNUM, currentType);

          break;
      }

      router.keyHand.numPressedNUM = 0; // reset num
    }

    // if (router.keyHand.backspacePressed) { // return
    // router.keyHand.backspacePressed = false;
    // gameState.currentSlot = 0;
    //
    // if (gameState.player2.ready && currentPlayer == PLAYER2) { // final accident
    // check
    // gameState.player2.ready = false;
    // System.out.println("[log: player2 unready]");
    // }
    //
    // else if (!gameState.player2.ready && currentPlayer == PLAYER2) { // accident
    // enter p2
    // gameState.player2.ready = false;
    // gameState.player1.ready = false;
    // gameState.currentPlayer = PLAYER1;
    // System.out.println("[log: player2 unready return to player 1]");
    // }
    //
    // else if (!gameState.player1.ready && currentPlayer == PLAYER1) { // accident
    // enter p1
    // gameState.player1.ready = false;
    // System.out.println("[log: player 1 unready]");
    // }
    // }

    if (router.keyHand.enterPressed) { // proceed
      router.keyHand.enterPressed = false;

      if (!gameState.player1.ready && currentPlayer == PLAYER1) {
        gameState.player1.ready = true;
        gameState.currentSlot = 0;
        gameState.currentType = 0;
        gameState.currentPlayer = PLAYER2;
        // System.out.println("[log: player1 ready]");
      }

      else if (!gameState.player2.ready && currentPlayer == PLAYER2) {
        gameState.player2.ready = true;
        gameState.currentSlot = 0;
        gameState.currentType = 0;
        // System.out.println("[log: player2 ready]");
        if (gameState.player1.ready && gameState.player2.ready) {
          Router.currentRoute = Router.PREVIEW_STATE;
          // reset values
          gameState.player1.ready = false;
          gameState.player2.ready = false;
          gameState.currentType = 0;
          gameState.currentPlayer = PLAYER1;
          gameState.currentSlot = 0;
          // System.out.println("[log: Switch!]");
        }

      }
    }
  }
}

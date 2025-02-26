package client.router;

import client.GameState;

public class CharacterRouter {
  private Router router;
  private GameState gameState;

  public CharacterRouter(Router router, GameState gameState) {
      this.router = router;
      this.gameState = gameState;
  }

  public void update() {
    int TOTAL_CHAR = GameState.TOTAL_CHAR;
    int currentChar = GameState.currentChar;
    int currentPlayer = GameState.currentPlayer;
    int PLAYER1 = GameState.PLAYER1;
    int PLAYER2 = GameState.PLAYER2;
  
  // DEBUG
  //System.out.printf("P1 %s %d\nP2 %s %d\n%d currentChar: %d\n", gameState.player1.ready, gameState.player1.character, gameState.player2.ready, gameState.player2.character, gameState.currentPlayer+1, gameState.currentChar);
  // System.out.println(currentPlayer + 1); 
  // gameState.player1.printInventory();
  // gameState.player2.printInventory();

  if (router.keyHand.leftPressed) { // Left 
    currentChar = (currentChar - 1 + TOTAL_CHAR) % TOTAL_CHAR;
    gameState.currentChar = currentChar;
    router.keyHand.leftPressed = false;
  }

  if (router.keyHand.rightPressed) { // Right
    currentChar = (currentChar + 1) % TOTAL_CHAR;
    gameState.currentChar = currentChar;
    router.keyHand.rightPressed = false;
  }

  if (router.keyHand.backspacePressed) { // Return
    router.keyHand.backspacePressed = false;
    gameState.currentChar = 0;

    if(!gameState.player1.ready && !gameState.player2.ready){
      Router.currentRoute = Router.S_SCENCE_STATE;
      gameState.player1.ready = false;
      gameState.player2.ready = false;
      gameState.player1.character = 0;
      gameState.player2.character = 0;
      gameState.currentPlayer = PLAYER1;
      System.out.println("[log: Switch Back!]");
    }

    else if(gameState.player2.ready && currentPlayer == PLAYER2){ // final accident check
      gameState.player2.ready = false;
      gameState.player2.character = 0;
      System.out.println("[log: player2 unready]");
    }

    else if(!gameState.player2.ready && currentPlayer == PLAYER2){ // accident enter p2
      gameState.player2.ready = false;
      gameState.player1.ready = false;
      gameState.currentPlayer = PLAYER1;
      System.out.println("[log: player2 unready return to player 1]");
    }

    else if(!gameState.player1.ready && currentPlayer == PLAYER1){ // accident enter p1
      gameState.player1.ready = false;
      gameState.player1.character = 0;
      System.out.println("[log: player 1 unready]");
    }

    //else if(gameState.player1.ready && currentPlayer == PLAYER1){
    //  gameState.player1.character = 0;
    //  gameState.player1.ready = false;
    //  System.out.println("[log: player1 unready]");
    //}
  }

  if (router.keyHand.enterPressed) { // Proceed
    //gameState.currentChar = 0; // if want to reset when select uncomment it
    router.keyHand.enterPressed = false;

    //if(gameState.player1.ready && gameState.player2.ready && enter_check){
    if(gameState.player1.ready && gameState.player2.ready){
      Router.currentRoute = Router.S_ITEM_STATE;
      gameState.player1.ready = false;
      gameState.player2.ready = false;
      gameState.currentPlayer = PLAYER1;
      gameState.currentChar = 0;
      System.out.println("[log: Switch!]");
    }

    else if(!gameState.player1.ready && currentPlayer == PLAYER1){
      
      gameState.player1.character = currentChar;
      gameState.player1.ready = true;
      gameState.currentPlayer = PLAYER2;
      gameState.currentChar = 0;
      System.out.println("[log: player1 ready]");
      //System.out.println("Pass 1");
    }

    else if(!gameState.player2.ready && currentPlayer == PLAYER2){
    //if(!gameState.player2.ready && enter_check){
      gameState.player2.character = currentChar;
      gameState.player2.ready = true;
      System.out.println("[log: player2 ready]");
      //System.out.println("Pass 2");
    }
   }
  }
}

    // Still leave it here, in case for bug and i want to fix from this.
        // protected final int TOTAL_CHAR = 3;
        // protected int currentChar = 0;
        // protected int currentPlayer = 0;
        // protected boolean popup_confirm = false;
        // if (route.keyHand.qPressed) {
        // currentChar = (currentChar - 1 + TOTAL_CHAR) % TOTAL_CHAR;
        // route.keyHand.qPressed = false;
        // }
        // if(!(gamePanel.player1.ready)) {
        // gamePanel.player1.ready = true;
        // gamePanel.player1.setCharacter("A");
        // currentPlayer = 1;
        // currentChar = 0;
        // route.keyHand.enterPressed = false;
        // }
        // if(!(gamePanel.player2.ready)) {
        // gamePanel.player2.ready = true;
        // gamePanel.player2.character = "B";
        // route.keyHand.enterPressed = false;
        // popup_confirm = true;
        // }
        // if(gamePanel.player1.ready && gamePanel.player2.ready){
        // route.currentState = route.S_ITEM_STATE;
        // gamePanel.player1.ready = false;
        // gamePanel.player2.ready = false;
        // route.keyHand.enterPressed = false;
        // }


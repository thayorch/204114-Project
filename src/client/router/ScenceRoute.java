package client.router;

import client.GameState;

public class ScenceRoute {

  private Router router;

  public ScenceRoute(Router router, GameState gameState) {
    this.router = router;
  }

  protected void update() {
    int TOTAL_BG = GameState.TOTAL_BG;
    int currentScence = GameState.currentScence;

    // System.out.println("BG : " + GameState.currentScence); // DEBUG

    if (router.keyHand.enterPressed) {
      Router.currentRoute = Router.S_CHAR_STATE;
      // System.out.println("Switch!");
      router.keyHand.enterPressed = false;
    }

    if (router.keyHand.backspacePressed) {
      Router.currentRoute = Router.LOBBY_STATE;
      router.keyHand.backspacePressed = false;
      // System.out.println("Switch Back!");
    }

    if (router.keyHand.rightPressed) {
      GameState.currentScence = (currentScence + 1) % TOTAL_BG;
      router.keyHand.rightPressed = false;
    }

    if (router.keyHand.leftPressed) {
      GameState.currentScence = (currentScence - 1 + TOTAL_BG) % TOTAL_BG;
      router.keyHand.leftPressed = false;
    }
  }
}

// This Comment waiting move file to State...
// protected final int TOTAL_BG = 3;
// protected int currentBackground = 0;

// if (router.keyHand.ePressed) {
// currentBackground = (currentBackground + 1) % TOTAL_BG;
// router.keyHand.ePressed = false;
// }

// if (router.keyHand.qPressed) {
// currentBackground = (currentBackground - 1 + TOTAL_BG) % TOTAL_BG;
// router.keyHand.qPressed = false;
// }

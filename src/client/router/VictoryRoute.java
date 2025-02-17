package client.router;

import client.GameState;
import entity.Player;

public class VictoryRoute {
    private Router router;
    private GameState gameState;
    public VictoryRoute(Router router, GameState gameState) {
        this.router = router;
        this.gameState = gameState;
    }

    protected void update() {
      Player player1 = gameState.player1;
      Player player2 = gameState.player2;

      System.out.println("P1 " + gameState.player1.win);
      System.out.println("P2 " + gameState.player2.win);

      if (router.keyHand.enterPressed) {
        Router.currentRoute = Router.LOBBY_STATE;
        player1.setDefaultvalues();
        player2.setDefaultvalues();
        gameState.currentRound = 1;
        router.keyHand.enterPressed = false;
      }
    }
}


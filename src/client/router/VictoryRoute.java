package client.router;

import client.GamePanel;
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

      if (router.keyHand.enterPressed) {
        player1.setDefaultvalues();
        player2.setDefaultvalues();
        GameState.currentRound = 1;
        GameState.currentType = 0;
        GameState.currentSlot = 0;
        Router.currentRoute = Router.LOBBY_STATE;
        router.keyHand.enterPressed = false;
      }
    }
}


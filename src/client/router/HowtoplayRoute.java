package client.router;

import java.awt.*;
import client.GameState;
import pages.Howtoplay;

public class HowtoplayRoute {
    private Router router;
    private GameState gameState;

    public HowtoplayRoute(Router router, GameState gameState) {
        this.router = router;
        this.gameState = gameState;
    }

    protected void update() {
        if (router.keyHand.enterPressed) {
            Howtoplay.pages++;
            if (Howtoplay.pages > 3) {
                Router.currentRoute = Router.LOBBY_STATE;
                Howtoplay.pages = 0;
            }
            router.keyHand.enterPressed = false;
        }
        if (router.keyHand.backspacePressed) {
            Howtoplay.pages--;
            if (Howtoplay.pages < 0) {
                Router.currentRoute = Router.LOBBY_STATE;
                Howtoplay.pages = 0;
            }
            router.keyHand.backspacePressed = false;
        }
    }

}

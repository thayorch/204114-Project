package client.router;

import java.awt.*;
import client.GamePanel;

public class LobbyRoute {
    private Router router;
    private GamePanel gamePanel;

    public LobbyRoute(Router router, GamePanel gamePanel) {
        this.router = router;
        this.gamePanel = gamePanel;
    }

    protected void update() {
        if (router.keyHand.enterPressed) {
            Router.currentRoute = Router.S_SCENCE_STATE;
            router.keyHand.enterPressed = false;
        }
    }

}

package client.router;

public class VictoryRoute {
    private Router router;
    public VictoryRoute(Router router) {
        this.router = router;
    }

    protected void update() {
        if (router.keyHand.enterPressed) {
            Router.currentRoute = Router.LOBBY_STATE;
            router.keyHand.enterPressed = false;
        }

        if (router.keyHand.backspacePressed) {
            Router.currentRoute = Router.PREVIEW_STATE;
            router.keyHand.backspacePressed = false;
        }
    }
}


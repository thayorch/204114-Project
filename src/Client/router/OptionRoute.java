package client.router;

public class OptionRoute {
    private Router router;
    public OptionRoute(Router router) {
        this.router = router;
    }

    protected void update() {
        if (router.keyHand.backspacePressed) {
            Router.currentRoute = Router.LOBBY_STATE;
            router.keyHand.backspacePressed = false;
        }
    }

}

package client.router;

public class LobbyRoute {
    private Router router;

    public LobbyRoute(Router router) {
        this.router = router;
    }
    
    protected void update() {
        if (router.keyHand.enterPressed) {
            Router.currentRoute = Router.S_SCENCE_STATE;
            router.keyHand.enterPressed = false;
        }
    }
}
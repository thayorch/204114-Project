package client.router;


public class PreviewRoute {
    private Router router;
    public PreviewRoute(Router router) {
        this.router = router;
    }

    protected void update() {
        if (router.keyHand.enterPressed) {
            Router.currentRoute = Router.VICTORY_STATE;
            router.keyHand.enterPressed = false;
        }

        if (router.keyHand.backspacePressed) {
            Router.currentRoute = Router.S_ITEM_STATE;
            router.keyHand.backspacePressed = false;
        }
    }
}
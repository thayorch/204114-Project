package client.router;

public class ItemRoute {
    private Router router;

    public ItemRoute(Router router) {
        this.router = router;
    }

    protected void update() {
        if (router.keyHand.backspacePressed) {
            Router.currentRoute = Router.S_CHAR_STATE;
            router.keyHand.backspacePressed = false;
        }

        if (router.keyHand.enterPressed) {
            Router.currentRoute = Router.PREVIEW_STATE;
            router.keyHand.enterPressed = false;
        }
        if (router.keyHand.spacePressed) {
            Router.currentRoute = Router.PREVIEW_STATE;
            router.keyHand.spacePressed = false;
        }
        
    }
}

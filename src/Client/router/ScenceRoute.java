package client.router;

public class ScenceRoute {
    private Router router;
    public ScenceRoute(Router router) {
        this.router = router;
    }
    
    protected void update() {
        if (router.keyHand.enterPressed) {
            Router.currentRoute = Router.S_CHAR_STATE;
            router.keyHand.enterPressed = false;
        }
        
        if (router.keyHand.backspacePressed) {
            Router.currentRoute = Router.LOBBY_STATE;
            router.keyHand.backspacePressed = false;
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
}

package client.router;

public class CharacterRouter {
    private Router router;

    public CharacterRouter(Router router) {
        this.router = router;
    }

    public void update() {

        if (router.keyHand.backspacePressed) {
            Router.currentRoute = Router.S_SCENCE_STATE;
            router.keyHand.backspacePressed = false;
        }

        if (router.keyHand.enterPressed) {
            Router.currentRoute = Router.S_ITEM_STATE;
            router.keyHand.enterPressed = false;
        }

        if (router.keyHand.spacePressed) {
            Router.currentRoute = Router.S_ITEM_STATE;
            router.keyHand.spacePressed = false;
        }

    // This Comment waiting move file to State...
        // protected final int TOTAL_CHAR = 3;
        // protected int currentChar = 0; 
        // protected int currentPlayer = 0;
        // protected boolean popup_confirm = false;
        // if (route.keyHand.ePressed) {
        // currentChar = (currentChar + 1) % TOTAL_CHAR;
        // route.keyHand.ePressed = false;
        // }
        // if (route.keyHand.qPressed) {
        // currentChar = (currentChar - 1 + TOTAL_CHAR) % TOTAL_CHAR;
        // route.keyHand.qPressed = false;
        // }
        // if(!(gamePanel.player1.ready)) {
        // gamePanel.player1.ready = true;
        // gamePanel.player1.setCharacter("A");
        // currentPlayer = 1;
        // currentChar = 0;
        // route.keyHand.enterPressed = false;
        // }
        // if(!(gamePanel.player2.ready)) {
        // gamePanel.player2.ready = true;
        // gamePanel.player2.character = "B";
        // route.keyHand.enterPressed = false;
        // popup_confirm = true;
        // }
        // if(gamePanel.player1.ready && gamePanel.player2.ready){
        // route.currentState = route.S_ITEM_STATE;
        // gamePanel.player1.ready = false;
        // gamePanel.player2.ready = false;
        // route.keyHand.enterPressed = false;
        // }

        
    }
}

package client;

import entity.Player;

public class GameState {

    // Constance Variable GameStates (page)
    protected final int LOBBY_STATE    = 0;
    protected final int S_SCENCE_STATE = 1;
    protected final int S_CHAR_STATE   = 2;
    protected final int S_ITEM_STATE   = 3;
    protected final int PREVIEW_STATE  = 4;
    protected final int VICTORY_STATE  = 5;
    // Constance Variable Character 
    protected final int CHAR1 = 0;
    protected final int CHAR2 = 1;
    protected final int CHAR3 = 2;
    protected int currentState;

    GamePanel gamePanel;  //from class Panel;
    KeyHandler keyHand;		// from class KeyHandler
    
    LobbyHandler lobbyHandler;	
    scenceHandler scenceHandler;
    characterHandler characterHandler;
    itemHandler itemHandler;
    previewHandler previewHandler;
    

    public GameState(GamePanel gamepanel, KeyHandler keyHand) {
        this.gamePanel = gamepanel;
        this.keyHand = keyHand;
        this.keyHand.setState(this);
        this.currentState = gamePanel.currentState;

        this.lobbyHandler = new LobbyHandler(this);
        this.scenceHandler = new scenceHandler(this);
        this.characterHandler = new characterHandler(this);
        this.itemHandler = new itemHandler(this);
        this.previewHandler = new previewHandler(this);
    }
    
    
    public GameState() {}
    public int getcurrentState() {
        return currentState;
    }

    public void update() {
        switch (currentState) {
            case LOBBY_STATE:
                lobbyHandler.update();
                break;
            case S_SCENCE_STATE:
                scenceHandler.update();
                break;
            case S_CHAR_STATE:
                characterHandler.update();
                break;
            case S_ITEM_STATE:
                itemHandler.update();
                break;
            case PREVIEW_STATE:
                previewHandler.update();
                break;
        }
    }
}

class LobbyHandler {
    GameState gameState;

    public LobbyHandler(GameState gameState) {
        this.gameState = gameState;
    }
    public void update() {
        if (gameState.keyHand.enterPressed) {
            gameState.currentState = gameState.S_SCENCE_STATE;
            gameState.keyHand.enterPressed = false;
        }
    }
}

// Background selection management class
class scenceHandler {
    GameState gameState;
    protected final int TOTAL_BG = 3;
    protected int currentBackground = 0;

    public scenceHandler(GameState gameState) {
        this.gameState = gameState;
    }

    public void update() {
        if (gameState.keyHand.ePressed) {
            currentBackground = (currentBackground + 1) % TOTAL_BG;
            gameState.keyHand.ePressed = false;
        }

        if (gameState.keyHand.qPressed) {
            currentBackground = (currentBackground - 1 + TOTAL_BG) % TOTAL_BG;
            gameState.keyHand.qPressed = false;
        }

        if (gameState.keyHand.enterPressed) {
            gameState.currentState = gameState.S_CHAR_STATE;
            gameState.keyHand.enterPressed = false;
        }
    }
}

class characterHandler {
	GamePanel gamePanel;
    GameState gameState;
    protected final int TOTAL_CHAR = 3;
    protected int currentChar = 0;
    protected int currentPlayer = 0;
    protected boolean popup_confirm = false;

    public characterHandler(GameState gameState) {
        this.gameState = gameState;
    }

    public void update() {
        if (gameState.keyHand.ePressed) {
            currentChar = (currentChar + 1) % TOTAL_CHAR;
            gameState.keyHand.ePressed = false;
        }

        if (gameState.keyHand.qPressed) {
            currentChar = (currentChar - 1 + TOTAL_CHAR) % TOTAL_CHAR;
            gameState.keyHand.qPressed = false;
        }

        if (gameState.keyHand.enterPressed) {
        	
        	if(!(gamePanel.player1.ready)) {        		
        		gamePanel.player1.ready = true;
        		gamePanel.player1.setCharacter("A");
        		currentPlayer = 1;
        		currentChar = 0;
        		gameState.keyHand.enterPressed = false;
        	}
        	if(!(gamePanel.player2.ready)) {        		
        		gamePanel.player2.ready = true;
        		gamePanel.player2.character = "B";
        		gameState.keyHand.enterPressed = false;
        		popup_confirm = true;
        	}
        	
        	if(gamePanel.player1.ready && gamePanel.player2.ready){
        		gameState.currentState = gameState.S_ITEM_STATE;
                gamePanel.player1.ready = false;
                gamePanel.player2.ready = false;
                gameState.keyHand.enterPressed = false;
              }
        
        }
    }
}


class itemHandler {
    GameState gameState;

    public itemHandler(GameState gameState) {
        this.gameState = gameState;
    }

    public void update() {
        // Ammo selection logic here
    }
}

class previewHandler {
    GameState gameState;

    public previewHandler(GameState gameState) {
        this.gameState = gameState;
    }

    public void update() {
        // Preview logic here
    }
}

package client.router;

import client.GamePanel;
import client.KeyHandler;
import client.MouseHandler;
import client.GameState;

public class Router {
    // Constance Variable GameStates (page)
    public static final int LOBBY_STATE = 0;
    public static final int S_SCENCE_STATE = 1;
    public static final int S_CHAR_STATE = 2;
    public static final int S_ITEM_STATE = 3;
    public static final int PREVIEW_STATE = 4;
    public static final int VICTORY_STATE = 5;
    public static int currentRoute;

    public KeyHandler keyHand;
    public MouseHandler mouHand;
    private LobbyRoute lobbyRoute;
    private ScenceRoute scenceRoute;
    private CharacterRouter characterRoute;
    private ItemRoute itemRoute;
    private PreviewRoute previewRoute;
    private VictoryRoute victoryRoute;
    private GameState gameState;

    public Router(KeyHandler keyHand, MouseHandler mouHand, GameState gameState) {
        currentRoute = GameState.currentRoute;
        this.keyHand = keyHand;
        this.mouHand = mouHand;

        this.keyHand.setRoute(this);
        this.mouHand.setRoute(this);

        this.gameState = gameState;
        this.lobbyRoute = new LobbyRoute(this);
        this.scenceRoute = new ScenceRoute(this, gameState);
        this.characterRoute = new CharacterRouter(this, gameState);
        this.itemRoute = new ItemRoute(this, gameState);
        this.previewRoute = new PreviewRoute(this, gameState);
        this.victoryRoute = new VictoryRoute(this);
    }

    public void update() {
      GameState.currentRoute = currentRoute;
      switch (Router.currentRoute) {
        case LOBBY_STATE:
          lobbyRoute.update();
          break;
        case S_SCENCE_STATE:
          scenceRoute.update();
          break;
        case S_CHAR_STATE:
          characterRoute.update();
          break;
        case S_ITEM_STATE:
          itemRoute.update();
          break;
        case PREVIEW_STATE:
          previewRoute.update();
          break;
        case VICTORY_STATE:
          victoryRoute.update();
          break;
        }
    }
}

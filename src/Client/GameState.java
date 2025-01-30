package client;

import entity.Player;

// This is a Game Manager but we too lazy to change it name;
public class GameState {

  private static final int LOBBY_STATE = 0;
  private static final int S_SCENCE_STATE = 1;
  private static final int S_CHAR_STATE = 2;
  private static final int S_ITEM_STATE = 3;
  private static final int PREVIEW_STATE = 4;
  private static final int VICTORY_STATE = 5;

  // Default Game State
  public static final int TOTAL_BG = 3; // n - 1
  public static final int TOTAL_CHAR = 3; // n - 1

  public static final int PLAYER1 = 0; // for check player
  public static final int PLAYER2 = 1;

  public static final int AMMO = 0; // for inventory
  public static final int ACTION = 1;

  public static int currentRoute = LOBBY_STATE;
  public static int currentPlayer = PLAYER1;
  public static int currentBG = 0;
  public static int currentChar = 0;
  public static int currentSlot = 0; // Action 5, Gun Barrel 6
  public static int currentType = 0; // Barrel 0, Action 1
  public static int currentRound = 1; // init Start Round

  public Player player1 = new Player(this, 0); // no static
  public Player player2 = new Player(this, 1);

  GamePanel gamePanel;
  KeyHandler keyHand;

  public GameState(GamePanel gamepanel, KeyHandler keyHand) {
    this.gamePanel = gamepanel;
    this.keyHand = keyHand;
  }

  public GameState() {
  }

  // LOBBY_STATE
}

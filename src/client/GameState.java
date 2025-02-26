package client;

import entity.Player;
import java.util.Random;

// This is a Game Manager but we too lazy to change it name;
public class GameState {

  private static final int LOBBY_STATE = 0;

  // Default Game State
  public static final int TOTAL_BG = 3; // n - 1
  public static final int TOTAL_CHAR = 3; // n - 1

  public static final int PLAYER1 = 0; // for check player
  public static final int PLAYER2 = 1;

  public static final int AMMO = 0; // for inventory
  public static final int ACTION = 1;

  public static int currentRoute = LOBBY_STATE;
  public static int currentPlayer = PLAYER1;
  public static int currentScence = 0;
  public static int currentChar = 0;
  public static int currentSlot = 0; // Action 5, Gun Barrel 6
  public static int currentType = 0; // Barrel 0, Action 1
  public static int currentRound = 1; // init Start Round
  public static int bulletPower = 0; // 3 great, 2 good, 1 bad, 0 worst
  public static int gauge = 0;
  public static int random_x = 0;

  private Random rand = new Random();

  public static boolean logger = false;
  public static boolean duel_input = false;
  public static boolean in_animation = false;
  public static int animationslot = 0;

  public Player player1 = new Player(this, 0); // no static
  public Player player2 = new Player(this, 1);

  GamePanel gamePanel;
  KeyHandler keyHand;

  public GameState(GamePanel gamepanel, KeyHandler keyHand) {
    this.gamePanel = gamepanel;
    this.keyHand = keyHand;
  }

  public void random_X() {
    random_x = rand.nextInt(250);
  }

  public GameState() {
  }

}

package client;

import javax.swing.JPanel;
import javax.swing.Timer;

import client.router.Router;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Color;

import entity.*;

public class GamePanel extends JPanel implements Runnable {

  // Screen Settings
  // ratios 16:9 ; 16x9, 256x144, 640x360, 960x540, 1280x720, 1920x1080
  // primary 640x360

  public static float scalingFactor = 2f;
  public static int screenWidth = (int) (640 * scalingFactor);
  public static int screenHeight = (int) (380 * scalingFactor);

  // private int totalPlayer = 2;

  // FPS
  private int FPS = 60;

  private Thread gameThread; // call game loop || call run()
  private KeyHandler keyHand = new KeyHandler((GamePanel) this);
  private MouseHandler mouHand = new MouseHandler((GamePanel) this);
  private GameState gameState = new GameState(this, keyHand);
  private Router gameRouter = new Router(this, gameState, keyHand, mouHand);
  private UI ui = new UI(this, gameState);
  public static MusicPlayer musicPlayer;

  public Player player1 = new Player(this);
  public Player player2 = new Player(this);
  public Modal debugScreen = new Modal(this, gameState);

  // Game State
  public static final int index = Router.LOBBY_STATE;
  public static boolean stopMusic = false;

  public GamePanel() {
    this.setDoubleBuffered(true); // drawing componet offscreen
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.addKeyListener(keyHand);
    this.setFocusable(true);
    musicPlayer = new MusicPlayer();

  }

  public void startGameThread() {
    gameThread = new Thread(this); // passing GamePanel Class
    gameThread.start();

    musicPlayer.play(0);

    new Timer(16, e -> debugScreen.repaint()).start();
  }

  @Override // Create Thread / game loop
  public void run() {

    double drawInterval = 1_000_000_000.0 / FPS; // 1 second / FPS
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while (gameThread != null) {
      currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / drawInterval;
      lastTime = currentTime;

      if (delta >= 1) {

        try {
          update();
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } // 1 Update: info, ammo & action phase, round result
        repaint(); // 2 Draw: scene, animation
        // it's paintComponent() but need to be call by name of repaint()
        delta--;
      }

    }
  }

  public void update() throws InterruptedException {
    gameRouter.update();
  }

  public void paintComponent(Graphics g) { // paintComponent is built in for JPanel
    super.paintComponent(g); // Parent is JPanel
    Graphics2D g2 = (Graphics2D) g; // turn to 2D
    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      ui.draw(g2);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } // interface drawing
    catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    if (GameState.logger) {
      debugScreen.log(g2);
    }
    g2.dispose(); // memory saving
  }

  public KeyHandler getKeyHand() {
    return keyHand;
  }
}

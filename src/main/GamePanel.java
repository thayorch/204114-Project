package main;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import entity.*;

public class GamePanel extends JPanel implements Runnable{

  // Screen Settings
  // ratios 16:9 ; 16x9, 256x144, 640x360, 960x540, 1280x720, 1920x1080 
  // primary 640x360
  protected float scalingFactor = 5f;
  public int screenWidth = (int)(245 * scalingFactor);
  public int screenHeight = (int)(144 * scalingFactor);

  // FPS
  private int FPS = 60;

  Thread gameThread; // call game loop, call run()
  KeyHandler keyHand = new KeyHandler(this); // passing GamePanel class
  GameState gameState = new GameState(this, keyHand);
  UI ui = new UI(this);

  public GamePanel(){
    this.setDoubleBuffered(true); // drawing componet offscreen
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.addKeyListener(keyHand);
    this.setFocusable(true);
  }

  public void startGameThread(){
    gameThread = new Thread(this); // passing GamePanel Class
    gameThread.start();
  }

  @Override // Create Thread / game loop
  public void run(){ 

    // fps stuff
    double drawInterval = 1_000_000_000.0 / FPS; // 1 second / FPS
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    // FPS counter
    //long timer = 0;
    //int drawCount = 0;

    while (gameThread != null) {
      // fps stuff
      currentTime = System.nanoTime();
      delta += (currentTime - lastTime)/drawInterval;
      lastTime = currentTime;

      if (delta >= 1){
        update(); // 1 Update: info, ammo & action phase, round result
        repaint(); // 2 Draw: scene, animation
        // it's paintComponent() but need to be call by name of repaint()
        delta--;
        //drawCount++;
      }
      /*
      timer += (currentTime - lastTime);
      // FPS counter
      if(timer >= Math.pow(10,9)){ // Every 1 second
        System.out.printf("FPS: %d\n", drawCount);
        drawCount = 0;
        timer = 0;
      } 
      */
    }
  }

  public void update() {
    gameState.update();
  }

  public void paintComponent(Graphics g) { // paintComponent is built in for JPanel
    super.paintComponent(g); // Parent is JPanel
    Graphics2D g2 = (Graphics2D)g; // turn to 2D

    gameState.draw(g2); // gameState
    g2.dispose(); // memory saving
  }

  public KeyHandler getKeyHand(){
    return keyHand;
  }
}

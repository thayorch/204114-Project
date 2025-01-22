package main;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import gamestate.*;
import entity.*;


public class GamePanel extends JPanel implements Runnable{

  // Screen Settings
  // ratios 16:9 ; 16x9, ,640x360, 960x540, 1280x720, 1920x1080 
  float scalingFactor = 1.5f;
  int screenWidth = (int)(640 * scalingFactor);
  int screenHeight = (int)(360 * scalingFactor);

  // FPS
  int FPS = 24;

  Thread gameThread; // call game loop, call run()
  KeyHandler keyHand = new KeyHandler();
  GameState gameState = new GameState();

  public GamePanel(){
    this.setDoubleBuffered(true); // drawing componet offscreen
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.addKeyListener(keyHand);
    this.setFocusable(true);
  }

  public void setupGame(){
    gameState.currentState = ammo_actionState;
  }

  public void startGameThread(){
    gameThread = new Thread(this); // passing GamePanel Class
    gameThread.start();
  }

  @Override // Create Thread / game loop
  public void run(){ 

    // fps stuff
    double drawInterval = Math.pow(10,9)/FPS; // Approximate 1 sec
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    // FPS counter
    long timer = 0;
    int drawCount = 0;

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
        drawCount++;
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
    switch (gameState.currentState) {
      case(gameState.titleState):
        break;
      case gameState.characterState:
        break;
      case gameState.ammo_actionState:
        break;
      case gameState.previewState:
        break;
      case gameState.bulletTimeState:
        break;
    }
    
    if(keyHand.qPressed) {} // left item
    if(keyHand.ePressed) {} // right item
    if(keyHand.vPressed) {} // visable action order
    if(keyHand.spacePressed) {} // select item
    
  }

  public void paintComponent(Graphics g) { // paintComponent is built in for JPanel
    super.paintComponent(g); // Parent is JPanel
    Graphics2D g2 = (Graphics2D)g; // turn to 2D
    g2.setColor(Color.white);
    g2.fillRect(100,100,100,100); // x,y,w,h
    g2.dispose(); // memory saving
  }
}

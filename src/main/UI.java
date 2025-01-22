package main;

import java.awt.Graphics2D;
import java.awt.Color;
import gamestate.GameState;

public class UI {
  GameState gameState = new GameState();
  GamePanel gp;
  Graphics2D g2;
  int screenWidth;
  int screenHeight;

  public UI(GamePanel gp){
    this.gp = gp;
    screenWidth = gp.screenWidth;
    screenHeight = gp.screenHeight;
  }

  public UI(GameState gameState){
    this.gameState = gameState;
  }
}

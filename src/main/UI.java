package main;

import java.awt.Graphics2D;
import gamestate.GameState;

public class UI {
  GameState gameState = new GameState();
  GamePanel gp;
  Graphics2D g2;

  public UI(GamePanel gp){
    this.gp = gp;
  }

  public UI(GameState gameState){
    this.gameState = gameState;
  }

  public void draw(Graphics2D g2) {
    this.g2 = g2;
    switch (gameState.currentState) {
      case gameState.titleState:
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
  }
  public void drawTitleState(){

  }
  public void drawCharacterState(){

  }
  public void drawAmmoState(){

  }
}

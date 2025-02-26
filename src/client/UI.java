package client;

import java.awt.Graphics2D;
import java.io.IOException;

import client.router.Router;
import entity.Animation;
import pages.*;

public class UI {

  // Page Objects
  private Lobby lobby;
  private S_Scence scence;
  private S_Char scenceChar;
  public static S_Item scenceItem;
  private Preview preview;
  private Victory victory;
  private Howtoplay howtoplay;
  public static Animation animation;

  public UI(GamePanel gamePanel, GameState gameState) {
    this.lobby = new Lobby(gamePanel, gameState);
    this.scence = new S_Scence(gamePanel, gameState);
    this.scenceChar = new S_Char(gamePanel, gameState);
    this.scenceItem = new S_Item(gamePanel, gameState);
    this.preview = new Preview(gamePanel, gameState);
    this.victory = new Victory(gamePanel, gameState);
    this.howtoplay = new Howtoplay(gamePanel, gameState);
    UI.animation = new Animation();
  }

  // Page rendering
  public void draw(Graphics2D g2) throws IOException, InterruptedException { // Graphics2D fronm GamePanel.java
    switch (GameState.currentRoute) {
      case (Router.LOBBY_STATE):
        lobby.render(g2);
        break;
      case (Router.S_SCENCE_STATE):
        scence.render(g2);
        break;
      case (Router.S_CHAR_STATE):
        scenceChar.render(g2);
        break;
      case (Router.S_ITEM_STATE):
        scenceItem.render(g2);
        break;
      case (Router.PREVIEW_STATE):
        S_Item.resetItem();
        preview.render(g2);
        break;
      case (Router.VICTORY_STATE):
        victory.render(g2);
        break;
      case (Router.HOW_TO_PLAY):
        howtoplay.render(g2);
        break;
    }

  }
}

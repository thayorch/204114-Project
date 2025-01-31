package client;

import java.awt.Graphics2D;

import client.router.Router;
import pages.*;

public class UI extends GameState {

  // Page Objects
  private Lobby lobby;
  private S_Scence scence;
  private S_Char scenceChar;
  private S_Item scenceItem;
  private Preview preview;
  private Victory victory;
  private Option option;

  public UI(GamePanel gamePanel, GameState gameState) {
    this.gamePanel = gamePanel;
    this.lobby = new Lobby(gamePanel, gameState);
    this.scence = new S_Scence(gamePanel, gameState);
    this.scenceChar = new S_Char(gamePanel, gameState);
    this.scenceItem = new S_Item(gamePanel, gameState);
    this.preview = new Preview(gamePanel, gameState);
    this.victory = new Victory(gamePanel, gameState);
    this.option = new Option(gamePanel, gameState);
  }

  // Page rendering
  public void draw(Graphics2D g2) { // Graphics2D fronm GamePanel.java
    switch (Router.currentRoute) {
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
        preview.render(g2);
        break;
      case (Router.VICTORY_STATE):
        victory.render(g2);
        break;
      case (Router.OPTION_STATE):
        option.render(g2);
        break;
    }
  }
}
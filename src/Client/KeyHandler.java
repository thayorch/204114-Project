package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import client.router.Router;

public class KeyHandler implements KeyListener {

  GamePanel gamePanel;
  // GameState gameState;
  Router route; 

  public boolean qPressed, ePressed, spacePressed,
      enterPressed, backspacePressed, wPressed, sPressed, numPressed;
  public int numPressedNUM;

  public KeyHandler(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  public void setRoute(Router route) {
    this.route = route;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  // Key at Page
  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode(); // return number of key
    switch (Router.currentRoute) {
      case (0): // Lobby
        LobbyKeyPress(code);
        break;

      case (1): // Select_Scence
        ScenceKeyPress(code);
        break;

      case (2): // Select_Character
        CharacterKeyPress(code);
        break;

      case (3): // Select_Item
        ItemKeyPress(code);
        break;

      case (4): // Preview
        PreviewKeyPress(code);
        break;

      case (5): // Victory
        VictoryKeyPress(code);
        break;
        case (6):
        OptionKeyPress(code);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();

    switch (code) {
      case (KeyEvent.VK_Q):
        qPressed = false;
        break;

      case (KeyEvent.VK_E):
        ePressed = false;
        break;

      case (KeyEvent.VK_SPACE):
        spacePressed = false;
        break;

      case (KeyEvent.VK_ENTER):
        enterPressed = false;
        break;

      case (KeyEvent.VK_BACK_SPACE):
        backspacePressed = false;
        break;

      case (KeyEvent.VK_1):
        numPressed = false;
        break;

      case (KeyEvent.VK_2):
        numPressed = false;
        break;

      case (KeyEvent.VK_3):
        numPressed = false;
        break;
    }
  }

  // For each Game State

  private void LobbyKeyPress(int code) {
    switch (code) {
      case (KeyEvent.VK_ENTER):
        enterPressed = true;
        break;
      default:
        break;
    }
  }

  private void ScenceKeyPress(int code) {
    switch (code) {
      case (KeyEvent.VK_Q):
        qPressed = true;
        break;
      case (KeyEvent.VK_E):
        ePressed = true;
        break;
      case (KeyEvent.VK_ENTER):
        enterPressed = true;
        break;
      case (KeyEvent.VK_BACK_SPACE):
        backspacePressed = true;
        break;
    }
  }

  private void CharacterKeyPress(int code) {
    switch (code) {
      case (KeyEvent.VK_Q):
        qPressed = true;
        break;
      case (KeyEvent.VK_E):
        ePressed = true;
        break;
      case (KeyEvent.VK_ENTER):
        enterPressed = true;
        break;
      case (KeyEvent.VK_BACK_SPACE):
        backspacePressed = true;
        break;
    }
  }

  private void ItemKeyPress(int code) {
    switch (code) {
      case (KeyEvent.VK_Q):
        qPressed = true;
        break;
      case (KeyEvent.VK_E):
        ePressed = true;
        break;
      case (KeyEvent.VK_ENTER):
        enterPressed = true;
        break;
      case (KeyEvent.VK_1):
        numPressed = true;
        numPressedNUM = 1;
        break;
      case (KeyEvent.VK_2):
        numPressed = true;
        numPressedNUM = 2;
        break;
      case (KeyEvent.VK_3):
        numPressed = true;
        numPressedNUM = 3;
        break;
      case (KeyEvent.VK_4):
        numPressed = true;
        numPressedNUM = 4;
        break;
      case (KeyEvent.VK_SPACE):
        spacePressed = true;
        break;
      case (KeyEvent.VK_BACK_SPACE):
        backspacePressed = true;
        break;
    }
  }

  private void PreviewKeyPress(int code) {

    switch (code) {
      case (KeyEvent.VK_ENTER):
        enterPressed = true;
        break;
      case (KeyEvent.VK_BACK_SPACE):
        backspacePressed = true;
        break;
    }
  }

  private void VictoryKeyPress(int code) {

    switch (code) {
      case (KeyEvent.VK_ENTER):
        enterPressed = true;
        break;
      case (KeyEvent.VK_BACK_SPACE):
        backspacePressed = true;
        break;
    }
  }

  private void OptionKeyPress(int code){
    switch (code) {
      case (KeyEvent.VK_BACK_SPACE):
        backspacePressed = true;
        break;
    }
  }
}
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

  GamePanel gp;

  public boolean qPressed, ePressed, spacePressed, enterPressed, wPressed, sPressed, numPressed;
  public int numPressedNUM;

  public KeyHandler(GamePanel gp){
    this.gp = gp;
  }


  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode(); // return number of key
    switch (gp.currentState) {
      case(0): // title
        TitleKeyPress(code);
        break;

      case(1): // bg
        BGKeyPress(code);
        break;

      case(2): // character
        CharPress(code);
        break;

      case(3): // ammo
        AmmoKeyPress(code);
        break;

      case(4): // action
        break;

      case(5): //bulletTime
        break;
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
  
  private void TitleKeyPress(int code){
    if(code == KeyEvent.VK_ENTER){
      enterPressed = true;
    }
  }

  private void BGKeyPress(int code){
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
    }
  }

  private void CharPress(int code) {
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
    }
  }

  private void AmmoKeyPress(int code) {
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
    }
  }
}

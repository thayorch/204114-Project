package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

  GamePanel gp;
  public boolean qPressed,ePressed,spacePressed,vPressed;
  public KeyHandler(GamePanel gp){
    this.gp = gp;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode(); // return number of key

    switch (code) {

      case (KeyEvent.VK_Q):
        qPressed = true;
        break;

      case (KeyEvent.VK_E):
        ePressed = true;
        break;

      case (KeyEvent.VK_V):
        vPressed = true;
        break;

      case (KeyEvent.VK_SPACE):
        spacePressed = true;
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

      case (KeyEvent.VK_V):
        vPressed = false;
        break;

      case (KeyEvent.VK_SPACE):
        spacePressed = false;
        break;
    }
  }
  
}

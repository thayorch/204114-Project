package  client;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;

import client.router.Router;
import client.GamePanel;

public class MouseHandler extends MouseAdapter{

  private GamePanel gamePanel;
  private Router route;

  public MouseHandler(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  public void setRoute(Router route) {
    this.route = route;
  }

  //private Rectangle hoverStart, hoverOption, hoverExit;

  public Rectangle createButton(int x, int y, int w, int h){
    return new Rectangle(x, y, w, h);
  }
  
  //@Override
  public void mouseMoved(MouseEvent e){
    Point mousePoint = e.getPoint();
    //LobbyMouseHover(mousePoint);
    switch (Router.currentRoute) {
      case (0):
        LobbyMouseHover(mousePoint);
        break;
    }
  }
  
  @Override
  public void mouseClicked(MouseEvent e) {
    Point mousePoint = e.getPoint();
    //LobbyMouseHover(mousePoint);
    switch (Router.currentRoute) {
      case (0):
        LobbyMouseClick(mousePoint);
        break;
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
      // You can leave this empty if you don't need it
  }
  
  @Override
  public void mouseExited(MouseEvent e) {
      // You can leave this empty if you don't need it
  }
  
  @Override
  public void mousePressed(MouseEvent e) {
      // You can leave this empty if you don't need it
  }
  
  @Override
  public void mouseReleased(MouseEvent e) {
      // You can leave this empty if you don't need it
  }

  private void LobbyMouseHover(Point e){

    // This just a sample
    Point mousePoint = e;
    int screenWidth = GamePanel.screenWidth;
    float scalingFactor = GamePanel.scalingFactor;

    // Creat Button
    Rectangle btnStart = new Rectangle((screenWidth / 2) - (int)(77.5 * scalingFactor), 154 - (int)(35 * scalingFactor), (int)(145 * scalingFactor), (int)(50 * scalingFactor));
    Rectangle btnOption = new Rectangle((screenWidth / 2) - (int)(77.5 * scalingFactor), 225 - (int)(35 * scalingFactor), (int)(145 * scalingFactor), (int)(50 * scalingFactor));
    Rectangle btnExit = new Rectangle((screenWidth / 2) - (int)(77.5 * scalingFactor), 297 - (int)(35 * scalingFactor), (int)(145 * scalingFactor), (int)(50 * scalingFactor));

    // Check if the mouse is hovering over any button
    boolean hoverStart = btnStart.contains(mousePoint);
    boolean hoverOption = btnOption.contains(mousePoint);
    boolean hoverExit = btnExit.contains(mousePoint);

    if (hoverStart || hoverOption || hoverExit) 
      System.out.println("Yes");
      //gamePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    else 
      System.out.println("No");
  }

  private void LobbyMouseClick(Point e){

  }
}

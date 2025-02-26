package client;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import client.router.Router;

public class MouseHandler extends MouseAdapter {

  private GamePanel gamePanel;
  public Rectangle startButtonBounds;
  protected Rectangle optionButtonBounds;
  public Rectangle howButtonBounds;
  public Rectangle exitButtonBounds;
  private boolean hoverStart, hoverExit, hoverHow;

  public MouseHandler(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  public void update() {
    switch (Router.currentRoute) {
      case 0:
        this.startButtonBounds = new Rectangle((gamePanel.screenWidth / 2) - 155, 307 - 70, 290, 100);
        this.exitButtonBounds = new Rectangle((gamePanel.screenWidth / 2) - 155, 450 - 70, 290, 100);
        this.howButtonBounds = new Rectangle((gamePanel.screenWidth / 2) - 155, 700, 300, 100);

        LobbyMouseHover();
        LobbyMouseClick();
        break;
    }
  }

  private void LobbyMouseHover() {
    gamePanel.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
        Point mousePoint = e.getPoint();

        hoverStart = startButtonBounds.contains(mousePoint);
        hoverExit = exitButtonBounds.contains(mousePoint);
        hoverHow = howButtonBounds.contains(mousePoint);

        if (hoverStart || hoverHow || hoverExit) {
          gamePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
          gamePanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        gamePanel.repaint();
      }
    });
  }

  private void LobbyMouseClick() {
    gamePanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Point clickPoint = e.getPoint();

        if (startButtonBounds.contains(clickPoint)) {
          startButtonBounds = new Rectangle(0, 0, 0, 0);
          exitButtonBounds = new Rectangle(0, 0, 0, 0);
          howButtonBounds = new Rectangle(0, 0, 0, 0);
          GamePanel.musicPlayer.sfx("click");
          Router.currentRoute = Router.S_SCENCE_STATE;
        }
        if (exitButtonBounds.contains(clickPoint)) {
          System.exit(0);
        }
        if (howButtonBounds.contains(clickPoint)) {
          startButtonBounds = new Rectangle(0, 0, 0, 0);
          exitButtonBounds = new Rectangle(0, 0, 0, 0);
          howButtonBounds = new Rectangle(0, 0, 0, 0);
          GamePanel.musicPlayer.sfx("click");
          Router.currentRoute = Router.HOW_TO_PLAY;
        }
      }
    });
  }
}
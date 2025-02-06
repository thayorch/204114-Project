package client.router;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import client.GamePanel;
import client.GameState;

public class LobbyRoute {
    private Router router;
    private boolean hoverStart, hoverExit;
    private GamePanel gamePanel;
    public Rectangle startButtonBounds, optionButtonBounds, exitButtonBounds;

    public LobbyRoute(Router router, GamePanel gamePanel) {
        this.router = router;
        this.gamePanel = gamePanel;
        // setupMouseListener();
    }

    protected void update() {
        if (router.keyHand.enterPressed) {
            Router.currentRoute = Router.S_SCENCE_STATE;
            router.keyHand.enterPressed = false;
        }
    }

    protected void setupMouseListener() {

        this.startButtonBounds = new Rectangle((gamePanel.screenWidth / 2) - 155, 307 - 70, 290, 100);
        this.exitButtonBounds = new Rectangle((gamePanel.screenWidth / 2) - 155, 450 - 70, 290, 100);

        gamePanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point mousePoint = e.getPoint();

                hoverStart = startButtonBounds.contains(mousePoint);
                hoverExit = exitButtonBounds.contains(mousePoint);

                if (hoverStart || hoverExit) {
                    gamePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    gamePanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
                gamePanel.repaint();
            }
        });

        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickPoint = e.getPoint();
                if (startButtonBounds.contains(clickPoint)) {
                    Router.currentRoute = Router.S_SCENCE_STATE;
                } else if (exitButtonBounds.contains(clickPoint)) {
                    System.exit(0);
                }
            }
        });

    }
}
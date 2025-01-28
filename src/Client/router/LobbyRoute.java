package client.router;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import client.GamePanel;

public class LobbyRoute {
    private Router router;
    private boolean hoverStart, hoverOption, hoverExit;
    private GamePanel gamePanel;
    protected Rectangle startButtonBounds, optionButtonBounds, exitButtonBounds;

    public LobbyRoute(Router router, GamePanel gamePanel) {
        this.router = router;
        this.gamePanel = gamePanel;
        setupMouseListener();
    }

    protected void update() {
        if (router.keyHand.enterPressed) {
            Router.currentRoute = Router.S_SCENCE_STATE;
            router.keyHand.enterPressed = false;
        }
    }

        protected void setupMouseListener() {
        this.startButtonBounds = new Rectangle((gamePanel.screenWidth / 2) - 155, 307 - 70, 290, 100);
        this.optionButtonBounds = new Rectangle((gamePanel.screenWidth / 2) - 155, 450 - 70, 290, 100);
        this.exitButtonBounds = new Rectangle((gamePanel.screenWidth / 2) - 155, 593 - 70, 290, 100);
        gamePanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point mousePoint = e.getPoint();

                // Check if the mouse is hovering over any button
                hoverStart = startButtonBounds.contains(mousePoint);
                hoverOption = optionButtonBounds.contains(mousePoint);
                hoverExit = exitButtonBounds.contains(mousePoint);

                // Set cursor to hand pointer if hovering over a button
                if (hoverStart || hoverOption || hoverExit) {
                    gamePanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    gamePanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }

                // Repaint the game panel to reflect hover changes
                gamePanel.repaint();
            }
        });

        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point clickPoint = e.getPoint();
                if (startButtonBounds.contains(clickPoint)) {
                    System.out.println("Start button clicked!");
                    Router.currentRoute = Router.S_SCENCE_STATE;

                } else if (optionButtonBounds.contains(clickPoint)) {
                    System.out.println("Option button clicked!");
                    Router.currentRoute = Router.OPTION_STATE;

                } else if (exitButtonBounds.contains(clickPoint)) {
                    System.out.println("Exit button clicked!");
                    System.exit(0);
                }
            }
        });

    }
}
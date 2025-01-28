package client.router;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import client.GamePanel;
import client.KeyHandler;

public class Router {
    // Constance Variable GameStates (page)
    public static final int LOBBY_STATE = 0;
    public static final int S_SCENCE_STATE = 1;
    public static final int S_CHAR_STATE = 2;
    public static final int S_ITEM_STATE = 3;
    public static final int PREVIEW_STATE = 4;
    public static final int VICTORY_STATE = 5;
    public static final int OPTION_STATE = 6;

    public static int currentRoute;
    public KeyHandler keyHand;
    private LobbyRoute lobbyRoute;
    private ScenceRoute scenceRoute;
    private CharacterRouter characterRoute;
    private ItemRoute itemRoute;
    private PreviewRoute previewRoute;
    private VictoryRoute victoryRoute;
    private OptionRoute optionRoute;

    private Rectangle startButtonBounds, optionButtonBounds, exitButtonBounds;
    private boolean hoverStart, hoverOption, hoverExit;
    private GamePanel gamePanel;

    public Router(GamePanel gamePanel, KeyHandler keyHand) {
        this.gamePanel = gamePanel;
        Router.currentRoute = GamePanel.index;
        this.keyHand = keyHand;
        this.keyHand.setRoute(this);
        this.lobbyRoute = new LobbyRoute(this);
        this.scenceRoute = new ScenceRoute(this);
        this.characterRoute = new CharacterRouter(this);
        this.itemRoute = new ItemRoute(this);
        this.previewRoute = new PreviewRoute(this);
        this.victoryRoute = new VictoryRoute(this);
        this.optionRoute = new OptionRoute(this);

        this.startButtonBounds = new Rectangle((gamePanel.screenWidth / 2) - 155, 307 - 70, 290, 100);
        this.optionButtonBounds = new Rectangle((gamePanel.screenWidth / 2) - 155, 450 - 70, 290, 100);
        this.exitButtonBounds = new Rectangle((gamePanel.screenWidth / 2) - 155, 593 - 70, 290, 100);
        setupMouseListener();
    }

    public void update() {
        switch (currentRoute) {
            case LOBBY_STATE:
                lobbyRoute.update();
                break;
            case S_SCENCE_STATE:
                scenceRoute.update();
                break;
            case S_CHAR_STATE:
                characterRoute.update();
                break;
            case S_ITEM_STATE:
                itemRoute.update();
                break;
            case PREVIEW_STATE:
                previewRoute.update();
                break;
            case VICTORY_STATE:
                victoryRoute.update();
                break;
            case OPTION_STATE:
                optionRoute.update();
                break;
        }
    }

    private void setupMouseListener() {
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

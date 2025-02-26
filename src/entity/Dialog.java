package entity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;
import client.GamePanel;
import client.GameState;
import client.Component;

public class Dialog {

  private final int x_position, y_position;
  public static int gauge, bullet_gauge1 = 0, bullet_gauge2 = 680;
  private int gaugeSpeed = 15; // Speed of the gauge movement
  private Timer timer, LtoR, RtoL; // Timer for animation
  private GameState gameState;
  private int bulletPower = 0;
  private int zone_3_width = 8;
  private int zone_2_width = 14;
  private int zone_1_width = 20;
  private BufferedImage scope;
  private Component component;

  public Dialog(GamePanel gamePanel, GameState gameState) {
    this.gameState = gameState;
    this.x_position = gamePanel.screenWidth / 2 - 170;
    this.y_position = gamePanel.screenHeight / 2 + 300;
    this.component = new Component(gamePanel, gameState);
    gauge = x_position; // Initialize gauge position
    loadassets();

    timer = new Timer(10, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        updateGaugePosition(); // Update gauge position
        gamePanel.repaint(); // Repaint the screen
      }
    });
    timer.start();
    
  }

  private void updateGaugePosition() {
    // Define the boundaries for the gauge movement
    int minX = x_position;
    int maxX = x_position + 360 - 10; // Adjust for gauge width

    // Move the gauge
    gauge += gaugeSpeed;

    // Reverse direction if the gauge hits the boundaries
    if (gauge > maxX || gauge < minX) {
      gaugeSpeed *= -1; // Reverse direction
    }
  }


  public void showDialog(Graphics2D g2, int random_x) {
    GameState.gauge = gauge;

    // Enable anti-aliasing for smoother graphics
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Draw semi-transparent background
    g2.setColor(new Color(0, 0, 0, 150));
    g2.fillRect(x_position, y_position, 360, 25);

    // Draw the zone   
    // zone 3
    g2.setColor(new Color(220, 20, 60));
    g2.fillRect(x_position + random_x, y_position, zone_3_width, 25);

    // zone 2
    g2.setColor(new Color(255, 215, 0));
    g2.fillRect(x_position + random_x - zone_2_width, y_position, zone_2_width, 25); // left
    g2.fillRect(x_position + random_x + zone_3_width, y_position, zone_2_width, 25); // right

    // zone 1
    g2.setColor(new Color(0, 255, 0));

    g2.fillRect(x_position + random_x - zone_1_width - zone_2_width, y_position, zone_1_width, 25);
    g2.fillRect(x_position + random_x + zone_3_width + zone_2_width, y_position, zone_1_width, 25);

    // Draw the red gauge (moving rectangle)
    g2.setColor(new Color(0, 150, 255));
    g2.drawImage(scope, gauge, y_position - 10, null);
    // g2.fillRect(gauge, y_position, 5, 25);

    // zone 3
    if (gauge >= x_position + random_x && gauge <= x_position + random_x + zone_3_width)
      gameState.bulletPower = 3;

    // zone 2
    else if (gauge >= x_position + random_x - zone_2_width && gauge <= x_position + random_x + zone_2_width) // left
      gameState.bulletPower = 2;

    else if (gauge >= x_position + random_x + zone_3_width
        && gauge <= x_position + random_x + zone_3_width + zone_2_width) // right
      gameState.bulletPower = 2;

    // zone 1
    else if (gauge >= x_position + random_x - zone_2_width - zone_1_width
        && gauge <= x_position + random_x + zone_2_width + zone_1_width) // left
      gameState.bulletPower = 1;

    else if (gauge >= x_position + random_x + zone_3_width + zone_2_width
        && gauge <= x_position + random_x + zone_3_width + zone_2_width + zone_1_width) // right
      gameState.bulletPower = 1;

    // zone 0
    else
      gameState.bulletPower = 0;
  }

  public void loadassets() {
    // Load the dialog assets
    try {
      scope = component.img("/resources/previewScene/scope.png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

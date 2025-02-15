package entity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import client.GamePanel;
import client.GameState;

public class Dialog {

    private final int x_position, y_position;
    public static int gate;
    private int gateSpeed = 5; // Speed of the gate movement
    private Timer timer; // Timer for animation

    public Dialog(GamePanel gamePanel, GameState gameState) {
        this.x_position = GamePanel.screenWidth / 2 - 170;
        this.y_position = GamePanel.screenHeight / 2 + 300;
        gate = x_position; // Initialize gate position

        // Initialize and start the timer for animation
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGatePosition(); // Update gate position
                gamePanel.repaint(); // Repaint the screen
            }
        });
        timer.start(); // Start the animation
    }

    private void updateGatePosition() {
        // Define the boundaries for the gate movement
        int minX = x_position;  
        int maxX = x_position + 360 - 10; // Adjust for gate width

        // Move the gate
        gate += gateSpeed;

        // Reverse direction if the gate hits the boundaries
        if (gate > maxX || gate < minX) {
            gateSpeed *= -1; // Reverse direction
        }
    }
    
    private void stopAnimation() {
        if (timer != null) {
            timer.stop();
        }
    }

    public int getGate(){
        return gate;
    }


    public void showDialog(Graphics2D g2) {
        // Enable anti-aliasing for smoother graphics
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw semi-transparent background
        g2.setColor(new Color(0, 0, 0, 150));
        // g2.fillRect(x_position, y_position, 360, 25);
        g2.fillRoundRect(x_position, y_position, 360, 25, 15,15);
        
        g2.setColor(new Color(0, 255, 0));
        g2.fillRoundRect(x_position+200, y_position, 60, 25,15,15);

        // Draw the red gate (moving rectangle)
        g2.setColor(new Color(255, 0, 0));
        g2.fillRoundRect(gate, y_position, 10, 25,5,5);

    }
}
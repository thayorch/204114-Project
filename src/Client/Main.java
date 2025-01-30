package client;

import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {

    JFrame frame = new JFrame();
    GamePanel gamePanel = new GamePanel();

    frame.add(gamePanel);
    frame.pack(); // set size to fit perferred size

    frame.setResizable(false); // Prevent resizeable
    frame.setTitle("Gunslinger V.0.1"); // Set Title
    
    // frame.setLayout(new BorderLayout()); // Set the layout manager for the frame

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
    frame.setLocationRelativeTo(null); // Set Window position to center

    gamePanel.startGameThread(); // Start GameLoop
    frame.setVisible(true); // Make the frame setVisible
  }
}

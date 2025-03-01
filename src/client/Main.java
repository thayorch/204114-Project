package client;

import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class Main {
  public static void main(String[] args) {

    JFrame frame = new JFrame();
    GamePanel gamePanel = new GamePanel();
    ImageIcon img = new ImageIcon("resources/assets/icon.png");

    frame.add(gamePanel);
    frame.pack(); // set size to fit perferred size

    frame.setResizable(false); // Prevent resizeable
    frame.setTitle("Pew Pew!"); // Set Title
    frame.setIconImage(img.getImage());
    // frame.setLayout(new BorderLayout()); // Set the layout manager for the frame

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
    frame.setLocationRelativeTo(null); // Set Window position to center

    gamePanel.startGameThread(); // Start GameLoop
    frame.setVisible(true); // Make the frame setVisible
  }
}

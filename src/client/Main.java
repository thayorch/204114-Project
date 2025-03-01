package client;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {

    JFrame frame = new JFrame();
    GamePanel gamePanel = new GamePanel();
    //ImageIcon img = new ImageIcon("resources/assets/icon.png");
    ImageIcon img;

    try {
      img = new ImageIcon(ImageIO.read(Main.class.getResourceAsStream("/resources/assets/icon.png")));
    } catch (IOException e) {
      e.printStackTrace();
      img = null;
    }

    frame.add(gamePanel);
    frame.pack(); // set size to fit perferred size

    frame.setResizable(false); // Prevent resizeable
    frame.setTitle("Pew Pew!"); // Set Title
    frame.setIconImage(img.getImage()); // Set Icon

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
    frame.setLocationRelativeTo(null); // Set Window position to center

    gamePanel.startGameThread(); // Start GameLoop
    frame.setVisible(true); // Make the frame setVisible
  }
}

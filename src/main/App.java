package main;

// import lib
import javax.swing.JFrame;
import java.awt.BorderLayout;
//import javax.swing.JLabel;

public class App {
  public static void main(String[] args) {

    // init Object
    JFrame frame = new JFrame(); 
    GamePanel gamePanel = new GamePanel();

    frame.add(gamePanel);
    frame.pack(); // set size to fit perferred size

    frame.setResizable(false); // Prevent resizeable
    frame.setTitle("Gunslinger V.0.1"); // Set Title
    //frame.setLayout(new BorderLayout()); // Set the layout manager for the frame

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
    frame.setLocationRelativeTo(null); // Set Window position to center

    gamePanel.startGameThread(); // Start GameLoop
    frame.setVisible(true); // Make the frame setVisible
  }
}


import javax.swing.*;
import java.awt.*;

public class HealthBarExample extends JPanel {
    private int health = 50; // Example health value (out of 100)

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background bar
        g2d.setColor(Color.GRAY);
        g2d.fillRect(50, 50, 200, 20);

        // Health bar (green when healthy, red when low)
        if (health > 50) {
            g2d.setColor(Color.GREEN);
        } else if (health > 20) {
            g2d.setColor(Color.ORANGE);
        } else {
            g2d.setColor(Color.RED);
        }
        g2d.fillRect(50, 50, health * 2, 20); // Scaling health to bar width

        // Health text
        g2d.setColor(Color.WHITE);
        g2d.drawString("Health: " + health + "/100", 100, 65);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Health Bar Example");
        HealthBarExample panel = new HealthBarExample();
        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

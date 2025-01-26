import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationExample extends JPanel implements ActionListener {
    private Image spriteSheet; // The spritesheet image
    private int frameWidth = 200;  // Width of each frame in the spritesheet
    private int frameHeight = 200; // Height of each frame in the spritesheet
    private int frameCount = 4;   // Total number of frames in the animation
    private int currentFrame = 0; // Index of the current frame
    private Timer timer;          // Timer to control animation speed

    public AnimationExample() {
        // Load the spritesheet
        spriteSheet = new ImageIcon("spritesheetRF2.png").getImage();

        // Set up a timer (200ms delay for ~5 FPS)
        timer = new Timer(180, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the x-coordinate of the current frame in the spritesheet
        int frameX = currentFrame * frameWidth;

        // Center the character in the panel
        int centerX = (getWidth() - frameWidth) / 2; // Horizontal centering
        int centerY = (getHeight() - frameHeight) / 2; // Vertical centering

        // Draw the current frame from the spritesheet
        g.drawImage(spriteSheet, centerX, centerY, centerX + frameWidth, centerY + frameHeight, 
                    frameX, 0, frameX + frameWidth, frameHeight, this);
        // g.drawImage(spriteSheet, 0, 0, 0 + frameWidth, 0 + frameHeight, 
        //             frameX, 0, frameX + frameWidth, frameHeight, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the frame index
        currentFrame = (currentFrame + 1) % frameCount;
        repaint(); // Repaint the panel to show the new frame
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Character Animation");
        AnimationExample animationPanel = new AnimationExample();

        frame.add(animationPanel);
        frame.setSize(640, 380);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

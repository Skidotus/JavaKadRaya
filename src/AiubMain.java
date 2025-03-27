import java.awt.*;
import javax.swing.*;

public class AiubMain {
    public static void main(String args[]) {

        // Set up JFrame
        JFrame frame = new JFrame("Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Adjust size as needed

        // Create a JLayeredPane for layering components
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 800, 600);

        // Add ajax-loader.gif to the layered pane
        Icon imgIcon = new ImageIcon(AiubMain.class.getResource("/videos/tet.gif"));
        JLabel gifLabel = new JLabel(imgIcon);
        gifLabel.setBounds(0, 0, 800, 600); // Adjust size to match the frame
        layeredPane.add(gifLabel, Integer.valueOf(1)); // Add GIF to the background layer

        // Add text over the GIF
        JLabel textLabel = new JLabel("Loading...", SwingConstants.CENTER);
        textLabel.setFont(new Font("Serif", Font.BOLD, 24));
        textLabel.setForeground(Color.WHITE); // Adjust color as needed
        textLabel.setBounds(0, 500, 800, 50); // Position the text over the GIF
        layeredPane.add(textLabel, Integer.valueOf(2)); // Add text to the foreground layer

        // Add the layered pane to the frame
        frame.add(layeredPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
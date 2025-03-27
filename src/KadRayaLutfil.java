import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;


public class KadRayaLutfil {

    public KadRayaLutfil() {
        // Create the main frame
        JFrame frameKad = new JFrame();

        //tknak bagi besar
        frameKad.setResizable(false);

        // Create a layered pane for stacking elements
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 500));

        // Create background panel (lowest layer)
        BackgroundPanel backgroundPanel = new BackgroundPanel("tobtobitop.png");
        backgroundPanel.setBounds(0, 0, 500, 500);

        // Create foreground image (top layer)
        JLabel foregroundLabel = new JLabel(new ImageIcon(getClass().getResource("layer1eid.png")));
        foregroundLabel.setBounds(0, 0, 500, 500); 
        // Create BIG title that fits the frame
        JLabel greetingLabel = createStyledLabel("Selamat Hari Raya", 50);
        greetingLabel.setBounds(0, 0, 500, 500); 

        // Create moving text
        JLabel movingText = createStyledLabel("Maaf Zahir dan Batin!!!", 30);
        movingText.setBounds(500, 300, 200, 50); // start off-screen

        // Add components to layered pane
        layeredPane.add(backgroundPanel, Integer.valueOf(1)); // Background
        layeredPane.add(greetingLabel, Integer.valueOf(2)); // Big Title (Centered)
        layeredPane.add(foregroundLabel, Integer.valueOf(3)); // Foreground image
        layeredPane.add(movingText, Integer.valueOf(4)); // Moving Text

        frameKad.add(layeredPane);

        // Frame settings
        frameKad.setTitle("Kad Hari Raya");
        frameKad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameKad.setSize(500, 500);
        frameKad.setLocationRelativeTo(null);
        frameKad.setVisible(true);

        // Start moving the text
        startMovingText(movingText);

        // Start playing music
        music();
    }

    private JLabel createStyledLabel(String text, int fontSize) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, fontSize));
        label.setForeground(new Color(1, 1, 1)); // Gold color
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private void startMovingText(JLabel movingText) {
        Timer timer = new Timer(10, e -> {
            int x = movingText.getX() - 1; // Move left by 1 pixel
            if (x + movingText.getWidth() < 0) {
                x = 500; // Reset to start from the right again
            }
            movingText.setLocation(x, movingText.getY()); // Update position
        });
        timer.start();
    }

    public static void music() {
        try {
            // Ensure the resource path is correct
            java.net.URL audioResource = KadRayaLutfil.class.getResource("/sound/tobitobcat.wav");
           

            // Load the audio file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioResource);

            // Log audio format details
            AudioFormat format = audioStream.getFormat();
            System.out.println("Audio Format: " + format);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the audio
            clip.start();
        } catch (IllegalArgumentException e) {
            System.err.println("Audio format issue: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error loading or playing audio file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

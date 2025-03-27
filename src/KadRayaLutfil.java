import javax.swing.*;
import java.awt.*;
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
        movingText.setBounds(500, 300, 400  , 50); // start off-screen

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

        //button
        JButton button1 = new JButton("Click Me");
        button1.setBounds(100,50,100,50);
        button1.addActionListener(e -> {
            // Set up JFrame
            JFrame frame = new JFrame("MatRiYa Ganggg!!! pew pew");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600); // Adjust size as needed

            // Create a JLayeredPane for layering components
            JLayeredPane animationLayeredPane = new JLayeredPane();
            animationLayeredPane.setBounds(0, 0, 800, 600);

            // Add ajax-loader.gif to the layered pane
            Icon imgIcon = new ImageIcon(KadRayaLutfil.class.getResource("/videos/tet.gif"));
            JLabel gifLabel = new JLabel(imgIcon);
            gifLabel.setBounds(0, 0, 800, 600); // Adjust size to match the frame
            animationLayeredPane.add(gifLabel, Integer.valueOf(1)); // Add GIF to the background layer

            // Add text over the GIF
            JLabel textLabel = new JLabel("Main Mercun pew pew!!", SwingConstants.CENTER);
            textLabel.setFont(new Font("Serif", Font.BOLD, 24));
            textLabel.setForeground(Color.WHITE); // Adjust color as needed
            textLabel.setBounds(0, 500, 800, 50); // Position the text over the GIF
            animationLayeredPane.add(textLabel, Integer.valueOf(2)); // Add text to the foreground layer

            // Add the layered pane to the frame
            frame.add(animationLayeredPane);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        layeredPane.add(button1, Integer.valueOf(5));
        
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

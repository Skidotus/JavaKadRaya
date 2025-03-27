import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;

public class KadRayaLutfil {
    private Clip clip;
    private JButton soundButton;

    public KadRayaLutfil() {
        // Create the main frame
        JFrame frameKad = new JFrame();
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
        JLabel movingText = createStyledLabel("Eid Mubarak!", 30);
        movingText.setBounds(500, 200, 200, 50); // start off-screen

        // Create QR button
        qrButton qrbutton = new qrButton("1");
        qrbutton.setBounds(420, 10, 50, 30); // Small button at the top-right
        qrbutton.setFont(new Font("Arial", Font.BOLD, 10));
        qrbutton.setFocusable(false);

        // Load and resize icons for sound ON and OFF
        ImageIcon soundOnIcon = resizeIcon(new ImageIcon(getClass().getResource("/icons/sound_on.png")), 40, 40);
        ImageIcon soundOffIcon = resizeIcon(new ImageIcon(getClass().getResource("/icons/sound_off.png")), 40, 40);

        // 🔊 Sound Toggle Button
        soundButton = new JButton(soundOnIcon);
        soundButton.setBounds(350, 10, 40, 40); // Exact size as icon
        soundButton.setBorderPainted(false); // Remove border
        soundButton.setContentAreaFilled(false); // Remove background
        soundButton.setFocusPainted(false); // Remove focus outline
        soundButton.addActionListener(e -> toggleMusic(soundOnIcon, soundOffIcon));

        // Add components to layered pane
        layeredPane.add(backgroundPanel, Integer.valueOf(1)); // Background
        layeredPane.add(greetingLabel, Integer.valueOf(2)); // Big Title (Centered)
        layeredPane.add(foregroundLabel, Integer.valueOf(3)); // Foreground image
        layeredPane.add(movingText, Integer.valueOf(4)); // Moving Text
        layeredPane.add(qrbutton, Integer.valueOf(5)); // QR button
        layeredPane.add(soundButton, Integer.valueOf(6)); // Sound Button

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

    private void music() {
        try {
            java.net.URL audioResource = getClass().getResource("/sound/tobitobcat.wav");
            if (audioResource == null) {
                System.err.println("Error: Audio file not found.");
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioResource);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the audio
            clip.start();
        } catch (Exception e) {
            System.err.println("Error loading or playing audio file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void toggleMusic(ImageIcon soundOnIcon, ImageIcon soundOffIcon) {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
                soundButton.setIcon(soundOffIcon); // Switch to "OFF" icon
            } else {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
                soundButton.setIcon(soundOnIcon); // Switch to "ON" icon
            }
        }
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KadRayaLutfil::new);
    }
}

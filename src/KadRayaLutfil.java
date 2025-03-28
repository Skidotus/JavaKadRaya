import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;

public class KadRayaLutfil {
    private Clip clip;
    private JButton soundButton;

    public KadRayaLutfil() {
        // Create the main frame
        JFrame frameKad = new JFrame();
        frameKad.setResizable(true);

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
        greetingLabel.setBounds(0, 10, 500, 500);

        // Create moving text
        JLabel movingText = createStyledLabel("Eid Mubarak!", 30);
        movingText.setBounds(500, 200, 200, 50); // start off-screen

        // Create QR button
        qrButton qrbutton = new qrButton("Nak kaya");
        
        // Load and resize icons for sound ON and OFF
        ImageIcon soundOnIcon = resizeIcon(new ImageIcon(getClass().getResource("/icons/sound_on.png")), 40, 40);
        ImageIcon soundOffIcon = resizeIcon(new ImageIcon(getClass().getResource("/icons/sound_off.png")), 40, 40);

        // 🔊 Sound Toggle Button
        soundButton = new JButton(soundOnIcon);
        soundButton.setBounds(10, 10, 40, 40);
        soundButton.setBorderPainted(false);
        soundButton.setContentAreaFilled(false);
        soundButton.setFocusPainted(false);
        soundButton.addActionListener(e -> toggleMusic(soundOnIcon, soundOffIcon));

        // Add components to layered pane
        layeredPane.add(backgroundPanel, Integer.valueOf(1));
        layeredPane.add(greetingLabel, Integer.valueOf(2));
        layeredPane.add(foregroundLabel, Integer.valueOf(3));
        layeredPane.add(movingText, Integer.valueOf(4));
        layeredPane.add(qrbutton, Integer.valueOf(5));
        layeredPane.add(soundButton, Integer.valueOf(6));

        JButton button1 = new JButton("1");
        button1.setBounds(200, 400, 80, 50); // Adjusted position to bottom center
        //button1.setOpaque(true);
        button1.setBackground(Color.BLUE);
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.YELLOW); // Added color to make it stand out
        button1.addActionListener(e -> showFestiveAnimation());
        layeredPane.add(button1, Integer.valueOf(10)); // Higher layer priority

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
        label.setForeground(new Color(1, 1, 1));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private void startMovingText(JLabel movingText) {
        Timer timer = new Timer(10, e -> {
            int x = movingText.getX() - 1;
            if (x + movingText.getWidth() < 0) {
                x = 500;
            }
            movingText.setLocation(x, movingText.getY());
        });
        timer.start();
    }

    private void showFestiveAnimation() {
        JFrame frame = new JFrame("MatRiYa Ganggg!!! pew pew");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        JLayeredPane animationLayeredPane = new JLayeredPane();
        animationLayeredPane.setBounds(0, 0, 800, 600);

        Icon imgIcon = new ImageIcon(getClass().getResource("/videos/tet.gif"));
        JLabel gifLabel = new JLabel(imgIcon);
        gifLabel.setBounds(0, 0, 800, 600);
        animationLayeredPane.add(gifLabel, Integer.valueOf(1));

        JLabel textLabel = new JLabel("Main Mercun pew pew!!", SwingConstants.CENTER);
        textLabel.setFont(new Font("Serif", Font.BOLD, 24));
        textLabel.setForeground(Color.WHITE);
        textLabel.setBounds(0, 500, 800, 50);
        animationLayeredPane.add(textLabel, Integer.valueOf(2));

        frame.add(animationLayeredPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
            clip.loop(Clip.LOOP_CONTINUOUSLY);
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
                soundButton.setIcon(soundOffIcon);
            } else {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
                soundButton.setIcon(soundOnIcon);
            }
        }
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;

public class KadRayaLutfil {
    private Clip clip;
    private JButton soundButton;

    public KadRayaLutfil() {
        JFrame frameKad = new JFrame();
        frameKad.setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 500));

        BackgroundPanel backgroundPanel = new BackgroundPanel("tobtobitop.png");
        backgroundPanel.setBounds(0, 0, 500, 500);

        JLabel foregroundLabel = new JLabel(new ImageIcon(getClass().getResource("layer1eid.png")));
        foregroundLabel.setBounds(0, 0, 500, 500);

        JLabel greetingLabel = createStyledLabel("Selamat Hari Raya", 50);
        greetingLabel.setBounds(0, 10, 500, 500);

        JLabel movingText = createStyledLabel("Maaf Zahir dan Batin!!!", 30);
        movingText.setBounds(500, 300, 400, 50); 

        qrButton qrbutton = new qrButton("Nak kaya");

        ImageIcon soundOnIcon = resizeIcon(new ImageIcon(getClass().getResource("/icons/sound_on.png")), 40, 40);
        ImageIcon soundOffIcon = resizeIcon(new ImageIcon(getClass().getResource("/icons/sound_off.png")), 40, 40);

        soundButton = new JButton(soundOnIcon);
        soundButton.setBounds(10, 10, 40, 40);
        soundButton.setBorderPainted(false);
        soundButton.setContentAreaFilled(false);
        soundButton.setFocusPainted(false);
        soundButton.addActionListener(e -> toggleMusic(soundOnIcon, soundOffIcon));

        layeredPane.add(backgroundPanel, Integer.valueOf(1));
        layeredPane.add(greetingLabel, Integer.valueOf(2));
        layeredPane.add(foregroundLabel, Integer.valueOf(3));
        layeredPane.add(movingText, Integer.valueOf(4));
        layeredPane.add(qrbutton, Integer.valueOf(5));
        layeredPane.add(soundButton, Integer.valueOf(6));

        frameKad.add(layeredPane);
        frameKad.setTitle("Kad Hari Raya");
        frameKad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameKad.setSize(500, 500);
        frameKad.setLocationRelativeTo(null);
        frameKad.setVisible(true);

        startMovingText(movingText);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KadRayaLutfil::new);
    }
}

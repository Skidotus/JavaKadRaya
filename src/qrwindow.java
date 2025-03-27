import javax.swing.*;
import java.awt.*;

public class qrwindow extends JFrame {
    public qrwindow() {
        // Load the image
        ImageIcon qrImage = new ImageIcon(getClass().getResource("/images/qr_lutfil.png")); // Change to your image path

        // Create a label for the image
        JLabel imageLabel = new JLabel(qrImage);

        // Create a decorated label for the text
        JLabel textLabel = new JLabel("DUIT RAYA UNTUK LUTFIL PLIZ", SwingConstants.CENTER);
        textLabel.setFont(new Font("Serif", Font.BOLD, 18));
        textLabel.setForeground(Color.WHITE);
        textLabel.setOpaque(true);
        textLabel.setBackground(new Color(34, 139, 34)); // Dark green background
        textLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Create a panel to hold the text on top and the image below
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(textLabel, BorderLayout.NORTH); // Place text at the top
        panel.add(imageLabel, BorderLayout.CENTER); // Image below

        // Set up the frame
        setTitle("QR Window");
        add(panel);
        pack(); // Auto-resize frame to fit content
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        setVisible(true);
    }
}

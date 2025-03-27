import javax.swing.*;
import java.awt.*;

public class qrwindow extends JFrame {

    public qrwindow(String[] imagePaths) {
        setTitle("Duit Raya Plisss");

        // Create a panel with BoxLayout (Y-axis) to hold multiple images
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));

        // Add a header text label
        JLabel textLabel = new JLabel("DUIT RAYA UNTUK KAMI PLIZ TERIMA KASIH", SwingConstants.CENTER);
        textLabel.setFont(new Font("Serif", Font.BOLD, 18));
        textLabel.setForeground(Color.WHITE);
        textLabel.setOpaque(true);
        textLabel.setBackground(new Color(34, 139, 34)); // Dark green background
        textLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        imagePanel.add(textLabel); // Add text at the top

        int totalHeight = textLabel.getPreferredSize().height; // Start with text label height
        int maxWidth = 0; // To determine frame width dynamically

        // Loop through image paths and add images
        for (String path : imagePaths) {
            ImageIcon qrImage = new ImageIcon(getClass().getResource(path));
            JLabel imageLabel = new JLabel(qrImage);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            imagePanel.add(imageLabel);

            // Update total height and max width
            totalHeight += qrImage.getIconHeight();
            maxWidth = Math.max(maxWidth, qrImage.getIconWidth());
        }

        // Set preferred size to avoid collapsed layout
        imagePanel.setPreferredSize(new Dimension(maxWidth, totalHeight));

        // Wrap the panel in a scroll pane
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane); // Add the scrollable panel to the frame

        // Adjust frame size based on content
        pack(); // Automatically fits content
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
    }

    // Static method to open the window
    public static void showQRWindow(String[] imagePaths) {
        SwingUtilities.invokeLater(() -> new qrwindow(imagePaths).setVisible(true));
    }
}

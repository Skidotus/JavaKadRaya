// CustomButton.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class qrButton extends JButton{
    public qrButton(String text) {
        super(text); // Set button text

        //button settings
        //setBounds(100, 110, 80, 80);
        setBounds(380, 10, 100, 50);
        setFont(new Font("Arial", Font.BOLD, 14));
        setFocusable(false);

        //color yang letak dekat sini
        setBackground(Color.BLUE);
        setForeground(Color.WHITE);
        setBorderPainted(false);


        //button listener
        addActionListener(new ButtonClickListener());
    }
    // Inner class to handle button click event
    private static class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            qrwindowhandler.showQRWindow();
        }
    }
    
}

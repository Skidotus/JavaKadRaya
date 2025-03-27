// CustomButton.java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class qrButton extends JButton{
    public qrButton(String text) {
        super(text); // Set button text

        //button settings
        //setBounds(100, 110, 80, 80);

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

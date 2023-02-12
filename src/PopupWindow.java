import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import java.awt.*;

/**
 * Creates a PopupWindow after either a not existent word is created,
 * the user runs out of guesses, or the right answer is obtained
 */
public class PopupWindow extends JFrame{
    public PopupWindow(String message){

        JLabel popupLabel = new JLabel();
        popupLabel.setText(message);
        popupLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        popupLabel.setBorder(new EmptyBorder(0, 20, 0, 20));

        this.add(popupLabel);
        this.setSize(400, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }
}
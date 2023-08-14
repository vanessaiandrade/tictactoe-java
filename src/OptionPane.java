import javax.swing.*;

public class OptionPane {

    OptionPane(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message);
    }

}
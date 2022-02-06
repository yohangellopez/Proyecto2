package Interface;

import javax.swing.*;
/**
 *
 * @author Yohangel
 */
public class Mar{
    JLabel label;
    
    public Mar() {
        label = new JLabel();
        label.setIcon(new ImageIcon(getClass().getResource("fondo_mar.png")));
        label.setLocation(0, 0);
        label.setSize(1366,768);
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}

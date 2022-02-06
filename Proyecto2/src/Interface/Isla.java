package Interface;

import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author Yohangel
 */
public class Isla {
    public String nombre;
    public JLabel label;
    public int x,y;
    
    public Isla(String nombre, int x, int y) {
        this.nombre = nombre;
        label = new JLabel(nombre);
        label.setForeground(Color.white);
        label.setIcon(new ImageIcon(getClass().getResource("isla.png")));
        this.x = x;
        this.y = y;
        label.setLocation(x, y);
        label.setSize(200,200);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     public String getNombre() {
        return nombre;
    }
     
    public void setLabel(JLabel label) {
        this.label = label;
    }
    
    public JLabel getLabel() {
        return label;
    }

    public void setX(int x) {
        this.x = x;
    }
    
     public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }   
}

package Interface;

import javax.swing.*;

/**
 *
 * @author Yohangel
 */
public class Cayo {
    public String nombre;
    public JLabel label;
    public int x,y;

    public Cayo(String nombre, int x, int y) {
        this.nombre = nombre;
        label = new JLabel(nombre);
        label.setIcon(new ImageIcon(getClass().getResource("cayo.png")));
        this.x = x;
        this.y = y;
        label.setLocation(x, y);
        label.setSize(160,160);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
    }
        
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
     
    public String getNombre() {
        return nombre;
    }

    public JLabel getLabel() {
        return label;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }   
}

package Interface;

import javax.swing.*;

/**
 *
 * @author Yohangel
 */
public class BarcoImg{
    
    public String nombre;
    public JLabel label;
    
    public BarcoImg(String nombre) {
        this.nombre = nombre;
        label = new JLabel(nombre);
        label.setIcon(new ImageIcon(getClass().getResource("barco.png")));
        label.setLocation(0, 0);
        label.setSize(150,150);
    }
    
    public BarcoImg() {
        nombre = null;
        label = new JLabel(nombre);
        label.setIcon(new ImageIcon(getClass().getResource("barco.png")));
        label.setLocation(-300,400);
        label.setSize(200,130);
    }
    
    public BarcoImg(String nombre, JLabel label){
        this.nombre = nombre;
        this.label = label;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void setPosicion(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

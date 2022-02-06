package Server;

public class Bajas {
    
    public String nombre;
    public int muertes;
    public int desc_municion;
    public int desc_racion;

    public Bajas(String nombre, int muertes, int desc_municion, int desc_racion) {
        this.nombre = nombre;
        this.muertes = muertes;
        this.desc_municion = desc_municion;
        this.desc_racion = desc_racion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMuertes() {
        return muertes;
    }

    public void setMuertes(int muertes) {
        this.muertes = muertes;
    }

    public int getDesc_municion() {
        return desc_municion;
    }

    public void setDesc_municion(int desc_municion) {
        this.desc_municion = desc_municion;
    }

    public int getDesc_racion() {
        return desc_racion;
    }

    public void setDesc_racion(int desc_racion) {
        this.desc_racion = desc_racion;
    }

    
    
    
    
    
}

package Server;

public class Mapa extends Tesoro{
    
    public String maquina; 
    public String localizacion; 
    public String ubicacion; 
    public int valorPosible; 
    
    public Mapa(String maquina, String sitio) {
        super("Mapa a " + sitio, 5, 2);
        this.maquina = maquina;
        this.localizacion = sitio;
    }
    
    public String getNombreMaquina() {
        return maquina;
    }

    public String getNombreSitio() {
        return localizacion;
    }

    public int getValorPosible() {
        return valorPosible;
    }
    
    // setters
    
    public void setNombreMaquina(String nombreMaquina) {
        this.maquina = nombreMaquina;
    }

    public void setNombreSitio(String nombreSitio) {
        this.localizacion = nombreSitio;
    }

    public void setValorPosible(int valorPosible) {
        this.valorPosible = valorPosible;
    }  
}

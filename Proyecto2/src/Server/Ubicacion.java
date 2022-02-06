package Server;

import java.util.*;

public class Ubicacion {

    public int id;
    public String nombre;
    public String tipo;
    public int tiempoExploracion;
    public Cofre cofre;
    public ArrayList<Ubicacion> subUbicaciones;
    public ArrayList<Bajas> bajas;
    public ArrayList<Barco> barcos = new ArrayList<>();
    public int valorPosible;
    public int x, y;
    
    // sitios dentro de islas
    public Ubicacion(int id, String nombre, String tipo, int tiempoExploracion, int padre) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.bajas = new ArrayList<>();
        this.subUbicaciones = null;
        this.tiempoExploracion = tiempoExploracion;
        this.x = 0;
        this.y = 0;
    }
    
    public Ubicacion(int id, String nombre, String tipo, int tiempoExploracion, int x, int y) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.bajas = new ArrayList<>();
        if (tipo.equals("isla"))
            this.subUbicaciones = new ArrayList<>();
        this.tiempoExploracion = tiempoExploracion;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTiempoExploracion() {
        return tiempoExploracion;
    }

    public void setTiempoExploracion(int tiempoExploracion) {
        this.tiempoExploracion = tiempoExploracion;
    }

    
    public Cofre getCofre() {
        return cofre;
    }

    public void setCofre(Cofre cofre) {
        this.cofre = cofre;
    }

    public ArrayList<Ubicacion> getSubUbicaciones() {
        return subUbicaciones;
    }

    public void setSubUbicaciones(ArrayList<Ubicacion> subUbicaciones) {
        this.subUbicaciones = subUbicaciones;
    }

    public ArrayList<Bajas> getBajas() {
        return bajas;
    }

    public void setBajas(ArrayList<Bajas> bajas) {
        this.bajas = bajas;
    }

    public ArrayList<Barco> getBarcos() {
        return barcos;
    }

    public void setBarcos(ArrayList<Barco> barcos) {
        this.barcos = barcos;
    }

    public int getValorPosible() {
        return valorPosible;
    }

    public void setValorPosible(int valorPosible) {
        this.valorPosible = valorPosible;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void agregarBajas(Bajas baja) {
        bajas.add(baja);
    }
    
    public void eliminarBajas(Bajas baja) {
        bajas.remove(baja);
    }

    public void agregarSubUbicaciones(Ubicacion ubicacion) {
        this.subUbicaciones.add(ubicacion);
    }
    
    public void recibirBarco(Barco barco){
        this.barcos.add(barco);
    }
    
    public void despedirBarco(Barco barco) {
        this.barcos.remove(barco);
    }
    
    public void eliminarBarco(Barco barco) {
        
        for (Barco barco_delete : barcos) {
            if (barco_delete==barco) {
                barcos.remove(barco_delete);
            }
        }
    }
    
}

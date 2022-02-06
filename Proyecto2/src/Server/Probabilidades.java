/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

/**
 *
 * @author Yohangel
 */
public class Probabilidades {
    public String orden = null;
    public Barco barco = null;
    public Ubicacion sitio = null;
    public String destino = null;

    public Probabilidades(String orden) {
        this.orden = orden;
    }

    public Probabilidades(String orden, Barco barco) {
        this.orden = orden;
        this.barco= barco;
    }

    public Probabilidades(String orden, Ubicacion sitio) {
        this.orden = orden;
        this.sitio = sitio;
    }

    public Probabilidades(String orden, String sitio) {
        this.orden = orden;
        this.destino = sitio;
    }
}

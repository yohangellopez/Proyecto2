/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Interface.BarcoMovil;
import Interface.MovilInterface;
import Server.Barco;

/**
 *
 * @author Yohangel
 */
public class Agente implements Runnable{
    
    Thread t;
    Barco barco;
    BarcoMovil agente;
    
    // se extraen los agente barco
    public Agente(BarcoMovil a){
        
        barco = new Barco(a.nombre, a.type, a.tripulacion_inicial, a.municion_inicial, a.racion_inicial, a.origin, a.id_origin);
        barco.municion = a.municion;
        barco.racion = a.racion;
        barco.tripulacion = a.tripulacion;
        barco.cofre = a.cofre;
        barco.origin = a.origin;
        barco.id_origin = 0;
        
        agente = a;
        
        t = new Thread(this, "Hilo de agente: " + a.nombre);
        t.yield();
        t.start();
    }
    
    @Override
    public void run() {
        barco.jugar();
        
        agente.cofre = barco.cofre;
        agente.racion = barco.racion;
        agente.tripulacion = barco.tripulacion;
        agente.municion = barco.municion;
        agente.Destino = barco.Destino;
        agente.idLugarOrigen = barco.idUbicacionActual;
        agente.ganador = barco.ganador;
        
        agente.send();
    }
}

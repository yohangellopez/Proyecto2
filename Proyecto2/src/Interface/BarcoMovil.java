/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Server.*;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yohangel
 */
public class BarcoMovil implements MovilInterface{
    
    // atributos de barco   
    public String nombre; 
    public String type;
    
    public int tripulacion_inicial;
    public int municion_inicial;
    public int racion_inicial;

    public int tripulacion;
    public int municion;
    public int racion;
    
    public String origin;
    public int id_origin;
    
    public String ubicacionActual;
    public int id_ubicacionActual;
    public Cofre cofre;
    public boolean inicializado;
    public boolean ganador;
    public boolean seGano;

    public int port;
    public String Actual, Destino;
    public String lugarDestino;
    public int idLugarOrigen;
    public int indiceMaquinas;
    public Vector maquinas;
    
    public BarcoMovil(String nombre, int port, Vector maquinas) {
        this.nombre = nombre;
        this.port = port;
        this.maquinas = maquinas;
        indiceMaquinas = 0;
        inicializado = false;
        lugarDestino = null;
    }
    
    @Override
    public void send() {

        String siguiente;
            
        indiceMaquinas++;

        if(indiceMaquinas == 4)
            indiceMaquinas = 0;
        
        if (indiceMaquinas < maquinas.size()) {
            try {
                siguiente = (String) maquinas.elementAt(indiceMaquinas);

                Registry registro = LocateRegistry
                        .getRegistry("localhost", port);
                ServidorInterface serv = (ServidorInterface) 
                        registro.lookup(siguiente);
                System.out.println( this.nombre + " viajando a " + siguiente);
                serv.consultar(this);
                //}
            } catch (RemoteException |  NotBoundException e) {
                System.out.println("Error en ejecutar: " + e);
            } catch (Exception ex) {
                Logger.getLogger(BarcoMovil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            System.out.println("Local");
        }
    }
    
    static void sleep(double tiempo) {
        try {
            Thread.sleep((long) (tiempo * 1000));
        } catch (InterruptedException e) {
            System.out.println("Error en dormir: " + e);
        }
    }
    
}

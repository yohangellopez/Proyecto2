/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import Server.*;

/**
 *
 * @author Yohangel
 */
 
public class ServerInterface extends UnicastRemoteObject implements ServidorInterface {
    
    public String Nombre;
    
    public static Main ventana;
    public static ArrayList<BarcoImg> barcosgraficos;
    public static ArrayList<Cayo> cayosgraficos;
    public static ArrayList<Isla> islasgraficos;
    public static Mar mar = new Mar();
    
    public static ArrayList<Ubicacion> sitios;
    public ArrayList<Barco> barcos;
    public static int MATRIZ[][] = new int[10][];
    public static boolean win = false;
    
    public ServerInterface(String Nombre) throws RemoteException{
        super();
        Nombre = Nombre;
        sitios = new ArrayList<>();
        barcos = new ArrayList<>();
        barcosgraficos = new ArrayList<>();
        cayosgraficos = new ArrayList<>();
        islasgraficos = new ArrayList<>();
        
        //Cada
        //travesía debe estar definida en una matriz de distancia en unidades de tiempo y
        //la estadía en cada sitio debe durar al menos una unidad de tiempo. 
        MATRIZ[0] = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; // Isla Nueva Esperanza
        MATRIZ[1] = new int[]{ 1, 0, 2, 3, 4, 5, 6, 7, 8, 9}; // Cayo del buen viento
        MATRIZ[2] = new int[]{ 2, 1, 3, 4, 5, 6, 7, 8, 9, 10}; // Cayo de sotavento
        MATRIZ[3] = new int[]{ 3, 4, 2, 0, 1, 3, 5, 8, 10,9}; // Isla la Holandesa
        MATRIZ[4] = new int[]{ 4, 0, 1, 2, 3, 5, 6, 7, 8, 9}; // Isla el Naufrago
        MATRIZ[5] = new int[]{ 5, 7, 5, 3, 2, 0, 2, 5, 7, 8}; // Isla Tortuga
        MATRIZ[6] = new int[]{ 6, 9, 7, 5, 4, 2, 0, 3, 5, 6}; // Isla las Aves
        MATRIZ[7] = new int[]{ 7, 5, 1, 8, 7, 5, 3, 0, 2, 3}; // Cayo de Barlovento
        MATRIZ[8] = new int[]{ 8, 9, 1, 2, 9, 7, 5, 2, 0, 1}; // La Gran Isla de la Española
        MATRIZ[9] = new int[]{ 9, 0, 1, 2, 3, 4, 5, 6, 7, 8}; // Cayo de los Pelicanos
    }
    
    // getters
    
    public ArrayList<Ubicacion> getUbicaciones() {
        return sitios;
    }

    public ArrayList<Barco> getBarcos() {
        return barcos;
    }
    
    // setters

    public void setUbicaciones(ArrayList<Ubicacion> ubicaciones) {
        this.sitios = ubicaciones;
    }

    public void setBarcos(ArrayList<Barco> barcos) {
        this.barcos = barcos;
    }
    
    
    
    public void addToGame(BarcoMovil barco) {
        System.out.println(barco.nombre + "llego a maquina destino");
        Agente barcorun = new Agente(barco);
    }

    
    public void addUbicacion(Ubicacion sitio) {
        this.sitios.add(sitio);
    }
    
    public void addBarco(Barco barco) {
        this.barcos.add(barco);
    }
    
    public void setSubsitios() {
        for (Ubicacion p : sitios) {
            for (Ubicacion s : sitios) {
                    s.x = p.x;
                    s.y = p.y;
                    p.agregarSubUbicaciones(s);
            }
        }
    }
    
    public void addBarco(BarcoImg barco) {
        this.barcosgraficos.add(barco);
    }
    
    public void addCayo(Cayo cayo) {
        this.cayosgraficos.add(cayo);
    }
    
    public void addIsla(Isla isla) {
        this.islasgraficos.add(isla);
    }
    
    public void iniciarVentana() {
        
        ventana = new Main();
        
        BarcoImg barco1 = new BarcoImg();
        BarcoImg barco2 = new BarcoImg();
        BarcoImg barco3 = new BarcoImg();
        
        addBarco(barco1);
        addBarco(barco2);
        addBarco(barco3);
        
        for(BarcoImg barco : barcosgraficos)
            ventana.add(barco.getLabel());
        
        for(Cayo cayo: cayosgraficos) 
            ventana.add(cayo.getLabel());

        for(Isla isla: islasgraficos) 
            ventana.add(isla.getLabel());
        
        ventana.add(mar.getLabel());
        
        ventana.repaint();
        
    }
    public void consultar(BarcoMovil a) throws RemoteException {
        
        // si llega un agente ganador
        if(a.ganador && !win) {
            win = true;
            System.out.println(a.nombre + " gano la partida");
            a.send();
        }
        
        // si aun no se ha ganado el juego
        if (!win) {
            // si esta en la maquina correcta
            if (a.Destino.equals(Nombre)) {
                // si nunca se inicializo
                if (!a.inicializado) {
                    Barco aux = barcos.get(0);
                    a.nombre = aux.nombre;
                    a.tripulacion_inicial = aux.tripulacion_inicial;
                    a.racion_inicial = aux.racion_inicial;
                    a.municion_inicial = aux.municion_inicial;
                    a.tripulacion = aux.tripulacion_inicial;
                    a.racion = aux.racion_inicial;
                    a.municion = aux.municion_inicial;
                    a.type = aux.type;
                    a.id_origin = aux.id_origin;
                    a.origin = aux.origin;
                    a.cofre = aux.cofre;
                    a.inicializado = true;
                    barcos.remove(0);
                }
                addToGame(a); 
            } else { 
                a.send(); 
            }
        }
    }
}

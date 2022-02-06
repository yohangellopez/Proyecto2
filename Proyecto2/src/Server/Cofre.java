/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.util.ArrayList;

/**
 *
 * @author Yohangel
 */
public class Cofre {
    
    public int capacidad;
    public ArrayList<Tesoro> tesoros = new ArrayList<>();
    public ArrayList<Mapa> mapas = new ArrayList<>();

    public Cofre(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public ArrayList<Tesoro> getTesoros() {
        return tesoros;
    }

    public void setTesoros(ArrayList<Tesoro> tesoros) {
        this.tesoros = tesoros;
    }

    public ArrayList<Mapa> getMapa() {
        return mapas;
    }

    public void setMapa(ArrayList<Mapa> mapa) {
        this.mapas = mapa;
    }
    
    public void agregarTesoro(Tesoro tesoro){    
        tesoros.add(tesoro);
    }
    
    public Tesoro removerTesoro(int i){
        return(tesoros.remove(i));
    }
    
    public void removerTesoro(Tesoro tesoro){
        tesoros.remove(tesoro);
    }
    
    public void agregarMapa(Mapa mapa){
        mapas.add(mapa);
    }   
    
    void transferir(Cofre cofreDestino) {
        
        ArrayList<Tesoro> copia = new ArrayList<>();
        
        for (Tesoro i : this.getTesoros()){
            copia.add(i);
        }
        
        for (Tesoro i : copia) {
            cofreDestino.agregarTesoro(i);
            this.removerTesoro(this.getTesoros().indexOf(i));
        }
     
        copia = null;
    }
    
    void transferirRetirada(Cofre cofreDestino){
        
        int mitad = this.getTesoros().size()/2;
        int aux = 0;

        ArrayList<Tesoro> copia = new ArrayList<>();
        for (Tesoro i : this.getTesoros()){
            copia.add(i);
        }
        
        ArrayList<Mapa> copiaMapas = new ArrayList<>();
        for (Mapa i : this.getMapa()){
            copiaMapas.add(i);
        }
        
        for (Mapa i : copiaMapas) {
            cofreDestino.agregarMapa(i);
        }
        
        for (Tesoro i: copia) {
            if (i.posibilidades==3){
                cofreDestino.agregarTesoro(i);
                this.removerTesoro(this.getTesoros().indexOf(i));
                aux++;
            }
        }
        for (Tesoro i : copia){
            if (i.getPosibilidades()==1){
                cofreDestino.agregarTesoro(i);
                this.removerTesoro(this.getTesoros().indexOf(i));
                aux++;
                if (aux==mitad){
                    break;
                }
            }
        }
        
        copia = null;
        copiaMapas = null;
        
    }
    
    public int retornarValorTotal(){
        int valortotal = 0; 
        for (Tesoro i : tesoros) {
            valortotal=valortotal+i.getPosibilidades();
            if (i.getPosibilidades()==2){
                valortotal=valortotal-1;
            }
        }
        return valortotal;
    }
    
    public int retornarPesoTotal() {
        int weight = 0; 
        for (Tesoro i : tesoros) {
            weight = weight + i.getPeso();
        }
        return weight;
    }

    public void registrarMapas() {
        for (Tesoro i : tesoros){
            if (i.posibilidades == 2){
                boolean nuevo = true;
                for (Mapa j : this.getMapa()) {
                    if(((Mapa)i).nombre.equals(j.nombre)) {
                        nuevo = false;
                    }
                }
                if (nuevo==true){
                    this.getMapa().add((Mapa)i);
                }
            }
        }
    }
    
    public void updateMap(Ubicacion ubicacion) {
        for (Mapa m : getMapa()) {
            if (m.ubicacion.equals(ubicacion.nombre)) {
                m.valorPosible = ubicacion.valorPosible;
            }
        }
    }
    
}

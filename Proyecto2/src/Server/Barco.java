package Server;

import Interface.BarcoImg;
import Interface.*;

public class Barco {
    
    public String nombre;
    
    public String type; //Piratas o Real Majestad
    
    public int tripulacion_inicial;
    public int municion_inicial;
    public int racion_inicial;
    
    public int tripulacion;
    public int municion;
    public int racion;
    
    public int id_origin;
    public String origin;
    public Cofre cofre = null;
    public Ubicacion ubicacionActual;
    public Ubicacion ubicacionDestino;
    public boolean local = true;
    public boolean ganador = false;
    public int idGrafico;
    public int idUbicacionPartida;
    public int idUbicacionActual;
    public String actual;
    public String Destino;
    public String maquinaDestino =  null;
    
    public Barco(String nombre, String type, int tripulacion_inicial, int municion_inicial, int racion_inicial, String origin, int id_origin) {
        this.nombre = nombre;
        this.type = type;
        this.tripulacion_inicial = tripulacion_inicial;
        this.municion_inicial = municion_inicial;
        this.racion_inicial = racion_inicial;
        this.origin = origin;
        this.id_origin = id_origin;
    }
    
    public Barco() {
    }

    Barco(String nombre, int faccion, int tripulacion_inicial, int municionesInicial, int racionesInicial, String origin, int id_origin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }
   

    public int getTripulacion_inicial() {
        return tripulacion_inicial;
    }

    public void setTripulacion_inicial(int tripulacion_inicial) {
        this.tripulacion_inicial = tripulacion_inicial;
    }

    public int getMunicion_inicial() {
        return municion_inicial;
    }

    public void setMunicion_inicial(int municion_inicial) {
        this.municion_inicial = municion_inicial;
    }

    public int getRacion_inicial() {
        return racion_inicial;
    }

    public void setRacion_inicial(int racion_inicial) {
        this.racion_inicial = racion_inicial;
    }

    public int getTripulacion() {
        return tripulacion;
    }

    public void setTripulacion(int tripulacion) {
        this.tripulacion = tripulacion;
    }

    public int getMunicion() {
        return municion;
    }

    public void setMunicion(int municion) {
        this.municion = municion;
    }

    public int getRacion() {
        return racion;
    }

    public void setRacion(int racion) {
        this.racion = racion;
    }

    public int getId_origin() {
        return id_origin;
    }

    public void setId_origin(int id_origin) {
        this.id_origin = id_origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Cofre getCofre() {
        return cofre;
    }

    public void setCofre(Cofre cofre) {
        this.cofre = cofre;
    }

    public Ubicacion getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(Ubicacion ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public Ubicacion getUbicacionDestino() {
        return ubicacionDestino;
    }

    public void setUbicacionDestino(Ubicacion ubicacionDestino) {
        this.ubicacionDestino = ubicacionDestino;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public boolean isGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }

    public int getIdGrafico() {
        return idGrafico;
    }

    public void setIdGrafico(int idGrafico) {
        this.idGrafico = idGrafico;
    }

    public int getIdUbicacionPartida() {
        return idUbicacionPartida;
    }

    public void setIdUbicacionPartida(int idUbicacionPartida) {
        this.idUbicacionPartida = idUbicacionPartida;
    }

    public int getIdUbicacionActual() {
        return idUbicacionActual;
    }

    public void setIdUbicacionActual(int idUbicacionActual) {
        this.idUbicacionActual = idUbicacionActual;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getSitioDestino() {
        return Destino;
    }

    public void setSitioDestino(String Destino) {
        this.Destino = Destino;
    }

    public String getMaquinaDestino() {
        return maquinaDestino;
    }

    public void setMaquinaDestino(String maquinaDestino) {
        this.maquinaDestino = maquinaDestino;
    }

    

   
    public void jugar() {
        boolean play = true;
        Ubicacion Destino = null;
        
        while(play) {
            
            if (this.Destino != null) {
                if (local) {
                    if (ubicacionActual != null) {
                        for (Ubicacion s : ServerInterface.sitios) {
                            if (this.Destino.equals(s.nombre)) {
                                Destino = s;   
                            }
                        }
                        this.anclarLocal(this.zarparLocal(Destino));
                    } else
                        anclarRemoto(this.Destino);
                }
                else {
                    zarpar();
                    break;
                }
                
                Probabilidades rEvaluar = evaluar();
                switch (rEvaluar.orden){
                    case "ganador" : {
                        // el barco gano la partida
                        play = false;
                        ganador = true;                   
                    }
                    case "zarpar" : {
                        if(rEvaluar.destino != null) {
                            this.Destino = rEvaluar.destino;
                            calcularDestino(this.Destino);
                        } else
                            calcularDestino();
                    }
                    case "retirada" : {
                        rEvaluar = this.retirada();
                        this.Destino = rEvaluar.destino;
                        calcularDestino(this.Destino);
                    }
                    case "explorar" : {
                        rEvaluar = this.explorar();
                        switch (rEvaluar.orden){
                            case "retirada":
                                this.retirada();
                            case "zarpar":
                                if (rEvaluar.destino == null){
                                    calcularDestino();
                                    break;
                                } else
                                    if (rEvaluar.destino != null){
                                        this.Destino = rEvaluar.destino;
                                        calcularDestino(this.Destino);
                                        break;
                                    }     
                        }
                    }

                }
                if (ganador || ServerInterface.win) {
                    play = false;
                }
            } 
            else {
                for (Ubicacion s : ServerInterface.sitios) {
                    if (s.nombre.equals(origin)) {
                        this.anclarLocalInicial(s);
                    }
                }
                calcularDestino();
                if (!local) {
                    play = false;   
                }
                zarpar();
            }       
        }
    }
    
    public Ubicacion zarparLocal(Ubicacion destino) {
        BarcoImg imagenbarco =  ServerInterface.barcosgraficos.get(idGrafico);
        imagenbarco.setPosicion(-300, 400);
        Ubicacion inicio = this.ubicacionActual;
        ubicacionActual.despedirBarco(this);   
        ubicacionActual = null; // No esta en ningun sitio, esta viajando
        int tiempo = ServerInterface.MATRIZ[inicio.id][destino.id];
        System.out.println( nombre + "  a " + destino.nombre);
        return destino;
    }
    
    public void anclarLocal(Ubicacion sitio){
        this.ubicacionActual = sitio;
        this.idUbicacionActual = sitio.getId();
        sitio.recibirBarco(this);
        BarcoImg imagenbarco =  ServerInterface.barcosgraficos.get(idGrafico);
        imagenbarco.setPosicion(sitio.x, sitio.y);
        System.out.println("Ha llegado "+ this.nombre + " a " + ubicacionActual.nombre);
    }
    
    public void anclarLocalInicial(Ubicacion sitio) {
        BarcoImg imagenbarco = null;
        int aux = 0;
        
        for (BarcoImg i : ServerInterface.barcosgraficos) {
            if (i.label.getText() == null) {
                idGrafico = aux;
                imagenbarco = i;
                i.label.setText(nombre);
                break;
            }
            aux++;
        }
        
        imagenbarco.setPosicion(sitio.x, sitio.y);
        
        this.ubicacionActual = sitio;
        this.idUbicacionActual = sitio.getId();
        sitio.recibirBarco(this); 
        
        System.out.println("Llego"+ this.nombre + " a " + ubicacionActual.nombre);
    }
    
    // metodo para viajar a un sitio en otra maquina
    public void zarpar() {
        ubicacionActual.despedirBarco(this);
        idUbicacionActual = ubicacionActual.getId();
        
        int tiempo = ServerInterface.MATRIZ[idUbicacionActual][idDestino()];

        BarcoImg imagenbarco =  ServerInterface.barcosgraficos.get(idGrafico);
        imagenbarco.label.setText(null);
        imagenbarco.setPosicion(-300, 400);        
    }

    public void anclarRemoto(String sitio) {
        int aux = 0;
        BarcoImg imagenbarco = null;
        
        for (BarcoImg i : ServerInterface.barcosgraficos) {
            if (i.label.getText() == null) {
                idGrafico = aux;
                imagenbarco = i;
                i.label.setText(nombre);
                break;
            }
            aux++;
        }
        
        for (Ubicacion s : ServerInterface.sitios) {
            if (sitio.equals(s.nombre)) {
                ubicacionActual = s;
                int tiempo = (int) ServerInterface.MATRIZ[idUbicacionPartida][ubicacionActual.id]/2;
                s.recibirBarco(this);
                imagenbarco.setPosicion(ubicacionActual.x, ubicacionActual.y);
                break;
            }
        }
        System.out.println("LLego "+ this.nombre + " a " + ubicacionActual.nombre);
    }
    
    public void calcularDestino() {
        this.local = false; 
        this.Destino = null;
        
        int valorPosible = -1;
        boolean originAqui = false;
        Ubicacion sitioBase = null;
        
        for (Ubicacion s : ServerInterface.sitios) {
            if (s.nombre.equals(origin)) {
                sitioBase = s;
            }
        }
        
        if (!originAqui) {
            for (Mapa mapa : cofre.getMapa()) {
                if (mapa.valorPosible > valorPosible && !(ubicacionActual.nombre.equals(mapa.ubicacion))) {
                    valorPosible = mapa.valorPosible;
                    Destino = mapa.ubicacion;
                    maquinaDestino = mapa.maquina;
                }
            }
        }
        else {
            for (Mapa mapa : cofre.getMapa()) {
                if (!mapa.localizacion.equals(sitioBase.nombre) && mapa.valorPosible > valorPosible && !(ubicacionActual.nombre.equals(mapa.ubicacion))) {
                    valorPosible = mapa.valorPosible;
                    Destino = mapa.ubicacion;
                    maquinaDestino = mapa.maquina;
                }
            }
        }
        
        for (Ubicacion s : ServerInterface.sitios) {
            if (s.nombre.equals(Destino)) {
                this.local = true;
                //ubicacionActual = s;
            }
        }
    }
    
    public void calcularDestino(String destino) {
        
        this.local = false;
        this.Destino = destino;
        
        for (Mapa m: cofre.getMapa()) {
            if (m.ubicacion.equals(destino)) {
                maquinaDestino = m.maquina;
            }
        }
           
        for (Ubicacion s : ServerInterface.sitios) {
            if (s.nombre.equals(destino)) {
                this.local = true;
            }
        }
        
    }
    
    public Probabilidades evaluar(){
      
        Ubicacion sitioBase = null;
        
        for (Ubicacion s : ServerInterface.sitios) {
            if (s.nombre.equals(origin)){
                sitioBase = s;
            }
        }
        
        if (sitioBase != null && ubicacionActual == sitioBase) {
            while (this.tripulacion < this.tripulacion_inicial || municion < municion_inicial || racion < racion_inicial){
                tripulacion += tripulacion;
                municion += 5;
                racion += 5;
                if(tripulacion > tripulacion_inicial) {tripulacion = tripulacion_inicial;};
                if(municion > municion_inicial) {municion = municion_inicial;};
                if(racion > racion_inicial) {racion = racion_inicial;};
                System.out.println(nombre+": Recuperando... T: "+ tripulacion + " - M: " + municion + " - R: " + racion);
            }
            
            System.out.println(nombre + ": Recuperado! T: " + tripulacion + " - M: "+ municion + " - R: " + racion);
            
            while (this.getCofre().getTesoros().size() > 0){
                // Espera tantos segundos como tesoros esta dejando
                // Transfiere todo
                this.getCofre().transferir(this.ubicacionActual.getCofre());
                System.out.println(nombre + " transfirio todo su cofre del tosoro a " + ubicacionActual.nombre);
            }
            
            for (Tesoro t : ubicacionActual.cofre.tesoros) {
                if  (t.posibilidades == 3)
                    System.out.println(nombre + " ha ganado!!!");
                return new Probabilidades("ganador");
            }
            
            // Zarpa a algun otro sitio de sus mapas.
            return new Probabilidades("zarpar");
        }
        else 
        {
            if(!ubicacionActual.getBajas().isEmpty()){
                String nombreC = ubicacionActual.getBajas().get(0).getNombre();
                int auxTrip = ubicacionActual.getBajas().get(0).getMuertes();
                int auxMun = ubicacionActual.getBajas().get(0).getDesc_municion();
                int auxRac = ubicacionActual.getBajas().get(0).getDesc_racion();
                tripulacion -= auxTrip;
                municion -= auxMun;
                racion -= auxRac;
                if(!ubicacionActual.getBajas().isEmpty())
                    ubicacionActual.eliminarBajas(ubicacionActual.bajas.get(0));
            }

            if(this.salud()){
                if(ubicacionActual.getBarcos().size() > 1) {
                    for (Barco i : ubicacionActual.barcos) {
                        if (i.getType() != this.getType()){
                            batalla(i);
                            if(!this.salud()) {
                                return new Probabilidades("retirada");
                            }    
                        }
                    }
                    return new Probabilidades("explorar");   
                }
                else {
                    return new Probabilidades("explorar");
                }
            }
            else {
                System.out.println(this.getNombre() + ": No fue a batalla");
                return new Probabilidades("zarpar", origin);
            }     
        } 
    }
    
    public boolean salud(){
        return !(tripulacion < tripulacion_inicial/3 || municion < municion_inicial/3 || racion < racion_inicial/3);
    }
    
    public void batalla(Barco enemigo) {
        
        System.out.println("Batalla:");
        
        System.out.println("Ataca: " + nombre + " Tripulacion: "+tripulacion + "Municion: " + enemigo.municion+ " Racion: " + racion);
        
        System.out.println("Esta siendo atacado: " + enemigo.nombre + "Tripulacion: " + "Municion: " + enemigo.municion+ enemigo.tripulacion);
        
        
        int dañosT = Math.abs(tripulacion - enemigo.tripulacion);
        int dañosM = Math.abs(municion - enemigo.municion);
        
        tripulacion -= dañosT;
        municion -= dañosM;
        
        enemigo.tripulacion -= dañosT;
        enemigo.municion -= dañosM;
        
        System.out.println("Datos");
        
        System.out.println("Atacante: " + nombre        + " Tripulacion: "+tripulacion +        "Municion: " + enemigo.municion+ " Racion: " + racion);
        
        System.out.println("Atacado: " + enemigo.nombre + " Tripulacion: "+ enemigo.tripulacion+"Municion: " + enemigo.municion);  
    }
    
    public Probabilidades retirada() {
        
        System.out.println( nombre + " tuvo que retirarse! Dejó la mitad de sus tesoros.");
        
        cofre.transferirRetirada(ubicacionActual.cofre);
        
        return new Probabilidades("zarpar", origin);
    }
    
    public Probabilidades explorar(){
        
        System.out.println(nombre + " esta explorando " + ubicacionActual.nombre);
        
        boolean full = false;
        Ubicacion objetivo = ubicacionActual;
        
        cofre.updateMap(objetivo);
        
        
        if (!this.salud()) {
            return new Probabilidades("retirada");
        } else {
            boolean corazon = false;
            for (Tesoro i : this.getCofre().getTesoros()){
                if (i.getPosibilidades()==3)
                    corazon=true;
            }
        }
        return null;
    }
    
    public int idDestino() {
        int aux = -1;
        String isla_cayo = null;
        for (Mapa m : cofre.mapas) {
            if (Destino.equals(m.ubicacion)) {
                isla_cayo = m.localizacion;
            }
        }
        switch (isla_cayo) {
            case "Isla Nueva Esperanza" : {
                aux = 0;
            }
            case "Cayo del buen viento" : {
                aux = 1;
            }
            case "Cayo de sotavento" : {
                aux = 2;
            }
            case "Isla La Holandesa" : {
                aux = 3;
            }
            case "Isla El Naufrago" : {
                aux = 4;
            }
            case "Isla Tortuga" : {
                aux = 5;
            }
            case "Isla Las Aves" : {
                aux = 6;
            }
            case "Cayo de Barlovento" : {
                aux = 7;
            }
            case "La Gran Isla de la Española" : {
                aux = 8;
            }
            case "Cayo de los Pelicanos" : {
                aux = 9;
            }
        }
        return aux;
    }
}
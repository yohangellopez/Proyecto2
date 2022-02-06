package Client;

import Interface.BarcoMovil;
import Interface.ServidorInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;

/**
 *
 * @author Yohangel
 */
public class Client {

    
    public static void main(String[] args) throws Exception {
        
        try {
                                                          // IP          PORT
            Registry registry = LocateRegistry.getRegistry("localhost",4000);
            
            ServidorInterface server = (ServidorInterface) registry.lookup("Maquina1");
            
            Vector Maquinas = new Vector();
            
            Maquinas.addElement("Maquina1");
            Maquinas.addElement("Maquina2");
            Maquinas.addElement("Maquina 3");
            Maquinas.addElement("Maquina 4");
            
            BarcoMovil pirate = new BarcoMovil("Pirate",4000, Maquinas);
            pirate.Actual  = "Maquina1";
            pirate.Destino = "Maquina1";
            
            server.consultar(pirate);
            
            
            BarcoMovil real_majestad = new BarcoMovil("Real Majestad",4000, Maquinas );
            real_majestad.Actual = "Maquina1";
            real_majestad.Destino = "Maquina2";
            
            BarcoMovil real_majestad2 = new BarcoMovil("Real Majestad",4000, Maquinas); 
            real_majestad2.Actual = "Maquina1";
            real_majestad2.Destino = "Maquina2"; 
            
            server.consultar(real_majestad);
            server.consultar(real_majestad2);
            
            System.out.println("Barcos enviados");
        } catch (NotBoundException | RemoteException e) {
            System.out.println("Error: " + e);
        }
    }
    
}

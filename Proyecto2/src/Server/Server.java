package Server;

import Interface.Cayo;
import Interface.Isla;
import Interface.ServerInterface;
import Interface.ServidorInterface;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author Yohangel
 */
public class Server {
    
     public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
         String machine = "";
         int id_machine=-1;
         ServerInterface servidor = null;
         try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(fileChooser);
            File archivo = fileChooser.getSelectedFile(); // obtiene el archivo seleccionado

            // muestra error si es inválido
            if ((archivo == null) || (archivo.getName().equals(""))) {
                System.out.println("ERROR AL SELECCIONAR EL ARCHIVO XML");
                System.exit(0);
            } else{
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
                Document document = documentBuilder.parse(archivo);
                document.getDocumentElement().normalize();

                
                
                Barco barco = new Barco();
                int id_maquina;
                String nombre, origen, maquina, tipo, isla, ubicacion, cayo, tipo_maquina; 
                int tripulacion, municiones, raciones, peso, valor, id;
                int x, y;
                Cofre cofre = null;
                Ubicacion localizacion = null;

                NodeList nList = document.getElementsByTagName("Machine");
                Node nNode = nList.item(0);
                Element element;
                element = (Element) nNode;
                
                
                machine = element.getElementsByTagName("Name").item(0).getTextContent();
                id_machine = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());

                try {
                    ServerInterface s = new ServerInterface(machine);
                    servidor = s;
                } catch (RemoteException ex) {
                    System.out.println("error: " + ex);
                    System.exit(0);
                }
                

                if (element.getElementsByTagName("Boat").item(0) != null) {
                    nNode = element.getElementsByTagName("Boat").item(0);
                    element = (Element) nNode;
                    nList = element.getElementsByTagName("Boat");
                    for (int i=0; i<nList.getLength(); i++) {        
                        nNode = nList.item(i);
                        element = (Element) nNode;
                        nombre      = element.getElementsByTagName("Name").item(0).getTextContent();
                        tripulacion = Integer.parseInt(element.getElementsByTagName("Crew").item(0).getTextContent()); 
                        municiones  = Integer.parseInt(element.getElementsByTagName("Ammunition").item(0).getTextContent()); 
                        raciones    = Integer.parseInt(element.getElementsByTagName("Ration").item(0).getTextContent());
                        tipo_maquina= element.getElementsByTagName("Type").item(0).getTextContent();
                        origen      = element.getElementsByTagName("Initial_departure").item(0).getTextContent();
                        barco       = new Barco(nombre, tipo_maquina, tripulacion, municiones, raciones, origen, id_machine);

                        if (tipo_maquina == "Pirate") {
                            cofre = new Cofre(100);
                        } else if(tipo_maquina=="Real Majestad") {
                            cofre = new Cofre(50);
                        }else{
                            cofre= null;
                        }

                        
                        nNode = element.getElementsByTagName("Islands").item(0);
                        element = (Element) nNode;
                        nList = element.getElementsByTagName("Island");
                        for (int j=0; j<nList.getLength(); j++) {
                            nNode = nList.item(j);
                            element = (Element) nNode;
                            tipo= null;
                            isla = null;
                            cayo = null;
                            localizacion = null;
                            maquina = element.getElementsByTagName("id").item(0).getTextContent();
                            if (element.getElementsByTagName("type").item(0) != null)
                                isla = element.getElementsByTagName("isla").item(0).getTextContent();
                            if (tipo=="island"){
                                isla= isla;
                            }else{
                                cayo=isla;
                            }
                            if (cayo != null)
                                cofre.agregarMapa(new Mapa(maquina, cayo));
                            else
                                cofre.agregarMapa(new Mapa(maquina, isla));
                        }
                        barco.setCofre(cofre);
                        servidor.addBarco(barco); 
                    }
                }

                // SITIOS DEL SERVIDOR
                nList = document.getElementsByTagName("Machine");
                nNode = nList.item(0);
                element = (Element) nNode;
                nNode = element.getElementsByTagName("Islands").item(0);
                element = (Element) nNode;
                nList = element.getElementsByTagName("Island");
                for (int i=0; i<nList.getLength(); i++){
                    id = -1;
                    tipo = null;
                    x = 0;
                    y = 0;
                    nNode = nList.item(i);
                    element = (Element) nNode;
                    nombre = element.getElementsByTagName("Name").item(0).getTextContent();
                    // si es cayo o isla
                    if (element.getElementsByTagName("id").item(0) != null) {
                        id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                        tipo = element.getElementsByTagName("type").item(0).getTextContent();
                        x = Integer.parseInt(element.getElementsByTagName("point_x").item(0).getTextContent());
                        y = Integer.parseInt(element.getElementsByTagName("point_y").item(0).getTextContent());
                        if (tipo.equals("dropped")) {
                            servidor.addCayo(new Cayo(nombre, x, y));
                        } else if(tipo.equals("island")) {
                            servidor.addIsla(new Isla(nombre, x, y));
                        }
                    }
                   
                    // si hay tesoros se agregan
                    if(element.getElementsByTagName("Treasures").item(0) != null) {
                        nNode = element.getElementsByTagName("Treasure").item(0);
                        element = (Element) nNode;
                        nList = element.getElementsByTagName("Treasure");
                        for(int j=0; j<nList.getLength(); j++) {
                            nNode = nList.item(j);
                            element = (Element) nNode;
                            nombre = element.getElementsByTagName("Name").item(0).getTextContent();
                            peso = Integer.parseInt(element.getElementsByTagName("Weight").item(0).getTextContent());
                            valor = Integer.parseInt(element.getElementsByTagName("id_island").item(0).getTextContent());
                            cofre.agregarTesoro(new Tesoro(nombre, peso, valor));
                        }
                    }
                    
                    
                    // si hay calamidades se agregan
                    if(element.getElementsByTagName("Calamities").item(0) != null) {
                        nNode = element.getElementsByTagName("Calamities").item(0);
                        element = (Element) nNode;
                        nList = element.getElementsByTagName("Calamity");
                        for(int j=0; j<nList.getLength(); j++) {
                            nNode = nList.item(j);
                            element = (Element) nNode;
                            nombre = element.getElementsByTagName("Name").item(0).getTextContent();
                            tripulacion = Integer.parseInt(element.getElementsByTagName("low_crew").item(0).getTextContent());
                            raciones = Integer.parseInt(element.getElementsByTagName("low_ration").item(0).getTextContent());
                            municiones = Integer.parseInt(element.getElementsByTagName("low_munition").item(0).getTextContent());
                            localizacion.agregarBajas(new Bajas(nombre, tripulacion, municiones, raciones));
                        }
                    }
                    
                }
            }
            //  -- TERMINA LECTURA DE XML --
            
            // inicializacion de registro RMI
            try {
                Registry registro = LocateRegistry.getRegistry(4000);
                registro.rebind(machine, servidor);
            } catch (RemoteException e) {
                Registry registro = LocateRegistry.createRegistry(4000);
                registro.rebind(machine, servidor);
            }
            
            servidor.setSubsitios();
            servidor.iniciarVentana();
            
            System.out.println("Servidor: " + machine + " listo");
        }catch (RemoteException e) {
            System.out.println("Error en servidor: " + e);
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error en lectura de numero: " + e);
            System.exit(0);
        } catch (ParserConfigurationException ex) {
            System.out.println("Error en ");
            System.exit(0);
        } catch (SAXException ex) {
            System.out.println("Error en SAX: " + ex);
            System.exit(0);
        }
    }
}

package Interface;

import java.rmi.Remote;

/**
 *
 * @author Yohangel
 */
public interface ServidorInterface extends Remote{
    public void consultar(BarcoMovil barco) throws Exception;
}

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmitongab;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class Client {
    RMIInterface rmiServer;
    Registry registry;
    public Client(String address, int port) throws NotBoundException{
        try {
            registry = LocateRegistry.getRegistry(address,port);
            rmiServer = (RMIInterface)(registry.lookup("rmiServer"));
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getResult(int a, int b){
        int tong = 0;
        try {
            tong=rmiServer.calculateSum(a, b);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tong;
    }
    public static void main(String[] args) throws NotBoundException {
        Client client = new Client("localhost", 3232);
        System.out.println("Tong la: "+ client.getResult(8, 6));
    }
}

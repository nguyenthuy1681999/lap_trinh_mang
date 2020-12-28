/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmitongab;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class Server extends UnicastRemoteObject implements RMIInterface {

    int thisPort = 3232;
    String thisAddress;
    Registry registry; //đăng kí RMI 

    @Override
    public int calculateSum(int a, int b) throws RemoteException {
        int tong = 0;
        tong = a + b;
        return tong;
    }

    public Server() throws RemoteException {
        //đăng kí RMI server
        registry = LocateRegistry.createRegistry(thisPort);
        registry.rebind("rmiServer", this);
    }

    public static void main(String[] args) throws RemoteException {
        Server server = new Server();
        System.out.println("RMI Server running...");
    }
}

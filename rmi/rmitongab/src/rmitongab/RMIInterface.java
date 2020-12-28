/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmitongab;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public interface RMIInterface extends Remote{
    public int calculateSum(int a, int b) throws RemoteException;
}

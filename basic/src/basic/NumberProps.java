/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import java.awt.RenderingHints;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class NumberProps {
    public static boolean isPrime(int k){
        if (k<2) return false;
        else{
        for (int i = 2; i <= Math.sqrt(k) ; i++) {
            if(k%i==0) return false; 
        }
        return true;
        }
    }
    public static void main(String[] args) {
        NumberProps n = new NumberProps();
        System.out.println(n.isPrime(-1));
    }
}

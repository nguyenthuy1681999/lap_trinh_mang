/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dethi;

import javafx.scene.input.KeyCode;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class bai1test4 {
    public static int[] getDoubleMirror(int a[], int n){
        int []doublemirror = new int[2*n]; // tao mang guong nhan doi
        //phan giong nhau
        for (int i = 0; i < n; i++) { // thay các phan tu giong nhau cua mang doublemirror voi a nhu binh thuong
            doublemirror[i] = a[i];
        }
        //phan phan chieu
        int k = 0; // tao mot bien k de thay nguoc cac phan tu cua a
        for (int i = n; i < 2*n; i++) { 
            k = k+1;
            doublemirror[i] = a[n-k]; // thay nguoc phan tu
        }
        return doublemirror; // tra ve ket qua
    }
    public static void main(String[] args) {
        int []a={1,2,4,3};
        int []ketqua = getDoubleMirror(a, 4); // mang ket qua lay ra tu ham
        System.out.println("Ket qua mang doublemirror: "); // in ket qua
        for (int i = 0; i < ketqua.length; i++) { // ham length lay so phan cua của mang ket qua
            System.out.print(ketqua[i]+" "); // in ra ket qua
        }
    }
}

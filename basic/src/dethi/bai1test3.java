/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dethi;

/**
 *
 * @author Nguyen Thu Thuy 1608
 */
public class bai1test3 {
    // lam giong bai 2, thay hieu bang thuong
    public static boolean isOddGeootrics(int a[], int n){
        
        for (int i = 2; i < n; i++) {
            //xet 3 so lien nhau
            //thuong 2 so sau
            float sau = a[i]/a[i-1];
            if(sau%2==0) return false; // neu chia het cho 2 thì hieu do la so chan, neu co la cong boi thi cũng khong phai cong boi le
            //hieu 2 so truoc
            float truoc = a[i-1]/a[i-2];
            if(sau != truoc) return false;  // hai thuong khong bang nhau nen khong phai cong boi   
        }
    return  true; // truong hop con lai dung
    }
    public static void main(String[] args) {
      int a[]= {1,3,9,27}; // mang test
        System.out.println("Ket qua: "+ isOddGeootrics(a, 4)); // in ket qua voi dau vao mang a, so phan tu n
    } 
}

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
public class bai1test2 {
    public static boolean isOddAdrithmetics(int a[], int n){
        
        for (int i = 2; i < n; i++) {
            //xet 3 so lien nhau
            //hieu 2 so sau
            int sau = a[i]-a[i-1];
            if(sau%2==0) return false; // neu chia het cho 2 thì hieu do la so chan, neu co la cong sai thi cũng khong phai cong sai le
            //hieu 2 so truoc
            int truoc = a[i-1]-a[i-2];
            if(sau != truoc) return false;  // hai hieu khong bang nhau nen khong phai cong sai   
        }
    return  true; // truong hop con lai dung
    }
    public static void main(String[] args) {
      int a[]= {1,2,5}; // mang test
        System.out.println("Ket qua: "+ isOddAdrithmetics(a, 3)); // in ket qua voi dau vao mang a, so phan tu n
    } 
}

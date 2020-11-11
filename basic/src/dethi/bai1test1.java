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
public class bai1test1 {
    public static int addSum(int begin, int end){
        int sum = 0;
        for(int i = begin; i<=end; i++){ // vong lap moi lan tang 1 don vi cho bien i
            if(i%2!=0) sum =sum+i; // xet i khong chia het cho 2 thi cong tiep vao tong sum
        }
        return sum; // tra ve tong
    }
    public static void main(String[] args) {
        System.out.println("Ket qua phep cong: "+addSum(1, 10)); // 1 vao 10 la 2 dau vao begin-end
    }
}

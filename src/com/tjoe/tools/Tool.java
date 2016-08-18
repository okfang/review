package com.tjoe.tools;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tjoe on 2016/3/22.
 */
public class Tool {
    /*
    * to generate a array
    * @param n the range of array
    * */
    public static List<Integer> range(int n){
        int i;
        int bound = n;
        List<Integer> temp = new ArrayList<Integer>();
        if(n < 0){
            for(i=n; i<0; i++){
                temp.add(i);
            }
        }else{
            for(i=0; i<bound; i++){
                temp.add(i);
            }
        }
        return temp;
    }
    public static List<Integer> range(int a, int b) throws Exception {
        if(a>b){
            throw new Exception("b must greater than a");
        }
        int i;
        List<Integer> temp = new ArrayList<Integer>();
        for(i=a; i<b; i++){
            temp.add(i);
        }
        return temp;
    }

    public  static void testPrime(int n){
        int range = n;
        int i,j;
        double k;
        int[] simple = {2,3,5,7,9};
        ArrayList primes =new ArrayList();
        for(int a :simple){
            primes.add(a);
        }
        for(i=11; i<range; i+=2){

            k = Math.sqrt(i);
            for(j=3; j<=k; j++){
                if(i%j == 0){
                    break;
                }
            }
            if(j>k){
                primes.add(i);
            }
        }
        for (Object a:primes){
            System.out.println(a);
        }

    }

    public static void toHex(byte[] bytes){
        String hex = "";
        String temp = "";
        for (byte b:bytes){
            temp = Integer.toHexString(b & 0xFF);
            if (temp.length() == 1){
                temp = '0'+temp;
            }
            hex += temp;
        }
        hex = hex.toUpperCase();
        for (int i = 0; i< hex.length(); i++){
            if (i % 2 == 0 ){
                System.out.print(" ");
            }
            System.out.print(hex.charAt(i));
        }
        System.out.println();
    }

}



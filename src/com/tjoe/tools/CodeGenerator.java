package com.tjoe.tools;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Tjoe on 2016/3/21.
 * 生成验证码
 */

public class CodeGenerator {

    final int num = 200;
    final int digits = 6;
    final char[] symbols = {'0','1','2','3','4','5','6','7','8','9'
            , 'A','B','C','D','E','F','G','H','I','J','K'
            ,'L','M','N','O','P','Q','R','S','T','U','V'
            ,'W','X','Y','Z'};
    List<String> codelist = new ArrayList<String>();

    public CodeGenerator(){}

    public void generate(){
        int index = 0;
        int lenOfSymbols = symbols.length;
        char symbol ;
        Random rand = new Random(new Date().getTime());
        int i,j;

        for(j=0; j<num; j++){
            String code ="";
            for(i = 0; i < digits; i++){
                symbol = symbols[rand.nextInt(lenOfSymbols)]; //Random.netInt() 可以产生正数和负数
                code += symbol;
            }
            if(!codelist.contains(code)){
                codelist.add(code);
                j++;
            }else{
                System.out.println("罕见的重复了....."+code);
            }
        }

        for(String str:codelist){
            System.out.println(str);
        }
    }

}

package com.tjoe.tools;

import java.io.*;

/**
 * Created by Tjoe on 2016/8/8.
 */
public class JavaCodeCounter {
    private int totalLine = 0;
    private String targets[] = {"E:/java/bitcoinj/core"};


    public static void main(String[] args) {
        JavaCodeCounter jcc = new JavaCodeCounter();
        for (String target: jcc.getTargets()){
            File file = new File(target);
            if(file.exists()){
                jcc.analye(0, file);
            }else{
                System.out.println(file.getAbsolutePath()+" 找不到该路径！请检查输入文件路径是否正确");
            }
        }
        System.out.println("总行数： "+ jcc.getTotalLine());
    }

    public void analye(int depth, File target){
        int height = depth;
        if(target.isFile()){
            if(target.getName().endsWith(".java")){
                int currentLine = 0;
                currentLine = countLine(target);
                totalLine += currentLine;

                for (int i = 0 ; i< height; i++){
                    System.out.print("|..");
                }
                System.out.println(target.getName()+" ==> line "+currentLine);
            }
        }else {
            for (int i = 0 ; i< height; i++){
                System.out.print("|..");
            }
            System.out.println(target.getName());
            for (File subFile: target.listFiles()){
                analye(height+1, subFile);
            }
        }
    }

    private int countLine(File f){
        int num = 0;
        FileReader fr = null;
        try {
            fr = new FileReader(f) ;
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            do{
                line = br.readLine();
                num++;
            }while(line != null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return num;
    }

    public int getTotalLine() {
        return totalLine;
    }

    public String[] getTargets() {
        return targets;
    }
}

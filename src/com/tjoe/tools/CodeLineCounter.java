package com.tjoe.tools;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tjoe on 2016/3/23.
 * 用于统计某个路径下所有源文件代码行数
 */
public class CodeLineCounter {

    String[] targets = {"E:/java/ideaproject"};
    int totalLine ;
    static int depth;   //目录深度
    static List<Parser> threadPool = new LinkedList<Parser>();

    CodeLineCounter(){

    }

    public static void main(String[] args){
        CodeLineCounter clc = new CodeLineCounter();
        clc.start();
    }

    public void start(){
        String path;
        for(String target:targets){
            depth = 1;            //新一个目录重置深度
            totalLine = 0;
            path = target.trim();
            System.out.println(path);
            File file = new File(path);
            if(file.exists()){
                System.out.println(file.getName());
                analyze(file);
            }else{
                System.out.println(file.getAbsolutePath()+" 找不到该路径！请检查输入文件路径是否正确");
            }
            for(Parser parser:threadPool){
                try {
                    parser.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("总行数 : "+totalLine +" 行\n\n");
        }

    }

    private void analyze(File current_file){
        int prefix = depth;         //记录当前目录深度，美化输出格式
        if(current_file.isFile()){//判断是否是文件
            if(current_file.getName().endsWith(".java")){ //判断是否是java文件,如果是则分析否则跳过
                //新建一个线程来处理文件
                Parser parser = new Parser();
                parser.setTarget(current_file);
                parser.start();
                threadPool.add(parser);
//                int line_of_num = countline(current_file);
//                totalLine += line_of_num;
//                System.out.println( current_file.getName()+" : "+ line_of_num+" 行");
            }
        }else{
            //文件是个目录，遍历目录下文件
            for(File child_file:current_file.listFiles()){
                for(int i=0; i<prefix; i++){
                    System.out.print("|..");
                }
                System.out.print(child_file.getName()+"\n");
                depth++;
                analyze(child_file);
                depth = prefix; //重置目录深度，以当前目录为准。
            }
        }
    }

    private class Parser extends Thread{
        private File target;
        private int line_of_num;
        @Override
        public void run() {
//            countline(target);
            line_of_num = countline(target);
            System.out.println( target.getName()+" : "+ line_of_num+" 行");
            totalLine += line_of_num;
        }

        public void setTarget(File target) {
            this.target = target;
        }

        private int countline(File f){
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
    }

    private int countline(File f){
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
}

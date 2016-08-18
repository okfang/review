package com.tjoe.demo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tjoe on 2016/6/10.
 */
public class PeopleStatistic {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i <5 ; i++) {
            exec.execute(new Entrance(i));
        }
        exec.shutdown();

        for (int i = 0; i <3 ; i++) {
            Entrance.show();
            try {
                Thread.sleep(2000);   //每隔三秒统计一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Entrance.cancle();
        if (!exec.awaitTermination(2500, TimeUnit.MILLISECONDS)){
            System.out.println("some tasks were not terminated!");
        }
        Entrance.show();

    }
    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            Entrance.show();
        }

    }
}



class Entrance implements Runnable {
    public static Count count = new Count();
    private int number;
    private int id;
    private static List<Entrance> entrances = new LinkedList<Entrance>();
    private static boolean iscancled;

    public static void cancle(){
        iscancled = true;
    }


    Entrance(int id) {
        this.id = id;
        this.number = 0;
        entrances.add(this);
    }

    @Override
    public void run() {
        while (!iscancled){
            synchronized (Entrance.class){
                number++;
                count.increment();
            }
        }
        System.out.println("com.tjoe.demo.Entrance["+id+"] stopping!");
    }

    public int getValue(){
        return number;
    }

    public static synchronized void show(){
        for (Entrance entrance: entrances){
            System.out.println(""+entrance+entrance.getValue());
        }
        System.out.println("tatol: "+count.count);
    }

    @Override
    public String toString() {
        return "com.tjoe.demo.Entrance["+id+"]: ";
    }
}

class Count{
    int count = 0;
    Count() {}
    public void increment() {
        count ++;
        Random rand = new Random(47);
        try {
            Thread.sleep(rand.nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
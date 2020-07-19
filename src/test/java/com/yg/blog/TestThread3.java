package com.yg.blog;

import java.util.concurrent.locks.ReentrantLock;

public class TestThread3 implements Runnable {
    private static String win;

    private  final ReentrantLock lock=new ReentrantLock();
    @Override
    public  void run() {

        for (int i = 0; i <=1000; i++) {
            lock.lock();

            boolean over=gameOver(i);
            if (over){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"====跑了"+i+"步");
            lock.unlock();
        }
    }

    private  boolean gameOver(int stopt){
        if (win!=null){
            return true;
        }else{
            if (stopt>=1000){
                win=Thread.currentThread().getName();
                System.out.println(win+"赢了");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TestThread3 testThread3=new TestThread3();
        new Thread(testThread3,"兔子").start();
        new Thread(testThread3,"乌龟").start();
    }
}

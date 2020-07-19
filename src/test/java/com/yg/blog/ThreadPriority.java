package com.yg.blog;

public class ThreadPriority {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"=="+Thread.currentThread().getPriority());
        Mypriority mypriority=new Mypriority();

        Thread t1=new Thread(mypriority,"1");
        Thread t2=new Thread(mypriority,"2");
        Thread t3=new Thread(mypriority,"3");
        Thread t4=new Thread(mypriority,"4");
        Thread t5=new Thread(mypriority,"5");
        Thread t6=new Thread(mypriority,"6");

        t1.start();

        t2.setPriority(Thread.MAX_PRIORITY);
        t2.start();

        t3.setPriority(3);
        t3.start();

        t4.setPriority(4);
        t4.start();

        t5.setPriority(10);
        t5.start();

        t6.setPriority(6);
        t6.start();
    }
}
class Mypriority implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"=="+Thread.currentThread().getPriority());
    }
}
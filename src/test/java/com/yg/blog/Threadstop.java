package com.yg.blog;

public class Threadstop implements Runnable{
    private boolean flag=true;
    @Override
    public void run() {
        int i=0;
        while (flag){
            System.out.println("run...."+i++);
        }
    }
    public void stop(){
        this.flag=false;
    }

    public static void main(String[] args) {
        Threadstop threadstop=new Threadstop();
        new Thread(threadstop).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("main"+i);
            if (i==90){
                //调用自己定义的stop方法 切换ture 或者是flase 让线程停止
                threadstop.stop();
                System.out.println("线程停止");
            }
        }
    }
}

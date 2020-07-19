package com.yg.blog;

public class ThreadDaemon {
    public static void main(String[] args) {
        You1 you1=new You1();
        God g=new God();
        Thread thread=new Thread(g);
        thread.setDaemon(true);
        thread.start();

        new Thread(you1).start();
    }

}
class God implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("上帝一直守护着你");
        }
    }
}

class You1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 35600; i++) {
            System.out.println("我一直都在");
        }
        System.out.println("结束");
    }
}
package com.yg.blog;

public class Threadsignal {
    public static void main(String[] args) {
        Tv tv=new Tv();
        producer p=new producer(tv);
        Player player=new Player(tv);
        new Thread(p).start();
        new Thread(player).start();
    }
}

//s生产
class producer implements Runnable{
    Tv tv;

    public producer(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i%2==0){
                tv.play("极限挑战");
            }else {
                tv.play("斗鱼广告");
            }
        }
    }
}
//消费
class Player implements Runnable{
    Tv tv;

    public Player(Tv tv) {
        this.tv = tv;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}

class Tv{
    String program;
    boolean flag=true;
//表演
    public synchronized void play(String voice){
        if (!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("我开始表演了："+voice);
        this.notifyAll();
        this.program=voice;
        this.flag=!this.flag;
    }
    //观看
    public synchronized void watch(){
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("观看了"+program);

        //通知表演
        this.notifyAll();
        this.flag=!this.flag;
    }
}

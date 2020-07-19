package com.yg.blog;

public class ThreadWait {
    public static void main(String[] args) {
        SynContainer synContainer=new SynContainer();
        Productor productor=new Productor(synContainer);
        Consumer consumer=new Consumer(synContainer);
        new Thread(productor).start();
        new Thread(consumer).start();
    }
}
//生产者
class Productor implements Runnable{
    SynContainer synContainer;

    public Productor(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synContainer.push(new Chiken(i));
            System.out.println("现在一共"+i);
        }
    }
}
//消费着
class Consumer implements Runnable{
    SynContainer synContainer;

    public Consumer(SynContainer synContainer) {
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了"+synContainer.pop().id);
        }
    }
}
//产品
class Chiken{
    int id=0;

    public Chiken(int id) {
        this.id = id;
    }
}

//缓存
class SynContainer{
    Chiken[] chikens=new Chiken[10];
    int count=0;
    //生产着放入商品
    public synchronized void push(Chiken chiken){
        if (count==chikens.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果没有满就继续加入商品
        chikens[count]=chiken;
        count++;
        //通知消费者消费
        this.notifyAll();
    }
    public synchronized Chiken pop(){
        if (count==0){
            //如果没商品了 就等待生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //如果有商品
        count--;
        Chiken chiken=chikens[count];
        //消费完了通知生产者生产
        this.notifyAll();
        return  chiken;
    }
}

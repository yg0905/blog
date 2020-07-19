package com.yg.blog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadSleep {
    public static void main(String[] args) {
        Date date=new Date(System.currentTimeMillis());//获得当前系统时间
        while (true){
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(date));
                date=new Date(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

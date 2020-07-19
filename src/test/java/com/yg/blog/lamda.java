package com.yg.blog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class lamda {
    public static void main(String[] args) {
        Hell hell=(a,b)->{
            SimpleDateFormat oldFormatter = new SimpleDateFormat("yyyy/MM/dd/HH:mm");
            Date date=new Date();
            System.out.println("这是a"+a+"==="+oldFormatter.format(date));
            System.out.println("这是b"+b);
        };
        hell.test(1,new Date().toString());
    }
}
interface Hell{
    void test(int a,String b);
}
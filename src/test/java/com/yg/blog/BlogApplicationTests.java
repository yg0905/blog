package com.yg.blog;

import org.junit.jupiter.api.Test;


class A {
    static {
        System.out.print("1");
    }
    public A() {
        System.out.print("2");
    }
}
class B extends A{
    static {
        System.out.print("a");
    }
    public B() {
        System.out.print("b");
    }
}

public  class BlogApplicationTests {
//当值超过127的时候就会重写new Integer（）,所以两个值就会不相等
    @Test
   public void contextLoads() {
        Integer a=128;
        Integer b=128;
        System.out.println(a == b);

    }


}

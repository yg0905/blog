package com.yg.blog;

public class Staticproxy {
    public static void main(String[] args) {
        WeddingCompany w=new WeddingCompany(new You());
        w.HappMarry();
    }

}
interface marry{
    void HappMarry();
}
//自己结婚
class You implements marry{

    @Override
    public void HappMarry() {
        System.out.println("我要结婚了");
    }
}
//婚庆公司代理 帮助你结婚
class WeddingCompany implements marry{
    private marry ma;

    public WeddingCompany(marry ma) {
        this.ma = ma;
    }

    //重写接口方法
    @Override
    public void HappMarry() {
        before();
        this.ma.HappMarry();//婚庆公司找我来结婚
        after1();
    }

    private void before() {
        System.out.println("结婚钱");
    }

    private void after1() {
        System.out.println("结婚后");
    }
}
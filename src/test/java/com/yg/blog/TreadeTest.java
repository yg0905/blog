package com.yg.blog;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TreadeTest extends Thread {
    private String name;
    private String url;

    public TreadeTest(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public void run() {
//        for (int i = 0; i < 1000; i++) {
//            System.out.println("run"+i);
//        }
        webDownloader webDownloader=new webDownloader();
        webDownloader.downloder(url,name);
        System.out.println("下载文件名为"+name);
    }

    public static void main(String[] args) {
//        TreadeTest treadeTest=new TreadeTest();
//        treadeTest.start();
//        for (int i = 0; i < 2000; i++) {
//            System.out.println("main"+i);
//        }
        TreadeTest treadeTest2=new TreadeTest("2.jpg","http://a4.att.hudong.com/21/09/01200000026352136359091694357.jpg");
        TreadeTest treadeTest=new TreadeTest("1.jpg","http://a0.att.hudong.com/78/52/01200000123847134434529793168.jpg");
        TreadeTest treadeTest3=new TreadeTest("3.jpg","https://c-ssl.duitang.com/uploads/item/201409/08/20140908130732_kVXzh.thumb.1900_0.jpeg");
        TreadeTest treadeTest4=new TreadeTest("4.jpg","http://a4.att.hudong.com/21/09/01200000026352136359091694357.jpg");
        TreadeTest treadeTest5=new TreadeTest("5.jpg","http://a0.att.hudong.com/78/52/01200000123847134434529793168.jpg");
        TreadeTest treadeTest6=new TreadeTest("6.jpg","https://c-ssl.duitang.com/uploads/item/201409/08/20140908130732_kVXzh.thumb.1900_0.jpeg");

        treadeTest.start();
        treadeTest2.start();
        treadeTest3.start();
        treadeTest4.start();
        treadeTest5.start();
        treadeTest6.start();
    }
}

class webDownloader{
    public void downloder(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("io异常");
        }
    }
}
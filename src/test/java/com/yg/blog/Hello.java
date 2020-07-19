package com.yg.blog;

import com.yg.blog.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class Super {
    public Integer getLenght() {
        return new Integer(4);
    }
}

class StaticTest{
    static int i=47;
}
@SpringBootTest
public class Hello extends Thread {
    @Override
    public void run() {
        super.run();
    }

    @Test
    public void s() throws Exception {
        Executors e = null;
        ExecutorService executorService = e.newCachedThreadPool();
        User s1 =new User();
        s1.setUsername("lisi");
        System.out.println("克隆前："+s1);
        User s2 = (User) s1.clone();
        System.out.println(s1.getUsername()==s2.getUsername() ?"是浅拷贝":"是深拷贝");
//        Thread thread=new Thread();
//        Thread thread1=new Thread();
//        thread.start();
//        thread1.run();
//        System.out.println("线程就绪");
//        thread.sleep(30000);
//        System.out.println("线程睡眠3秒");
//        thread.run();
//        System.out.println("线程运行了");
//        thread.yield();
//        System.out.println("线程暂停");
        List<String> list=new Vector<>();
//        List<String> list1=new LinkedList<>();
//
//        list.add(0,"1");
//        list1.addAll(list);
//        List<String> list2=new ArrayList<>(list);
//        list2.listIterator();
//        Vector vector=new Vector();
//        vector.add(0,"1");
//        vector.add(1,"2");
//        vector.add(2,"3");
//        Enumeration elements = vector.elements();
//        while (elements.hasMoreElements()){
//
//            System.out.println(elements.nextElement());
//        }

//        for (String s : list1) {
//            System.out.println(s);
//        }
//        System.out.println(3&9);
//        long temp=(int)5.9;
//        System.out.println(temp);
//        System.out.println(temp%=2);
    }

//    public Integer getLenght() {
//        return new Integer(5);
//    }
//
//    public static void main(String[] args) {
//        Super sooper = new Super();
//        Hello sub = new Hello();
//        System.out.println(sooper.getLenght().toString() + "," + sub.getLenght().toString());
//    }
//        System.gc();
////        A ab = new B();
////        B b = new B();
//        Integer a=22;
//
//        StringBuffer sb = new StringBuffer();
//        sb.append("hello");
//        sb.append("123");
//        //插入一条消息
//       // sb.insert(sb.length(),"");
//     //   sb.reverse();//反转
//        System.out.println(sb);
    //更改日期格式
//        SimpleDateFormat oldFormatter = new SimpleDateFormat("yyyy/MM/dd/HH:mm");
//        Date date1 = new Date();
//        System.out.println(date1);
//        System.out.println(oldFormatter.format(date1));

    //软引用
//        Object obj=new Object();
//        User user=new User();
//        SoftReference SoftReference=new SoftReference(user);
//        //Collections工具类的使用
    // Compare（）比较用来排序的两个参数。根据第一个参数小于、等于或大于第二个参数分别返回负整数、零或正整数。 在前面的描述中，符号 sgn(expression) 表示 signum 数学函数，根据 expression 的值为负数、0 还是正数，该函数分别返回 -1、0 或 1。
//    List<Double> list = new ArrayList<>();
//        Collections.sort(list, new Comparator() {
//
//            @Override
//            public int compare(Map.Entry<Integer, User> o1, Object o2) {
//                return 0;
//            }
//        });
//        double array[] = {112, 111, 23, 456, 231 };
//        for (int i = 0; i < array.length; i++) {
//            list.add(new Double(array[i]));
//        }
//        Collections.fill(list,1.0);
//        for (int i = 0; i < array.length; i++) {
//            System.out.println(list.get(i));
//        }
    //冒泡排序
//        int arr[]={5,4,2,9};
//        for(int i=0;i<arr.length-1;i++)
//        {
//            //外层循环控制排序趟数
//            for(int j=0;j<arr.length-1-i;j++)
//            {//内层循环控制每一趟排序多少次
//                if(arr[j]>arr[j+1]) {
//                    int temp=arr[j];
//                    arr[j]=arr[j+1];
//                    arr[j+1]=temp;
//                }
//            }
//        }
//        for (int k=0;k<arr.length;k++){
//            System.out.println(arr[k]);
//        }


}
class test{
     static int Factorial(int n){
        if (n == 0)  return 1;
        return n * Factorial(n - 1);
    }


    public static void main(String[] args) {
        System.out.println( Factorial(4));
        StringBuffer stringBuffer=new StringBuffer();
        StringBuilder stringBuilder=new StringBuilder();
        Map<String,String> mao=new HashMap<>();
        //使用spring创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        User user=(User)context.getBean("user");
        System.out.println(user.getId());
    }
}



class ma{
    public static void main(String[] args) {
                int arr[]={5,1,4,2,9};
        for(int i=0;i<arr.length-1;i++)
        {
            //外层循环控制排序趟数
            for(int j=0;j<arr.length-1-i;j++)
            {//内层循环控制每一趟排序多少次
                if(arr[j]>arr[j+1]) {
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        for (int k=0;k<arr.length;k++){
            System.out.println(arr[k]);
        }
    }
}

class tag{
    private String name;

    public tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class optional{
    @Test
    public void mai1111n1() {
        tag t=new tag("hhh");
        try {
            System.out.println(getname(t));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getname(tag tag) throws Exception {
        return Optional.ofNullable(tag).map(u->u.getName()).orElse("起重机哦");
    }

    public static void main(String[] args) {
        String a="Aa";
        String b="BB";
        System.out.println(a.hashCode()+"----"+b.hashCode());
        System.out.println(a.equals(b));
    }
}

class map{
    public static void main(String[] args) {
        Integer arr[]={5,1,1,1,9,9,9,9,9,9,9,9};
        List<Integer> list=new ArrayList<>(Arrays.asList(arr));
        Map<Integer,Integer> map=new HashMap<>();

        for (int i : list) {
            if (!map.containsKey(i)){
                map.put(i,1);
            }else{
                map.put(i,map.get(i)+1);
            }
        }
        System.out.println(map);

        try {

        }catch (Exception e){

        }
    }
}
class set1{
    public static void main(String[] args) {
        Set<String> set=new TreeSet<>();
        set.add("1");
        set.add("1");
    }
}
class ThreadT extends Thread{
    private  final ReentrantLock lock=new ReentrantLock();
    static int r=1;
    @Override
    public void run() {
        synchronized (ThreadT.class) {
            for (int i = 0; i <5; i++) {
                ThreadT.class.notify();
                System.out.println(Thread.currentThread().getName() + ":" + r);
                r++;
                try {
                    ThreadT.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {

        ThreadT t1=new ThreadT();
        t1.setName("A");
        t1.start();
        ThreadT t2=new ThreadT();
        t2.setName("B");
        t2.start();
    }
}


class Threadxunhuan implements Runnable{
    private int a=100;
    @Override
    public synchronized void run() {
        while (true){
            if (a<=0){
                break;
            }

            System.out.println(Thread.currentThread().getName()+"-----"+a--);
        }
    }

    public static void main(String[] args) {
        Threadxunhuan threadxunhuan=new Threadxunhuan();
        new Thread(threadxunhuan,"A").start();
        new Thread(threadxunhuan,"B").start();
        new Thread(threadxunhuan,"C").start();
    }
}



class equlua{
    public static void main(String[] args) {
        String a=null;
        System.out.println("掌声".equals(a));
    }
}

class array{
    public static void main(String[] args) {
        Integer[] a={1,2,3,0};
        List<Integer> li1=new ArrayList<>();
        List<Integer> li=Arrays .asList(a);
        Collections.reverse(li);
        Object[] objects = li.toArray();
        System.out.println();
    }
}
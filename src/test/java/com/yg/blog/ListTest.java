package com.yg.blog;

import com.yg.blog.bean.Tag;
import com.yg.blog.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ListTest {
    public static void main(String[] args) {
        int i=1;
        String str="hello";
        Integer um=200;
        int my[]={1,2,3,4,5};
        myDate m=new myDate();
        chang(i,str,um,my,m);
        System.out.println(i);
        System.out.println(str);
        System.out.println(um);
        System.out.println(Arrays.toString(my));
        System.out.println(m.a);


        //i++ 加加在后面是将i放到操作数栈里面 然后对原变量i进行++ 最后操作数栈里面的的值赋值给i  也就是说将原变量0赋值给i所以还是0
        // 加加在前面 就先加加 在赋值

//        int i=0;
//        System.out.println(i);
//        i=++i;
//        System.out.println(i);
//        int j=++i;
//        System.out.println(i);
//        System.out.println(j);
//        List<Integer> list=new ArrayList<>();
//        List<Integer> w=new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(2);
//        list.add(3);
//        list.add(3);
//        list.add(4);
//        for (Integer s:list) {
//
//            if (!w.contains(s)) {
//
//                w.add(s);
//
//            }
//
//        }
//迭代器删除元素
//        Iterator<Integer> iterator = list.iterator();
//        while (iterator.hasNext()){
//            Integer next = iterator.next();
//            if (2==next){
//                iterator.remove();
//            }
//            System.out.println(next);
//        }
//        System.out.println(w.toString());
    }

    public static void chang(int i,String b,Integer c,int[] d,myDate m){
        i+=1;
        b+="ccc";
        c+=1;
        d[0]+=1;
        m.a+=1;
    }
}
class myDate {
    int a=10;
}

class digui{
    public static void main(String[] args) {
        System.out.println(test(5));
    }
    //这样的方式效率快 不耗时间
    public static int test(int num){
        if (num==1||num==2){
            return num;
        }
        int a=1;
        int b=2;
        int sum=0;
        for (int i =3; i <= num; i++) {
            sum=a+b;
            a=b;
            b=sum;
        }
        return  sum;
    }
}

class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int CACHE_SIZE;

    // 这里就是递进来最多能缓存多少数据
    public LRUCache(int cacheSize) {
        // 这块就是设置一个hashmap的初始大小，同时最后一个true指的是让linkedhashmap按照访问顺序来进行排序，最近访问的放在头，最老访问的就在尾
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > CACHE_SIZE; // 这个意思就是说当map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据
    }

}
@Controller
@RequestMapping("/")
class Controller1{
    TagService blogService;
    @RequestMapping("/WW")
    public List<Tag> users(){
        return  blogService.tags();
    }

}
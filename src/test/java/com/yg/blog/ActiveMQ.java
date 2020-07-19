//package com.yg.blog;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.ActiveMQMessageProducer;
//import org.apache.activemq.AsyncCallback;
//import org.apache.activemq.ScheduledMessage;
//
//import javax.jms.*;
//import java.io.IOException;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.stream.Stream;
//
//public class ActiveMQ {
//    public static final String ActiveMq_URL="tcp://192.168.176.129:61616";
//    public static final String QUEUE_NAME="XIAOXI001";
//    public static void main(String[] args) throws JMSException {
//
//        //1.创建链接工厂
//        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory(ActiveMq_URL);
//        //2.通过链接工厂，获得链接并启动
//        Connection connection=activeMQConnectionFactory.createConnection();
//        connection.start();
//        //3.创建会话session 两个参数，第一个叫事务，第二个叫签收
//        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        //4.创建目的地（看是创建主题还是队列）
//        //Queue queue=session.createQueue(QUEUE_NAME);
//        //创建主题Topic 一对多
//        Topic topic=session.createTopic(QUEUE_NAME);
//        //5.创建消息的生产者
//        ActiveMQMessageProducer messageProducer= (ActiveMQMessageProducer) session.createProducer(topic);
//        //6.通过messageProducer生产三条消息发送给mq的队列里面
//        for (int i = 1; i <= 7; i++) {
//            //7.创建消息
//            TextMessage textMessage=session.createTextMessage("msg----"+i);
//            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,3000);
//            //8.通过messageProducer发送给mq
//            messageProducer.send(textMessage, new AsyncCallback() {
//                @Override
//                public void onSuccess() {
//                    System.out.println("回调成功");
//                }
//
//                @Override
//                public void onException(JMSException e) {
//                    System.out.println("回调失败");
//                }
//            });
//        }
//        //9.关闭
//        messageProducer.close();
//        session.close();
//        connection.close();
//        System.out.println("发送完成");
//    }
//}
//class Xiaofei{
//    public static final String ActiveMq_URL="tcp://192.168.176.129:61616";
//    public static final String QUEUE_NAME="XIAOXI001";
//    public static void main(String[] args) throws JMSException, IOException {
//        //1.创建链接工厂
//        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory(ActiveMq_URL);
//        //2.通过链接工厂，获得链接并启动
//        Connection connection=activeMQConnectionFactory.createConnection();
//        connection.start();
//        //3.创建会话session 两个参数，第一个叫事务，第二个叫签收
//        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        //4.创建目的地（看是创建主题还是队列）
//        //Queue queue=session.createQueue(QUEUE_NAME);
//        //创建主题Topic 一对多
//        Topic topic=session.createTopic(QUEUE_NAME);
//        MessageConsumer messageConsumer=session.createConsumer(topic);
//        //通过监听的方式消费消息 只要有消息进来 就消费  如果没有就等待 使用的是lambda表达式
//        messageConsumer.setMessageListener(message->{
//            TextMessage textMessage= (TextMessage) message;
//            if (message!=null &&message instanceof TextMessage){
//                try {
//                    System.out.println("消费者接受到消息"+textMessage.getText());
//                } catch (JMSException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//       /* messageConsumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                TextMessage textMessage= (TextMessage) message;
//                if (message!=null &&message instanceof TextMessage){
//                    try {
//                        System.out.println("消费者接受到消息"+textMessage.getText());
//                    } catch (JMSException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });*/
//        //让控制台等待
//        System.in.read();
//        messageConsumer.close();
//        session.close();
//        connection.close();
//    }
//}
//
//class stream{
//    public static void main(String[] args) {
//        //窜
//        List<String> list = new ArrayList<String>();
//        for(int i=0;i<1000000;i++){
//            double d = Math.random()*1000;
//            list.add(d+"");
//        }
//        long start = System.nanoTime();//获取系统开始排序的时间点
//        int count= (int) ((Stream) list.stream().sequential()).sorted().count();
//        long end = System.nanoTime();//获取系统结束排序的时间点
//        long ms = TimeUnit.NANOSECONDS.toMillis(end-start);//得到串行排序所用的时间
//        System.out.println(ms+"ms");
//    }
//    public void bing(){
//        List<String> list = new ArrayList<String>();
//        for(int i=0;i<1000000;i++){
//            double d = Math.random()*1000;
//            list.add(d+"");
//        }
//        long start = System.nanoTime();//获取系统开始排序的时间点
//        int count = (int)((Stream) list.stream().parallel()).sorted().count();
//        long end = System.nanoTime();//获取系统结束排序的时间点
//        long ms = TimeUnit.NANOSECONDS.toMillis(end-start);//得到并行排序所用的时间
//        System.out.println(ms+"ms");
//    }
//}
//
////java.util.ConcurrentModificationException 并发修改异常
//class list{
//    public static void main(String[] args) {
//        List<String> list=new ArrayList<>();
//        for (int i=0;i<=30;i++) {
//            new Thread(()->{
//                list.add(UUID.randomUUID().toString().substring(0,6));
//                System.out.println(list);
//            },String.valueOf(i)).start();
//        }
//    }
//}
//class gc{
//    public static void main(String[] args) {
//        String str="www";
//        while (true){
//            str+=str+new Random().nextInt(88888888)+new Random().nextInt(99999999);
//        }
//    }
//}
//class funaction{
//    public static void main(String[] args) {
//        Function<String,Integer> function=s -> {
//            return s.length();
//        };
//        System.out.println(function.apply("sssss"));
//        Predicate<Integer> predicate=s->{ if (s==0){ return false; }return true; };
//        System.out.println(predicate.test(2));
//
//    }
//}
////生成构造函数
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//class user{
//    private int id;
//    private int age;
//    private String name;
//}
//class testuser {
//    public static void main(String[] args) {
//        user u1=new user(10,23,"a");
//        user u2=new user(12,24,"b");
//        user u3=new user(13,25,"c");
//        user u4=new user(14,26,"d");
//        user u5=new user(15,27,"e");
//        List<user> list=Arrays.asList(u1,u2,u3,u4,u5);
//        list.stream().filter(s->{
//            return s.getId()%2==0;
//        }).filter(s->{
//            return s.getAge()>2;
//        }).map(s->{
//            return s.getName().toUpperCase();
//        }).sorted((o1, o2) -> {
//            return o2.compareTo(o1);
//        }).skip(1).limit(1).forEach(System.out::println);
//    }
//}
//
//class Titck{
//    private int num=3000;
//    Lock lock=new ReentrantLock();
//    public void sale(){
//        lock.lock();
//        if(num>0){
//            System.out.println(Thread.currentThread().getName() + "卖出" + (num--)+ ",还剩" + num);
//        }
//        lock.unlock();
//    }
//}
//class TitckTest{
//    public static void main(String[] args) {
//        Titck titck=new Titck();
//        new Thread(()->{ for (int i = 0; i <= 3000; i++) titck.sale(); },"a").start();
//        new Thread(()->{ for (int i = 0; i <= 3000; i++) titck.sale(); },"b").start();
//        new Thread(()->{ for (int i = 0; i <= 3000; i++) titck.sale(); },"c").start();
//    }
//}
////集合不安全异常测试
//class listTes1t{
//    public static void main(String[] args) {
//        Map<String,String> map=new HashMap<>();
//        for (int i = 0; i < 30; i++) {
//            new Thread(()->{map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8)); System.out.println(map);},String.valueOf(i)).start();
//
//        }
//    }
//}
//class jvmTest{
//    public static void main(String[] args) {
//        for (int i = 0; i < 100000; i++) {
//            String.valueOf(i).intern();
//        }
//    }
//}
//class jvmtest{
//    private int a=1;
//    private static int c=3;
//
//    public static void main(String[] args) {
//        List<String> list=new ArrayList<>();
//        list.add("adadsss");
//        list.add("sdddd");
//        list.stream().filter((s) -> s.endsWith("s")).forEach(System.out::println);//过滤
//
//    }
//    public jvmtest(){
//        a=10;
//        int b=20;
//    }
//
//}
//class Intern1 {
//    public static void main(String[] args) {
//        String s=new String("1");//这个是堆空间的地址  返回的是堆空间的对象
//        String s1="1";//这个是常量池中的地址
//        System.out.println(s==s1);//false 因为地址不同
//
//        String s2=new String("1")+new String("1");//s2变量记录的地址是 new String（“11”）的地址，不在常量池中
//        s2.intern();//这个方法的作用是检查常量池中有没有11 如果没有就放进去 有就不放,这时s2变量记录的地址就是常量池中的
//        String s3="11";//s3记录的是上一行代码在常量池中生产的地址
//        System.out.println(s2==s3);
//    }
//}
//interface interface1{
//    default void show(){
//        System.out.println("11");
//    }
//}
//class Math1{
//    public static void main(String[] args) {
//        System.out.println(Math.round(2.5));
//        Map<String,String> map=new HashMap<>();
//        map.put("11","222");
//    }
//}
//
//class Test1 {
//    public static void main(String[] args) {
//        //统计相同字符串的个数
//        String str = "aahhhbbbbbbb";
//        char[] arr = str.toCharArray();//1.转换成数组
//        HashMap<Character,Integer> hs = new HashMap<>();//2.创建一个map
//        //3.遍历，从数组中取出每一个字符
//        for (char c : arr) {
//            //System.out.println(c);
//            //每取出一个元素，判断一下，该元素是否在map中报班这一个key
//            //containsKey判断是否包含指定的键
//            if(!hs.containsKey(c)){
//                //如果不包含，把当前字符当作key存起来，value=1
//                hs.put(c, 1);
//            }
//            else{
//                //如果包含，去修改相对应的value的值，value+1
//                hs.put(c, hs.get(c)+1);
//            }
//        }
//
//        System.out.println(hs);
//
//    }
//}
//
//class ifelse{
//    public static void main(String[] args) {
//        Integer i=0;
//        String sex=i==1?"男":"女";
//        System.out.println(sex);
//    }
//}
////数组越界，测试hashMap死循环
//class HashMapTest {
//    public static void main(String[] args) {
//        HashMapThread thread0 = new HashMapThread();
//        HashMapThread thread1 = new HashMapThread();
//        HashMapThread thread2 = new HashMapThread();
//        HashMapThread thread3 = new HashMapThread();
//        HashMapThread thread4 = new HashMapThread();
//        thread0.start();
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//    }
//}
//
//class HashMapThread extends Thread {
//    private static AtomicInteger ai = new AtomicInteger();
//    private static Map<Integer, Integer> map = new HashMap<>();
//
//    @Override
//    public void run() {
//        while (ai.get() < 1000000) {
//            map.put(ai.get(), ai.get());
//            ai.incrementAndGet();
//        }
//    }
//}
//class intern{
//    public static void main(String[] args) {
//        String s1 = "Programming";
//        String s2 = new String("Programming");
//        String s3 = "Program";
//        String s4 = "ming";
//        String s5 = "Program" + "ming";
//        String s6 = s3 + s4;
//        System.out.println(s1 == s2); //false
//        System.out.println(s1 == s5); //true
//        System.out.println(s1 == s6.intern()); //true
//        System.out.println(s2 == s2.intern()); //false
//       }
//}
////台阶走法
//class taijie{
//    private static int f(int n){
//        if (n<1){
//        }
//        if (n==1||n==2){
//            return n;
//        }
//        return f(n-1)+f(n-2);
//    }
//    //循环迭代 时间端短效率高
//    private static int f2(int n){
//        if (n<1){
//        }
//        if (n==1||n==2){
//            return n;
//        }
//        int one=2;//初始化台阶只有2个的走法
//        int two=1;//初始化台阶只有1个的走法
//        int sum=0;//这个sum等于前两个台阶的走法次数相加
//
//        for (int i=3;i<=n;i++){
//            sum=two+one;
//            two=one;
//            one=sum;
//        }
//        return sum;
//    }
//    public static void main(String[] args) {
//        long start=System.currentTimeMillis();
//        System.out.println(f(400));
//        long end=System.currentTimeMillis();
//        System.out.println(end - start);
//        long start2=System.currentTimeMillis();
//        System.out.println(f2(400));
//        long end2=System.currentTimeMillis();
//        System.out.println(end2 - start2);
//    }
//}
//
////请用任何一种编程语言(包括伪代码)实现这道题目:按顺序报数:前3个人要说的数字是固定的，
////        第1个人报3,第2个人报5,第3个人报7。那么从4个人开始，编号为i的人需要说出的数字为编号i-1和编号i-3的人的数字之和，
////        减去编号为i-2的人的数字的2倍。
////
////        举个例子:第四个人报0(说明: 3+7-2*5=0), 第五个人报- 9 (说明: 5+0-2*7= -9)。
//class suanfaM{
//    private static int one =3;
//    private static int twe=5;
//    private static int tree=7;
//    public static void main(String[] args) {
//        main1(7);
//    }
//    public static int show(){
//
//        int a=0;
//        int b=0;
//        int c=0;
//        for (int i=4;i<=5;i++){
//            if (i-1==3){
//                 a=tree;
//            }
//            if (i-3==1){
//                 b=one;
//            }
//            if (i-2==2){
//                c=twe;
//            }
//        }
//        one=12;
//        return a + b - c * 2;
//    }
//
//    public static void main1(int n){
////        int n=3;
////        if (n<=3) {
////            if (n == 1) {
////                System.out.println(3);
////            } else if (n == 2) {
////                System.out.println(5);
////            } else if(n==3){
////                System.out.println(7);
////            }
////        }
//        int a[]=new int[9999];
//         a[0]=3;
//         a[1]=5;
//         a[2]=7;
//        for (int i=4; i<n; i++) {
//            a[i]=a[i-1]+a[i-3]-a[i-2]*2;
//        }
//        System.out.println(n+"======"+a[n-1]);
//    }
//}
//class erfen{
//    public static void main(String[] args) {
//        int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };
////        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13,14,15,16,17,18,19,20 };
////		int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
////		System.out.println("resIndex=" + resIndex);
//        List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1000);
//        System.out.println("resIndexList=" + resIndexList);
//    }
//    // 二分查找算法
//    /**
//     *
//     * @param arr
//     *            数组
//     * @param left
//     *            左边的索引
//     * @param right
//     *            右边的索引
//     * @param findVal
//     *            要查找的值
//     * @return 如果找到就返回下标，如果没有找到，就返回 -1
//     */
//    public static int binarySearch(int[] arr, int left, int right, int findVal) {
//        // 当 left > right 时，说明递归整个数组，但是没有找到
//        if (left > right) {
//            return -1;
//        }
//        int mid = (left + right) / 2;
//        int midVal = arr[mid];
//
//        if (findVal > midVal) { // 向 右递归
//            return binarySearch(arr, mid + 1, right, findVal);
//        } else if (findVal < midVal) { // 向左递归
//            return binarySearch(arr, left, mid - 1, findVal);
//        } else {
//
//            return mid;
//        }
//    }
//
//    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
//        System.out.println("hello~");
//        // 当 left > right 时，说明递归整个数组，但是没有找到
//        if (left > right) {
//            return new ArrayList<Integer>();
//        }
//        int mid = (left + right) / 2;
//        int midVal = arr[mid];
//
//        if (findVal > midVal) { // 向 右递归
//            return binarySearch2(arr, mid + 1, right, findVal);
//        } else if (findVal < midVal) { // 向左递归
//            return binarySearch2(arr, left, mid - 1, findVal);
//        } else {
////			 * 思路分析
////			 * 1. 在找到mid 索引值，不要马上返回
////			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
////			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
////			 * 4. 将Arraylist返回
//            List<Integer> resIndexlist = new ArrayList<Integer>();
//            //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//            int temp = mid - 1;
//            while(true) {
//                if (temp < 0 || arr[temp] != findVal) {//退出
//                    break;
//                }
//                //否则，就temp 放入到 resIndexlist
//                resIndexlist.add(temp);
//                temp -= 1; //temp左移
//            }
//            resIndexlist.add(mid);  //
//            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//            temp = mid + 1;
//            while(true) {
//                if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
//                    break;
//                }
//                //否则，就temp 放入到 resIndexlist
//                resIndexlist.add(temp);
//                temp += 1; //temp右移
//            }
//            return resIndexlist;
//        }
//    }
//}
////kmp匹配算法
//class kmp{
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        String str1 = "BBC ABCDAB ABCDABCDABDE";
//        String str2 = "ABCDABD";
//        //String str2 = "BBC";
//
//        int[] next = kmpNext("ABCDABD"); //[0, 1, 2, 0]
//        System.out.println("next=" + Arrays.toString(next));
//
//        int index = kmpSearch(str1, str2, next);
//        System.out.println("index=" + index); // 15了
//
//
//    }
//
//    //写出我们的kmp搜索算法
//    /**
//     *
//     * @param str1 源字符串
//     * @param str2 子串
//     * @param next 部分匹配表, 是子串对应的部分匹配表
//     * @return 如果是-1就是没有匹配到，否则返回第一个匹配的位置
//     */
//    public static int kmpSearch(String str1, String str2, int[] next) {
//
//        //遍历
//        for(int i = 0, j = 0; i < str1.length(); i++) {
//
//            //需要处理 str1.charAt(i) ！= str2.charAt(j), 去调整j的大小
//            //KMP算法核心点, 可以验证...
//            while( j > 0 && str1.charAt(i) != str2.charAt(j)) {
//                j = next[j-1];
//            }
//
//            if(str1.charAt(i) == str2.charAt(j)) {
//                j++;
//            }
//            if(j == str2.length()) {//找到了 // j = 3 i
//                return i - j + 1;
//            }
//        }
//        return  -1;
//    }
//
//    //获取到一个字符串(子串) 的部分匹配值表
//    public static  int[] kmpNext(String dest) {
//        //创建一个next 数组保存部分匹配值
//        int[] next = new int[dest.length()];
//        next[0] = 0; //如果字符串是长度为1 部分匹配值就是0
//        for(int i = 1, j = 0; i < dest.length(); i++) {
//            //当dest.charAt(i) != dest.charAt(j) ，我们需要从next[j-1]获取新的j
//            //直到我们发现 有  dest.charAt(i) == dest.charAt(j)成立才退出
//            //这时kmp算法的核心点
//            while(j > 0 && dest.charAt(i) != dest.charAt(j)) {
//                j = next[j-1];
//            }
//
//            //当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
//            if(dest.charAt(i) == dest.charAt(j)) {
//                j++;
//            }
//            next[i] = j;
//        }
//        return next;
//    }
//}
////两个数组合并一个新的数组并且排序
//class array{
//    public static void main(String[] args) {
//        Integer[] a={23,41,1,45};
//        Integer[] b={2,3,1,9};
//        Integer[] c= new Integer[a.length + b.length];
//        for (int i = 0; i < c.length; i++) {
//            if (i < a.length) {
//                c[i] = a[i];
//            }else{
//                c[i] = b[i-b.length];
//            }
//            System.out.println(c[i]);
//        }
//        System.out.println("==================");
//        int d=0;
//        for (int i = 0; i < c.length; i++) {
//            for (int k=0;k<c.length-1-i;k++){
//                if (c[k]>c[k+1]){
//                    d=c[k];
//                    c[k]=c[k+1];
//                    c[k+1]=d;
//                }
//            }
//        }
//        for (int i = 0; i < c.length; i++) {
//            System.out.println(c[i]);
//        }
//    }
//}
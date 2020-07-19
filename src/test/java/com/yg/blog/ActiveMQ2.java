//package com.yg.blog;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//
//import javax.jms.*;
//import java.io.IOException;
//
//public class ActiveMQ2 {
//    public static final String ActiveMq_URL="tcp://192.168.176.129:61616";
//    public static final String QUEUE_NAME="XIAOXI001";
//    public static void main(String[] args) throws JMSException {
//        //1.创建链接工厂
//        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory(ActiveMq_URL);
//        //2.通过链接工厂，获得链接并启动
//        Connection connection=activeMQConnectionFactory.createConnection();
//        //3.创建会话session 两个参数，第一个叫事务，第二个叫签收
//        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        //4.创建目的地（看是创建主题还是队列）
//        //Queue queue=session.createQueue(QUEUE_NAME);
//        //创建主题Topic 一对多
//        Topic topic=session.createTopic(QUEUE_NAME);
//        //5.创建消息的生产者
//        MessageProducer messageProducer=session.createProducer(topic);
//        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
//        connection.start();
//        //6.通过messageProducer生产三条消息发送给mq的队列里面
//        for (int i = 1; i <= 3; i++) {
//            //7.创建消息
//            TextMessage textMessage=session.createTextMessage("msg----"+i);
//            //8.通过messageProducer发送给mq
//            messageProducer.send(textMessage);
//        }
//        //9.关闭
//        messageProducer.close();
//        session.close();
//        connection.close();
//        System.out.println("发送完成");
//    }
//}
//class Xiaofei2{
//    public static final String ActiveMq_URL="tcp://192.168.176.129:61616";
//    public static final String QUEUE_NAME="XIAOXI001";
//    public static void main(String[] args) throws JMSException, IOException {
//        //1.创建链接工厂
//        ActiveMQConnectionFactory activeMQConnectionFactory=new ActiveMQConnectionFactory(ActiveMq_URL);
//        //2.通过链接工厂，获得链接并启动
//        Connection connection=activeMQConnectionFactory.createConnection();
//        //为连接设置一个客户ID；
//        connection.setClientID("z4");
//        //3.创建会话session 两个参数，第一个叫事务，第二个叫签收
//        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        //4.创建目的地（看是创建主题还是队列）
//        //Queue queue=session.createQueue(QUEUE_NAME);
//        //创建主题Topic 一对多
//        Topic topic=session.createTopic(QUEUE_NAME);
//        TopicSubscriber topicSubscriber=session.createDurableSubscriber(topic,"remak");
//        connection.start();
//        Message message=topicSubscriber.receive();
//        while (message!=null){
//            TextMessage textMessage= (TextMessage) message;
//            System.out.println("收到消息"+textMessage.getText());
//            message=topicSubscriber.receive();
//        }
//        session.close();
//        connection.close();
//        //1.一定要先运行一次消费者，等于向mq注册，类似关注
//        //2.然后在运行生产者发送消息，此时
//        //3.无论消费者是否在线，都会接收到，不在线的话，下次链接的时候就会把没有收到的消息收到
//    }
//}

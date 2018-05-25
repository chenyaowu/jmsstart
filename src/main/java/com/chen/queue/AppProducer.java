package com.chen.queue;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppProducer {

    //本地查看：http://localhost:8161/
    private static final String url = "tcp://localhost:61616";

    private static final String queueName = "queue-test";

    public static void main(String args[]) throws JMSException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        //2.创建Connection
        Connection connection = connectionFactory.createConnection();

        //3.启动连接
        connection.start();

        //4.创建会话
        Session session =  connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //5.创建目标
        Destination destination =  session.createQueue(queueName);

        //6.创建一个生产者
        MessageProducer messageProducer = session.createProducer(destination);

        //7.循环发送消息
        for (int i=0;i<100;i++){
            //创建消息
            TextMessage textMessage = session.createTextMessage("test"+i);
            messageProducer.send(textMessage);

            System.out.println("发送消息"+textMessage.getText());
        }
        connection.close();
    }
}

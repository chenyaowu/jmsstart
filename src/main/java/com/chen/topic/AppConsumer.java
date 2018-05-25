package com.chen.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class AppConsumer {
    //本地查看：http://localhost:8161/
    private static final String url = "tcp://localhost:61616";

    private static final String topicName = "topic-test";

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
        Destination destination =  session.createTopic(topicName);


        //6.创建一个消费者
        MessageConsumer messageConsumer = session.createConsumer(destination);

        //7.创建一个监听器
        messageConsumer.setMessageListener(message-> {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
        });

       // connection.close();
    }
}

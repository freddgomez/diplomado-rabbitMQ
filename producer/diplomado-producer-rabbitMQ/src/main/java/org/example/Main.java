package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Main {

        public static void main(String[] args) {

                ConnectionFactory connectionFactory = new ConnectionFactory();
                connectionFactory.setHost("localhost");

                try{
                        Connection connection = connectionFactory.newConnection();
                        Channel channel = connection.createChannel();

                        channel.queueDeclare("notification", false, false, false, null);
                        String message = "Te ha llegado un nuevo correo, entra para validar desde JAVA!!!";
                        channel.basicPublish("","notification", null, message.getBytes());

                        System.out.println(" [x] Enviado '" + message + "'");

                        channel.close();
                        connection.close();

                }catch(Exception e){
                        e.printStackTrace();
                }
        }
}
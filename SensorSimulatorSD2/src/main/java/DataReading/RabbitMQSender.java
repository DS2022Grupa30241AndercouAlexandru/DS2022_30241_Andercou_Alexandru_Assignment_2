package DataReading;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQSender {


    boolean connected=false;
    Channel channel;
    public RabbitMQSender(){

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try{ Connection connection = factory.newConnection();

                channel = connection.createChannel();
                channel.queueDeclare("Sensor sends", false, false, false, null);





        }catch(IOException i)
        {
          System.out.println("can't connect to RabbitMQServer");
            connected=false;

        }catch(TimeoutException t)
        {
            System.out.println("can't connect to RabbitMQServer");
            connected=false;
        }

    }

    public void  sendJson(String jsonString)  {
        try {
            channel.basicPublish("", "Sensor_sends", null, jsonString.getBytes());
        } catch (IOException e) {
            System.out.println("can't send data IO Exception");
        }
    }
}

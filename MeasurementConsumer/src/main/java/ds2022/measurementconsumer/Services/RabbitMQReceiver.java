package main.java.ds2022.measurementconsumer.Services;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import main.java.ds2022.measurementconsumer.models.MeasurementDto;
import main.java.ds2022.measurementconsumer.models.MeasurementDto2;
import org.springframework.beans.factory.annotation.Value;


public class RabbitMQReceiver {
	boolean connected=false;
	
	 Channel channel;
	 String message;
	 boolean received=false;
	 DeliverCallback deliverCallback;
	 MeasurementDto measurement;

	String queue;
   public RabbitMQReceiver() {    message=null;

	    	ConnectionFactory factory = new ConnectionFactory();

	  String host="localhost";
	   try {

		   Properties p=new Properties();
		   ClassLoader cl=Thread.currentThread().getContextClassLoader();
		   p.load( cl.getResourceAsStream("application.properties"));

		   Map<String, String> env = System.getenv();
		   String en1 = env.get("RQ_HOST");
		   String en2 = env.get("RQ_QUEUE");

		   if (en1 != null) {
			   System.out.println("R1:" + en1);
			   p.setProperty("RQ_HOST", en1);
		   }

		   if (en2 != null) {
			   System.out.println("R2:" + en2);
			   p.setProperty("RQ_QUEUE", en2);
		   }



		   queue=p.getProperty("RQ_QUEUE");
		    host=p.getProperty("RQ_HOST");

	   } catch (IOException e) {
		    System.out.println("aici e problema");
	   }
	   System.out.println("cum merge?");
	   factory.setHost(host);
	   factory.setPort(5672);
	   System.out.println("Queue:"+queue +" host:"+host +"port:"+5672);
	   try {
	    connected=true;
	    Connection connection = factory.newConnection();
	     channel = connection.createChannel();

	    channel.queueDeclare(queue, false, false, false, null);
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    
	        deliverCallback = (consumerTag, delivery) -> {
	        
		    message = new String(delivery.getBody(), "UTF-8");
		    
		    
		     System.out.println(" [x] Received '" + message + "'");
		     GsonBuilder gb=new GsonBuilder();
			 Gson gson=gb.create();
			 MeasurementDto2 measurementInput= gson.fromJson(message,MeasurementDto2.class);
			 //build MeasurementDto
			  
			 this.measurement=new  MeasurementDto();
			 this.measurement.setEnergyCon(measurementInput.getMeasurement_value());
		
			 this.measurement.setTimestamp(measurementInput.getTimestamp());
			 this.measurement.setDevice_id(measurementInput.getDevice_id());
				

			 
		    this.received=true;
		    
		    
		    
		    
		}; 
	    
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
   public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public boolean isReceived() {
	return received;
}
public void setReceived(boolean received) {
	this.received = received;
}
public MeasurementDto getMeasurement() {
	return measurement;
}
public void setMeasurement(MeasurementDto measurement) {
	this.measurement = measurement;
}
public void receive()
   {
	   try {
		 //  System.out.println("try to consume");
		channel.basicConsume(this.queue, true, deliverCallback, consumerTag -> { });
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   
}

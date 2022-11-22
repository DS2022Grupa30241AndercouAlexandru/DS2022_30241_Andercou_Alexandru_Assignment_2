package main.models.res;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import main.models.MeasurementDto;
import main.models.MeasurementDto2;

public class RabbitMQReceiver {
	boolean connected=false;
	
	 Channel channel;
	 String message;
	 boolean received=false;
	 DeliverCallback deliverCallback;
	 MeasurementDto measurement;
	 
   public RabbitMQReceiver()
   {    message=null;
	    try {
	    	ConnectionFactory factory = new ConnectionFactory();
	 	    factory.setHost("localhost");
	    
	    connected=true;
	    Connection connection = factory.newConnection();
	     channel = connection.createChannel();

	    channel.queueDeclare("Sensor_sends", false, false, false, null);
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
		channel.basicConsume("Sensor_sends", true, deliverCallback, consumerTag -> { });
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   
}

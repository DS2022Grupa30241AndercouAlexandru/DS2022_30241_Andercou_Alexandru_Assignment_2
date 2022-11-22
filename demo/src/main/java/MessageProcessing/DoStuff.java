package WebSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import main.models.res.MeasurementSenderToDatabase;
import main.models.res.RabbitMQReceiver;
@Service
public class DoStuff {

	@Autowired
	public DataProcessor dp;
	
	

	@Bean
	 void do_stuff()
	 {
    MeasurementSenderToDatabase mstdb=new  MeasurementSenderToDatabase();
    System.out.println("Pana aici e ok 1");
    RabbitMQReceiver rmr=new RabbitMQReceiver();
    System.out.println("Pana aici e ok  2");
    System.out.println("Pana aici e ok  3");
    Thread thread = new Thread(){
        public void run(){
        	   while(true)
        	    {
        	     while(!rmr.isReceived()){ //System.out.println("stuck");  
        	     rmr.receive()
        	     ;}
        	  
        	    rmr.setReceived(false);
        	   
        	   System.out.println("ajunge aici");
        	   dp.process(rmr.getMeasurement());
        	   mstdb.sendMeasurementToDatabase(rmr.getMeasurement());
        	   
        	    }
        }
      };
      thread.start();

 
	 }
	

}

package WebSocket;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import res.MeasurementSenderToDatabase;
import res.RabbitMQReceiver;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(Main.class);
		app.setDefaultProperties(Collections
		          .<String, Object>singletonMap("server.port", "8083"));
		        app.run(args);
		
		
		
		// TODO Auto-generated method stub
       
      // mstdb.getDeviceInfo(Long.valueOf(17));
       
       
       
       
       
	}
}


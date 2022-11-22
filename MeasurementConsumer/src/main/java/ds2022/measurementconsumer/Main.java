package main.java.ds2022.measurementconsumer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws IOException {
		Properties p = new Properties();
		Map<String, String> env = System.getenv();


		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		p.load(cl.getResourceAsStream("application.properties"));

		String en3 = env.get("R_PORT");

		if(en3!=null)
		{   System.out.println("R3:" + en3);
			p.setProperty("R_PORT",en3);
		}





		SpringApplication app = new SpringApplication(Main.class);
		app.setDefaultProperties(Collections
		          .<String, Object>singletonMap("server.port", p.getProperty("R_PORT")));
		        app.run(args);
		
		Date date=new Date();
		System.out.println("date"+date);
		// TODO Auto-generated method stub
       
      // mstdb.getDeviceInfo(Long.valueOf(17));
       
       
       
       
       
	}
}



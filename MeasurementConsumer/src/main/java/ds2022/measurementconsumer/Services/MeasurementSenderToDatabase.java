package main.java.ds2022.measurementconsumer.Services;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.Map;
import java.util.Properties;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.ds2022.measurementconsumer.models.DeviceDto;
import main.java.ds2022.measurementconsumer.models.MeasurementDto;

public class MeasurementSenderToDatabase {

	
	Long device_id;
	String basePath="http://localhost:8080";
	String devicePath="/device/";
	String measurementPath="/insertMeasure";
	
	

	
	public MeasurementSenderToDatabase()
	{
		Properties p=new Properties();

		ClassLoader cl=Thread.currentThread().getContextClassLoader();
		try {
			p.load( cl.getResourceAsStream("application.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		Map<String, String> env = System.getenv();
		String en1 = env.get("BACKEND_HOST");
		String en2 = env.get("BACKEND_PORT");

		if (en1 != null && en2!=null) {
			p.setProperty("BACKEND_HOST",en1);
			p.setProperty("BACKEND_PORT",en2);
			basePath="http://"+p.getProperty("BACKEND_HOST")+":"+p.getProperty("BACKEND_PORT");
		}else
			basePath="http://"+p.getProperty("BACKEND_HOST")+":"+p.getProperty("BACKEND_PORT");


	}
	
	public DeviceDto getDeviceInfo(Long d_id)
	{
		 System.out.println("try to get the device "+d_id);
		try {


		
			//WebSocket.Builder
			URL url = new URL(basePath+devicePath+d_id.toString());
			 HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			   connection.setDoInput(true);			    // instead of a GET, we're going to send using method="POST"
			   connection.setRequestMethod("GET");
			   
			   
			   int responseCode=connection.getResponseCode();
			   System.out.println("response code :"+ responseCode);
			 
			    
			     GsonBuilder gb=new GsonBuilder();
				 Gson gson=gb.create();
				 
			    	BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
				    String response=new String();
				    String line=in.readLine();
				    while(line!=null)
				    {
				    	
				    	 response=response+line;
				    	 line=in.readLine();
				    }

				 
				DeviceDto device=gson.fromJson(response, DeviceDto.class);

			return device;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void sendMeasurementToDatabase(MeasurementDto mdt)
	{     System.out.println("try to send to spring");
		try {

			URL url = new URL(basePath+ measurementPath);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			 connection.setDoOutput(true);
			 connection.setRequestMethod("POST");
			 connection.setRequestProperty("Content-Type", "application/json");
			 
			 GsonBuilder gb=new GsonBuilder();
			 Gson gson=gb.create();
			 String jtoSend= gson.toJson(mdt);
	         OutputStream os=connection.getOutputStream();
	         System.out.println("Message to send to spring:"+jtoSend);
			 os.write(jtoSend.getBytes());
		   	 os.flush();
		   	 os.close();
		   	 
		   	 int responseCode=connection.getResponseCode();
		     System.out.println("response code :"+ responseCode);
		} catch (MalformedURLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
}

package res;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.DeviceDto;
import models.MeasurementDto;
public class MeasurementSenderToDatabase {

	
	Long device_id;
	String basePath="http://localhost:8080";
	String devicePath="/device/";
	String measurementPath="/insertMeasure";
	
	

	
	public MeasurementSenderToDatabase()
	{ 

	}
	
	public DeviceDto getDeviceInfo(Long d_id)
	{
		
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
				 
				 
				 
				 
				 System.out.println("result:"+response);
				 
				DeviceDto device=gson.fromJson(response, DeviceDto.class);
				
				
				 System.out.println("device  as object:"+device.toString());
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
	{
		try {
			System.out.println("try to send to spring");
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

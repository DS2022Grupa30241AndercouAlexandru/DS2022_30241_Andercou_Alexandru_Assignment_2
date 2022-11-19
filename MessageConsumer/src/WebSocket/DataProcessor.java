package WebSocket;

import models.DeviceDto;
import models.MeasurementDto;
import models.Notification;
import res.MeasurementSenderToDatabase;

import java.net.*;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//also notifier
@Component
public class DataProcessor {

	@Autowired
	WebSocketServer ws;
	
	Float totalConsumption;
	Integer numberOfMeasurement;
	Long device_id;
	static final Integer NUMBER_READINGS=5;
	
;
	DeviceDto device;
	
	public DataProcessor()
	{
		totalConsumption=Float.valueOf(0);
		device_id=Long.valueOf(0);
		device=null;
		numberOfMeasurement=0;
		
	}
	
	
	
	
	public void process(MeasurementDto measurement)
	{
		System.out.println("process");
		if(measurement.getDevice_id()==device_id && device!=null)
		{
			numberOfMeasurement=numberOfMeasurement+1;
			if(numberOfMeasurement>NUMBER_READINGS)
			{
				totalConsumption=Float.valueOf(0);
				numberOfMeasurement=0;
				
			}
			
			totalConsumption=totalConsumption+measurement.getEnergyCon();
			System.out.println("totalConssumption:"+totalConsumption);
		
			//device.getMaximumHourlyEnergyConsumption())
			if(totalConsumption>1)
			{
				String message=new String("Device with id:"+ device.getId()+"and name:"+device.getName()+"consumption has passed the preseted maxEnergyCon");
		        
				Notification notif=new Notification();
				notif.setMessage(message);
				notif.setDevice_id(device.getId());
				notif.setUser_id(device.getOwner());
				
		       	try {
					ws.sendNotification(notif);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Notify client using WebSocket
			
			}
			
		
			
		}else
		{   numberOfMeasurement=1;
			device_id=measurement.getDevice_id();
			MeasurementSenderToDatabase mstdb=new  MeasurementSenderToDatabase();
			mstdb.getDeviceInfo(device_id);
			device=mstdb.getDeviceInfo(device_id);
			
			
		}
	}
	
	
	
}

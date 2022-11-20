package WebSocket;

import models.DeviceDto;
import models.MeasurementDto;
import models.Notification;
import models.State;
import res.MeasurementSenderToDatabase;

import java.net.*;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//also notifier
@Component
public class DataProcessor {

	@Autowired
	WebSocketServer ws;
	Map<Long, State> devicesStates;
	Float totalConsumption;
	Integer numberOfMeasurement;
	Long device_id;
	static final Integer NUMBER_READINGS = 5;

	;
	DeviceDto device;

	public DataProcessor() {
		totalConsumption = Float.valueOf(0);
		device_id = Long.valueOf(0);
		device = null;
		numberOfMeasurement = 0;
		devicesStates = new HashMap<Long, State>();
	}


	public void process(MeasurementDto measurement) {
		System.out.println("process");
		if (measurement.getDevice_id() == device_id && device != null) {


			devicesStates.get(device_id).setNumberMeasurements(devicesStates.get(device_id).getNumberMeasurements() + 1);
			if (devicesStates.get(device_id).getNumberMeasurements() > NUMBER_READINGS) {
				devicesStates.get(device_id).setNumberMeasurements(0);
				devicesStates.get(device_id).setTotalConsumption(0.0f);

			}

			devicesStates.get(device_id)
					.setTotalConsumption(devicesStates.get(device_id).getTotalConsumption()
							+ measurement.getEnergyCon());

			System.out.println("totalConssumption:" + devicesStates.get(device_id).getTotalConsumption());

			//device.getMaximumHourlyEnergyConsumption())
			if (devicesStates.get(device_id).getTotalConsumption() >
					devicesStates.get(device_id).getMaxConsumption()) {
				String message = new String("Device with id: " + device_id + " and name:"
						+ device.getName() + " consumption per hour is:"
						+ devicesStates.get(device_id).getTotalConsumption());

				Notification notif = new Notification();
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


		} else {
			numberOfMeasurement = 1;
			device_id = measurement.getDevice_id();
			MeasurementSenderToDatabase mstdb = new MeasurementSenderToDatabase();
			device = mstdb.getDeviceInfo(device_id);
			if (!devicesStates.containsKey(device_id)) {
				State s = new State();
				s.setMaxConsumption(device.getMaximumHourlyEnergyConsumption());
				devicesStates.put(device_id, s);
			}
		}


	}
}

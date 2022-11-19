package models;

public class MeasurementDto2 {
	  String timestamp;
	    Float measurement_value;
	    Long device_id;

	    public Float getMeasurement_value() {
	        return measurement_value;
	    }

	    public void setMeasurement_value(Float measurement_value) {
	        this.measurement_value = measurement_value;
	    }

	    public Long getDevice_id() {
	        return device_id;
	    }

	    public void setDevice_id(Long device_id) {
	        this.device_id = device_id;
	    }

	    public String getTimestamp() {
	        return timestamp;
	    }

	    public void setTimestamp(String timestamp) {
	        this.timestamp = timestamp;
	    }
}

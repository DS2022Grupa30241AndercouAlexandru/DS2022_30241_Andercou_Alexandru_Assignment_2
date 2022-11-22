package main.java.ds2022.measurementconsumer.models;

public class  MeasurementDto {
	 private Float energyCon;
     private String timestamp;
	 private Long device_id;
	 
	public Float getEnergyCon() {
		return energyCon;
	}
	public void setEnergyCon(Float energyCon) {
		this.energyCon = energyCon;
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

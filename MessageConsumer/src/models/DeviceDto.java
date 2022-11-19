package models;

import java.util.List;

public class DeviceDto {
	@Override
	public String toString() {
		return "DeviceDto [id=" + id + ", name=" + name + ", description=" + description + ", address=" + address
				+ ", maximumHourlyEnergyConsumption=" + maximumHourlyEnergyConsumption + ", owner=" + owner
				+ ", measurements=" + measurements + "]";
	}
	private Long id;
    private String name;
    private String description;
    private String address;
    private Float maximumHourlyEnergyConsumption;
    private Long owner;
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Float getMaximumHourlyEnergyConsumption() {
		return maximumHourlyEnergyConsumption;
	}
	public void setMaximumHourlyEnergyConsumption(Float maximumHourlyEnergyConsumption) {
		this.maximumHourlyEnergyConsumption = maximumHourlyEnergyConsumption;
	}
	public Long getOwner() {
		return owner;
	}
	public void setOwner(Long owner) {
		this.owner = owner;
	}
	public List<MeasurementDto> getMeasurements() {
		return measurements;
	}
	public void setMeasurements(List<MeasurementDto> measurements) {
		this.measurements = measurements;
	}
	private List<MeasurementDto> measurements;

}

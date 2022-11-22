package main.models;

public class Notification {
	
	String message;
	Long device_id;
	Long user_id;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getDevice_id() {
		return device_id;
	}
	public void setDevice_id(Long device_id) {
		this.device_id = device_id;
	}

}

package application;

import javafx.beans.property.SimpleStringProperty;

public class Activity {
	private SimpleStringProperty activityName;
	private SimpleStringProperty organizer;
	private SimpleStringProperty date;
	private SimpleStringProperty timeSlot; 
	private SimpleStringProperty location;
	private SimpleStringProperty status;

	public Activity(String activityName, String organizer, String date, String timeSlot, String location, String status) {
		this.activityName = new SimpleStringProperty(activityName);
		this.organizer = new SimpleStringProperty(organizer);
		this.date = new SimpleStringProperty(date);
		this.timeSlot = new SimpleStringProperty(timeSlot);
		this.location = new SimpleStringProperty(location);
		this.status = new SimpleStringProperty(status);
	}

	public String getActivityName() {
		return activityName.get();
	}

	public String getOrganizer() {
		return organizer.get();
	}

	public String getDate() {
		return date.get();
	}
	
    public String getTimeSlot(){ 
    	return timeSlot.get(); 
    } 
    
	public String getLocation() {
		return location.get();
	}

	public String getStatus() {
		return status.get();
	}
}

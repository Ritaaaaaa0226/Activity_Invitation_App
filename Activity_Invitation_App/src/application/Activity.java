package application;

import javafx.beans.property.SimpleStringProperty;

public class Activity {
	private SimpleStringProperty activityName;
	private SimpleStringProperty organizer;
	private SimpleStringProperty date;
	private SimpleStringProperty location;
	private SimpleStringProperty status;

	public Activity(String activityName, String organizer, String date, String location, String status) {
		this.activityName = new SimpleStringProperty(activityName);
		this.organizer = new SimpleStringProperty(organizer);
		this.date = new SimpleStringProperty(date);
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

	public String getLocation() {
		return location.get();
	}

	public String getStatus() {
		return status.get();
	}
}

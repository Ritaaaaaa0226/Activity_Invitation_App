package application;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvitationManager {
    private ArrayList<Invitation> invitationList;
    private ObservableList<Activity> activityList;

    public InvitationManager() {
        invitationList = new ArrayList<>();
        activityList = FXCollections.observableArrayList();
    }

    public void addInvitation(Invitation invitation) {
        if (invitation != null) {
            invitationList.add(invitation);
            Activity activity = new Activity(invitation.getSport(), "Organizer", invitation.getTimeSlot(), 
            		invitation.getLocation(), "Open", invitation.getPin() );
                activityList.add(activity);
        }
    }

    public ArrayList<Invitation> getInvitationList() {
        return invitationList;
    }

    public Invitation findByPin(String pin) {
        if (pin == null) {
            return null;
        }

        for (Invitation invitation : invitationList) {
            if (invitation.getPin().equalsIgnoreCase(pin)) {
                return invitation;
            }
        }
        return null;
    }

    public boolean removeByPin(String pin) {
        Invitation invitation = findByPin(pin);
        if (invitation != null) {
            invitationList.remove(invitation);
            
            Activity activity = findActivityByPin(pin);
            if (activity != null) {
                activityList.remove(activity);
            }
            
            return true;
        }
        return false;
    }

    public int getSize() {
        return invitationList.size();
    }

    public boolean isEmpty() {
        return invitationList.isEmpty();
    }
    
    public ObservableList<Activity> getActivityList() {
        return activityList;
    }
    
    public Activity findActivityByPin(String pin) {
        if (pin == null) return null;

        for (Activity activity : activityList) {
            if (activity.getPin().equalsIgnoreCase(pin)) {
                return activity;
            }
        }
        return null;
    }
    
    public boolean updateActivityByPin(String pin, String newSport, String newLocation) {
        Activity activity = findActivityByPin(pin);

        if (activity != null) {
            activity.setActivityName(newSport);
            activity.setLocation(newLocation);
            return true;
        }
        return false;
    }
    
    
}

package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ActivityListController implements Initializable {

    @FXML private TableView<Activity> activityTable;
    @FXML private TableColumn<Activity, String> colName;
    @FXML private TableColumn<Activity, String> colOrganizer;
    @FXML private TableColumn<Activity, String> colTimeSlot;
    @FXML private TableColumn<Activity, String> colDate;
    @FXML private TableColumn<Activity, String> colLocation;
    @FXML private TableColumn<Activity, String> colStatus;
    @FXML private Label messageLabel;
          private InvitationManager manager;
          
    // fake data
          public void setManager(InvitationManager manager) {
              this.manager = manager;
              loadInvitations(); // 注入後立刻載入資料
          }

          private void loadInvitations() {
              ObservableList<Activity> list = FXCollections.observableArrayList();

              for (Invitation inv : manager.getInvitationList()) {
                  list.add(new Activity(
                      inv.getSport(),
                      inv.getOrganizer(),              // 或之後可以加 organizer 欄位
                      inv.getDate(),
                      inv.getTimeSlot(),  // 用 getTimeSlot() 顯示時段
                      inv.getLocation(),
                      "Open"
                  ));
              }

              activityTable.setItems(list);
          }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // grab column
    	activityTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colName.setCellValueFactory(
            data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getActivityName()));
        colOrganizer.setCellValueFactory(
            data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getOrganizer()));
        colDate.setCellValueFactory(
        	    data -> new javafx.beans.property.SimpleStringProperty(
        	        data.getValue().getDate()));
        colTimeSlot.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(
                    data.getValue().getTimeSlot()));
        colLocation.setCellValueFactory(
            data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getLocation()));
        colStatus.setCellValueFactory(
            data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getStatus()));

        //activityTable.setItems(activityList);
    }

    @FXML
    private void handleJoin() {
        Activity selected = activityTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            messageLabel.setText("Please select one activity");
            return;
        }
        messageLabel.setText("Joined Successfully :  " + selected.getActivityName());
    }
    
    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Stage stage = (Stage) activityTable.getScene().getWindow();
            stage.setScene(new Scene(root, 700, 500));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}


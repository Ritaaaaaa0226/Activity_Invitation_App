package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ActivityListController implements Initializable {

    @FXML private TableView<Activity> activityTable;
    @FXML private TableColumn<Activity, String> colName;
    @FXML private TableColumn<Activity, String> colOrganizer;
    @FXML private TableColumn<Activity, String> colDate;
    @FXML private TableColumn<Activity, String> colLocation;
    @FXML private TableColumn<Activity, String> colStatus;
    @FXML private Label messageLabel;

    // fake data
    private ObservableList<Activity> activityList = FXCollections.observableArrayList(
        new Activity("Badminton Game",   "Alice", "2025-04-10", "Marinal", "Open"),
        new Activity("Basketball Game", "Bob", "2025-04-12", "Open Court", "Open"),
        new Activity("Baseball Game",   "Carol", "2025-04-15", "Open Court", "Open"),
        new Activity("Soccer Game",     "David", "2025-04-20", "National Park", "Open")
    );

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
        colLocation.setCellValueFactory(
            data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getLocation()));
        colStatus.setCellValueFactory(
            data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getStatus()));

        activityTable.setItems(activityList);
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
}


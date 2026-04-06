package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML private TableColumn<Activity, String> colDate;
    @FXML private TableColumn<Activity, String> colLocation;
    @FXML private TableColumn<Activity, String> colStatus;
    @FXML private Label messageLabel;

    // fake data
    private ObservableList<Activity> activityList = FXCollections.observableArrayList(
        new Activity("Badminton Game",   "Alice", "2025-04-10", "Marinal", "Open","AA001A"),
        new Activity("Basketball Game", "Bob", "2025-04-12", "Open Court", "Open","AA002A"),
        new Activity("Baseball Game",   "Carol", "2025-04-15", "Open Court", "Open","AA003A"),
        new Activity("Soccer Game",     "David", "2025-04-20", "National Park", "Open","AA004A")
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
    
 // ===== 新增 1：讓 MainController 可以拿到目前選取的 activity =====
    public Activity getSelectedActivity() {
        return activityTable.getSelectionModel().getSelectedItem();
    }

    // ===== 新增 2：讓外部可以新增 activity 到 table =====
    public void addActivity(Activity activity) {
        if (activity != null) {
            activityList.add(activity);
        }
    }

    // ===== 新增 3：讓外部可以取得整個 activityList =====
    public ObservableList<Activity> getActivityList() {
        return activityList;
    }

    // ===== 新增 4：手動刷新 table（保險用，雖然有時不一定需要） =====
    public void refreshTable() {
        activityTable.refresh();
    }
    
 // ===== 新增：Edit 按鈕邏輯 =====
    @FXML
    private void handleEditActivity() {

        Activity selected = activityTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Please select one activity first.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("PIN Verification");
        dialog.setHeaderText("Enter the activity PIN");
        dialog.setContentText("PIN:");

        dialog.showAndWait().ifPresent(inputPin -> {

            if (inputPin.equals(selected.getPin())) {

                try {
                    // ⭐⭐ 這段才是你缺的 ⭐⭐
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateInvitation.fxml"));
                    Parent root = loader.load();

                    CreateInvitationController controller = loader.getController();

                    // ⚠️ 這裡先不傳 manager（你目前 fake data 結構）
                    controller.setEditActivity(selected);

                    Stage stage = new Stage();
                    stage.setTitle("Edit Activity");
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                messageLabel.setText("Wrong PIN.");
            }
        });
    }
}


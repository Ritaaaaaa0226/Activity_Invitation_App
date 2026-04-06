package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CreateInvitationController {

    private InvitationManager manager;

    // ===== 新增 1：編輯模式判斷 =====
    private boolean editMode = false;

    // ===== 新增 2：記住目前正在編輯哪一筆 =====
    private Activity editingActivity = null;

    @FXML private TextField startHField;
    @FXML private TextField startMField;
    @FXML private TextField endHField;
    @FXML private TextField endMField;

    @FXML private TextField countField;
    @FXML private TextField sportField;

    @FXML private ComboBox<String> locationBox;
    @FXML private ComboBox<String> genderBox;

    @FXML private Button saveButton;

    @FXML
    public void initialize() {
        locationBox.getItems().addAll(
            "Marino Recreation Center", "Cabot Center", "Carter Playground", "SquashBusters", "Roxbury YMCA");

        genderBox.getItems().addAll(
            "Male", "Female", "All Gender");
    }

    public void setManager(InvitationManager manager) {
        this.manager = manager;
    }

    // ===== 新增 3：進入 edit mode 的方法 =====
    public void setEditActivity(Activity activity) {
        this.editMode = true;
        this.editingActivity = activity;

        // ===== 新增 4：把舊資料塞回 UI =====
        sportField.setText(activity.getActivityName());
        locationBox.setValue(activity.getLocation());
    }

    @FXML
    private void handleSave() {
        try {
            int startH = Integer.parseInt(startHField.getText());
            int startM = Integer.parseInt(startMField.getText());
            int endH = Integer.parseInt(endHField.getText());
            int endM = Integer.parseInt(endMField.getText());

            int count = Integer.parseInt(countField.getText());
            String sport = sportField.getText();
            String location = locationBox.getValue();
            String gender = genderBox.getValue();

            // ===== 新增 5：分成「新增」與「編輯」兩種模式 =====
            if (!editMode) {
                // ===== 原本邏輯（新增）=====
                Invitation invitation = new Invitation(startH, startM, endH, endM, count, sport, location, gender);
                manager.addInvitation(invitation);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Reservation Successful!");
                alert.setContentText("Your PIN: " + invitation.getPin());
                alert.showAndWait();

            } else {
                // ===== 新增 6：編輯模式（不新增，只修改）=====
                editingActivity.setActivityName(sport);
                editingActivity.setLocation(location);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Update Successful!");
                alert.setContentText("Activity updated successfully.");
                alert.showAndWait();
            }

            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            showError("Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        } catch (Exception e) {
            showError("Unexpected error occurred.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid Input");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// ===== 新增 1：import Alert / TextInputDialog =====
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

public class MainController {

    @FXML
    private Button createBtn;

    @FXML
    private Button joinBtn;

    // ===== 新增 2：建立 manager（共用資料）=====
    private InvitationManager manager = new InvitationManager();

    // ===== 新增 3：存 ActivityListController（之後要拿 selected）=====
    private ActivityListController activityListController;

    @FXML
    private void handleCreateActivity(ActionEvent event) throws Exception {

        // ===== 新增 4：打開 CreateInvitation.fxml =====
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateInvitation.fxml"));
        Parent root = loader.load();

        CreateInvitationController controller = loader.getController();

        // ===== 新增 5：傳 manager 進去 =====
        controller.setManager(manager);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Create Activity");
        stage.show();
    }

    @FXML
    private void handleJoinActivity(ActionEvent event) throws Exception {

        // ===== 修改：用 loader 取 controller（原本是直接 load）=====
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ActivityList.fxml"));
        Parent root = loader.load();

        // ===== 新增 6：拿到 ActivityListController =====
        activityListController = loader.getController();

        Stage stage = (Stage) joinBtn.getScene().getWindow();
        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }

    // ===== 新增 7：Edit Button 對應的方法 =====
    @FXML
    private void handleEditActivity(ActionEvent event) {

        // ===== 新增 8：先確認有沒有進過 ActivityList 頁面 =====
        if (activityListController == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No Activity Page");
            alert.setContentText("Please open Activity List first.");
            alert.showAndWait();
            return;
        }

        // ===== 新增 9：取得選到的 activity =====
        Activity selected = activityListController.getSelectedActivity();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No Selection");
            alert.setContentText("Please select an activity first.");
            alert.showAndWait();
            return;
        }

        // ===== 新增 10：跳出輸入 PIN 視窗 =====
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("PIN Verification");
        dialog.setHeaderText("Enter the activity PIN");
        dialog.setContentText("PIN:");

        dialog.showAndWait().ifPresent(inputPin -> {

            // ===== 新增 11：驗證 PIN =====
            if (inputPin.equals(selected.getPin())) {

                try {
                    // ===== 新增 12：打開 CreateInvitationController（edit mode）=====
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateInvitation.fxml"));
                    Parent root = loader.load();

                    CreateInvitationController controller = loader.getController();
                    
                    controller.setManager(manager);
                    controller.setEditActivity(selected);

                    Stage stage = new Stage();
                    stage.setTitle("Edit Activity");
                    stage.setScene(new Scene(root));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                // ===== 新增 13：PIN 錯誤 =====
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Wrong PIN");
                alert.setContentText("Incorrect PIN.");
                alert.showAndWait();
            }
        });
    }
}
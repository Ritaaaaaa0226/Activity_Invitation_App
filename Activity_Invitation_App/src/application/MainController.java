package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class MainController {

    // ===== 共用同一個 manager =====
    private InvitationManager manager = new InvitationManager();

    // ===== 記住 ActivityListController，之後 edit 會用到 =====
    private ActivityListController activityListController;

    @FXML
    private Button createBtn;

    @FXML
    private Button joinBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Label infoLabel;

    @FXML
    private Polygon carterPlayground;

    @FXML
    private Polygon marinoRecreationCenter;

    @FXML
    private Polygon cabotCenter;

    @FXML
    private Polygon fenwayCourt;

    @FXML
    private Polygon titusSparrowPark;

    @FXML
    public void initialize() {

        // ===== hover layer =====
        if (carterPlayground != null) carterPlayground.toFront();
        if (marinoRecreationCenter != null) marinoRecreationCenter.toFront();
        if (cabotCenter != null) cabotCenter.toFront();
        if (fenwayCourt != null) fenwayCourt.toFront();
        if (titusSparrowPark != null) titusSparrowPark.toFront();
        if (infoLabel != null) infoLabel.toFront();

        if (infoLabel != null) {
            infoLabel.setText("Move your mouse over a court to see information.");
        }

        if (carterPlayground != null) {
            setupHover(carterPlayground, "Carter Playground\n★★★★\n06:00 ~ 00:00\nModerate 🟡");
        }

        if (marinoRecreationCenter != null) {
            setupHover(marinoRecreationCenter, "Marino Recreation Center\n★★★\n05:30 ~ 00:00\nCrowded 🔴");
        }

        if (cabotCenter != null) {
            setupHover(cabotCenter, "Cabot Center\n★★\n05:30 ~ 22:15\nModerate 🟡");
        }

        if (fenwayCourt != null) {
            setupHover(fenwayCourt, "Fenway Court\n★★★★\n00:00 ~ 00:00\nModerate 🟡");
        }

        if (titusSparrowPark != null) {
            setupHover(titusSparrowPark, "Titus Sparrow Park\n★★★★\n06:00 ~ 23:30\nLow 🟢");
        }
    }

    // ===== hover helper =====
    private void setupHover(Polygon area, String message) {
        area.setOnMouseEntered(e -> {
            area.setOpacity(0.35);
            if (infoLabel != null) {
                infoLabel.setText(message);
            }
        });

        area.setOnMouseExited(e -> {
            area.setOpacity(0.0);
            if (infoLabel != null) {
                infoLabel.setText("Move your mouse over a court to see information.");
            }
        });
    }

    @FXML
    private void handleCreateActivity(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateInvitation.fxml"));
            Parent root = loader.load();

            CreateInvitationController controller = loader.getController();
            controller.setManager(manager);   // 同一份 manager

            Stage stage = new Stage();
            stage.setTitle("Create Activity");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showError("Failed to open Create Activity window.");
        }
    }

    @FXML
    private void handleJoinActivity(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ActivityList.fxml"));
            Parent root = loader.load();

            activityListController = loader.getController();
            activityListController.setManager(manager);   // 同一份 manager

            Stage stage = (Stage) joinBtn.getScene().getWindow();
            stage.setScene(new Scene(root, 700, 500));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showError("Failed to open Activity List.");
        }
    }

    @FXML
    private void handleEditActivity(ActionEvent event) {

        // ===== 先檢查 ActivityListController 是否已經建立 =====
        if (activityListController == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Activity List Not Opened");
            alert.setContentText("Please click Join Activity first, then select an activity to edit.");
            alert.showAndWait();
            return;
        }

        Activity selected = activityListController.getSelectedActivity();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No Selection");
            alert.setContentText("Please select an activity first.");
            alert.showAndWait();
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("PIN Verification");
        dialog.setHeaderText("Enter the activity PIN");
        dialog.setContentText("PIN:");

        dialog.showAndWait().ifPresent(inputPin -> {
            if (inputPin.equals(selected.getPin())) {
                try {
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
                    showError("Failed to open Edit Activity window.");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Wrong PIN");
                alert.setContentText("Incorrect PIN.");
                alert.showAndWait();
            }
        });
    }

    public void setManager(InvitationManager manager) {
		// TODO Auto-generated method stub
		 this.manager = manager;
	}
    
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Operation Failed");
        alert.setContentText(message);
        alert.showAndWait();
    }

	
}
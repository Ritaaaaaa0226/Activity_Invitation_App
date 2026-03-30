package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button createBtn;

    @FXML
    private Button joinBtn;

    @FXML
    private void handleCreateActivity(ActionEvent event) {
        System.out.println("Create Activity");
    }

    @FXML
    private void handleJoinActivity(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ActivityList.fxml"));
        Stage stage = (Stage) joinBtn.getScene().getWindow();
        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }

}

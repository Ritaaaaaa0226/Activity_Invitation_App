package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
    
	private InvitationManager manager = new InvitationManager(); // ← 加
    
	@FXML
    private Button createBtn;

    @FXML
    private Button joinBtn;
    
    @FXML
    private Button editBtn;
    
    @FXML
    private void handleCreateActivity(ActionEvent event) {
    	  try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateInvitation.fxml"));
              Parent root = loader.load();

              CreateInvitationController controller = loader.getController();
              controller.setManager(manager); // ← 共用同一個 manager

              Stage stage = new Stage();
              stage.setTitle("Create Invitation");
              stage.setScene(new Scene(root));
              stage.show();

          } catch (Exception e) {
              e.printStackTrace();
          }
    }
    
    @FXML
    private void handleEditActivity(ActionEvent event) {
        // 你的邏輯寫這裡
        System.out.println("Edit Activity clicked");
    }

    @FXML
    private void handleJoinActivity(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ActivityList.fxml"));
        Parent root = loader.load();

        // 注入同一個 manager
        ActivityListController controller = loader.getController();
        controller.setManager(manager);

        Stage stage = (Stage) joinBtn.getScene().getWindow();
        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }



}

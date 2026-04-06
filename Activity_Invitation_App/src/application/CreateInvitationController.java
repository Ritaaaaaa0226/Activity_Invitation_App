package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CreateInvitationController {

    private InvitationManager manager;

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

    //save
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

            //invitation+pin created
            Invitation invitation = new Invitation(startH, startM, endH, endM, count, sport, location, gender);
   
            manager.addInvitation(invitation);

            //success&pin
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Reservation Successful!");
            alert.setContentText("Your PIN: " + invitation.getPin());
            alert.showAndWait();

            //close popup
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

    //error
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid Input");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

package asuEffortLogger1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class FAQ {

    @FXML
    private Hyperlink returnToLoginLink;

    @FXML
    private void handleReturnToLogin() {
        try {
            // Get the current stage using the Return to Login link component
            Stage stage = (Stage) returnToLoginLink.getScene().getWindow();

            // Load the login screen (login.fxml) into a new Scene
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));

            // Set the current stage's scene to the new scene (display the login screen)
            stage.setScene(scene);
        } catch (Exception e) {
            // Print any exceptions to the console (for debugging purposes)
            e.printStackTrace();
        }
    }

}

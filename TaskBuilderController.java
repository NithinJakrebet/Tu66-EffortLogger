import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;


public class TaskBuilderController {

    private MainApp MainApp; // Reference to the main application

    public void setMainApp(MainApp mainApp) {
        this.MainApp = mainApp;
    }

    @FXML
    private TextField currentUsername;

    @FXML
    private TextField newUsername;

    @FXML
    private TextField currentPassword;

    @FXML
    private TextField newPassword;

    @FXML
    private CheckBox notifOn;

    @FXML
    private CheckBox notifOff;

    @FXML
    private Button applyAndCloseButton;

    @FXML
    private Button resetToDefaultButton;

    @FXML
    private Button helpButton;

    @FXML
    private void initialize() {
        // Initialize your controller here if needed
    }

    @FXML
    private void handleApplyAndCloseAction(ActionEvent event) {
        // Implement the logic to apply settings and close the window
        String username = newUsername.getText();
        String password = newPassword.getText();
        boolean notificationsEnabled = notifOn.isSelected();

        // Here you would call your backend service to update the username, password, and notification settings
        // For example: settingsService.updateSettings(username, password, notificationsEnabled);

        // Then close the window or show a confirmation message
    }

    @FXML
    private void handleResetToDefaultAction(ActionEvent event) {
        // Implement the logic to reset settings to their defaults
        // For example: settingsService.resetSettingsToDefault();

        // Update the UI to reflect the default settings
        currentUsername.setText("Default Username");
        currentPassword.setText("Default Password");
        newUsername.setText("Default Username");
        newPassword.setText("Default Password");
        notifOn.setSelected(true);
        notifOff.setSelected(false);
    }

    @FXML
    private void handleHelpAction(ActionEvent event) {
        // Implement the logic to show help information
        // For example, open a help dialog or a help web page
    }

    // ... existing controller code ...

    @FXML
    void switchToTask(ActionEvent event) {
        try {
            // Load the Task FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Task.fxml"));
            Parent taskRoot = loader.load();

            // Get the current stage using the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene to Task
            Scene scene = new Scene(taskRoot);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

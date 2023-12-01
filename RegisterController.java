package asuEffortLogger1;

import java.util.Base64;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.Scene;
import javafx.stage.Stage;

// RegisterController class handles the logic behind the registration screen of the application
public class RegisterController {

    // Link the FXML components to Java variables
    @FXML
    private TextField registerUsernameField;     // Text field for the user's desired username
    @FXML
    private PasswordField registerPasswordField; // Password field for the user's desired password
    @FXML
    private PasswordField confirmPasswordField;  // Password field to confirm the user's desired password
    @FXML
    private TextField emailField;                // Text field for the user's email
    @FXML
    private Label registerErrorMsg;              // Label to display any registration error messages
    @FXML
    private Hyperlink loginLink;                 // Hyperlink to redirect user to the login page
    
    private CredentialStorage credentialStorage = new CredentialStorage();

    // Method called when the user tries to register
    @FXML
    private void handleRegisterUser() {
    	  String username = registerUsernameField.getText();
          String password = registerPasswordField.getText();
          String confirmPassword = confirmPasswordField.getText();

          if (!password.equals(confirmPassword)) {
              registerErrorMsg.setText("Passwords do not match!");
              return;
          }

          // Additional validation for username and password can be added here

          byte[] salt = credentialStorage.generateSalt();
          String hashedPassword = credentialStorage.hashPassword(password, salt);
          String storedCredentials = Base64.getEncoder().encodeToString(salt) + "$" + hashedPassword;

          credentialStorage.storeCredentials(username, storedCredentials);
          registerErrorMsg.setText("Registered successfully!");

    }

    // Method to handle redirection to the login page
    @FXML
    private void handleGoToLogin() {
        try {
            // Retrieve the current stage using the login link component
            Stage stage = (Stage) loginLink.getScene().getWindow();
            
            // Load the login screen and set it as the current scene
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("login.fxml")));
            stage.setScene(scene);
        } catch (Exception e) {
            // Print any exceptions that occur during the redirection process
            e.printStackTrace();
        }
    }
}

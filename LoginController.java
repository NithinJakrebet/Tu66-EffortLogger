// Author: Vishal Vincentsundar
// Package that holds the current class
package asuEffortLogger1;

// Importing necessary JavaFX components and classes
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


// Class that acts as the controller for the login screen
public class LoginController {

    // FXML annotations are used to link the Java code to elements defined in the FXML file
    
    // TextField for the user to input their username
    @FXML
    private TextField usernameField;
    
    // PasswordField for the user to input their password (hides the characters typed)
    @FXML
    private PasswordField passwordField;
    
    // Label to display any error messages to the user
    @FXML
    private Label errorMsg;
    
    // Hyperlink that leads to the registration screen
    @FXML
    private Hyperlink registerLink;
    
    // Hyperlink that leads to the FAQ screen
    @FXML
    private Hyperlink faqLink;
    
    private CredentialStorage credentialStorage;

    public LoginController() {
        // Initialize CredentialStorage
        this.credentialStorage = new CredentialStorage();
    }
    
    private boolean validateLogin(String username, String password) {
    	String storedCredentials = credentialStorage.retrieveCredentials(username);
        if (storedCredentials == null) {
            return false; // Username not found
        }
        String[] parts = storedCredentials.split("\\$");
        if (parts.length != 2) {
            return false; // Incorrect format
        }
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        String computedHash = credentialStorage.hashPassword(password, salt);
        return computedHash.equals(storedCredentials);

    }

    // In a real application, use a secure hashing algorithm with salt
    private String hashAndSaltPassword(String password) {
        // Implement your secure password hashing logic here
        // For demonstration, return the password as-is (not secure, for illustration purposes only)
        return credentialStorage.hashPassword(password, null);
    }
    
 // Helper method to verify the entered password against the stored encrypted password
    private boolean verifyPassword(String enteredPassword, String storedEncryptedPassword) {
        if (storedEncryptedPassword == null) {
            return false; // User not found
        }
        String[] parts = storedEncryptedPassword.split("\\$");
        if (parts.length != 2) {
            System.out.println("Invalid stored password format");
            return false; // Invalid stored password format
        }

        byte[] storedSalt = Base64.getDecoder().decode(parts[0]);
        byte[] storedHash = Base64.getDecoder().decode(parts[1]);

        System.out.println("Entered Password: " + enteredPassword);
        System.out.println("Stored Salt: " + Base64.getEncoder().encodeToString(storedSalt));
        System.out.println("Stored Hash: " + Base64.getEncoder().encodeToString(storedHash));

        KeySpec spec = new PBEKeySpec(enteredPassword.toCharArray(), storedSalt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] enteredHash = factory.generateSecret(spec).getEncoded();

            // Compare the entered hash with the stored hash
            return MessageDigest.isEqual(enteredHash, storedHash);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
 // Method triggered when the login button is clicked
    @FXML
    private void handleLogin() {
//       
    	 // Retrieve text entered by the user in the username and password fields
        String username = usernameField.getText();
        String enteredPassword = passwordField.getText();
        System.out.println("Attempting to log in with username: " + username);

        // Retrieve the stored encrypted password for the given username
        String storedEncryptedPassword = credentialStorage.retrieveCredentials(username);
        if (username.isEmpty() || enteredPassword.isEmpty()) {
            errorMsg.setText("Username and password are required!");
            return;
        }
        // Validate the credentials
        boolean isValid = validateLogin(username, enteredPassword);
        if (isValid) {
            // Handle successful login
            // Redirect to the main application screen or other appropriate action
        } else {
            // Handle failed login
            errorMsg.setText("Invalid username or password.");
        }
    }

    // Method triggered when the "Register new user" hyperlink is clicked
    @FXML
    private void handleRegister() {
//        try {
//            // Get the current stage (window) from the hyperlink
//            Stage stage = (Stage) registerLink.getScene().getWindow();
//            // Load the registration screen (register.fxml) into a new Scene
//            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("register.fxml")));
//            // Set the current stage's scene to the new scene (display the registration screen)
//            stage.setScene(scene);
//        } catch (Exception e) {
//            // Print any exceptions to the console (for debugging purposes)
//            e.printStackTrace();
//        }
    	 try {
    	        // Get the current stage (window) from the hyperlink
    	        Stage stage = (Stage) registerLink.getScene().getWindow();
    	        
    	        // Retrieve text entered by the user in the username and password fields
    	        String username = usernameField.getText();
    	        String enteredPassword = passwordField.getText();
    	        
    	        // Store or retrieve credentials for the new user
    	        validateLogin(username, enteredPassword);

    	        // Load the registration screen (register.fxml) into a new Scene
    	        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("register.fxml")));
    	        
    	        // Set the current stage's scene to the new scene (display the registration screen)
    	        stage.setScene(scene);
    	    } catch (Exception e) {
    	        // Print any exceptions to the console (for debugging purposes)
    	        e.printStackTrace();
    	    }
    }

 // Method triggered when the "FAQ" hyperlink is clicked
    @FXML
    private void handleFAQ() {
        try {
            // Get the current stage using the FAQ link component
            Stage stage = (Stage) faqLink.getScene().getWindow();

            // Load the FAQ screen (faq.fxml) into a new Scene
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("faq.fxml")));

            // Set the current stage's scene to the new scene (display the FAQ screen)
            stage.setScene(scene);
        } catch (Exception e) {
            // Print any exceptions to the console (for debugging purposes)
            e.printStackTrace();
        }
    }
    

    // Method triggered when the "Forgot your credentials?" hyperlink is clicked
    @FXML
    private void handleForgotCredentials() {
        // For demonstration purposes, a simple message is displayed.
        // In a real application, this would involve sending a reset link to the user's registered email.
        errorMsg.setText("Reset link sent to registered email!");
    }
}

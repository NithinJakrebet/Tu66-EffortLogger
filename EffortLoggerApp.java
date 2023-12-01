// Author: Vishal Vincentsundar
package asuEffortLogger1;

// Importing the necessary JavaFX classes for the application
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// The EffortLoggerApp class extends the JavaFX Application class, which is the entry point for JavaFX applications
public class EffortLoggerApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the login.fxml file to get the layout for the initial screen.
        // The FXMLLoader.load() method returns a Parent object which represents the root of the view hierarchy.
        Parent root = FXMLLoader.load(getClass().getResource("/asuEffortLogger1/login.fxml"));
        
        // Set the title of the primary window (stage)
        primaryStage.setTitle("EffortLogger");
        
        // Set the scene of the primary stage. A scene represents the content of the window, including all JavaFX controls.
        primaryStage.setScene(new Scene(root));
        
        // Display the primary stage to the user
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

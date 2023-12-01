import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the initial FXML file (for example, Task.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Task.fxml"));
            Parent root = loader.load();

            // Get the controller of the Task.fxml
            TaskController taskController = loader.getController();
            taskController.setMainApp(this); // If you have a method to pass main app reference

            // Set the primary stage
            primaryStage.setTitle("JavaFX Application");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Navigation method to switch to TaskBuilder scene
    public void switchToTaskBuilder(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskBuilder.fxml"));
        Parent taskBuilderRoot = loader.load();

        TaskBuilderController taskBuilderController = loader.getController();
        taskBuilderController.setMainApp(this); // Now this method is defined in the TaskBuilderController

        Scene scene = new Scene(taskBuilderRoot);
        stage.setScene(scene);
        stage.show();
    }


    // Navigation method to switch to Task scene
    public void switchToTask(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Task.fxml"));
        Parent taskRoot = loader.load();

        TaskController taskController = loader.getController();
        taskController.setMainApp(this);

        Scene scene = new Scene(taskRoot);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

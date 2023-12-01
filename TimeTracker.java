import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TimeTracker {
    @FXML
    private Button StartButton;

    @FXML
    private Button StopButton;

    @FXML
    private TextField Hours;

    @FXML
    private TextField Minutes;

    @FXML
    private ComboBox<String> DropdownButton;

    @FXML
    private Button SaveButton;

    private boolean timerRunning;
    private long startTime;

    public TimeTracker() {
        timerRunning = false;
        startTime = 0;
    }

    @FXML
    void startTimer(ActionEvent event) {
        if (!timerRunning) {
            timerRunning = true;
            startTime = System.currentTimeMillis();
            // You can update the UI or perform any necessary actions here.
        }
    }

    @FXML
    void stopTimer(ActionEvent event) {
        if (timerRunning) {
            timerRunning = false;
            long currentTime = System.currentTimeMillis();
            long elapsedTime = (currentTime - startTime) / 1000; 
            int hours = (int) (elapsedTime / 3600);
            int minutes = (int) ((elapsedTime % 3600) / 60);

            Hours.setText(Integer.toString(hours));
            Minutes.setText(Integer.toString(minutes));
            // You can update the UI or perform any necessary actions here.
        }
    }

    @FXML
    void saveInfo(ActionEvent event) {
        // This is where you can save the information to your data structure or file.
        // For example, you can use the values from hoursTextField, minutesTextField, and dropdownButton
        // to save the tracked time and selected task.
    }
}

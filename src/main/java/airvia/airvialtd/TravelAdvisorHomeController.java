package airvia.airvialtd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TravelAdvisorHomeController {
    private Stage stage;
    private Scene scene;

    @FXML
    private Button backButton;

    /**
     * This method is called when the "Back" button is clicked and returns the user to the main TravelAdvisor screen.
     *
     * @param event the action event triggered by clicking the "Back" button
     * @throws IOException if there is an error loading the FXML file for the main TravelAdvisor screen
     */
    @FXML
    void backButtonClick(ActionEvent event) throws IOException {
        // Load the FXML file for the main TravelAdvisor screen
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisor.fxml"));
        // Get the stage and scene from the event source
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        // Create a new scene using the FXML file for the main TravelAdvisor screen
        scene = new Scene(root);
        // Set the scene on the stage and show it
        stage.setScene(scene);
        stage.show();
    }


}

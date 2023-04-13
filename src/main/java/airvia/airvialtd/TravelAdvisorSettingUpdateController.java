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

public class TravelAdvisorSettingUpdateController {
    private Stage stage;
    private Scene scene;

    @FXML
    private Button backButton;

    @FXML
    private Button generalButton;

    @FXML
    private Button securityButton;

    @FXML
    private Button updateButton;

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

    /**
     * This method is called when the "General" button is clicked and opens the general settings screen.
     *
     * @param event the action event triggered by clicking the "General" button
     * @throws IOException if there is an error loading the FXML file for the general settings screen
     */
    @FXML
    void generalButtonClick(ActionEvent event) throws IOException{
        // Load the FXML file for the general settings screen
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorSettingGeneral.fxml"));
        // Get the stage and scene from the event source
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        // Create a new scene using the FXML file for the general settings screen
        scene = new Scene(root);
        // Set the scene on the stage and show it
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is called when the "Security" button is clicked and opens the security settings screen.
     *
     * @param event the action event triggered by clicking the "Security" button
     * @throws IOException if there is an error loading the FXML file for the security settings screen
     */
    @FXML
    void securityButtonClick(ActionEvent event) throws IOException{
        // Load the FXML file for the security settings screen
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorSettingSecurity.fxml"));
        // Get the stage and scene from the event source
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        // Create a new scene using the FXML file for the security settings screen
        scene = new Scene(root);
        // Set the scene on the stage and show it
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is called when the "Update" button is clicked and opens the update settings screen.
     *
     * @param event the action event triggered by clicking the "Update" button
     * @throws IOException if there is an error loading the FXML file for the update settings screen
     */
    @FXML
    void updateButtonClick(ActionEvent event) throws IOException {
        // Load the FXML file for the update settings screen
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorSettingUpdate.fxml"));
        // Get the stage and scene from the event source
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        // Create a new scene using the FXML file for the update settings screen
        scene = new Scene(root);
        // Set the scene on the stage and show it
        stage.setScene(scene);
        stage.show();
    }


}

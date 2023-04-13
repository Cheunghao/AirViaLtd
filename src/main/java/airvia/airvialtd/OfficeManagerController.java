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

/**
 * The controller class for the Office Manager UI.
 */
public class OfficeManagerController {
    // The primary window of the application.
    private Stage stage;

    // The scene containing the UI components for the application.
    private Scene scene;


    /**
     * The "Blanks" button on the UI.
     */
    @FXML
    private Button blanksButton;

    /**
     * The "Settings" button on the UI.
     */
    @FXML
    private Button settingButton;

    /**
     * The "Home" button on the UI.
     */
    @FXML
    private Button homeButton;

    /**
     * The "Reports" button on the UI.
     */
    @FXML
    private Button reportButton;

    /**
     * The "Tickets" button on the UI.
     */
    @FXML
    private Button ticketButton;

    /**
     * Event handler method for the "Settings" button on the Office Manager UI.
     *
     * Loads the OfficeManagerSettingGeneral.fxml file and displays it in the current window.
     *
     * @param event The ActionEvent that triggered this method.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    void settingButtonClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManagerSettingGeneral.fxml")); // Load the OfficeManagerSettingGeneral.fxml file and set its root as the parent node
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();// Get the Stage object associated with the event source i.e. button click
        scene = new Scene(root); // Create a new Scene object and set it to the loaded FXML root
        stage.setScene(scene);// Set the stage's scene to the newly created scene
        stage.show();// Display the stage

    }
    /**
     * Event handler method for the "Home" button on the Office Manager UI.
     *
     * Loads the OfficeManagerHome.fxml file and displays it in the current window.
     *
     * @param event The ActionEvent that triggered this method.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    void homeButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManagerHome.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Event handler method for the "Blanks" button on the Office Manager UI.
     *
     * Loads the OfficeManagerBlanks.fxml file and displays it in the current window.
     *
     * @param event The ActionEvent that triggered this method.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    void blankButtonClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManagerBlanks.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Event handler method for the "Reports" button on the Office Manager UI.
     *
     * Loads the OfficeManagerReports.fxml file and displays it in the current window.
     *
     * @param event The ActionEvent that triggered this method.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    void reportButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManagerReports.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Event handler method for the "Tickets" button on the Office Manager UI.
     *
     * Loads the OfficeManagerTickets.fxml file and displays it in the current window.
     *
     * @param event The ActionEvent that triggered this method.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    void ticketButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManagerTickets.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

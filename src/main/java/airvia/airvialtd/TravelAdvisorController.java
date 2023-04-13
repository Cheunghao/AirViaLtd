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
 * This class represents the controller for the Travel Advisor view, which allows the travel advisor to manage bookings for customers.
 */
public class TravelAdvisorController {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button clientButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button reportButton;

    @FXML
    private Button settingButton;

    @FXML
    private Button ticketButton;

    @FXML
    private Button logOutButton;

    /**
     * Handles the action of the user clicking on the "Log Out" button, which logs the user out of the system and returns them to the login screen.
     *
     * @param event The action event representing the user's click on the "Log Out" button.
     * @throws IOException If an error occurs while loading the Login.fxml file.
     */
    @FXML
    void logOutButtonClick(ActionEvent event) throws IOException {
        // Load the Login.fxml file into a Parent object
        Parent root = FXMLLoader.load(Main.class.getResource("Login.fxml"));

        // Get the stage from the event source and set its scene to the Login.fxml file
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }


    /**
     * Loads the TravelAdvisorCustomers.fxml file and sets the stage to display the corresponding scene.
     * This method is called when the "Client" button is clicked.
     *
     * @param event the action event triggered by clicking the "Client" button
     * @throws IOException if an I/O exception occurs while loading the FXML file
     */
    @FXML
    void clientButtonClick(ActionEvent event) throws IOException {
        // Load the TravelAdvisorCustomers.fxml file
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorCustomers.fxml"));

        // Get the current stage and set its scene to display the TravelAdvisorCustomers.fxml file
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Loads the TravelAdvisorHome.fxml file and sets the stage to display the corresponding scene.
     * This method is called when the "Home" button is clicked.
     *
     * @param event the action event triggered by clicking the "Home" button
     * @throws IOException if an I/O exception occurs while loading the FXML file
     */
    @FXML
    void homeButtonClick(ActionEvent event) throws IOException {
        // Load the TravelAdvisorHome.fxml file
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorHome.fxml"));

        // Get the current stage and set its scene to display the TravelAdvisorHome.fxml file
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void reportButtonClick(ActionEvent event) {

    }

    /**
     * This method handles the action when the "settingButton" is clicked.
     * It loads the "TravelAdvisorSettingGeneral.fxml" file using the FXMLLoader class
     * and sets it as the root of the scene. The scene is then set to the current stage
     * and shown to the user.
     *
     * @param event the action event when the "settingButton" is clicked
     * @throws IOException if the "TravelAdvisorSettingGeneral.fxml" file cannot be loaded
     */
    @FXML
    void settingButtonClick(ActionEvent event) throws IOException {
        // Load the "TravelAdvisorSettingGeneral.fxml" file using the FXMLLoader class
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorSettingGeneral.fxml"));

        // Get the current stage from the event source and set the root of the scene to the loaded FXML file
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        // Set the scene to the current stage and show it to the user
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This method handles the action when the "ticketButton" is clicked.
     * It loads the "TravelAdvisorTickets.fxml" file using the FXMLLoader class
     * and sets it as the root of the scene. The scene is then set to the current stage
     * and shown to the user.
     *
     * @param event the action event when the "ticketButton" is clicked
     * @throws IOException if the "TravelAdvisorTickets.fxml" file cannot be loaded
     */
    @FXML
    void ticketButtonClick(ActionEvent event) throws IOException {
        // Load the "TravelAdvisorTickets.fxml" file using the FXMLLoader class
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorTickets.fxml"));

        // Get the current stage from the event source and set the root of the scene to the loaded FXML file
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        // Set the scene to the current stage and show it to the user
        stage.setScene(scene);
        stage.show();
    }


}

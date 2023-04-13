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
 * The SysadminHomeController class is the controller class for the SystemAdmin Home UI.
 */
public class SysadminHomeController {
    private Stage stage;
    private Scene scene;

    @FXML
    private Button backButton;

    /**
     * Handles the action event triggered by the back button.
     *
     * @param event the action event triggered by the back button
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
    @FXML
    void backButtonClick(ActionEvent event) throws IOException {
        // Load the "Sysadmin" FXML file
        Parent root = FXMLLoader.load(Main.class.getResource("Sysadmin.fxml"));
        // Get the current stage and set the scene to the loaded FXML file
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        // Show the stage with the loaded FXML file
        stage.show();
    }

}

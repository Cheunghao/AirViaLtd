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
 * The OfficeManagerHomeController class is the controller class for the Office Manager Home UI.
 */
public class OfficeManagerHomeController {
    private Stage stage;
    private Scene scene;

    @FXML
    private Button backButton;

    /**
     * Handles the click event for the back button by loading the OfficeManager.fxml file and setting the stage's scene to it.
     *
     * @param event The event triggered by clicking the back button.
     * @throws IOException If an error occurs while loading the OfficeManager.fxml file.
     */
    @FXML
    void backButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManager.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

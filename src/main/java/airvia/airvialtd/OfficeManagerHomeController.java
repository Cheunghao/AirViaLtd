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

public class OfficeManagerHomeController {
    private Stage stage;
    private Scene scene;

    @FXML
    private Button backButton;

    @FXML
    void backButtonClick(ActionEvent event) throws IOException {

        System.out.println("back");

        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManager.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}

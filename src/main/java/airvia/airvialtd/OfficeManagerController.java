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

public class OfficeManagerController {

    private Stage stage;
    private Scene scene;
    @FXML
    private Button blanksButton;

    @FXML
    private Button settingButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button reportButton;

    @FXML
    private Button ticketButton;

    @FXML
    void settingButtonClick(ActionEvent event) {

    }

    @FXML
    void homeButtonClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManagerHome.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void blankButtonClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManagerBlanks.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void reportButtonClick(ActionEvent event) {

    }

    @FXML
    void ticketButtonClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManagerTickets.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

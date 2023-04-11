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
    void clientButtonClick(ActionEvent event) {

    }

    @FXML
    void homeButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorHome.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void reportButtonClick(ActionEvent event) {

    }

    @FXML
    void settingButtonClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorSetting.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ticketButtonClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorTickets.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}

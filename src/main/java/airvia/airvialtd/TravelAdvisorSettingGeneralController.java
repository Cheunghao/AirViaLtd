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

public class TravelAdvisorSettingGeneralController {
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

    @FXML
    void backButtonClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisor.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void generalButtonClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorSettingGeneral.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void securityButtonClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorSettingSecurity.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void updateButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorSettingUpdate.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}

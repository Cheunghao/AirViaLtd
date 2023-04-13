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
 * The OfficeManagerSettingSecurityController class is the controller class for the Office Manager Security Settings UI.
 */
public class OfficeManagerSettingSecurityController {
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
     * This method is called when the back button is clicked on the office manager reports screen.
     * It loads the "OfficeManager.fxml" file and sets it as the root component for the current window,
     * effectively navigating back to the previous screen.
     *
     * @param event The ActionEvent object that triggered this method.
     * @throws IOException if the "OfficeManager.fxml" file cannot be loaded.
     */
    @FXML
    void backButtonClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManager.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This method is called when the general settings button is clicked on the office manager reports screen.
     * It loads the "OfficeManagerSettingGeneral.fxml" file and sets it as the root component for the current window,
     * effectively navigating to the general settings screen.
     *
     * @param event The ActionEvent object that triggered this method.
     * @throws IOException if the "OfficeManagerSettingGeneral.fxml" file cannot be loaded.
     */
    @FXML
    void generalButtonClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManagerSettingGeneral.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This method is called when the security settings button is clicked on the office manager reports screen.
     * It loads the "OfficeManagerSettingSecurity.fxml" file and sets it as the root component for the current window,
     * navigating to the security settings screen.
     *
     * @param event The ActionEvent object that triggered this method.
     * @throws IOException if the "OfficeManagerSettingSecurity.fxml" file cannot be loaded.
     */
    @FXML
    void securityButtonClick(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManagerSettingSecurity.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * This method is called when the update settings button is clicked on the office manager reports screen.
     * It loads the "OfficeManagerSettingUpdate.fxml" file and sets it as the root component for the current window,
     * navigating to the update settings screen.
     *
     * @param event The ActionEvent object that triggered this method.
     * @throws IOException if the "OfficeManagerSettingUpdate.fxml" file cannot be loaded.
     */
    @FXML
    void updateButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManagerSettingUpdate.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

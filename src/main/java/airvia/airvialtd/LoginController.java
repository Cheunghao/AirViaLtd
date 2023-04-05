package airvia.airvialtd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button btnSignIn;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private RadioButton rbtnRememberMe;

    @FXML
    private TextField tfUsername;

    @FXML
    void switchToMainMenu(ActionEvent event) throws IOException {
        System.out.println("login");

        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisorMenu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}

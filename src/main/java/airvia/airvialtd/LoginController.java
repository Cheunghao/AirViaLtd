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
import java.sql.Connection;
import java.sql.DriverManager;

public class LoginController {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button btnSignIn;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private RadioButton rbtnRememberMe;

    @FXML
    private TextField tfUsername;

    Connection connection;

    @FXML
    void switchToMainMenu(ActionEvent event) throws IOException {
        System.out.println("login button clicked");

        // sql statement to decide which type of employee has logged in then does this

        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManagerHome.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk/in2018g22", "in2018g22_a", "dM8Sf9EB");
            System.out.println("Connected");


        } catch (Exception e) {
            System.out.println("Connection Error");
            e.printStackTrace();
        }

    }

}

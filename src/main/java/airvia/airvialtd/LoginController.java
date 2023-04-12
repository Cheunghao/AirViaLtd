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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

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

    Connection connection;

    @FXML
    void switchToMainMenu(ActionEvent event) throws IOException {
        System.out.println("login button clicked");

        // sql statement to decide which type of employee has logged in then does this
        String username = tfUsername.getText();
        String password = pfPassword.getText();

        connectToDatabase();
        try {
            PreparedStatement pst = connection.prepareStatement("select * from Employees");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                if (Objects.equals(username, rs.getString("username")) && Objects.equals(password, rs.getString("password"))) {
                    switch (rs.getString("employee_type")) {

                        case "Office Manager":
                            root = FXMLLoader.load(Main.class.getResource("OfficeManager.fxml"));
                            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            break;
                        case "Travel Advisor":
                            root = FXMLLoader.load(Main.class.getResource("TravelAdvisor.fxml"));
                            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            break;
                        case "System Admin":
                            root = FXMLLoader.load(Main.class.getResource("Sysadmin.fxml"));
                            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            break;
                        default:
                            System.out.println("failed login");
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("failed sql");
        }
    }

    public void connectToDatabase() {
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

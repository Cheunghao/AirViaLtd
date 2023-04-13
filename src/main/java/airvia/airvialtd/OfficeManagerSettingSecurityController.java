package airvia.airvialtd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

/**
 * The OfficeManagerSettingSecurityController class is the controller class for the Office Manager Security Settings UI.
 */
public class OfficeManagerSettingSecurityController {
    private Connection connection;
    private Stage stage;
    private Scene scene;

    @FXML
    private Button backButton;

    @FXML
    private Button confirmButton;

    @FXML
    private PasswordField newPasswordTextField;

    @FXML
    private PasswordField oldPasswordTextField;

    @FXML
    private Button generalButton;

    @FXML
    private Button securityButton;

    @FXML
    private Button updateButton;

    /**
     * This method is called when the user clicks the confirm button to change the password of the office manager.
     * It retrieves the old password from the oldPasswordTextField, checks if it matches the password of the office manager
     * in the database, and if so, updates the password to the value in the newPasswordTextField.
     *
     * @param event the action event triggered by the user clicking the confirm button
     */
    @FXML
    void confirmButtonClick(ActionEvent event) {
        connectToDatabase(); // Connect to the database
        try {
            String password = oldPasswordTextField.getText(); // Get the old password from the oldPasswordTextField
            PreparedStatement pst = connection.prepareStatement("select password from Employees where employee_type = 'Office Manager'");
            ResultSet rs = pst.executeQuery();
            rs.next();
            if (Objects.equals(password, rs.getString("password"))) { // Check if the old password matches the password of the office manager in the database
                String updateSQL = "update Employees set password = '" + newPasswordTextField.getText() + "' where employee_type = 'Office Manager'";

                pst = connection.prepareStatement(updateSQL); // Prepare the SQL statement to update the password
                pst.execute(); // Execute the SQL statement to update the password
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace if there is an exception
        }
    }


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

    /**
     *
     * Attempts to connect to a MySQL database using the JDBC driver and the provided credentials.
     * The connection object is stored in the instance variable 'connection'.
     */
    public void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver class
            connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk/in2018g22", "in2018g22_a", "dM8Sf9EB"); // Attempt to establish a connection to MySQL database
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

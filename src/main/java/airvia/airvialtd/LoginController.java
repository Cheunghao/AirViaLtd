package airvia.airvialtd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
/**
 * The LoginController class handles user authentication and login functionality.
 * It provides methods for validating user credentials.
 */
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

    private Connection connection;
    private boolean loggedIn;

    /**
     * Event handler for the login button that switches to the appropriate main menu based on the employee type.
     *
     * @param event the action event that triggers the method
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
    @FXML
    void switchToMainMenu(ActionEvent event) throws IOException {



        /**
         * Retrieves the username and password entered in the login form and connects to the database.
         *
         * @return void
         */String username = tfUsername.getText(); // Retrieve the text from the tfUsername TextField and store it in a String variable
        String password = pfPassword.getText(); // Retrieve the text from the pfPassword PasswordField and store it in a String variable
        connectToDatabase(); // Call a method named connectToDatabase()


        /**
         * Retrieves user data from the database and switches to the appropriate scene based on the user type.
         *
         * @param event the event triggered by clicking the login button
         * @throws IOException if an I/O error occurs while loading the FXML files for the different scenes
         */


        try {
            PreparedStatement pst = connection.prepareStatement("select * from Employees"); // Create a prepared statement to query the "Employees" table
            ResultSet rs = pst.executeQuery(); // Execute the query and store the results in a ResultSet

            while (rs.next()) { // Iterate through each row of the ResultSet
                if (Objects.equals(username, rs.getString("username")) && Objects.equals(password, rs.getString("password"))) { // Check if the current row's username and password match the provided username and password
                    loggedIn = true;

                    switch (rs.getString("employee_type")) { // Switch statement based on employee type retrieved from the database
                        case "Office Manager": // If employee type is Office Manager
                            root = FXMLLoader.load(Main.class.getResource("OfficeManager.fxml")); // Load OfficeManager.fxml
                            stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); // Get the current stage
                            scene = new Scene(root); // Create a new scene with the loaded fxml
                            stage.setScene(scene); // Set the stage to the new scene
                            stage.show(); // Display the stage
                            break; // Exit the switch statement
                        case "Travel Advisor": // If employee type is Travel Advisor
                            root = FXMLLoader.load(Main.class.getResource("TravelAdvisor.fxml")); // Load TravelAdvisor.fxml
                            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            break;
                        case "System Admin": // If employee type is System Admin
                            root = FXMLLoader.load(Main.class.getResource("Sysadmin.fxml")); // Load Sysadmin.fxml
                            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                            break;
                        default:
                            loggedIn = false;// If employee type is none of the above
                    }
                }
            }
            if (!loggedIn) {
                System.out.println("no login");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText("Incorrect username or password");
                alert.setContentText("Please enter the correct username and password.");

                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println("failed sql");
        }
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

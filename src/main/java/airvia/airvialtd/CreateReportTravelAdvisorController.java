package airvia.airvialtd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
/**
 * The CreateReportTravelAdvisorController class is responsible for managing the user interface and
 * processing user input related to creating a new travel report .
 * This class implements the Initializable interface, which allows it to perform initialization
 * tasks when the corresponding user interface is loaded.
 */
public class CreateReportTravelAdvisorController implements Initializable {

    private Connection connection;
    private String flightType, commissionRate, saleTotal, currencyID, travelAdvisorID, ticketID, taxPercentage, taxValue, date;

    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();

    @FXML
    private TextField choiceBoxTextField;

    @FXML
    private TextField commissionRateTextField;

    @FXML
    private Button saveCloseButton;

    @FXML
    private TextField taxTextField;

    /**
     * Handles the Save and Close button click event.
     *
     * @param event the action event triggered by the Save and Close button
     */
    @FXML
    void saveCloseButtonClick(ActionEvent event) {
        getInfo();
    }


    /**
     * Handles the action event triggered by the choice box.
     *
     * @param event the action event triggered by the choice box
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
    @FXML
    void handleChoiceBoxAction(ActionEvent event) throws IOException {
        // Check if the selected value in the choice box is "Travel Agency"
        if (choiceBox.getValue().equals("Travel Agency")) {
            // Load the "CreateReportTravelAgency" FXML file
            Parent root = FXMLLoader.load(Main.class.getResource("CreateReportTravelAgency.fxml"));
            // Get the current stage and set the scene to the loaded FXML file
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Show the stage with the loaded FXML file
            stage.show();
        }
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resource bundle used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the default value of the choice box to "Travel Advisor"
        choiceBox.setValue("Travel Advisor");
    }


    /**
     * Retrieves information from the database and calculates the total payment amount.
     */
    public void getInfo() {
        // Connect to the database
        connectToDatabase();

        // Initialize variables
        Float total = 0.0F;
        commissionRate = commissionRateTextField.getText();
        taxPercentage = taxTextField.getText();
        travelAdvisorID = choiceBoxTextField.getText();

        try {
            // Execute SQL query to retrieve payment total from the database
            PreparedStatement pst = connection.prepareStatement("select * from Ticket t join Blank b on t.blank_id = b.blank_id where b.travel_advisor_ID = " + travelAdvisorID);
            ResultSet rs = pst.executeQuery();
            // Loop through the result set and calculate the total payment amount
            while (rs.next()) {
                total += rs.getFloat("payment_total");
            }
        } catch (Exception e) {
            // Print the stack trace if an error occurs
            e.printStackTrace();
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

package airvia.airvialtd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class CreateBlankController {
    private Connection connection;

    private String blankType, blankNumber, airlineName, auditorsCoupons, flightCoupon, travelAdvisorID, sysadminID;

    @FXML
    private TextField sysadminIDTextField;

    @FXML
    private TextField airlineNameTextField;

    @FXML
    private TextField auditorsCouponTextField;

    @FXML
    private TextField blankNumberTextField;

    @FXML
    private TextField blankTypeTextField;

    @FXML
    private TextField flightCouponTextField;

    @FXML
    private Button saveCloseButton;

    @FXML
    private TextField travelAdvisorIDTextField;

    /**
     * This method is called when the Save & Close button is clicked. It retrieves information from the UI fields, checks the input for errors, and inserts the data into the database.
     *
     * @param event the event that triggered this method call
     */
    @FXML
    void saveCloseButtonClick(ActionEvent event) {
        // Retrieve information from the UI fields
        getInfo();
        // Check if the input is valid
        if (checkInfo()) {

            // Define the SQL statement for inserting data into the database
            String insertSQL = "INSERT INTO Blank (blank_type, blank_number, airline_name, auditors_coupons, flight_coupon, travel_advisor_id, sysadmin_id) VALUES (?, ?, ?, ?, ?, ?, ?);";

            try {
                // Prepare the SQL statement for execution
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                // Set the parameter values
                preparedStatement.setInt(1, Integer.parseInt(blankType));
                preparedStatement.setInt(2, Integer.parseInt(blankNumber));
                preparedStatement.setString(3, airlineName);
                preparedStatement.setInt(4, Integer.parseInt(auditorsCoupons));
                preparedStatement.setInt(5, Integer.parseInt(flightCoupon));
                preparedStatement.setInt(6, Integer.parseInt(travelAdvisorID));
                preparedStatement.setInt(7, Integer.parseInt(sysadminID));
                System.out.println(insertSQL);
                // Execute the SQL statement
                preparedStatement.executeUpdate();

                // Get the current window and close it
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method checks the input for errors.
     *
     * @return true if there are no errors, false otherwise
     */
    public Boolean checkInfo() {
        Boolean noError = false;
        try {
            // Parse input strings into integers to check for errors
            Integer.parseInt(blankType);
            Integer.parseInt(blankNumber);
            Integer.parseInt(auditorsCoupons);
            Integer.parseInt(flightCoupon);
            Integer.parseInt(travelAdvisorID);
            Integer.parseInt(sysadminID);
            // If there are no errors, set noError to true
            noError = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Return true if there are no errors, false otherwise
        return noError;
    }


    /**
     * This method retrieves information from the UI fields and stores them in instance variables.
     */
    public void getInfo() {
        // Connect to the database
        connectToDatabase();
        // Retrieve information from the UI fields and store them in instance variables
        blankType = blankTypeTextField.getText();
        blankNumber = blankNumberTextField.getText();
        airlineName = airlineNameTextField.getText();
        auditorsCoupons = auditorsCouponTextField.getText();
        flightCoupon = flightCouponTextField.getText();
        travelAdvisorID = travelAdvisorIDTextField.getText();
        sysadminID = sysadminIDTextField.getText();
    }


    /**
     *
     * Attempts to connect to a MySQL database using the JDBC driver and the provided credentials.
     * The connection object is stored in the instance variable 'connection'.
     * @throws ClassNotFoundException if the JDBC driver class cannot be found
     * @throws Exception if there is an error with the SQL syntax or connection
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

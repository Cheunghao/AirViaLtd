package airvia.airvialtd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class AddTicketsController {
    private Connection connection;
    private String validityStatus, purchaseDate, paymentType, paymentTotal, currencyID, blankID, refundStatus;

    @FXML
    private Button SaveCloseButton;

    @FXML
    private TextField blankIDTextField;

    @FXML
    private TextField currencyIDTextField;

    @FXML
    private TextField paymentTotalTextField;

    @FXML
    private TextField paymentTypeTextField;

    @FXML
    private TextField purchaseDateTextField;

    @FXML
    private TextField refundStatusTextField;

    @FXML
    private TextField validityStatusTextField;

    /**
     * Saves the ticket information to the database and closes the window.
     * This method is called when the Save and Close button is clicked.
     *
     * @param event The event triggered by the Save and Close button click
     */
    @FXML
    void SaveCloseButtonClick(ActionEvent event) {
        // Get the ticket information from the form
        getInfo();

        // Check if the information is valid
        if (checkInfo()) {
            // Define the SQL query to insert the ticket information
            String insertSQL = "INSERT INTO Ticket (validity_status, purchase_date, payment_type, payment_total, currency_ID, blank_id, refund_status) VALUES (?, ?, ?, ?, ?, ?, ?);";

            try {
                // Create a prepared statement for the query
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1, validityStatus);
                preparedStatement.setString(2, purchaseDate);
                preparedStatement.setString(3, paymentType);
                preparedStatement.setFloat(4, Float.parseFloat(paymentTotal));
                preparedStatement.setInt(5, Integer.parseInt(currencyID));
                preparedStatement.setInt(6, Integer.parseInt(blankID));
                preparedStatement.setString(7, refundStatus);

                // Execute the query
                preparedStatement.executeUpdate();

                // Get the stage of the current window and close it
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Checks if the ticket information is valid.
     *
     * @return A boolean indicating if the ticket information is valid (true) or not (false)
     */
    public Boolean checkInfo() {
        // Initialize the noError variable to false
        Boolean noError = false;

        // Check if the validityStatus is either "valid" or "invalid"
        if (Objects.equals(validityStatus, "valid") || Objects.equals(validityStatus, "invalid")) {
            noError = true;
        }

        // Check if the purchaseDate can be parsed using the specified formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            LocalDateTime.parse(purchaseDate, formatter);
            noError = true;
        } catch (DateTimeParseException e) {}

        // Check if the paymentType is either "cash" or "card"
        if (Objects.equals(paymentType, "cash") || Objects.equals(paymentType, "card")) {
            noError = true;
        }

        // Check if the paymentTotal can be parsed as a float and is greater than or equal to 0
        try {
            float floatValue = Float.parseFloat(paymentTotal);
            if (floatValue >= 0) {
                noError = true;
            }
        } catch (NumberFormatException e) {}

        // Check if the currencyID can be parsed as an integer
        try {
            Integer.parseInt(currencyID);
        } catch (NumberFormatException e) {}

        // Check if the blankID can be parsed as an integer
        try {
            Integer.parseInt(blankID);
        } catch (NumberFormatException e) {}

        // Check if the refundStatus is either "yes" or "no"
        if (Objects.equals(refundStatus, "yes") || Objects.equals(refundStatus, "no")) {
            noError = true;
        }

        // Return the value of noError
        return noError;
    }

    /**
     * Gets the ticket information from the text fields.
     */
    public void getInfo() {
        connectToDatabase();
        validityStatus = validityStatusTextField.getText();
        purchaseDate = purchaseDateTextField.getText();
        paymentType = paymentTypeTextField.getText();
        paymentTotal = paymentTotalTextField.getText();
        currencyID = currencyIDTextField.getText();
        blankID = blankIDTextField.getText();
        refundStatus = refundStatusTextField.getText();
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

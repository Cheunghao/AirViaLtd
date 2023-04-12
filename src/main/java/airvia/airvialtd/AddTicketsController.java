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

    @FXML
    void SaveCloseButtonClick(ActionEvent event) {
        getInfo();
        if (checkInfo()) {

            String insertSQL = "INSERT INTO Ticket (validity_status, purchase_date, payment_type, payment_total, currency_ID, blank_id, refund_status) VALUES (?, ?, ?, ?, ?, ?, ?);";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1, validityStatus);
                preparedStatement.setString(2, purchaseDate);
                preparedStatement.setString(3, paymentType);
                preparedStatement.setFloat(4, Float.parseFloat(paymentTotal));
                preparedStatement.setInt(5, Integer.parseInt(currencyID));
                preparedStatement.setInt(6, Integer.parseInt(blankID));
                preparedStatement.setString(7, refundStatus);
                System.out.println(insertSQL);
                preparedStatement.executeUpdate();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Close the window
                stage.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }

    public Boolean checkInfo() {
        Boolean noError = false;
        if (Objects.equals(validityStatus, "valid") || Objects.equals(validityStatus, "invalid")) {
            noError = true;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            LocalDateTime.parse(purchaseDate, formatter);
            noError = true;
        } catch (DateTimeParseException e) {}

        if (Objects.equals(paymentType, "cash") || Objects.equals(paymentType, "card")) {noError = true;}

        try {
            float floatValue = Float.parseFloat(paymentTotal);
            if (floatValue >= 0) {noError = true;}
        } catch (NumberFormatException e) {}

        try {
            Integer.parseInt(currencyID);
        } catch (NumberFormatException e) {}

        try {
            Integer.parseInt(blankID);
        } catch (NumberFormatException e) {}


        if (Objects.equals(refundStatus, "yes") || Objects.equals(refundStatus, "no")) {noError = true;}




        return noError;
    }

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

    public void connectToDatabase() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk/in2018g22", "in2018g22_a", "dM8Sf9EB");
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println("connection error");
            e.printStackTrace();
        }
    }

}

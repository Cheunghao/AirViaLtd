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

    @FXML
    void saveCloseButtonClick(ActionEvent event) {
        getInfo();
        if (checkInfo()) {

            String insertSQL = "INSERT INTO Blank (blank_type, blank_number, airline_name, auditors_coupons, flight_coupon, travel_advisor_id, sysadmin_id) VALUES (?, ?, ?, ?, ?, ?, ?);";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setInt(1, Integer.parseInt(blankType));
                preparedStatement.setInt(2, Integer.parseInt(blankNumber));
                preparedStatement.setString(3, airlineName);
                preparedStatement.setInt(4, Integer.parseInt(auditorsCoupons));
                preparedStatement.setInt(5, Integer.parseInt(flightCoupon));
                preparedStatement.setInt(6, Integer.parseInt(travelAdvisorID));
                preparedStatement.setInt(7, Integer.parseInt(sysadminID));
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
        try {
            Integer.parseInt(blankType);
            Integer.parseInt(blankNumber);
            Integer.parseInt(auditorsCoupons);
            Integer.parseInt(flightCoupon);
            Integer.parseInt(travelAdvisorID);
            Integer.parseInt(sysadminID);
            noError = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return noError;
    }

    public void getInfo() {
        connectToDatabase();
        blankType = blankTypeTextField.getText();
        blankNumber = blankNumberTextField.getText();
        airlineName = airlineNameTextField.getText();
        auditorsCoupons = auditorsCouponTextField.getText();
        flightCoupon = flightCouponTextField.getText();
        travelAdvisorID = travelAdvisorIDTextField.getText();
        sysadminID = sysadminIDTextField.getText();

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

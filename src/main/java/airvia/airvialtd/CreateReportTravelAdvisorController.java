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
    private String paymentType, flightType, commissionRate, saleTotal, currencyID, travelAdvisorID, ticketID, taxPercentage, taxValue, date;

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

    @FXML
    void saveCloseButtonClick(ActionEvent event) {
        getInfo();

    }

    @FXML
    void handleChoiceBoxAction(ActionEvent event) throws IOException {
        if (choiceBox.getValue().equals("Travel Agency")) {

            Parent root = FXMLLoader.load(Main.class.getResource("CreateReportTravelAgency.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.setValue("Travel Advisor");
    }

    public void getInfo() {
        connectToDatabase();
        //paymentType, flightType, saleTotal, currencyID, ticketID, taxValue, date;
        commissionRate = commissionRateTextField.getText();
        taxPercentage = taxTextField.getText();
        travelAdvisorID = choiceBoxTextField.getText();

        try {
            PreparedStatement pst = connection.prepareStatement("select * from Ticket t join Blank b on t.blank_id = b.blank_id where b.travel_advisor_ID = " + travelAdvisorID);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                paymentType = rs.getString("payment_type");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
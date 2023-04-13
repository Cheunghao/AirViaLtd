package airvia.airvialtd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 * The SysadminReportsController class is the controller class for the System Admin UI.
 * It handles user input and updates the UI accordingly, as well as communicating with the back-end server to retrieve and update data.
 *
 * This class also implements the Initializable interface, which provides a method for initializing the controller after its
 * root element has been loaded.
 */
public class SysadminReportsController implements Initializable {
    private Connection connection;
    private Stage stage;
    private Scene scene;
    private ResultSet rs;

    @FXML
    private Button backButton;

    @FXML
    private Button createReportButton;

    @FXML
    private Button removeReportButton;

    @FXML
    private ChoiceBox<?> reportChoiceBox;

    @FXML
    private TableView<Reports> reportTable;
    @FXML
    private TableColumn<Reports, Integer> colReportID = new TableColumn("Report ID");
    @FXML
    private TableColumn<Reports, String> colPaymentType = new TableColumn("Payment Type");
    @FXML
    private TableColumn<Reports, Integer> colFlightType = new TableColumn("Flight Type");
    @FXML
    private TableColumn<Reports, Integer> colCommissionRate = new TableColumn("Commission Rate");
    @FXML
    private TableColumn<Reports, Integer> colSaleTotal = new TableColumn("Sale Total");
    @FXML
    private TableColumn<Reports, Integer> colCurrencyID = new TableColumn("Currency ID");
    @FXML
    private TableColumn<Reports, Integer> colTravelAdvisorID = new TableColumn("Travel Advisor ID");
    @FXML
    private TableColumn<Reports, Integer> colTicketID = new TableColumn("Ticket ID");
    @FXML
    private TableColumn<Reports, String> colCardInfo = new TableColumn("Card Info");


    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private ChoiceBox<?> timeChoiceBox;

    @FXML
    private Label totalReportsLabel;
    /**
     * Handles the click event for the create report button by loading the CreateReportTravelAdvisor.fxml file and setting the stage's scene to it.
     *
     * @param event The event triggered by clicking the create report button.
     * @throws IOException If an error occurs while loading the CreateReportTravelAdvisor.fxml file.
     */
    @FXML
    void createReportButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("CreateReportTravelAdvisor.fxml"));
        Stage newStage = new Stage();
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.show();
    }


    /**
     * Handles the click event for the back button by loading the Sysadmin.fxml file and setting the stage's scene to it.
     *
     * @param event The event triggered by clicking the back button.
     * @throws IOException If an error occurs while loading the OfficeManager.fxml file.
     */
    @FXML
    void backButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("Sysadmin.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void removeReportButtonClick(ActionEvent event) {
    }

    /**
     * Handles the click event for the search button by calling the displayTable method to display the search results in a table.
     *
     * @param event The event triggered by clicking the search button.
     */
    @FXML
    void searchButtonClick(ActionEvent event) {
        displayTable();
    }
    /**
     * Queries the SaleInfo table in the database for sale information that matches the search criteria and populates a TableView with the results.
     */
    public void displayTable() {
        ObservableList<Reports> reportList = FXCollections.observableArrayList();

        try {
            PreparedStatement pst = connection.prepareStatement("select * from SaleInfo where sale_ID like '%" + searchTextField.getText() + "%'");
            rs = pst.executeQuery();
            while(rs.next()) {
                Integer reportID = rs.getInt("sale_ID");
                String paymentType = rs.getString("payment_type");
                String flightType = rs.getString("flight_type");
                Float commissionRate = rs.getFloat("commission_rate");
                Float saleTotal = rs.getFloat("sale_total");
                Integer currencyID = rs.getInt("currency_ID");
                Integer travelAdvisorID = rs.getInt("travel_advisor_ID");
                Integer ticketID = rs.getInt("ticket_ID");
                String cardInfo = rs.getString("card_info");

                reportList.add(new Reports(reportID, paymentType, flightType, commissionRate, saleTotal, currencyID, travelAdvisorID, ticketID, cardInfo));
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        reportTable.setItems(reportList);
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


    /**
     * This method is called by the FXMLLoader to initialize the SysadminReport view after its components have been loaded.
     * It sets up the table columns and fetches the report data from the database.
     *
     * @param url The location used to resolve relative paths for the root object or null if the location is not known
     * @param rb The resources used to localize the root object or null if the root object was not localized
     */
    public void initialize(URL url, ResourceBundle rb) {
        // Print a message to the console to indicate that the method has been called
        System.out.println("connect");

        // Connect to the database using the connectToDatabase method
        connectToDatabase();

        // Set up the table columns using PropertyValueFactory and add them to the reportTable
        colReportID.setCellValueFactory(new PropertyValueFactory<>("reportID"));
        reportTable.getColumns().add(colReportID);
        colPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        reportTable.getColumns().add(colPaymentType);
        colFlightType.setCellValueFactory(new PropertyValueFactory<>("flightType"));
        reportTable.getColumns().add(colFlightType);
        colCommissionRate.setCellValueFactory(new PropertyValueFactory<>("commissionRate"));
        reportTable.getColumns().add(colCommissionRate);
        colSaleTotal.setCellValueFactory(new PropertyValueFactory<>("saleTotal"));
        reportTable.getColumns().add(colSaleTotal);
        colCurrencyID.setCellValueFactory(new PropertyValueFactory<>("currencyID"));
        reportTable.getColumns().add(colCurrencyID);
        colTravelAdvisorID.setCellValueFactory(new PropertyValueFactory<>("travelAdvisorID"));
        reportTable.getColumns().add(colTravelAdvisorID);
        colTicketID.setCellValueFactory(new PropertyValueFactory<>("ticketID"));
        reportTable.getColumns().add(colTicketID);
        colCardInfo.setCellValueFactory(new PropertyValueFactory<>("cardInfo"));
        reportTable.getColumns().add(colCardInfo);
    }


}

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
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
/**
 * This class represents the controller for the Travel Advisor Tickets view, which displays a list of tickets that have been booked by customers.
 */
public class TravelAdvisorTicketsController implements Initializable {


    private Connection connection;
    private ResultSet rs;
    private Stage stage;
    private Scene scene;
    private String textEntered;



    @FXML
    private Label totalProfitsLabel;

    @FXML
    private Label totalTicketsLabel;

    @FXML
    private Button addTicketButton;

    @FXML
    private Button backButton;

    @FXML
    private Button removeTicketButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;


    @FXML
    private TableColumn<Tickets, Integer> colTicketID = new TableColumn<>("Ticket ID");

    @FXML
    private TableColumn<Tickets, String> colValidityStatus = new TableColumn<>("Validity Status");

    @FXML
    private TableColumn<Tickets, String> colPurchaseDate = new TableColumn<>("Purchase Date");
    @FXML
    private TableColumn<Tickets, String> colPaymentType = new TableColumn<>("Payment Type");
    @FXML
    private TableColumn<Tickets, Float> colPaymentTotal = new TableColumn<>("Payment Total");
    @FXML
    private TableColumn<Tickets, Integer> colCurrencyID = new TableColumn<>("Currency ID");
    @FXML
    private TableColumn<Tickets, Integer> colBlankID = new TableColumn<>("Blank ID");
    @FXML
    private TableColumn<Tickets, String > colRefundStatus = new TableColumn<>("Refund Status");

    @FXML
    private TableView<Tickets> ticketTable;

    @FXML
    private Button viewTransactionButton;

    /**
     * This method is called when the "Add Ticket" button is clicked in the Travel Advisor Tickets view. It loads the "Add Tickets" view in a new window.
     *
     * @param event The ActionEvent object that triggered the method call.
     * @throws IOException If an error occurs while loading the "Add Tickets" view.
     */
    @FXML
    void addTicketButton(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Main.class.getResource("AddTickets.fxml"));
        Stage newStage = new Stage();
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.show();
    }


    /**
     * Handles the action event triggered by the back button.
     *
     * @param event the action event triggered by the back button
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
    @FXML
    void backButtonClick(ActionEvent event) throws IOException {
        // Load the "TravelAdvisor" FXML file
        Parent root = FXMLLoader.load(Main.class.getResource("TravelAdvisor.fxml"));
        // Get the current stage and set the scene to the loaded FXML file
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        // Show the stage with the loaded FXML file
        stage.show();
    }

    @FXML
    void removeTicketButton(ActionEvent event) {

    }

    /**
     * Populates and displays the ticket table with data from the Ticket table in the database.
     * The data displayed is filtered by the search text entered in the search text field.
     * If no search text is entered, all data from the Ticket table is displayed.
     */
    public void displayTable() {
        // Create an ObservableList to store the Tickets data to be displayed in the table
        ObservableList<Tickets> ticketList = FXCollections.observableArrayList();

        try {
            // Prepare the SQL statement to retrieve the ticket data from the database
            PreparedStatement pst = connection.prepareStatement("select * from Ticket where ticket_ID like '%" + searchTextField.getText() + "%'");
            // Execute the SQL statement and store the result set in the 'rs' variable
            rs = pst.executeQuery();

            // Iterate through each row in the result set
            while(rs.next()) {
                // Retrieve the ticket data from each column in the row
                Integer ticketID = rs.getInt("ticket_ID");
                String valid = rs.getString("validity_status");
                java.sql.Timestamp timestamp = rs.getTimestamp("purchase_date");
                java.util.Date date = new java.util.Date(timestamp.getTime());
                String formattedDate = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(date);
                String paymentType = rs.getString("payment_type");
                Float paymentTotal = rs.getFloat("payment_total");
                Integer currencyID = rs.getInt("currency_id");
                Integer blankID = rs.getInt("blank_id");
                String refundStatus = rs.getString("refund_status");

                // Create a new Tickets object with the retrieved data and add it to the ticketList
                ticketList.add(new Tickets(ticketID, valid, formattedDate, paymentType, paymentTotal, currencyID, blankID, refundStatus));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the ticketList as the items to be displayed in the ticketTable
        ticketTable.setItems(ticketList);
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
     * Handles the event when the search button is clicked.
     * Connects to the database and displays the ticket table.
     */
    @FXML
    void searchButtonClick(ActionEvent event) {
        connectToDatabase(); // Connect to the database
        displayTable(); // Display the ticket table
    }

    /**
     * Retrieves the total number of tickets from the Ticket table in the database.
     * @return A string representing the total number of tickets.
     */
    public String getTotalTickets() {
        int total = 0;
        try {
            // Prepare the SQL statement to retrieve all ticket data from the database
            PreparedStatement pst = connection.prepareStatement("select * from Ticket");
            // Execute the SQL statement and store the result set in the 'rs' variable
            rs = pst.executeQuery();

            // Iterate through each row in the result set and increment the total counter
            while (rs.next()) {
                total++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(total);
        return String.valueOf(total);
    }

    /**
     * Initializes the ticket table and sets up the columns with their respective cell values.
     * Connects to the database and retrieves the total number of tickets, and sets it as the totalTicketsLabel text.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    public void initialize(URL url, ResourceBundle rb) {
        connectToDatabase(); // Connect to the database

        // Set up the table columns with their respective cell values
        colTicketID.setCellValueFactory(new PropertyValueFactory<>("ticketID"));
        ticketTable.getColumns().add(colTicketID);
        colValidityStatus.setCellValueFactory(new PropertyValueFactory<>("validityStatus"));
        ticketTable.getColumns().add(colValidityStatus);
        colPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        ticketTable.getColumns().add(colPurchaseDate);
        colPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        ticketTable.getColumns().add(colPaymentType);
        colPaymentTotal.setCellValueFactory(new PropertyValueFactory<>("paymentTotal"));
        ticketTable.getColumns().add(colPaymentTotal);
        colCurrencyID.setCellValueFactory(new PropertyValueFactory<>("currencyID"));
        ticketTable.getColumns().add(colCurrencyID);
        colBlankID.setCellValueFactory(new PropertyValueFactory<>("blankID"));
        ticketTable.getColumns().add(colBlankID);
        colRefundStatus.setCellValueFactory(new PropertyValueFactory<>("refundStatus"));
        ticketTable.getColumns().add(colRefundStatus);

        // Set the total number of tickets as the totalTicketsLabel text
        totalTicketsLabel.setText(getTotalTickets());
    }


}

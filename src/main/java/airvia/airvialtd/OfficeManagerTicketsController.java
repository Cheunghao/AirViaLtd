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
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * The OfficeManagerTicketsController class is the controller class for the Office Manager Tickets UI.
 * It handles user input and updates the UI accordingly, as well as communicating with the back-end server to retrieve and update data.
 *
 * This class also implements the Initializable interface, which provides a method for initializing the controller after its
 * root element has been loaded.
 */
public class OfficeManagerTicketsController implements Initializable {
    private Connection connection;
    private ResultSet rs;
    private Stage stage;
    private Scene scene;

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
     * This method handles the event when the "Add Ticket" button is clicked and opens the AddTickets.fxml view in a new window.
     *
     * @param event The event triggered by clicking the "Add Ticket" button.
     * @throws IOException If an input/output error occurs while loading the AddTickets.fxml file.
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
        // Load the "OfficeManager" FXML file
        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManager.fxml"));
        // Get the current stage and set the scene to the loaded FXML file
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        // Show the stage with the loaded FXML file
        stage.show();
    }



    /**
     * This method handles the event when the "Remove Ticket" button is clicked and removes a ticket from the database.
     *
     * @param event The event triggered by clicking the "Remove Ticket" button.
     */
    @FXML
    void removeTicketButton(ActionEvent event) {

        // Display confirmation dialog to ensure the user wants to perform this action
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to perform this action?");

        Optional<ButtonType> result = alert.showAndWait();
        int ticket;

        // If the user confirms the action, proceed with removing the ticket
        if (result.isPresent() && result.get() == ButtonType.OK) {
            PreparedStatement pst;
            try {
                // If the search text field is empty, find the minimum ticket ID from the Ticket table
                if (Objects.equals(searchTextField.getText(), "")) {
                    System.out.println("empty");
                    pst = connection.prepareStatement("select MIN(ticket_ID) from Ticket ");
                    rs = pst.executeQuery();
                    rs.next();
                    ticket = rs.getInt(1);
                } else {
                    ticket = Integer.parseInt(searchTextField.getText());
                }

                // Remove the ticket from all related tables
                pst = connection.prepareStatement("DELETE FROM Refund where ticket_ID = "+ ticket);
                pst.executeUpdate();
                pst = connection.prepareStatement("DELETE FROM Customer where ticket_ID = "+ ticket);
                pst.executeUpdate();
                pst = connection.prepareStatement("DELETE FROM SaleInfo where ticket_ID = "+ ticket);
                pst.executeUpdate();
                pst = connection.prepareStatement("DELETE FROM Ticket where ticket_ID = " + ticket);
                pst.executeUpdate();

            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        } else {
        }
    }


    /**
     * This method retrieves ticket data from the database and displays it in a table.
     */
    public void displayTable() {
        // Create an observable list to hold the ticket data
        ObservableList<Tickets> ticketList = FXCollections.observableArrayList();
        try {
            // Prepare a statement to retrieve ticket data from the Ticket table
            PreparedStatement pst = connection.prepareStatement("select * from Ticket where ticket_ID like '%" + searchTextField.getText() + "%'");
            rs = pst.executeQuery();
            while(rs.next()) {
                // Retrieve the data for each ticket from the result set
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

                // Add the ticket data to the observable list
                ticketList.add(new Tickets(ticketID, valid, formattedDate, paymentType, paymentTotal, currencyID, blankID, refundStatus));
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        // Set the observable list as the data source for the ticket table
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
     * This method is called when the search button is clicked. It retrieves ticket data from the database and displays it in a table.
     *
     * @param event the ActionEvent that triggered the method call
     */
    @FXML
    void searchButtonClick(ActionEvent event) {
        // Call the displayTable method to retrieve and display ticket data
        displayTable();
    }


    /**
     * Retrieves the total number of tickets in the database.
     *
     * @return a String representation of the total number of tickets in the database
     */
    public String getTotalTickets() {
        // Initialize a counter to keep track of the total number of tickets
        int total = 0;
        try {
            // Prepare and execute a query to retrieve all tickets from the database
            PreparedStatement pst = connection.prepareStatement("select * from Ticket");
            rs = pst.executeQuery();

            // Loop through the result set and increment the total for each ticket
            while (rs.next()) {
                total++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Return the total as a String
        return String.valueOf(total);
    }

    /**
     * Initializes the controller class.
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("connect");
        connectToDatabase();

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


        totalTicketsLabel.setText(getTotalTickets());
    }

}

package airvia.airvialtd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class TravelAdvisorCustomersController implements Initializable {

    private Connection connection;
    private ResultSet rs;
    private Stage stage;
    private Scene scene;


    @FXML
    private Button addClientButton;

    @FXML
    private Button backButton;

    @FXML
    private Button removeClientButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableColumn<Customers, Integer> colCustomerID = new TableColumn<>("Customer ID");
    @FXML
    private TableColumn<Customers, String> colFirstName = new TableColumn<>("First Name");
    @FXML
    private TableColumn<Customers, String> colSurname = new TableColumn<>("Surname");
    @FXML
    private TableColumn<Customers, String> colEmail = new TableColumn<>("Email");
    @FXML
    private TableColumn<Customers, String> colNumber = new TableColumn<>("Number");
    @FXML
    private TableColumn<Customers, String> colValuedCustomer = new TableColumn<>("Valued Customer");
    @FXML
    private TableColumn<Customers, Integer> colTicketID = new TableColumn<>("Ticket ID");

    @FXML
    private TableView<Customers> customerTable;

    @FXML
    private Label totalClientsLabel;

    @FXML
    void addClientButtonClick(ActionEvent event) {

    }

    @FXML
    void backButtonClick(ActionEvent event) {

    }

    @FXML
    void removeClientButtonClick(ActionEvent event) {

    }

    /**
     * This method handles the action when the "searchButton" is clicked.
     * It calls the "displayTable" method to display the table to the user.
     *
     * @param event the action event when the "searchButton" is clicked
     */
    @FXML
    void searchButtonClick(ActionEvent event) {
        // Call the "displayTable" method to display the table to the user
        displayTable();
    }


    /**
     * This method displays the customer information in the table based on the search query.
     * It retrieves the search query from the "searchTextField" and executes a SQL statement
     * to retrieve the customer information from the database. The information is then added
     * to an ObservableList of Customers and displayed in the table.
     */
    public void displayTable() {
        // Create an ObservableList of Customers to store the retrieved customer information
        ObservableList<Customers> customerList = FXCollections.observableArrayList();

        try {
            // Execute a SQL statement to retrieve the customer information based on the search query
            PreparedStatement pst = connection.prepareStatement("select * from Customer where customer_id like '%" + searchTextField.getText() + "%'");
            rs = pst.executeQuery();

            // Iterate through the retrieved result set and add the customer information to the ObservableList
            while(rs.next()) {
                Integer customerID = rs.getInt("customer_id");
                String firstName= rs.getString("customer_F_name");
                String surname = rs.getString("customer_L_name");
                String email = rs.getString("customer_email");
                String number = rs.getString("customer_contact_number");
                String valuedCustomer = rs.getString("valued_customer");
                Integer ticketID = rs.getInt("ticket_id");

                customerList.add(new Customers(customerID, firstName, surname, email, number, valuedCustomer, ticketID));
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        // Set the customer information in the ObservableList to the customerTable and display it in the table
        customerTable.setItems(customerList);
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

    /**
     * This method is called when the FXML file is loaded and initializes the GUI elements.
     * It calls the "connectToDatabase" method to establish a connection to the database.
     * It also sets up the columns in the customerTable and associates them with the appropriate
     * properties in the Customers class.
     *
     * @param url the URL location of the FXML file
     * @param resourceBundle the resource bundle associated with the FXML file
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Establish a connection to the database
        connectToDatabase();

        // Set up the columns in the customerTable and associate them with the appropriate properties in the Customers class
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerTable.getColumns().add(colCustomerID);
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        customerTable.getColumns().add(colFirstName);
        colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        customerTable.getColumns().add(colSurname);
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        customerTable.getColumns().add(colEmail);
        colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        customerTable.getColumns().add(colNumber);
        colValuedCustomer.setCellValueFactory(new PropertyValueFactory<>("valuedCustomer"));
        customerTable.getColumns().add(colValuedCustomer);
        colTicketID.setCellValueFactory(new PropertyValueFactory<>("ticketID"));
        customerTable.getColumns().add(colTicketID);
    }

}

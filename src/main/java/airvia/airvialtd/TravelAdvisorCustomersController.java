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

    @FXML
    void searchButtonClick(ActionEvent event) {
        displayTable();

    }

    public void displayTable() {
        ObservableList<Customers> customerList = FXCollections.observableArrayList();

        try {
            PreparedStatement pst = connection.prepareStatement("select * from Customer where customer_id like '%" + searchTextField.getText() + "%'");
            rs = pst.executeQuery();
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
        customerTable.setItems(customerList);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectToDatabase();

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

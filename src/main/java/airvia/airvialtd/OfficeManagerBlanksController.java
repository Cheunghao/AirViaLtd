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
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The OfficeManagerBlanksController class represents a controller for managing blanks in an office.
 * It implements the Initializable interface to initialize the controller after its root element has been processed.
 */
public class OfficeManagerBlanksController implements Initializable {
    private Connection connection;
    private ResultSet rs;
    private Stage stage;
    private Scene scene;


    @FXML
    private Button assignBlankButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Blanks> blankTable;

    @FXML
    private TableColumn<Blanks, Integer> colBlankID = new TableColumn<>("Blank ID");

    @FXML
    private TableColumn<Blanks, Integer> colBlankType = new TableColumn<>("Blank Type");

    @FXML
    private TableColumn<Blanks, Integer> colBlankNumber = new TableColumn<>("Blank Number");

    @FXML
    private TableColumn<Blanks, String> colAirlineName = new TableColumn<>("Airline");

    @FXML
    private TableColumn<Blanks, Integer> colAuditorsCoupons = new TableColumn<>("Auditors Coupon");
    @FXML
    private TableColumn<Blanks, Integer> colFlightCoupon = new TableColumn<>("Flight Coupon");

    @FXML
    private TableColumn<Blanks, Integer> colTravelAdvisorID = new TableColumn<>("TravelAdvisorID");

    @FXML
    private TableColumn<Blanks, Integer> colSysadminID = new TableColumn<>("Sysadmin ID");

    @FXML
    private Label blanksCreatedLabel;

    @FXML
    private Button createBlankButton;

    @FXML
    private Button removeBlankButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    void assignBlankClick(ActionEvent event) {

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
     * Handles the action event triggered by the create blank button.
     *
     * @param event the action event triggered by the create blank button
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
    @FXML
    void createBlankClick(ActionEvent event) throws IOException {
        try {
            // Load the "CreateBlank" FXML file
            Parent root = FXMLLoader.load(Main.class.getResource("CreateBlank.fxml"));
            // Create a new stage and set the scene to the loaded FXML file
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            // Show the new stage with the loaded FXML file
            newStage.show();
        } catch (Exception e) {
            // Print the stack trace if an error occurs
            e.printStackTrace();
        }
    }


    /**
     * Handles the event when the user clicks the "Remove Blank" button. This method displays a confirmation dialog asking
     * the user if they're sure they want to perform the action. If the user confirms the action, the method first deletes
     * all associated data (such as refunds and customer information) for the blank and then deletes the blank itself from
     * the database. If the user cancels the action, nothing happens.
     *
     * @param event The event that triggered this method.
     */
    @FXML
    void removeBlankButton(ActionEvent event) {
        // Display a confirmation dialog asking the user if they're sure they want to perform the action
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to perform this action?");

        Optional<ButtonType> result = alert.showAndWait();
        int ticket;

        // If the user confirms the action, delete all associated data for the blank and then delete the blank itself
        if (result.isPresent() && result.get() == ButtonType.OK) {
            PreparedStatement pst;
            try {
                if (Objects.equals(searchTextField.getText(), "")) {
                    // Do nothing
                } else {
                    pst = connection.prepareStatement("select * from Ticket where blank_id = " + searchTextField.getText());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        ticket = rs.getInt(1);

                        pst = connection.prepareStatement("DELETE FROM Refund where ticket_ID = " + ticket);
                        pst.executeUpdate();
                        pst = connection.prepareStatement("DELETE FROM Customer where ticket_ID = " + ticket);
                        pst.executeUpdate();
                        pst = connection.prepareStatement("DELETE FROM SaleInfo where ticket_ID = " + ticket);
                        pst.executeUpdate();
                        pst = connection.prepareStatement("DELETE FROM Ticket where ticket_ID = " + ticket);
                        pst.executeUpdate();
                    }
                    pst = connection.prepareStatement("select * from Blank where blank_id = " + searchTextField.getText());
                    rs = pst.executeQuery();

                    rs.next();

                    ticket = rs.getInt(1);
                    pst = connection.prepareStatement("DELETE FROM Blank where blank_id = " + ticket);
                    pst.executeUpdate();
                }
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
        } else {
            // If the user cancels the action, do nothing
        }
    }


    /**
     * Handles the event when the user clicks the search button. This method calls the displayTable() method to
     * refresh the TableView with the results of the search query.
     *
     * @param event The event that triggered this method.
     */
    @FXML
    void searchButtonClick(ActionEvent event) {
        // Call the displayTable() method to refresh the TableView with the results of the search query
        displayTable();
    }


    /**
     * Populates the TableView with data from the Blank table in the database.
     * Data is filtered by the value entered in the searchTextField.
     *
     */
    public void displayTable() {
        // Create an ObservableList to hold the data retrieved from the database
        ObservableList<Blanks> blankList = FXCollections.observableArrayList();

        try {
            // Create a PreparedStatement to execute a SQL query to retrieve data from the database
            PreparedStatement pst = connection.prepareStatement("select * from Blank where blank_id like '%" + searchTextField.getText() + "%'");

            // Execute the query and store the results in a ResultSet
            rs = pst.executeQuery();

            // Iterate through the ResultSet and add each row to the ObservableList
            while(rs.next()) {
                Integer blankID = rs.getInt("blank_id");
                Integer blankType = rs.getInt("blank_type");
                Long blankNumber = rs.getLong("blank_number");
                String airlineName = rs.getString("airline_name");
                Integer auditorsCoupons = rs.getInt("auditors_coupons");
                Integer flightCoupon = rs.getInt("flight_Coupon");
                Integer travelAdvisorID = rs.getInt("travel_advisor_ID");
                Integer sysAdminID = rs.getInt("sysadmin_ID");

                blankList.add(new Blanks(blankID, blankType, blankNumber, airlineName, auditorsCoupons, flightCoupon, travelAdvisorID, sysAdminID));
            }
        } catch (Exception e) {
            // Print an error message and stack trace if an exception occurs
            System.out.println("Error");
            e.printStackTrace();
        }
        // Set the items of the blankTable TableView to the ObservableList of data
        blankTable.setItems(blankList);
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
     * Initializes the controller after its root element has been completely processed.
     * This method sets up the TableView columns and connects to the database.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb  The resources used to localize the root object, or null if the root object was not localized.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // Print a message to the console to indicate that the controller is being initialized
        System.out.println("connect");

        // Connect to the database
        connectToDatabase();

        // Set up the TableView columns using PropertyValueFactory objects and add them to the TableView
        colBlankID.setCellValueFactory(new PropertyValueFactory<>("blankID"));
        blankTable.getColumns().add(colBlankID);
        colBlankType.setCellValueFactory(new PropertyValueFactory<>("blankType"));
        blankTable.getColumns().add(colBlankType);
        colBlankNumber.setCellValueFactory(new PropertyValueFactory<>("blankNumber"));
        blankTable.getColumns().add(colBlankNumber);
        colAirlineName.setCellValueFactory(new PropertyValueFactory<>("airlineName"));
        blankTable.getColumns().add(colAirlineName);
        colAuditorsCoupons.setCellValueFactory(new PropertyValueFactory<>("auditorsCoupons"));
        blankTable.getColumns().add(colAuditorsCoupons);
        colFlightCoupon.setCellValueFactory(new PropertyValueFactory<>("flightCoupon"));
        blankTable.getColumns().add(colFlightCoupon);
        colTravelAdvisorID.setCellValueFactory(new PropertyValueFactory<>("travelAdvisorID"));
        blankTable.getColumns().add(colTravelAdvisorID);
        colSysadminID.setCellValueFactory(new PropertyValueFactory<>("sysadminID"));
        blankTable.getColumns().add(colSysadminID);
    }


}

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

    @FXML
    void addTicketButton(ActionEvent event) {

    }

    @FXML
    void backButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManager.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void removeTicketButton(ActionEvent event) {

    }

    public void displayTable() {
        ObservableList<Tickets> ticketList = FXCollections.observableArrayList();



        try {
            PreparedStatement pst = connection.prepareStatement("select * from Ticket where ticket_ID like '%" + searchTextField.getText() + "%'");
            rs = pst.executeQuery();
            while(rs.next()) {
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

                ticketList.add(new Tickets(ticketID, valid, formattedDate, paymentType, paymentTotal, currencyID, blankID, refundStatus));
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        ticketTable.setItems(ticketList);
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

    @FXML
    void searchButtonClick(ActionEvent event) {
        System.out.println("search clicked");
        connectToDatabase();
        displayTable();
    }

    public String getTotalTickets() {
        int total = 0;
        try {
            PreparedStatement pst = connection.prepareStatement("select * from Ticket");
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.println("something");
                total++;
            }
        } catch (Exception e) {
            System.out.println("total ticket error");
            e.printStackTrace();
        }
        System.out.println(total);
        return String.valueOf(total);
    }

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
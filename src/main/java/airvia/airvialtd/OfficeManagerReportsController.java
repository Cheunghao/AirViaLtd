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

public class OfficeManagerReportsController implements Initializable {
    private Connection connection;
    private Stage stage;
    private Scene scene;
    private ResultSet rs;

    @FXML
    private Button backButton;



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
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private ChoiceBox<?> timeChoiceBox;

    @FXML
    private Label totalReportsLabel;
    @FXML
    void createReportButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("CreateReportTravelAdvisor.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

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
    void removeBlankButtonClick(ActionEvent event) {

    }

    @FXML
    void searchButtonClick(ActionEvent event) {
        displayTable();

    }

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

                reportList.add(new Reports(reportID, paymentType, flightType, commissionRate, saleTotal, currencyID, travelAdvisorID, ticketID));
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        reportTable.setItems(reportList);
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

    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("connect");
        connectToDatabase();


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
    }

}

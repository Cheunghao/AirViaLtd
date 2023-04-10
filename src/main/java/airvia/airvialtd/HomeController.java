package airvia.airvialtd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    Connection connection;

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
    private Button btn = new Button();

    @FXML
    private TextField tf = new TextField();

    @FXML
    void onClick(ActionEvent event) {
        System.out.println("click");
        ObservableList<Tickets> ticketList = FXCollections.observableArrayList();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk/in2018g22", "in2018g22_a", "dM8Sf9EB");
            System.out.println("Connected");

            PreparedStatement pst = connection.prepareStatement("select * from Ticket where ticket_ID like '%" + tf.getText() + "%'");
            ResultSet rs = pst.executeQuery();
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

    public void initialize(URL url, ResourceBundle rb) {

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


    }
}

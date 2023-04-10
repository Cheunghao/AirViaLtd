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
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    Connection connection;

    @FXML
    private TableColumn<Tickets, Integer> colTicketID = new TableColumn<>("Ticket ID");

    @FXML
    private TableColumn<Tickets, String> colValidityStatus = new TableColumn<>("validity Status");

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
                int id = rs.getInt("ticket_ID");
                System.out.println("ticket");
                String valid = rs.getString("validity_status");
                ticketList.add(new Tickets(id, valid));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("error");
        ticketTable.setItems(ticketList);
        System.out.println("error");
    }

    public void initialize(URL url, ResourceBundle rb) {

        colTicketID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ticketTable.getColumns().add(colTicketID);
        colValidityStatus.setCellValueFactory(new PropertyValueFactory<>("validityStatus"));
        ticketTable.getColumns().add(colValidityStatus);


    }
}

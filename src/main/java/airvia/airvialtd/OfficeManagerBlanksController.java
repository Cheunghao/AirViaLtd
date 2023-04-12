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
    private TableColumn<Blanks, ?> colAirlineName = new TableColumn<>("Airline");

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

    @FXML
    void backButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("OfficeManager.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void createBlankClick(ActionEvent event) throws IOException {
        try {
            System.out.println("buttonclick");

            Parent root = FXMLLoader.load(Main.class.getResource("CreateBlank.fxml"));
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void removeBlankButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Are you sure you want to perform this action?");

        Optional<ButtonType> result = alert.showAndWait();
        int ticket;

        if (result.isPresent() && result.get() == ButtonType.OK) {
            PreparedStatement pst;
            try {
                if (Objects.equals(searchTextField.getText(), "")) {
                } else {

                    pst = connection.prepareStatement("select * from Ticket where blank_id = "+ searchTextField.getText());
                    rs = pst.executeQuery();
                     while(rs.next()) {

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
                    pst = connection.prepareStatement("select * from Blank where blank_id =" +searchTextField.getText());
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
        }
    }

    @FXML
    void searchButtonClick(ActionEvent event) {
        displayTable();

    }

    public void displayTable() {
        ObservableList<Blanks> blankList = FXCollections.observableArrayList();

        try {
            PreparedStatement pst = connection.prepareStatement("select * from Blank where blank_id like '%" + searchTextField.getText() + "%'");
            rs = pst.executeQuery();
            while(rs.next()) {
                Integer blankID = rs.getInt("blank_id");
                Integer blankType = rs.getInt("blank_type");
                Integer blankNumber = rs.getInt("blank_number");
                String airlineName = rs.getString("airline_name");
                Integer auditorsCoupons = rs.getInt("auditors_coupons");
                Integer flightCoupon = rs.getInt("flight_Coupon");
                Integer travelAdvisorID = rs.getInt("travel_advisor_ID");
                Integer sysAdminID = rs.getInt("sysadmin_ID");

                blankList.add(new Blanks(blankID, blankType, blankNumber, airlineName, auditorsCoupons, flightCoupon, travelAdvisorID, sysAdminID));
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        blankTable.setItems(blankList);
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

package airvia.airvialtd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class TravelAdvisorController {

    @FXML
    private AnchorPane ClientPane;

    @FXML
    private AnchorPane HomePane;

    @FXML
    private Rectangle RectangleBlock;

    @FXML
    private AnchorPane ReportSalesPane;

    @FXML
    private AnchorPane SettingPane;

    @FXML
    private AnchorPane TicketPane;

    @FXML
    private Button btnClient;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnReportSales;

    @FXML
    private Button btnSetting;

    @FXML
    private Button btnTicket;

    @FXML
    void ClientOnClick(ActionEvent event) {
        RectangleBlock.setVisible(false);
        ClientPane.setVisible(true);
        ReportSalesPane.setVisible(false);
        SettingPane.setVisible(false);
        HomePane.setVisible(false);
        TicketPane.setVisible(false);
    }

    @FXML
    void ReportSalesOnClick(ActionEvent event) {
        RectangleBlock.setVisible(false);
        ClientPane.setVisible(false);
        ReportSalesPane.setVisible(true);
        SettingPane.setVisible(false);
        HomePane.setVisible(false);
        TicketPane.setVisible(false);

    }

    @FXML
    void SettingOnClick(ActionEvent event) {
        RectangleBlock.setVisible(false);
        ClientPane.setVisible(false);
        ReportSalesPane.setVisible(false);
        SettingPane.setVisible(true);
        HomePane.setVisible(false);
        TicketPane.setVisible(false);

    }

    @FXML
    void homeOnClick(ActionEvent event) {
        RectangleBlock.setVisible(false);
        ClientPane.setVisible(false);
        ReportSalesPane.setVisible(false);
        SettingPane.setVisible(false);
        HomePane.setVisible(true);
        TicketPane.setVisible(false);

    }

    @FXML
    void ticketOnClick(ActionEvent event) {
        RectangleBlock.setVisible(false);
        ClientPane.setVisible(false);
        ReportSalesPane.setVisible(false);
        SettingPane.setVisible(false);
        HomePane.setVisible(false);
        TicketPane.setVisible(true);

    }

}

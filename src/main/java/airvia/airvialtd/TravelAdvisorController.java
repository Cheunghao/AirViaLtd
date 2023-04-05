package airvia.airvialtd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TravelAdvisorController {


    @FXML
    private HBox HomeBox;

    @FXML
    private HBox TicketBox;

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
    void ticketOnClick(ActionEvent event) {
        HomeBox.setVisible(false);
        TicketBox.setVisible(true);
    }

    @FXML
    public void homeOnClick(ActionEvent event) {
        HomeBox.setVisible(true);
        TicketBox.setVisible(false);
    }

}

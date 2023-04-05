module airvia.airvialtd {
    requires javafx.controls;
    requires javafx.fxml;


    opens airvia.airvialtd to javafx.fxml;
    exports airvia.airvialtd;
}
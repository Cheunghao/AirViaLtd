module airvia.airvialtd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens airvia.airvialtd to javafx.fxml;
    exports airvia.airvialtd;
}
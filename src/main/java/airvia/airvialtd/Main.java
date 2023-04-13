package airvia.airvialtd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    /**
     * Starts the JavaFX application by loading the Login.fxml file and displaying it in a new stage.
     *
     * @param stage the primary stage for this application
     * @throws IOException if an I/O error occurs while loading the FXML file
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("AirVia Ltd");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * The main method that launches the JavaFX application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }

}
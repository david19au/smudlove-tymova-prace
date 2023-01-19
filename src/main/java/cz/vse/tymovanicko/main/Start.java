package cz.vse.tymovanicko.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Třída  Start je hlavní třídou projektu,
 * který představuje správu týmů
 *
 * @author    ?
 * @version   ?
 */
public class Start extends Application {

    /**
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args parametry příkazového řádku
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Spouštění v okně
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        loader.load();
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);
        stage.setTitle("Týmováníčko");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.jpg"))));
        stage.show();
    }
}

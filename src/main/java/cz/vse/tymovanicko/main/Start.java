package cz.vse.tymovanicko.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        stage.show();
    }
}
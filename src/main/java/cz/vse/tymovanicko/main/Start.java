package cz.vse.tymovanicko.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Třída Start je hlavní třídou projektu,
 * který představuje správu týmů.
 * Třída by default zapíná své grafické rozhraní, textové rozhraní k dispozici není.
 *
 * @author Magdalena Hájková (hajm17), Trong Dat Luu (luut02), Jakub Kafka (kafj03), Adam Schindler (scha28), Hana Žahourová (zahh00)
 * @version 1.0.0
 */
public class Start extends Application {

    /**
     * Pravá metoda main, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku / parametry spuštění
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Metoda start zapne program Týmováníčko v grafickém prostředí.
     * Použije se soubor login.fxml, který obsahuje, kde se nachází jaké prvky, v tomto případě prvky pro přihlašování a
     * odhlašování, vytvoří a nastaví se nová scene, zároveň se nastaví i jméno a ikonka okna.
     *
     * @param stage stage GUI
     * @throws Exception vyhodí případné chyby
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/login.fxml"));
        loader.load();
        Scene scene = new Scene(loader.getRoot());
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Přihlášení");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("other/logo.jpg"))));
        stage.show();
    }


}

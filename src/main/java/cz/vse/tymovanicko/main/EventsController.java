package cz.vse.tymovanicko.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Třída  EventController je hlavní třídou okna,
 * které představuje události
 *
 * @author ?
 * @version ?
 */
public class EventsController {

    @FXML
    private ImageView nastaveni;
    @FXML
    private ImageView chat;
    @FXML
    private ImageView zpet;
    private Stage stage;
    private Scene scene;

    @FXML
    private void zpracujNaChat(MouseEvent mouseEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chat.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Chat");
        stage.show();
    }

    /**
     * Metoda, která nechá ztmavnout chat, když na něj najede myš
     *
     * @param mouseEvent
     */
    @FXML
    private void ztmavniChat(MouseEvent mouseEvent) {
        ColorAdjust ztmavnuti = new ColorAdjust();
        ztmavnuti.setBrightness(-0.5);
        chat.setEffect(ztmavnuti);
    }

    /**
     * Metoda, která nechá zesvětlat chat, když myš odejde
     *
     * @param mouseEvent
     */
    @FXML
    private void zesvetlejChat(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        chat.setEffect(zesvetleni);
    }

    /**
     * Metoda, která nechá ztmavnout nastavení, když na něj najede myš
     *
     * @param mouseEvent
     */
    @FXML
    private void ztmavniNastaveni(MouseEvent mouseEvent) {
        ColorAdjust ztmavnuti = new ColorAdjust();
        ztmavnuti.setBrightness(-0.5);
        nastaveni.setEffect(ztmavnuti);
    }

    /**
     * Metoda, která nechá zesvětlat nastavení, když myš odejde
     *
     * @param mouseEvent
     */
    @FXML
    private void zesvetlejNastaveni(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        nastaveni.setEffect(zesvetleni);
    }

    /**
     * Metoda, která změní obrazovku na nastavení profilu
     *
     * @param mouseEvent
     * @throws Exception
     */
    @FXML
    private void zpracujNaNastaveni(MouseEvent mouseEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("profile_settings.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Nastavení profilu");
        stage.show();
    }

    /**
     * Metoda, která se vrací na domovskou obrazovku
     *
     * @param mouseEvent
     * @throws Exception
     */
    @FXML
    public void zpracujZpatky(MouseEvent mouseEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko");
        stage.show();
    }

    /**
     * Metoda, která nechá ztmavnout šipku, když na ní najede myš
     *
     * @param mouseEvent
     */
    @FXML
    public void ztmavni(MouseEvent mouseEvent) {
        ColorAdjust ztmavnuti = new ColorAdjust();
        ztmavnuti.setBrightness(-0.5);
        zpet.setEffect(ztmavnuti);
    }

    /**
     * Metoda, která nechá zesvětlat šipku, když myš odejde
     *
     * @param mouseEvent
     */
    @FXML
    public void zesvetlej(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        zpet.setEffect(zesvetleni);
    }
}

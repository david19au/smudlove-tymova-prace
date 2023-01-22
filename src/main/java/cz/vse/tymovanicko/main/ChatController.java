package cz.vse.tymovanicko.main;

import cz.vse.tymovanicko.logika.Tymovanicko;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Třída  ChatController je hlavní třídou okna,
 * které představuje chat
 *
 * @author ?
 * @version ?
 */
public class ChatController {


    // datové atributy
    @FXML
    private TextField vstupZprava;

    @FXML
    private TextArea zpravyChatu;
    @FXML
    private ImageView kalendar;
    @FXML
    private ImageView nastaveni;
    private Stage stage;
    private Scene scene;
    @FXML
    private ImageView zpet;

    private static final SimpleDateFormat datumCas = new SimpleDateFormat("dd.MM.yyyy, HH:mm");

    Date date = new Date();

    public ChatController() {
        Platform.runLater(this::nactiStareZpravy);
    }

    public void nactiStareZpravy() {

        ArrayList<String> zpravy = Tymovanicko.TYMOVANICKO.getChatLog().getZpravy();
        for (String zprava : zpravy) {
            zpravyChatu.appendText(zprava + "\n");
        }
    }


    @FXML
    private void zpracujPoslani(ActionEvent actionEvent) throws IOException {
        String zprava = vstupZprava.getText();
        if (zprava.isBlank())
            return;
        Tymovanicko.TYMOVANICKO.getChat().pridatZpravu("[" + datumCas.format(date) + "] " + Tymovanicko.TYMOVANICKO.getJmeno(Tymovanicko.TYMOVANICKO.getId()) + ": " + zprava);
        zpravyChatu.appendText("[" + datumCas.format(date) + "] " + Tymovanicko.TYMOVANICKO.getJmeno(Tymovanicko.TYMOVANICKO.getId()) + ": " + zprava + "\n");
        vstupZprava.setText("");
    }

    /**
     * Metoda, která nechá ztmavnout kalendář, když na něj najede myš
     *
     * @param mouseEvent
     */
    @FXML
    private void ztmavniKalendar(MouseEvent mouseEvent) {
        ColorAdjust ztmavnuti = new ColorAdjust();
        ztmavnuti.setBrightness(-0.5);
        kalendar.setEffect(ztmavnuti);
    }

    /**
     * Metoda, která nechá zesvětlat kalendář, když myš odejde
     *
     * @param mouseEvent
     */
    @FXML
    private void zesvetlejKalendar(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        kalendar.setEffect(zesvetleni);
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
     * Metoda, která se vrací na domovskou obrazovku
     *
     * @param mouseEvent
     * @throws Exception
     */
    @FXML
    private void zpracujZpatky(MouseEvent mouseEvent) throws Exception {
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
    private void ztmavni(MouseEvent mouseEvent) {
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
    private void zesvetlej(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        zpet.setEffect(zesvetleni);
    }
}

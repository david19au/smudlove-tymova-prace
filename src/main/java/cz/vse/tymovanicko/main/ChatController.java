package cz.vse.tymovanicko.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import cz.vse.tymovanicko.logika.ChatLog;
import cz.vse.tymovanicko.logika.Tymovanicko;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Třída ChatController je hlavní třídou okna,
 * které představuje chat
 *
 * @author Magdalena Hájková (hajm17), Trong Dat Luu (luut02), Jakub Kafka (kafj03), Adam Schindler (scha28), Hana Žahourová (zahh00)
 * @version 1.0.0
 */
public class ChatController {

    private static final SimpleDateFormat datumCas = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
    Date date = new Date();
    // datové atributy
    @FXML
    private TextField vstupZprava;
    @FXML
    private Label home;
    @FXML
    private TextArea zpravyChatu;
    @FXML
    private ImageView udalosti;
    @FXML
    private ImageView nastaveni;
    private Stage stage;
    private Scene scene;
    @FXML
    private ImageView zpet;

    /**
     * Konstruktor který načítá staré zprávy.
     */
    public ChatController() {
        Platform.runLater(this::nactiStareZpravy);
    }

    /**
     * Inicializační metoda, která pomáhá aktualizovat okno aplikace
     */
    @FXML
    private void initialize() {
        Platform.runLater(() -> vstupZprava.requestFocus());
    }

    /**
     * Tato metoda načítá zprávy z JSON souboru chat.json.
     */
    public void nactiStareZpravy() {
        try (Reader reader = new FileReader("data/chat.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(jsonElement);
            Tymovanicko.TYMOVANICKO.setChatLog(gson.fromJson(jsonInString, ChatLog.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> zpravy = Tymovanicko.TYMOVANICKO.getChatLog().getZpravy();
        for (String zprava : zpravy) {
            zpravyChatu.appendText(zprava + "\n");
        }
    }

    /**
     * Tato metoda zpracovává poslání zpravy do chatu
     * s časem poslání této zprávy.
     *
     * @param actionEvent
     * @throws IOException
     */
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
        udalosti.setEffect(ztmavnuti);
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
        udalosti.setEffect(zesvetleni);
    }

    /**
     * Metoda, která změní obrazovku na nastavení profilu
     *
     * @param mouseEvent
     * @throws Exception
     */
    @FXML
    private void zpracujNaNastaveni(MouseEvent mouseEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/profileSettings.fxml")));
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/home.fxml")));
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

    /**
     * Metoda, která změní obrazovku na události
     *
     * @param mouseEvent
     * @throws Exception
     */
    @FXML
    private void zpracujNaUdalosti(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/events.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Události");
        stage.show();
    }

    /**
     * Metoda, která změní obrazovku na home
     *
     * @param mouseEvent
     * @throws Exception
     */
    @FXML
    private void zpracujNaHome(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/home.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko");
        stage.show();
    }

    /**
     * Metoda, která nechá ztmavnout "Týmováníčko", když na něj najede myš
     *
     * @param mouseEvent
     */
    @FXML
    private void ztmavniHome(MouseEvent mouseEvent) {
        ColorAdjust ztmavnuti = new ColorAdjust();
        ztmavnuti.setBrightness(-0.5);
        home.setEffect(ztmavnuti);
    }

    /**
     * Metoda, která nechá zesvětlat "Týmováníčko", když myš odejde
     *
     * @param mouseEvent
     */
    @FXML
    private void zesvetlejHome(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        home.setEffect(zesvetleni);
    }
}

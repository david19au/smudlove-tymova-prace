package cz.vse.tymovanicko.main;

import cz.vse.tymovanicko.logika.Tymovanicko;
import cz.vse.tymovanicko.logika.Udalost;
import cz.vse.tymovanicko.logika.Uzivatel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

/**
 * Třída  HomeController je hlavní třídou okna,
 * které představuje domovskou obrazovku
 *
 * @author ?
 * @version ?
 */
public class HomeController {

    // datové atributy
    @FXML
    private ImageView udalosti;
    @FXML
    private ImageView chat;
    @FXML
    private ImageView nastaveni;
    private Stage stage;
    private Scene scene;
    @FXML
    private ListView panelClenu;

    @FXML
    private void initialize() {
        naplneniPaneluClenu();

        panelClenu.setCellFactory(uzivatelListView -> new ListCell<Uzivatel>() {
            @Override
            protected void updateItem(Uzivatel uzivatel, boolean empty) {
                super.updateItem(uzivatel, empty);
                if (!empty) {
                    setText(uzivatel.getKrestniJmeno() + " " + uzivatel.getPrijmeni());
                } else {
                    setText(null);
                }
            }
        });
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/profile_settings.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Nastavení profilu");
        stage.show();
    }

    /**
     * Metoda, která změní obrazovku na chat
     *
     * @param mouseEvent
     * @throws Exception
     */
    @FXML
    private void zpracujNaChat(MouseEvent mouseEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/chat.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Chat");
        stage.show();
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

    private void naplneniPaneluClenu() {
        panelClenu.getItems().clear();
        Collection<Uzivatel> clenove = Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().getUzivatele();
        panelClenu.getItems().addAll(clenove);
    }
}

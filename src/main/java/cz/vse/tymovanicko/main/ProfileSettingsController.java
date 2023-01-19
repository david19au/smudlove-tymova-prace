package cz.vse.tymovanicko.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Třída  ProfileSettingsController je hlavní třídou okna,
 * které představuje obrazovku pro úpravu profilu
 *
 * @author    ?
 * @version   ?
 */
public class ProfileSettingsController {

    // datové atributy
    @FXML
    private Button zmenTym;
    @FXML
    private Button zmenHeslo;
    @FXML
    private Button odhlas;
    @FXML
    private Button uloz;
    @FXML
    private ImageView kalendar;
    @FXML
    private ImageView chat;

    @FXML
    private void zpracujUlozeni(ActionEvent actionEvent) {
    }

    @FXML
    private void zpracujOdhlaseni(ActionEvent actionEvent) {
    }

    @FXML
    private void zpracujNaZmenuHesla(ActionEvent actionEvent) {
    }

    @FXML
    private void zpracujNaZmenuTymu(ActionEvent actionEvent) {
    }

    @FXML
    private void ztmavniKalendar(MouseEvent mouseEvent) {
        ColorAdjust ztmavnuti = new ColorAdjust();
        ztmavnuti.setBrightness(-0.5);
        kalendar.setEffect(ztmavnuti);
    }

    @FXML
    private void zesvetlejKalendar(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        kalendar.setEffect(zesvetleni);
    }

    @FXML
    private void ztmavniChat(MouseEvent mouseEvent) {
        ColorAdjust ztmavnuti = new ColorAdjust();
        ztmavnuti.setBrightness(-0.5);
        chat.setEffect(ztmavnuti);
    }

    @FXML
    private void zesvetlejChat(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        chat.setEffect(zesvetleni);
    }
}

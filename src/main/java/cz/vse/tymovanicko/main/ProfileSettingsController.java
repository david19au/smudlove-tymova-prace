package cz.vse.tymovanicko.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
}

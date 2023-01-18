package cz.vse.tymovanicko.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Třída  RegisterController je hlavní třídou okna,
 * které představuje registrační obrazovku
 *
 * @author    ?
 * @version   ?
 */
public class RegisterController {

    // datové atributy
    @FXML
    private Button zaregistruj;
    @FXML
    private ImageView zpet;

    @FXML
    private void zpracujZaregistrovani(ActionEvent actionEvent) {
    }

    @FXML
    private void ztmavni(MouseEvent mouseEvent) {
        ColorAdjust ztmavnuti = new ColorAdjust();
        ztmavnuti.setBrightness(-0.5);
        zpet.setEffect(ztmavnuti);
    }

    @FXML
    private void zesvetlej(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        zpet.setEffect(zesvetleni);
    }
}

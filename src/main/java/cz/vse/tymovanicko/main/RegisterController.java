package cz.vse.tymovanicko.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Třída  RegisterController je hlavní třídou okna,
 * které představuje registrační obrazovku
 *
 * @author ?
 * @version ?
 */
public class RegisterController {

    // datové atributy
    @FXML
    private Button zaregistruj;
    @FXML
    private ImageView zpet;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField email;
    @FXML
    private TextField jmeno;
    @FXML
    private PasswordField heslo;
    @FXML
    private PasswordField potvrzeniHesla;

    @FXML
    private void zpracujZaregistrovani(ActionEvent actionEvent) {
        String regexJmeno = ".+";
        Pattern patternJmeno = Pattern.compile(regexJmeno);
        String stringJmeno = jmeno.getCharacters().toString();
        Matcher jeValidniJmeno = patternJmeno.matcher(stringJmeno);

        if (jeValidniJmeno.matches() == true) {
            String regexEmail = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
            Pattern patternEmail = Pattern.compile(regexEmail);
            String stringEmail = email.getCharacters().toString();
            Matcher jeValidniEmail = patternEmail.matcher(stringEmail);

            if (jeValidniEmail.matches() == true) {
                String regexHeslo = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
                Pattern patternHeslo = Pattern.compile(regexHeslo);
                String stringHeslo = heslo.getCharacters().toString();
                Matcher jeValidniHeslo = patternHeslo.matcher(stringHeslo);

                if (jeValidniHeslo.matches() == true) {
                    if (stringHeslo.equals(potvrzeniHesla.getCharacters().toString())) {
                        System.out.println("Vse je validni");
                    } else System.out.println("Hesla se neshodují");
                } else System.out.println("Není validní heslo");
            } else System.out.println("Není validní email");
        } else System.out.println("Není validní jméno");
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

    @FXML
    private void zpracujZpatky(MouseEvent mouseEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

package cz.vse.tymovanicko.main;

import cz.vse.tymovanicko.logika.Tymovanicko;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Třída  LoginController je hlavní třídou okna,
 * které představuje přihlašovací obrazovku
 *
 * @author ?
 * @version ?
 */
public class LoginController {

    // datové atributy
    @FXML
    private Button prihlasit;
    @FXML
    private Button zaregistrovat;
    private Stage stage;
    private Scene scene;

    private Tymovanicko tymovanicko = new Tymovanicko();

    public LoginController() throws IOException {
    }

    @FXML
    private void zpracujPrihlaseni(ActionEvent actionEvent) throws Exception {
        // tady to hodit do ifu
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoda, která změní obrazovku na registrační formulář
     *
     * @param actionEvent
     * @throws Exception
     */
    @FXML
    private void zpracujNaZaregistrovani(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

package cz.vse.tymovanicko.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Třída  LoginController je hlavní třídou okna,
 * které představuje přihlašovací obrazovku
 *
 * @author    ?
 * @version   ?
 */
public class LoginController {

    // datové atributy
    @FXML
    private Button prihlasit;
    @FXML
    private Button zaregistrovat;
    private Stage stage;
    private Scene scene;

    @FXML
    private void zpracujPrihlaseni(ActionEvent actionEvent) throws Exception {
        // tady to hodit do ifu
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void zpracujNaZaregistrovani(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

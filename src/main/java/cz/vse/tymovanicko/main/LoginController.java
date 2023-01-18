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
    private Parent root;

    @FXML
    private void zpracujPrihlaseni(ActionEvent actionEvent) {

    }

    @FXML
    private void zpracujNaZaregistrovani(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

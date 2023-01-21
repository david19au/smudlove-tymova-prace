package cz.vse.tymovanicko.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.vse.tymovanicko.logika.Tymovanicko;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    private Tymovanicko tymovanicko = new Tymovanicko();

    public LoginController() throws IOException {
    }

    @FXML
    private void zpracujPrihlaseni(ActionEvent actionEvent) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String emaily = tymovanicko.getSeznamUzivatelu().emailyUzivatelu();
        String stringEmail = email.getCharacters().toString();
        if (emaily.contains("," + stringEmail + ",")) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home.fxml")));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);
            VBox dialogVbox = new VBox(20);
            dialogVbox.setAlignment(Pos.CENTER);
            dialogVbox.setStyle("-fx-background: #37598e;");
            final Text text = new Text("Účet se zadaným emailem neexistuje");
            text.setStyle("-fx-font: 14 arial;");
            text.setFill(Color.WHITE);
            Button button = new Button("OK");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    dialog.close();
                }
            });
            dialogVbox.getChildren().add(text);
            dialogVbox.getChildren().add(button);
            Scene dialogScene = new Scene(dialogVbox, 250, 100);
            dialog.setScene(dialogScene);
            dialog.setTitle("Týmováníčko");
            dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.jpg"))));
            dialog.show();
        }
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

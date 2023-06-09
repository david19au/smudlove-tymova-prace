package cz.vse.tymovanicko.main;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

/**
 * Třída LoginController je hlavní třídou okna,
 * které představuje přihlašovací obrazovku
 *
 * @author Magdalena Hájková (hajm17), Trong Dat Luu (luut02), Jakub Kafka (kafj03), Adam Schindler (scha28), Hana Žahourová (zahh00)
 * @version 1.0.0
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

    /**
     * Konstruktor
     */
    public LoginController() {
    }

    /**
     * Metoda, která zpracovává přihlášení,
     * když zadané udáje sedí s JSON souborem,
     * tak se okno přepne na home.
     *
     * @param actionEvent
     * @throws Exception
     */
    @FXML
    private void zpracujPrihlaseni(ActionEvent actionEvent) throws Exception {
        String emaily = Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().emailyUzivatelu();
        String stringEmail = email.getCharacters().toString();
        String stringHeslo = password.getCharacters().toString();
        if (emaily.contains("," + stringEmail + ",")) {
            String ulozeneHashnuteHeslo = Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().hesloUzivatele(stringEmail);
            if (BCrypt.checkpw(stringHeslo, ulozeneHashnuteHeslo)) {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/home.fxml")));
                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Týmováníčko");
                stage.show();
                Tymovanicko.TYMOVANICKO.setId(stringEmail);
            } else {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(stage);
                VBox dialogVbox = new VBox(20);
                dialogVbox.setAlignment(Pos.CENTER);
                dialogVbox.setStyle("-fx-background: #37598e;");
                HBox hBox = new HBox(20);
                hBox.setAlignment(Pos.CENTER);
                ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("other/x-mark-white.png")).toString());
                imageView.setFitHeight(50);
                imageView.setPreserveRatio(true);
                final Text text = new Text("Heslo není správně.");
                text.setStyle("-fx-font: 14 arial;");
                text.setFill(Color.WHITE);
                Button button = new Button("OK");
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        dialog.close();
                    }
                });
                hBox.getChildren().add(imageView);
                hBox.getChildren().add(text);
                dialogVbox.getChildren().add(hBox);
                dialogVbox.getChildren().add(button);
                Scene dialogScene = new Scene(dialogVbox, 230, 120);
                dialog.setScene(dialogScene);
                dialog.setTitle("Upozornění");
                dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("other/logo.jpg"))));
                dialog.show();
            }
        } else {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);
            VBox dialogVbox = new VBox(20);
            dialogVbox.setAlignment(Pos.CENTER);
            dialogVbox.setStyle("-fx-background: #37598e;");
            HBox hBox = new HBox(20);
            hBox.setAlignment(Pos.CENTER);
            ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("other/x-mark-white.png")).toString());
            imageView.setFitHeight(50);
            imageView.setPreserveRatio(true);
            final Text text = new Text("Účet se zadaným emailem neexistuje.");
            text.setStyle("-fx-font: 14 arial;");
            text.setFill(Color.WHITE);
            Button button = new Button("OK");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    dialog.close();
                }
            });
            hBox.getChildren().add(imageView);
            hBox.getChildren().add(text);
            dialogVbox.getChildren().add(hBox);
            dialogVbox.getChildren().add(button);
            Scene dialogScene = new Scene(dialogVbox, 330, 120);
            dialog.setScene(dialogScene);
            dialog.setTitle("Upozornění");
            dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("other/logo.jpg"))));
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/register.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Registrace");
        stage.show();
    }
}

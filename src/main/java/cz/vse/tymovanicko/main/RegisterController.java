package cz.vse.tymovanicko.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.vse.tymovanicko.logika.Tymovanicko;
import cz.vse.tymovanicko.logika.Uzivatel;
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
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Třída RegisterController je hlavní třídou okna,
 * které představuje registrační obrazovku
 *
 * @author Magdalena Hájková (hajm17), Trong Dat Luu (luut02), Jakub Kafka (kafj03), Adam Schindler (scha28), Hana Žahourová (zahh00)
 * @version 1.0.0
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
    private TextField prijmeni;

    /**
     * Konstruktor
     */
    public RegisterController() throws IOException {
    }

    /**
     * Metoda, která zpracovává zaregistrování.
     * Kontroluje se validnost jména, příjmení, emailu a hesla.
     * Kontroluje se, zda se hesla shodují.
     * Když je vše v pořádku, účet se založí a zapíše do JSONu.
     * Okno se přepne zpět na login.
     *
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    private void zpracujZaregistrovani(ActionEvent actionEvent) throws IOException {
        String regexJmeno = ".+";
        Pattern patternJmeno = Pattern.compile(regexJmeno);
        String stringJmeno = jmeno.getCharacters().toString();
        Matcher jeValidniJmeno = patternJmeno.matcher(stringJmeno);

        if (jeValidniJmeno.matches() == true) {
            String regexPrijmeni = ".+";
            Pattern patternPrijmeni = Pattern.compile(regexPrijmeni);
            String stringPrijmeni = prijmeni.getCharacters().toString();
            Matcher jeValidniPrijmeni = patternPrijmeni.matcher(stringPrijmeni);

            if (jeValidniPrijmeni.matches() == true) {
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
                            String hasnuteHeslo = BCrypt.hashpw(stringHeslo, BCrypt.gensalt());
                            // Gson builder pro lepší vzhled struktury JSONu
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            // Vytváří nového uživatele
                            Uzivatel uzivatel = new Uzivatel(stringEmail, stringJmeno, stringPrijmeni, hasnuteHeslo);
                            String emaily = Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().emailyUzivatelu();
                            if (emaily.contains("," + stringEmail + ",")) {
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
                                final Text text = new Text("Tento email je již zaregistrovaný.");
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
                                Scene dialogScene = new Scene(dialogVbox, 300, 120);
                                dialog.setScene(dialogScene);
                                dialog.setTitle("Upozornění");
                                dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("other/logo.jpg"))));
                                dialog.show();
                            } else {
                                // Vkládá nového uživatele a vytváří JSON s jeho zadanými hodnotami
                                if (Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().getPocetUzivatelu() == 0) {
                                    uzivatel.setRole("Trenér");
                                } else uzivatel.setRole("Člen");
                                Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().vlozUzivateleDoSeznamu(uzivatel);
                                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/uzivatele.json"))) {
                                    String json = gson.toJson(Tymovanicko.TYMOVANICKO.getSeznamUzivatelu());
                                    bufferedWriter.write(json);
                                    bufferedWriter.newLine();
                                    bufferedWriter.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                final Stage dialog = new Stage();
                                dialog.initModality(Modality.APPLICATION_MODAL);
                                dialog.initOwner(stage);
                                VBox dialogVbox = new VBox(20);
                                dialogVbox.setAlignment(Pos.CENTER);
                                dialogVbox.setStyle("-fx-background: #37598e;");
                                HBox hBox = new HBox(20);
                                hBox.setAlignment(Pos.CENTER);
                                ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("other/checkmark-white.png")).toString());
                                imageView.setFitHeight(50);
                                imageView.setPreserveRatio(true);
                                final Text text = new Text("Úspěšně jste se zaregistrovali!");
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
                                Scene dialogScene = new Scene(dialogVbox, 300, 120);
                                dialog.setScene(dialogScene);
                                dialog.setTitle("Týmováníčko");
                                dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("other/logo.jpg"))));
                                dialog.show();
                                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/login.fxml")));
                                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
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
                            final Text text = new Text("Hesla se neshodují.");
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
                            Scene dialogScene = new Scene(dialogVbox, 250, 120);
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
                        final Text text = new Text("Heslo musí obsahovat alespoň jedno písmeno, jedno číslo a minimálně osm znaků.");
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
                        Scene dialogScene = new Scene(dialogVbox, 620, 120);
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
                    final Text text = new Text("Není validní email.");
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
                    Scene dialogScene = new Scene(dialogVbox, 220, 120);
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
                final Text text = new Text("Není validní příjmení.");
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
                Scene dialogScene = new Scene(dialogVbox, 240, 120);
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
            final Text text = new Text("Není validní jméno.");
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
            Scene dialogScene = new Scene(dialogVbox, 220, 120);
            dialog.setScene(dialogScene);
            dialog.setTitle("Upozornění");
            dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("other/logo.jpg"))));
            dialog.show();
        }
    }

    /**
     * Metoda, která nechá ztmavnout šipku, když na ní najede myš
     *
     * @param mouseEvent
     */
    @FXML
    private void ztmavni(MouseEvent mouseEvent) {
        ColorAdjust ztmavnuti = new ColorAdjust();
        ztmavnuti.setBrightness(-0.5);
        zpet.setEffect(ztmavnuti);
    }

    /**
     * Metoda, která nechá zesvětlat šipku, když myš odejde
     *
     * @param mouseEvent
     */
    @FXML
    private void zesvetlej(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        zpet.setEffect(zesvetleni);
    }

    /**
     * Metoda, která vrací obrazovku zpět na přihlašovací formulář
     *
     * @param mouseEvent
     * @throws Exception
     */
    @FXML
    private void zpracujZpatky(MouseEvent mouseEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/login.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Přihlášení");
        stage.show();
    }
}

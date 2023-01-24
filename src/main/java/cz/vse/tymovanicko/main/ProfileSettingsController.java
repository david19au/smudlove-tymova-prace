package cz.vse.tymovanicko.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.vse.tymovanicko.logika.Tymovanicko;
import cz.vse.tymovanicko.logika.Uzivatel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
 * Třída  ProfileSettingsController je hlavní třídou okna,
 * které představuje obrazovku pro úpravu profilu
 *
 * @author ?
 * @version ?
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
    private ImageView udalosti;
    @FXML
    private ImageView chat;
    private Stage stage;
    private Scene scene;
    @FXML
    private ImageView zpet;
    @FXML
    private TextField jmeno;
    @FXML
    private TextField prijmeni;
    @FXML
    private TextField email;
    @FXML
    private PasswordField heslo;
    @FXML
    private Label home;
    @FXML
    private TextField role;

    /**
     * Inicializační metoda, která pomáhá aktualizovat okno aplikace
     */
    @FXML
    private void initialize() {
        for (Uzivatel uzivatel : Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().getUzivatele()) {
            if (uzivatel.getEmail().equals(Tymovanicko.TYMOVANICKO.getId())) {
                jmeno.setText(uzivatel.getKrestniJmeno());
                prijmeni.setText(uzivatel.getPrijmeni());
                email.setText(uzivatel.getEmail());
                role.setText(uzivatel.getRole());
            }
        }
        Platform.runLater(() -> uloz.requestFocus());
    }

    /**
     * Metoda, která zpracovává uložení nových údajů.
     * Kontroluje se validnost a neexistence emailu v JSONu.
     *
     * @param actionEvent
     */
    @FXML
    private void zpracujUlozeni(ActionEvent actionEvent) {
        String stringJmeno = jmeno.getCharacters().toString();
        String stringPrijmeni = prijmeni.getCharacters().toString();
        String stringEmail = email.getCharacters().toString();
        String stringHeslo = heslo.getCharacters().toString();

        String regexJmeno = ".+";
        Pattern patternJmeno = Pattern.compile(regexJmeno);
        Matcher jeValidniJmeno = patternJmeno.matcher(stringJmeno);
        if (jeValidniJmeno.matches()) {
            String regexPrijmeni = ".+";
            Pattern patternPrijmeni = Pattern.compile(regexPrijmeni);
            Matcher jeValidniPrijmeni = patternPrijmeni.matcher(stringPrijmeni);
            if (jeValidniPrijmeni.matches()) {
                String regexEmail = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
                Pattern patternEmail = Pattern.compile(regexEmail);
                Matcher jeValidniEmail = patternEmail.matcher(stringEmail);
                if (jeValidniEmail.matches()) {
                    String aktualniHeslo = null;
                    for (Uzivatel uzivatel : Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().getUzivatele()) {
                        if (uzivatel.getEmail().equals(Tymovanicko.TYMOVANICKO.getId())) {
                            aktualniHeslo = uzivatel.getHeslo();
                        }
                    }
                    if (BCrypt.checkpw(stringHeslo, aktualniHeslo)) {
                        // Gson builder pro lepší vzhled struktury JSONu
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        String emaily = Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().emailyUzivatelu();
                        if (emaily.contains("," + stringEmail + ",")) {
                            if (stringEmail.equals(Tymovanicko.TYMOVANICKO.getId())) {
                                for (Uzivatel uzivatel : Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().getUzivatele()) {
                                    if (uzivatel.getEmail().equals(Tymovanicko.TYMOVANICKO.getId())) {
                                        uzivatel.setKrestniJmeno(stringJmeno);
                                        uzivatel.setPrijmeni(stringPrijmeni);
                                        uzivatel.setEmail(stringEmail);
                                        Tymovanicko.TYMOVANICKO.setId(stringEmail);
                                    }
                                }
                                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("target/uzivatele.json"))) {
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
                                final Text text = new Text("Údaje úspěšně změněny!");
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
                                Scene dialogScene = new Scene(dialogVbox, 280, 120);
                                dialog.setScene(dialogScene);
                                dialog.setTitle("Týmováníčko");
                                dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("other/logo.jpg"))));
                                dialog.show();
                            } else {
                                final Stage dialog = new Stage();
                                dialog.initModality(Modality.APPLICATION_MODAL);
                                dialog.initOwner(stage);
                                VBox dialogVbox = new VBox(20);
                                dialogVbox.setAlignment(Pos.CENTER);
                                dialogVbox.setStyle("-fx-background: #37598e;");
                                HBox hBox = new HBox(20);
                                hBox.setAlignment(Pos.CENTER);
                                ImageView imageView = new ImageView(getClass().getResource("other/x-mark-white.png").toString());
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
                                Scene dialogScene = new Scene(dialogVbox, 310, 120);
                                dialog.setScene(dialogScene);
                                dialog.setTitle("Upozornění");
                                dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("other/logo.jpg"))));
                                dialog.show();
                            }
                        } else {
                            for (Uzivatel uzivatel : Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().getUzivatele()) {
                                if (uzivatel.getEmail().equals(Tymovanicko.TYMOVANICKO.getId())) {
                                    uzivatel.setKrestniJmeno(stringJmeno);
                                    uzivatel.setPrijmeni(stringPrijmeni);
                                    uzivatel.setEmail(stringEmail);
                                    Tymovanicko.TYMOVANICKO.setId(stringEmail);
                                }
                            }
                            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("target/uzivatele.json"))) {
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
                            final Text text = new Text("Údaje úspěšně změněny!");
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
                            Scene dialogScene = new Scene(dialogVbox, 280, 120);
                            dialog.setScene(dialogScene);
                            dialog.setTitle("Týmováníčko");
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
                        final Text text = new Text("Neplatné heslo.");
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
                        Scene dialogScene = new Scene(dialogVbox, 200, 120);
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
     * Metoda, která zpracovává odhlášení z aplikace.
     *
     * @param actionEvent
     * @throws Exception
     */
    @FXML
    private void zpracujOdhlaseni(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/login.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Přihlášení");
        stage.show();
    }

    /**
     * Metoda, která změní obrazovku na změnu hesla
     *
     * @param actionEvent
     * @throws Exception
     */
    @FXML
    private void zpracujNaZmenuHesla(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/changePassword.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Změna hesla");
        stage.show();
    }

    /**
     * Metoda, která nechá ztmavnout kalendář, když na něj najede myš
     *
     * @param mouseEvent
     */
    @FXML
    private void ztmavniKalendar(MouseEvent mouseEvent) {
        ColorAdjust ztmavnuti = new ColorAdjust();
        ztmavnuti.setBrightness(-0.5);
        udalosti.setEffect(ztmavnuti);
    }

    /**
     * Metoda, která nechá zesvětlat kalendář, když myš odejde
     *
     * @param mouseEvent
     */
    @FXML
    private void zesvetlejKalendar(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        udalosti.setEffect(zesvetleni);
    }

    /**
     * Metoda, která nechá ztmavnout chat, když na něj najede myš
     *
     * @param mouseEvent
     */
    @FXML
    private void ztmavniChat(MouseEvent mouseEvent) {
        ColorAdjust ztmavnuti = new ColorAdjust();
        ztmavnuti.setBrightness(-0.5);
        chat.setEffect(ztmavnuti);
    }

    /**
     * Metoda, která nechá zesvětlat chat, když myš odejde
     *
     * @param mouseEvent
     */
    @FXML
    private void zesvetlejChat(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        chat.setEffect(zesvetleni);
    }

    /**
     * Metoda, která nechá zesvětlat chat, když myš odejde
     *
     * @param mouseEvent
     */
    @FXML
    private void zpracujNaChat(MouseEvent mouseEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/chat.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Chat");
        stage.show();
    }

    /**
     * Metoda, která se vrací na domovskou obrazovku
     *
     * @param mouseEvent
     * @throws Exception
     */
    @FXML
    public void zpracujZpatky(MouseEvent mouseEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/home.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko");
        stage.show();
    }

    /**
     * Metoda, která nechá ztmavnout šipku, když na ní najede myš
     *
     * @param mouseEvent
     */
    @FXML
    public void ztmavni(MouseEvent mouseEvent) {
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
    public void zesvetlej(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        zpet.setEffect(zesvetleni);
    }

    /**
     * Metoda, která změní obrazovku na události
     *
     * @param mouseEvent
     * @throws Exception
     */
    @FXML
    private void zpracujNaUdalosti(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/events.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Události");
        stage.show();
    }

    /**
     * Metoda, která změní obrazovku na home
     *
     * @param mouseEvent
     * @throws Exception
     */
    @FXML
    private void zpracujNaHome(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/home.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko");
        stage.show();
    }

    /**
     * Metoda, která nechá ztmavnout "Týmováníčko", když na něj najede myš
     *
     * @param mouseEvent
     */
    @FXML
    private void ztmavniHome(MouseEvent mouseEvent) {
        ColorAdjust ztmavnuti = new ColorAdjust();
        ztmavnuti.setBrightness(-0.5);
        home.setEffect(ztmavnuti);
    }

    /**
     * Metoda, která nechá zesvětlat "Týmováníčko", když myš odejde
     *
     * @param mouseEvent
     */
    @FXML
    private void zesvetlejHome(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        home.setEffect(zesvetleni);
    }
}

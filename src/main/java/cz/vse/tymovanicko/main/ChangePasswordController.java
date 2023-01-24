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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
 * Třída  ChangePasswordController je hlavní třídou okna,
 * které představuje obrazovku pro změnu hesla
 *
 * @author ?
 * @version ?
 */
public class ChangePasswordController {

    // datové atributy
    @FXML
    private Label home;
    @FXML
    private Button zmenHeslo;
    @FXML
    private ImageView zpet;
    private Stage stage;
    private Scene scene;
    @FXML
    private ImageView udalosti;
    @FXML
    private ImageView chat;
    @FXML
    private PasswordField stareHeslo;
    @FXML
    private PasswordField noveHeslo;
    @FXML
    private PasswordField noveHesloZnovu;

    /**
     * Konstruktor
     */
    public ChangePasswordController() {
    }

    /**
     * Tato metoda kontroluje zda je nové heslo validní,
     * zda se shodují nové hesla,
     * zda bylo zadáno správné staré heslo,
     * když bylo vše zadáno správně, proběhně změna hesla.
     *
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    private void zpracujZmenuHesla(ActionEvent actionEvent) throws IOException {
        String stare = stareHeslo.getCharacters().toString();
        String nove = noveHeslo.getCharacters().toString();
        String noveZnovu = noveHesloZnovu.getCharacters().toString();
        String ulozeneHashnuteHeslo = Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().hesloUzivatele(Tymovanicko.TYMOVANICKO.getId());
        String noveHasnuteHeslo = BCrypt.hashpw(nove, BCrypt.gensalt());
        if (BCrypt.checkpw(stare, ulozeneHashnuteHeslo)) {
            String regexHeslo = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
            Pattern patternHeslo = Pattern.compile(regexHeslo);
            Matcher jeValidniHeslo = patternHeslo.matcher(nove);
            if (jeValidniHeslo.matches() == true) {
                if (nove.equals(noveZnovu)) {
                    // Gson builder pro lepší vzhled struktury JSONu
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    for (Uzivatel uzivatel : Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().getUzivatele()) {
                        if (uzivatel.getEmail().equals(Tymovanicko.TYMOVANICKO.getId())) {
                            uzivatel.setHeslo(noveHasnuteHeslo);
                            final Stage dialog = new Stage();
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            dialog.initOwner(stage);
                            VBox dialogVbox = new VBox(20);
                            dialogVbox.setAlignment(Pos.CENTER);
                            dialogVbox.setStyle("-fx-background: #37598e;");
                            HBox hBox = new HBox(20);
                            hBox.setAlignment(Pos.CENTER);
                            ImageView imageView = new ImageView(getClass().getResource("other/checkmark-white.png").toString());
                            imageView.setFitHeight(50);
                            imageView.setPreserveRatio(true);
                            final Text text = new Text("Heslo bylo úspěšně změněno!");
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
                            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/profileSettings.fxml")));
                            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("Týmováníčko - Nastavení profilu");
                            stage.show();
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
                ImageView imageView = new ImageView(getClass().getResource("other/x-mark-white.png").toString());
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
            ImageView imageView = new ImageView(getClass().getResource("other/x-mark-white.png").toString());
            imageView.setFitHeight(50);
            imageView.setPreserveRatio(true);
            final Text text = new Text("Staré heslo se neshoduje.");
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
            dialog.setTitle("Upozornění");
            dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("other/logo.jpg"))));
            dialog.show();
        }
    }

    /**
     * Metoda, která vrací obrazovku zpět na profilové nastavení
     *
     * @param mouseEvent
     * @throws Exception
     */
    @FXML
    private void zpracujZpatky(MouseEvent mouseEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/profileSettings.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Nastavení profilu");
        stage.show();
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
     * Metoda, která změní obrazovku na chat
     *
     * @param mouseEvent
     * @throws Exception
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

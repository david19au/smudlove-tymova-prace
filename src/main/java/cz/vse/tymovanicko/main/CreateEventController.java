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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import javafx.util.converter.LocalDateStringConverter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Třída CreateEventController je hlavní třídou okna,
 * které představuje okno pro vytvoření události
 *
 * @author Magdalena Hájková (hajm17), Trong Dat Luu (luut02), Jakub Kafka (kafj03), Adam Schindler (scha28), Hana Žahourová (zahh00)
 * @version 1.0.0
 */
public class CreateEventController {

    // datové atributy
    @FXML
    private TextField jmenoUdalosti;
    @FXML
    private DatePicker datumUdalosti;
    @FXML
    private TextField lokaceUdalosti;
    @FXML
    private ImageView nastaveni;
    @FXML
    private ImageView chat;
    @FXML
    private ImageView zpet;
    private Stage stage;
    private Scene scene;
    @FXML
    private Label home;
    private String pattern = "dd.MM.yyyy";
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);

    /**
     * Inicializační metoda, která pomáhá aktualizovat okno aplikace
     */
    @FXML
    private void initialize() {
        datumUdalosti.setConverter(new LocalDateStringConverter(dateTimeFormatter, null));
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
     * Metoda, která nechá ztmavnout nastavení, když na něj najede myš
     *
     * @param mouseEvent
     */
    @FXML
    private void ztmavniNastaveni(MouseEvent mouseEvent) {
        ColorAdjust ztmavnuti = new ColorAdjust();
        ztmavnuti.setBrightness(-0.5);
        nastaveni.setEffect(ztmavnuti);
    }

    /**
     * Metoda, která nechá zesvětlat nastavení, když myš odejde
     *
     * @param mouseEvent
     */
    @FXML
    private void zesvetlejNastaveni(MouseEvent mouseEvent) {
        ColorAdjust zesvetleni = new ColorAdjust();
        zesvetleni.setBrightness(0);
        nastaveni.setEffect(zesvetleni);
    }

    /**
     * Metoda, která změní obrazovku na nastavení profilu
     *
     * @param mouseEvent
     * @throws Exception
     */
    @FXML
    private void zpracujNaNastaveni(MouseEvent mouseEvent) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/profileSettings.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Nastavení profilu");
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/events.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Události");
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
     * Metoda, která zpracuje vytvoření události
     * a přepne okno zpět na events.
     *
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    private void vytvorUdalost(ActionEvent actionEvent) throws IOException, ParseException {
        String regexJmeno = ".+";
        Pattern patternJmeno = Pattern.compile(regexJmeno);
        String stringJmeno = jmenoUdalosti.getCharacters().toString();
        Matcher jeValidniJmeno = patternJmeno.matcher(stringJmeno);

        if (jeValidniJmeno.matches()) {
            if (datumUdalosti.getValue() == null) {
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
                final Text text = new Text("Nebylo zvoleno datum události.");
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
                String regexLokace = ".+";
                Pattern patternLokace = Pattern.compile(regexLokace);
                String stringLokace = lokaceUdalosti.getCharacters().toString();
                Matcher jeValidniLokace = patternLokace.matcher(stringLokace);

                if (jeValidniLokace.matches()) {
                    String pocatecniUdalost = datumUdalosti.getValue().toString();
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy");
                    Date datum = inputFormat.parse(pocatecniUdalost);
                    String konecnaUdalost = outputFormat.format(datum);
                    boolean cilovaUdalost = Tymovanicko.TYMOVANICKO.getSpravaUdalosti().getUdalosti().stream().anyMatch(udalost -> udalost.getJmenoUdalosti().equals(jmenoUdalosti.getCharacters().toString()) && udalost.getLokaceUdalosti().equals(lokaceUdalosti.getCharacters().toString()) && udalost.getDatumUdalosti().equals(konecnaUdalost));
                    if (cilovaUdalost) {
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
                        final Text text = new Text("Nelze vytvořit událost, která již existuje.");
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
                        Scene dialogScene = new Scene(dialogVbox, 340, 120);
                        dialog.setScene(dialogScene);
                        dialog.setTitle("Upozornění");
                        dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("other/logo.jpg"))));
                        dialog.show();
                    } else {
                        String jmeno = jmenoUdalosti.getCharacters().toString();
                        String lokace = lokaceUdalosti.getCharacters().toString();
                        LocalDate date = datumUdalosti.getValue();
                        Date datumJava = java.sql.Date.valueOf(date);
                        Tymovanicko.TYMOVANICKO.getSpravaUdalosti().vytvorUdalost(jmeno, datumJava, lokace);
                        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/events.fxml")));
                        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setTitle("Týmováníčko - Události");
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
                    final Text text = new Text("Není validní lokace události.");
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
                }
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
            final Text text = new Text("Není validní jméno události.");
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

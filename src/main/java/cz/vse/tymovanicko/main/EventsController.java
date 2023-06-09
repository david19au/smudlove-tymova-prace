package cz.vse.tymovanicko.main;

import cz.vse.tymovanicko.logika.Tymovanicko;
import cz.vse.tymovanicko.logika.Udalost;
import cz.vse.tymovanicko.logika.Uzivatel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

/**
 * Třída EventController je hlavní třídou okna,
 * které představuje události
 *
 * @author Magdalena Hájková (hajm17), Trong Dat Luu (luut02), Jakub Kafka (kafj03), Adam Schindler (scha28), Hana Žahourová (zahh00)
 * @version 1.0.0
 */
public class EventsController {

    // datové atributy
    @FXML
    private Label home;
    @FXML
    private Button vytvorUdalost;
    @FXML
    private ListView<Udalost> panelUdalosti;
    @FXML
    private ImageView nastaveni;
    @FXML
    private ImageView chat;
    @FXML
    private ImageView zpet;
    private Stage stage;
    private Scene scene;

    /**
     * Inicializační metoda, která pomáhá aktualizovat okno aplikace
     */
    @FXML
    private void initialize() {
        naplneniPaneluUdalosti();

        panelUdalosti.setCellFactory(udalostListView -> new ListCell<>() {
            @Override
            protected void updateItem(Udalost udalost, boolean empty) {
                super.updateItem(udalost, empty);
                if (!empty) {
                    setText(udalost.getJmenoUdalosti() + " (" + udalost.getDatumUdalosti() + ") " + "\n"
                            + " – Zúčastní se: " + udalost.getSeznamJde().size() + "\n"
                            + " – Nezúčastní se: " + udalost.getSeznamNejde().size());
                    //setStyle(getIndex() % 2 == 0 ? "-fx-background-color: white; -fx-text-fill: black;" : "-fx-background-color: #C5C5C5; -fx-text-fill: black;");
                } else {
                    setText(null);
                }
            }
        });

        for (Uzivatel uzivatel : Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().getUzivatele()) {
            if (uzivatel.getEmail().equals(Tymovanicko.TYMOVANICKO.getId())) {
                if (uzivatel.getRole().equals("Člen")) {
                    vytvorUdalost.setDisable(true);
                }
            }
        }
    }

    /**
     * Metoda, která naplňuje panel udalostí
     */
    private void naplneniPaneluUdalosti() {
        panelUdalosti.getItems().clear();
        Collection<Udalost> udalosti = Tymovanicko.TYMOVANICKO.getUdalosti();
        panelUdalosti.getItems().addAll(udalosti);
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
     * Metoda, která změní obrazovku na okno pro vytvoření události.
     *
     * @param actionEvent
     * @throws Exception
     */
    @FXML
    private void zpracujNaVytvoreniUdalosti(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/createEvent.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Vytvoření události");
        stage.show();
    }

    /**
     * Metoda, která otevírá okno události při kliknutí na událost v panelu událostí.
     *
     * @param mouseEvent
     */
    @FXML
    private void klikPanelUdalosti(MouseEvent mouseEvent) {
        Udalost cilovaUdalost = panelUdalosti.getSelectionModel().getSelectedItem();
        if (cilovaUdalost == null) return;
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox();
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.setStyle("-fx-background: #37598e;");
        dialogVbox.setPadding(new Insets(10, 20, 20, 20));
        dialogVbox.setSpacing(10);

        final Text jmeno = new Text(cilovaUdalost.getJmenoUdalosti());
        jmeno.setStyle("-fx-font: 16 arial;");
        jmeno.setFill(Color.WHITE);
        final Text datum = new Text(cilovaUdalost.getDatumUdalosti());
        datum.setStyle("-fx-font: 14 arial;");
        datum.setFill(Color.WHITE);
        final Text lokace = new Text(cilovaUdalost.getLokaceUdalosti());
        lokace.setStyle("-fx-font: 14 arial;");
        lokace.setFill(Color.WHITE);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);

        HBox hBoxSmazat = new HBox();
        hBoxSmazat.setAlignment(Pos.CENTER);
        hBoxSmazat.setSpacing(100);
        hBoxSmazat.setPadding(new Insets(0, 0, 0, 200));

        VBox vBoxSmazat = new VBox();
        vBoxSmazat.setAlignment(Pos.CENTER);
        vBoxSmazat.setSpacing(10);

        VBox vBoxUcastnici = new VBox();
        vBoxUcastnici.setSpacing(2);

        final Text ucastnici = new Text("Zúčastní se:");
        ucastnici.setStyle("-fx-font: 14 arial;");
        ucastnici.setFill(Color.WHITE);

        ListView<String> panelUcastnici = new ListView<String>();
        panelUcastnici.getItems().clear();
        Collection<String> jde = Tymovanicko.TYMOVANICKO.getSpravaUdalosti().getJde(cilovaUdalost);
        panelUcastnici.getItems().addAll(jde);

        VBox vBoxNeucastnici = new VBox();
        vBoxNeucastnici.setSpacing(2);

        final Text neucastnici = new Text("Nezúčastní se:");
        neucastnici.setStyle("-fx-font: 14 arial;");
        neucastnici.setFill(Color.WHITE);

        ListView<String> panelNeucastnici = new ListView<>();
        panelNeucastnici.getItems().clear();
        Collection<String> nejde = Tymovanicko.TYMOVANICKO.getSpravaUdalosti().getNejde(cilovaUdalost);
        panelNeucastnici.getItems().addAll(nejde);

        HBox hBoxTlacitka = new HBox();
        hBoxTlacitka.setAlignment(Pos.CENTER);
        hBoxTlacitka.setSpacing(10);

        Button buttonZucastnim = new Button("Zúčastním se");
        Button buttonNezucastnim = new Button("Nezúčastním se");

        Button buttonOk = new Button("OK");

        buttonOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dialog.close();
            }
        });

        Button buttonSmazat = new Button("Smazat událost");
        buttonSmazat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Tymovanicko.TYMOVANICKO.getSpravaUdalosti().smazUdalost(cilovaUdalost.getJmenoUdalosti());
                naplneniPaneluUdalosti();

                dialog.close();
            }
        });
        for (Uzivatel uzivatel : Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().getUzivatele()) {
            if (uzivatel.getEmail().equals(Tymovanicko.TYMOVANICKO.getId())) {
                if (uzivatel.getRole().equals("Člen")) {
                    buttonSmazat.setDisable(true);
                }
            }
        }

        buttonZucastnim.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (Tymovanicko.TYMOVANICKO.getSpravaUdalosti().getJde(cilovaUdalost).contains(Tymovanicko.TYMOVANICKO.getJmeno(Tymovanicko.TYMOVANICKO.getId()))) {
                    return;
                } else {
                    if (Tymovanicko.TYMOVANICKO.getSpravaUdalosti().getNejde(cilovaUdalost).contains(Tymovanicko.TYMOVANICKO.getJmeno(Tymovanicko.TYMOVANICKO.getId()))) {
                        Tymovanicko.TYMOVANICKO.getSpravaUdalosti().getNejde(cilovaUdalost).remove(Tymovanicko.TYMOVANICKO.getJmeno(Tymovanicko.TYMOVANICKO.getId()));
                        panelNeucastnici.getItems().clear();
                        Collection<String> nejde = Tymovanicko.TYMOVANICKO.getSpravaUdalosti().getNejde(cilovaUdalost);
                        panelNeucastnici.getItems().addAll(nejde);
                    }
                    Tymovanicko.TYMOVANICKO.getSpravaUdalosti().zmenRSVP(cilovaUdalost, Tymovanicko.TYMOVANICKO.getJmeno(Tymovanicko.TYMOVANICKO.getId()), "jdu");
                    panelUcastnici.getItems().clear();
                    Collection<String> jde = Tymovanicko.TYMOVANICKO.getSpravaUdalosti().getJde(cilovaUdalost);
                    panelUcastnici.getItems().addAll(jde);
                    naplneniPaneluUdalosti();
                }
            }
        });

        buttonNezucastnim.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (Tymovanicko.TYMOVANICKO.getSpravaUdalosti().getNejde(cilovaUdalost).contains(Tymovanicko.TYMOVANICKO.getJmeno(Tymovanicko.TYMOVANICKO.getId()))) {
                    return;
                } else {
                    if (Tymovanicko.TYMOVANICKO.getSpravaUdalosti().getJde(cilovaUdalost).contains(Tymovanicko.TYMOVANICKO.getJmeno(Tymovanicko.TYMOVANICKO.getId()))) {
                        Tymovanicko.TYMOVANICKO.getSpravaUdalosti().getJde(cilovaUdalost).remove(Tymovanicko.TYMOVANICKO.getJmeno(Tymovanicko.TYMOVANICKO.getId()));
                        panelUcastnici.getItems().clear();
                        Collection<String> jde = Tymovanicko.TYMOVANICKO.getSpravaUdalosti().getJde(cilovaUdalost);
                        panelUcastnici.getItems().addAll(jde);
                    }
                    Tymovanicko.TYMOVANICKO.getSpravaUdalosti().zmenRSVP(cilovaUdalost, Tymovanicko.TYMOVANICKO.getJmeno(Tymovanicko.TYMOVANICKO.getId()), "nejdu");
                    panelNeucastnici.getItems().clear();
                    Collection<String> nejde = Tymovanicko.TYMOVANICKO.getSpravaUdalosti().getNejde(cilovaUdalost);
                    panelNeucastnici.getItems().addAll(nejde);
                    naplneniPaneluUdalosti();
                }
            }
        });

        vBoxSmazat.getChildren().add(jmeno);
        vBoxSmazat.getChildren().add(datum);
        vBoxSmazat.getChildren().add(lokace);

        hBoxSmazat.getChildren().add(vBoxSmazat);
        hBoxSmazat.getChildren().add(buttonSmazat);

        dialogVbox.getChildren().add(hBoxSmazat);
        dialogVbox.getChildren().add(hBox);
        dialogVbox.getChildren().add(hBoxTlacitka);
        dialogVbox.getChildren().add(buttonOk);

        hBox.getChildren().add(vBoxUcastnici);
        hBox.getChildren().add(vBoxNeucastnici);

        vBoxUcastnici.getChildren().add(ucastnici);
        vBoxUcastnici.getChildren().add(panelUcastnici);

        vBoxNeucastnici.getChildren().add(neucastnici);
        vBoxNeucastnici.getChildren().add(panelNeucastnici);

        hBoxTlacitka.getChildren().add(buttonZucastnim);
        hBoxTlacitka.getChildren().add(buttonNezucastnim);

        Scene dialogScene = new Scene(dialogVbox);
        dialog.setScene(dialogScene);
        dialog.setTitle("Událost - " + panelUdalosti.getSelectionModel().getSelectedItem().getJmenoUdalosti());
        dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("other/logo.jpg"))));
        dialog.show();
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

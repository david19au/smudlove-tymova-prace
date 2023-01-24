package cz.vse.tymovanicko.main;

import cz.vse.tymovanicko.logika.Tymovanicko;
import cz.vse.tymovanicko.logika.Udalost;
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
 * Třída  EventController je hlavní třídou okna,
 * které představuje události
 *
 * @author ?
 * @version ?
 */
public class EventsController {

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

    @FXML
    private void initialize() {
        naplneniPaneluUdalosti();

        panelUdalosti.setCellFactory(udalostListView -> new ListCell<>() {
            @Override
            protected void updateItem(Udalost udalost, boolean empty) {
                super.updateItem(udalost, empty);
                if (!empty) {
                    setText("[" + udalost.getDatumUdalosti() + "] " + udalost.getJmenoUdalosti());
                } else {
                    setText(null);
                }
            }
        });
    }

    /**
     * Metoda, která naplňuje panel udalostí
     */
    private void naplneniPaneluUdalosti() {
        panelUdalosti.getItems().clear();
        Collection<Udalost> udalosti = Tymovanicko.TYMOVANICKO.getUdalosti();
        panelUdalosti.getItems().addAll(udalosti);
    }

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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/profile_settings.fxml")));
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

    @FXML
    private void zpracujNaVytvoreniUdalosti(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/createEvent.fxml")));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Týmováníčko - Vytvoření události");
        stage.show();
    }

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

        VBox vBoxUcastnici = new VBox();
        vBoxUcastnici.setSpacing(2);

        final Text ucastnici = new Text("Účastnící:");
        ucastnici.setStyle("-fx-font: 14 arial;");
        ucastnici.setFill(Color.WHITE);

        ListView panelUcastnici = new ListView();

        VBox vBoxNeucastnici = new VBox();
        vBoxNeucastnici.setSpacing(2);

        final Text neucastnici = new Text("Neúčastníci:");
        neucastnici.setStyle("-fx-font: 14 arial;");
        neucastnici.setFill(Color.WHITE);

        ListView panelNeucastnici = new ListView();

        HBox hBoxTlacitka = new HBox();
        hBoxTlacitka.setAlignment(Pos.CENTER);
        hBoxTlacitka.setSpacing(10);

        Button buttonZucastnim = new Button("Zúčastním se");
        Button buttonNezucastnim = new Button("Nezúčastním se");

        Button buttonSmazat = new Button("Smazat událost");
        buttonSmazat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Tymovanicko.TYMOVANICKO.getSpravaUdalosti().smazUdalost(cilovaUdalost.getJmenoUdalosti());
                naplneniPaneluUdalosti();

                dialog.close();
            }
        });

        buttonZucastnim.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Tymovanicko.TYMOVANICKO.getSpravaUdalosti().zmenRSVP(cilovaUdalost.getJmenoUdalosti(), Tymovanicko.TYMOVANICKO.getJmeno(Tymovanicko.TYMOVANICKO.getId()), "jdu");
            }
        });

        buttonNezucastnim.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Tymovanicko.TYMOVANICKO.getSpravaUdalosti().zmenRSVP(cilovaUdalost.getJmenoUdalosti(), Tymovanicko.TYMOVANICKO.getJmeno(Tymovanicko.TYMOVANICKO.getId()), "nejdu");
            }
        });


        dialogVbox.getChildren().add(jmeno);
        dialogVbox.getChildren().add(datum);
        dialogVbox.getChildren().add(lokace);
        dialogVbox.getChildren().add(hBox);
        dialogVbox.getChildren().add(hBoxTlacitka);
        dialogVbox.getChildren().add(buttonSmazat);

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
        dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.jpg"))));
        dialog.show();
    }
}

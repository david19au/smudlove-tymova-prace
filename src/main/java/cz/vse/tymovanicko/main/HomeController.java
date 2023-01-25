package cz.vse.tymovanicko.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.vse.tymovanicko.logika.Tymovanicko;
import cz.vse.tymovanicko.logika.Udalost;
import cz.vse.tymovanicko.logika.Uzivatel;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ChoiceBox;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;

/**
 * Třída  HomeController je hlavní třídou okna,
 * které představuje domovskou obrazovku
 *
 * @author Magdalena Hájková (hajm17), Trong Dat Luu (luut02), Jakub Kafka (kafj03), Adam Schindler (scha28), Hana Žahourová (zahh00)
 * @version 1.0.0
 */
public class HomeController {

    // datové atributy
    @FXML
    private ImageView udalosti;
    @FXML
    private ImageView chat;
    @FXML
    private ImageView nastaveni;
    private Stage stage;
    private Scene scene;
    @FXML
    private ListView panelClenu;

    /**
     * Inicializační metoda, která pomáhá aktualizovat okno aplikace
     */
    @FXML
    private void initialize() {
        naplneniPaneluClenu();

        panelClenu.setCellFactory(uzivatelListView -> new ListCell<Uzivatel>() {
            @Override
            protected void updateItem(Uzivatel uzivatel, boolean empty) {
                super.updateItem(uzivatel, empty);
                if (!empty) {
                    String role = "";
                    if (uzivatel.getRole().equals("Trenér")) {
                        role = "T";
                    }
                    if (uzivatel.getRole().equals("Kapitán")) {
                        role = "C";
                    }
                    if (uzivatel.getRole().equals("Člen")) {
                        role = "M";
                    }
                    String zakladniVypis = uzivatel.getKrestniJmeno() + " " + uzivatel.getPrijmeni() + " (" + uzivatel.getRole() + ")";

                    URL urlObrazku = getClass().getResource("icons/icon" + role + ".PNG");
                    if (urlObrazku == null) {
                        setText(zakladniVypis);
                        setGraphic(null);
                        return;
                    }
                    ImageView iw = new ImageView(urlObrazku.toString());
                    iw.setFitWidth(25);
                    iw.setPreserveRatio(true);
                    setText(zakladniVypis);
                    setGraphic(iw);
                } else {
                    setText(null);
                    setGraphic(null);
                }
            }
        });
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
     * Metoda, která naplňuje panel členů
     */
    private void naplneniPaneluClenu() {
        panelClenu.getItems().clear();
        Collection<Uzivatel> clenove = Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().getUzivatele();
        panelClenu.getItems().addAll(clenove);
    }

    /**
     * Metoda, která zobrazuje okno pro změnu role uživatele při kliknutí na uživatele v panelu členů
     *
     * @param mouseEvent
     */
    @FXML
    public void klikPanelClenu(MouseEvent mouseEvent) {
        Uzivatel cilovyUzivatel = (Uzivatel) panelClenu.getSelectionModel().getSelectedItem();
        if (cilovyUzivatel == null) return;
        for (Uzivatel uzivatel : Tymovanicko.TYMOVANICKO.getSeznamUzivatelu().getUzivatele()) {
            if (uzivatel.getEmail().equals(Tymovanicko.TYMOVANICKO.getId())) {
                if (uzivatel.getRole().equals("Trenér")) {
                    if (!uzivatel.getEmail().equals(cilovyUzivatel.getEmail())) {
                        final Stage dialog = new Stage();
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(stage);

                        VBox dialogVbox = new VBox(20);
                        dialogVbox.setAlignment(Pos.CENTER);
                        dialogVbox.setStyle("-fx-background: #37598e;");
                        dialogVbox.setPadding(new Insets(10, 10, 10, 10));

                        final Text text = new Text("Jakou roli chcete uživateli " + cilovyUzivatel.getKrestniJmeno() + " " + cilovyUzivatel.getPrijmeni() + " přiřadit?");
                        text.setStyle("-fx-font: 14 arial;");
                        text.setFill(Color.WHITE);

                        ChoiceBox choiceBox = new ChoiceBox();
                        choiceBox.setItems(FXCollections.observableArrayList("Trenér", "Kapitán", "Člen"));
                        choiceBox.setValue(cilovyUzivatel.getRole());

                        Button button = new Button("Přiřaď roli");
                        button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                cilovyUzivatel.setRole(choiceBox.getSelectionModel().getSelectedItem().toString());
                                dialog.close();
                                naplneniPaneluClenu();

                                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data/uzivatele.json"))) {
                                    String json = gson.toJson(Tymovanicko.TYMOVANICKO.getSeznamUzivatelu());
                                    bufferedWriter.write(json);
                                    bufferedWriter.newLine();
                                    bufferedWriter.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        dialogVbox.getChildren().add(text);
                        dialogVbox.getChildren().add(choiceBox);
                        dialogVbox.getChildren().add(button);

                        Scene dialogScene = new Scene(dialogVbox);
                        dialog.setScene(dialogScene);
                        dialog.setTitle("Změna role: " + cilovyUzivatel.getKrestniJmeno() + " " + cilovyUzivatel.getPrijmeni());
                        dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("other/logo.jpg"))));
                        dialog.show();
                    } else return;
                } else return;
            }
        }
    }
}

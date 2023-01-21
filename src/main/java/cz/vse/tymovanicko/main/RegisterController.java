package cz.vse.tymovanicko.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import cz.vse.tymovanicko.logika.SeznamUzivatelu;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Třída  RegisterController je hlavní třídou okna,
 * které představuje registrační obrazovku
 *
 * @author ?
 * @version ?
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
    private Tymovanicko tymovanicko = new Tymovanicko();

    public RegisterController() throws IOException {
    }

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
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            Uzivatel uzivatel = new Uzivatel(stringEmail, stringJmeno, stringPrijmeni, stringHeslo);
                            String emaily = tymovanicko.getSeznamUzivatelu().emailyUzivatelu();
                            if (emaily.contains("," + stringEmail + ",")) {
                                final Stage dialog = new Stage();
                                dialog.initModality(Modality.APPLICATION_MODAL);
                                dialog.initOwner(stage);
                                VBox dialogVbox = new VBox(20);
                                dialogVbox.setAlignment(Pos.CENTER);
                                dialogVbox.setStyle("-fx-background: #37598e;");
                                final Text text = new Text("Tento email je již zaregistrovaný");
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
                            } else {
                                tymovanicko.getSeznamUzivatelu().vlozUzivateleDoSeznamu(uzivatel);
                                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("target/jsonUzivatel.json"))) {
                                    String json = gson.toJson(tymovanicko.getSeznamUzivatelu());
                                    bufferedWriter.write(json);
                                    bufferedWriter.newLine();
                                    bufferedWriter.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Vse je validni");
                            }
                        } else {
                            final Stage dialog = new Stage();
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            dialog.initOwner(stage);
                            VBox dialogVbox = new VBox(20);
                            dialogVbox.setAlignment(Pos.CENTER);
                            dialogVbox.setStyle("-fx-background: #37598e;");
                            final Text text = new Text("Hesla se neshodují");
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
                            Scene dialogScene = new Scene(dialogVbox, 200, 100);
                            dialog.setScene(dialogScene);
                            dialog.setTitle("Týmováníčko");
                            dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.jpg"))));
                            dialog.show();
                        }
                    } else {
                        final Stage dialog = new Stage();
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(stage);
                        VBox dialogVbox = new VBox(20);
                        dialogVbox.setAlignment(Pos.CENTER);
                        dialogVbox.setStyle("-fx-background: #37598e;");
                        final Text text = new Text("Heslo musí obsahovat alespoň jedno písmeno, jedno číslo a minimálně osm znaků");
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
                        Scene dialogScene = new Scene(dialogVbox, 400, 100);
                        dialog.setScene(dialogScene);
                        dialog.setTitle("Týmováníčko");
                        dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.jpg"))));
                        dialog.show();
                    }
                } else {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(stage);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.setAlignment(Pos.CENTER);
                    dialogVbox.setStyle("-fx-background: #37598e;");
                    final Text text = new Text("Není validní email");
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
                    Scene dialogScene = new Scene(dialogVbox, 200, 100);
                    dialog.setScene(dialogScene);
                    dialog.setTitle("Týmováníčko");
                    dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.jpg"))));
                    dialog.show();
                }
            } else {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(stage);
                VBox dialogVbox = new VBox(20);
                dialogVbox.setAlignment(Pos.CENTER);
                dialogVbox.setStyle("-fx-background: #37598e;");
                final Text text = new Text("Není validní příjmení");
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
                Scene dialogScene = new Scene(dialogVbox, 200, 100);
                dialog.setScene(dialogScene);
                dialog.setTitle("Týmováníčko");
                dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.jpg"))));
                dialog.show();
            }
        } else {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);
            VBox dialogVbox = new VBox(20);
            dialogVbox.setAlignment(Pos.CENTER);
            dialogVbox.setStyle("-fx-background: #37598e;");
            final Text text = new Text("Není validní jméno");
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
            Scene dialogScene = new Scene(dialogVbox, 200, 100);
            dialog.setScene(dialogScene);
            dialog.setTitle("Týmováníčko");
            dialog.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("logo.jpg"))));
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

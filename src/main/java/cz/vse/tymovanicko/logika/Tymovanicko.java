package cz.vse.tymovanicko.logika;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public class Tymovanicko {

    private SeznamUzivatelu seznamUzivatelu;
    private Uzivatel uzivatel;
    private Chat chat;
    private ChatLog chatLog;

    public Tymovanicko() throws IOException {
        zalozTymovanicko();
    }

    private void zalozTymovanicko() throws IOException {
        seznamUzivatelu = new SeznamUzivatelu();
        chat = new Chat();
        chatLog = new ChatLog();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader("target/jsonUzivatel.json")) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(jsonElement);
            setSeznamUzivatelu(gson.fromJson(jsonInString, SeznamUzivatelu.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SeznamUzivatelu getSeznamUzivatelu() {
        return seznamUzivatelu;
    }

    public void setSeznamUzivatelu(SeznamUzivatelu seznamUzivatelu) {
        this.seznamUzivatelu = seznamUzivatelu;
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public ChatLog getChatLog() {
        return chatLog;
    }

    public void setChatLog(ChatLog chatLog) {
        this.chatLog = chatLog;
    }
}

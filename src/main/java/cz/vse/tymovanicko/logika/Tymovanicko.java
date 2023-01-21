package cz.vse.tymovanicko.logika;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public enum Tymovanicko {
    TYMOVANICKO;

    private SeznamUzivatelu seznamUzivatelu;
    private Uzivatel uzivatel;
    private Chat chat;
    private ChatLog chatLog;
    private String id;

    Tymovanicko() {
        zalozTymovanicko();
    }

    private void zalozTymovanicko() {
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
        try (Reader reader = new FileReader("target/chat" + "chat" + ".json")) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(jsonElement);
            setChatLog(gson.fromJson(jsonInString, ChatLog.class));
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJmeno(String stringEmailu) {
        String getJmeno = null;
        for (Uzivatel uzivatel : TYMOVANICKO.getSeznamUzivatelu().getUzivatele()) {
            if (uzivatel.getEmail().equals(stringEmailu)) {
                getJmeno = uzivatel.getKrestniJmeno() + " " + uzivatel.getPrijmeni();
            }
        }
        return getJmeno;
    }
}

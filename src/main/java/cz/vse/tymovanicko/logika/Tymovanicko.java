package cz.vse.tymovanicko.logika;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Třída Tymovanicko - představuje stav aplikace.
 * Tato třída je součástí aplikace Týmováníčko.
 * Tato třída inicializuje prvky ze kterých se aplikace skládá.
 *
 * @author ?
 * @version ?
 */
public enum Tymovanicko {
    TYMOVANICKO;

    private SeznamUzivatelu seznamUzivatelu;
    private Uzivatel uzivatel;
    private Chat chat;
    private ChatLog chatLog;
    private String id;
    private SpravaUdalosti spravaUdalosti;
    private Udalost udalost;
    private List<Udalost> udalosti;


    Tymovanicko() {
        zalozTymovanicko();
    }

    private void zalozTymovanicko() {
        seznamUzivatelu = new SeznamUzivatelu();
        chat = new Chat();
        chatLog = new ChatLog();
        spravaUdalosti = new SpravaUdalosti();
        // Gson builder pro lepší vzhled struktury JSONu
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader("target/jsonUzivatel.json")) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(jsonElement);
            setSeznamUzivatelu(gson.fromJson(jsonInString, SeznamUzivatelu.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Reader reader = new FileReader("target/chat.json")) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(jsonElement);
            setChatLog(gson.fromJson(jsonInString, ChatLog.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Reader reader = new FileReader("target/" + "udalosti.json")) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(jsonElement);
            setUdalosti(gson.fromJson(jsonInString, new TypeToken<List<Udalost>>() {
            }.getType()));
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

    public SpravaUdalosti getSpravaUdalosti() {
        return spravaUdalosti;
    }


    public void setSpravaUdalosti(SpravaUdalosti spravaUdalosti) {
        this.spravaUdalosti = spravaUdalosti;
    }

    public Udalost getUdalost() {
        return udalost;
    }

    public void setUdalost(Udalost udalost) {
        this.udalost = udalost;
    }

    public List<Udalost> getUdalosti() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader("target/" + "udalosti.json")) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(jsonElement);
            setUdalosti(gson.fromJson(jsonInString, new TypeToken<List<Udalost>>() {
            }.getType()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return udalosti;
    }

    public void setUdalosti(List<Udalost> udalosti) {
        this.udalosti = udalosti;
    }
}

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
 * Tato třída je enum a aplikuje se zde Singleton.
 *
 * @author Magdalena Hájková (hajm17), Trong Dat Luu (luut02), Jakub Kafka (kafj03), Adam Schindler (scha28), Hana Žahourová (zahh00)
 * @version 1.0.0
 */
public enum Tymovanicko {
    TYMOVANICKO;

    // datové atributy
    private SeznamUzivatelu seznamUzivatelu;
    private Uzivatel uzivatel;
    private Chat chat;
    private ChatLog chatLog;
    private String id;
    private SpravaUdalosti spravaUdalosti;
    private Udalost udalost;
    private List<Udalost> udalosti;


    /**
     * Konstruktor volá metodu zalozTymovanicko
     */
    Tymovanicko() {
        zalozTymovanicko();
    }

    /**
     * Metoda zalozTymovanicko vytváří novou instanci seznamu uživatelů, chatu, chatLogu a správu událostí.
     * Dělá novou instanci Gson pro převod JSON souborů do Javy.
     * Reader se pokusí přečíst zde soubory pro uživatele, chatu a seznamu událostí. Ty se potom nastaví setterem.
     */
    private void zalozTymovanicko() {
        seznamUzivatelu = new SeznamUzivatelu();
        chat = new Chat();
        chatLog = new ChatLog();
        spravaUdalosti = new SpravaUdalosti();
        // Gson builder pro lepší vzhled struktury JSONu
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader("data/uzivatele.json")) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(jsonElement);
            setSeznamUzivatelu(gson.fromJson(jsonInString, SeznamUzivatelu.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Reader reader = new FileReader("data/chat.json")) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(jsonElement);
            setChatLog(gson.fromJson(jsonInString, ChatLog.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Reader reader = new FileReader("data/" + "udalosti.json")) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(jsonElement);
            setUdalosti(gson.fromJson(jsonInString, new TypeToken<List<Udalost>>() {
            }.getType()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda getSeznamUzivatelu vrací instanci seznam uživatelů
     *
     * @return instance seznamu uživatelů
     */
    public SeznamUzivatelu getSeznamUzivatelu() {
        return seznamUzivatelu;
    }

    /**
     * Nastavuje seznam uživatelů
     *
     * @param seznamUzivatelu
     */
    public void setSeznamUzivatelu(SeznamUzivatelu seznamUzivatelu) {
        this.seznamUzivatelu = seznamUzivatelu;
    }


    /**
     * Vrací instanci Chat
     *
     * @return instance Chat
     */
    public Chat getChat() {
        return chat;
    }

    /**
     * Nastavuje tuto danou instanci chatu
     *
     * @param chat
     */
    public void setChat(Chat chat) {
        this.chat = chat;
    }

    /**
     * Vrací instanci ChatLog
     *
     * @return instance ChatLog
     */
    public ChatLog getChatLog() {
        return chatLog;
    }

    /**
     * Nastavuje tuto danou instanci chatLogu
     *
     * @param chatLog
     */
    public void setChatLog(ChatLog chatLog) {
        this.chatLog = chatLog;
    }

    /**
     * Vrací String Id, což je emailová adresa uživatele
     *
     * @return Id/email ve stringu
     */
    public String getId() {
        return id;
    }

    /**
     * Nastavuje tuto danou instanci Id
     *
     * @param id ve Stringu
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metoda getJmeno vrací křestní jméno a příjmení uživatele ve Stringu.
     * Metoda páruje email ve Stringu s jménem uživatele a pak vrací jeho jméno.
     *
     * @return jméno uživatele ve Stringu
     */
    public String getJmeno(String stringEmailu) {
        String getJmeno = null;
        for (Uzivatel uzivatel : TYMOVANICKO.getSeznamUzivatelu().getUzivatele()) {
            if (uzivatel.getEmail().equals(stringEmailu)) {
                getJmeno = uzivatel.getKrestniJmeno() + " " + uzivatel.getPrijmeni();
            }
        }
        return getJmeno;
    }

    /**
     * Vrací instanci správy událostí
     *
     * @return instance SpravaUdalosti
     */
    public SpravaUdalosti getSpravaUdalosti() {
        return spravaUdalosti;
    }


    /**
     * Metoda getUdalosti vrací seznam událostí
     * Předtím než jej vrátí, je JSON s událostmi přečten aby se zachytly veškeré aktualizace.
     *
     * @return seznam s událostmi
     */
    public List<Udalost> getUdalosti() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader("data/" + "udalosti.json")) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(jsonElement);
            setUdalosti(gson.fromJson(jsonInString, new TypeToken<List<Udalost>>() {
            }.getType()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return udalosti;
    }

    /**
     * Metoda setUdálosti je setter pro události.
     *
     * @param udalosti
     */
    public void setUdalosti(List<Udalost> udalosti) {
        this.udalosti = udalosti;
    }
}

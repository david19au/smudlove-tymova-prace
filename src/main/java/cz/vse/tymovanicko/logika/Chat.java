package cz.vse.tymovanicko.logika;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Třída Chat - představuje chat a jeho stav.
 * Tato třída je součástí aplikace Týmováníčko.
 * "Chat" reprezentuje zprávy v aplikaci.
 *
 * @author ?
 * @version ZS2022/23
 */
public class Chat {

    private ChatLog chatLog;

    /**
     * Vytvoření nové instance chatu s chatLogem.
     */
    public Chat() {
        chatLog = new ChatLog();
        nactiZpravyZJSON();
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
     * Metoda pridatZpravu vezme zprávy napsané ve Stringu a zavolá metodu ulozitZpravyDoJSON
     *
     * @param zprava zpráva, kterou chce uživatel odeslat
     * @throws IOException
     */
    public void pridatZpravu(String zprava) throws IOException {
        chatLog.zpravy.add(zprava);
        ulozitZpravyDoJSON();
    }

    /**
     * Metoda ulozitZpravyDoJSON používá knihovnu GSON od Google pro ukládání informací do souborů JSON.
     *
     * @throws IOException
     */
    private void ulozitZpravyDoJSON() throws IOException {
        // Gson builder pro lepší vzhled struktury JSONu
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(chatLog);
        BufferedWriter bw = new BufferedWriter(new FileWriter("target/" + "chat.json"));
        bw.write(json);
        bw.newLine();
        bw.flush();
        bw.close();
    }

    /**
     * Metoda nactiZpravyZJSON používá knihovnu GSON od Google pro čtení informací ze souborů JSON.
     *
     * @throws IOException
     */
    public void nactiZpravyZJSON() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("target/" + "chat.json")) {
            chatLog = gson.fromJson(reader, ChatLog.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

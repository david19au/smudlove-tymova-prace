package cz.vse.tymovanicko.logika;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Třída Chat - představuje chat a jeho stav.
 *
 * Tato třída je součástí aplikace Týmováníčko.
 *
 * "Chat" reprezentuje zprávy v aplikaci.
 *
 * @author     ?
 * @version    ?
 */
public class Chat {

    private ChatLog chatLog;

    public Chat() {
        chatLog = new ChatLog();
        nactiZpravyZJSON();
    }

    public void pridatZpravu(String zprava) throws IOException {
        chatLog.zpravy.add(zprava);
        ulozitZpravyDoJSON();
    }

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

    public void nactiZpravyZJSON() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("target/" + "chat.json")) {
            chatLog = gson.fromJson(reader, ChatLog.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

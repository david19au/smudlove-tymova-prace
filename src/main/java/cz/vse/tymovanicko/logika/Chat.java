package cz.vse.tymovanicko.logika;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Chat {

    private ChatLog chatLog;

    public Chat() throws IOException {
        chatLog = new ChatLog();
        nactiZpravyZJSON();
    }

    public void pridatZpravu(String zprava) throws IOException {
        chatLog.zpravy.add(zprava);
        ulozitZpravyDoJSON();
    }

    private void ulozitZpravyDoJSON() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(chatLog);
        BufferedWriter bw = new BufferedWriter(new FileWriter("target/chat" + "chat" + ".json"));
        bw.write(json);
        bw.newLine();
        bw.flush();
        bw.close();
    }

    public void nactiZpravyZJSON() throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("target/chat" + "chat" + ".json")) {
            chatLog = gson.fromJson(reader, ChatLog.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> vratZpravy() {
        return this.chatLog.zpravy;
    }
}

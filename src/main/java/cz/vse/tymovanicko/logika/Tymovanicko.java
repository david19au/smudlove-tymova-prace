package cz.vse.tymovanicko.logika;

import java.io.IOException;

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

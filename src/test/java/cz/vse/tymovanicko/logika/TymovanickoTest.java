package cz.vse.tymovanicko.logika;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TymovanickoTest {


    @Test
    public void testSetSeznamUzivatelu() {
        SeznamUzivatelu seznam = new SeznamUzivatelu();
        Tymovanicko.TYMOVANICKO.setSeznamUzivatelu(seznam);
        assertEquals(seznam, Tymovanicko.TYMOVANICKO.getSeznamUzivatelu());
    }


    @Test
    public void testSetChat() {
        Chat chat = new Chat();
        Tymovanicko.TYMOVANICKO.setChat(chat);
        assertEquals(chat, Tymovanicko.TYMOVANICKO.getChat());
    }


    @Test
    public void testSetChatLog() {
        ChatLog chatLog = new ChatLog();
        Tymovanicko.TYMOVANICKO.setChatLog(chatLog);
        assertEquals(chatLog, Tymovanicko.TYMOVANICKO.getChatLog());
    }

    @Test
    public void testGetId() {
        String expectedId = "123";
        Tymovanicko.TYMOVANICKO.setId(expectedId);
        assertEquals(expectedId, Tymovanicko.TYMOVANICKO.getId());
    }

    @Test
    public void testSetId() {
        String id = "456";
        Tymovanicko.TYMOVANICKO.setId(id);
        assertEquals(id, Tymovanicko.TYMOVANICKO.getId());
    }

}
package cz.vse.tymovanicko.logika;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Třída TymovanickoTest kontroluje funkcionalitu této třídy
 *
 * @author ?
 * @version 1.0.0
 */
public class TymovanickoTest {

    /**
     * testSetSeznamUzivatelu kontroluje nastavení seznamu uživatelů
     */
    @Test
    public void testSetSeznamUzivatelu() {
        SeznamUzivatelu seznam = new SeznamUzivatelu();
        Tymovanicko.TYMOVANICKO.setSeznamUzivatelu(seznam);
        assertEquals(seznam, Tymovanicko.TYMOVANICKO.getSeznamUzivatelu());
    }

    /**
     * testSetChat kontroluje nastavení chatu
     */
    @Test
    public void testSetChat() {
        Chat chat = new Chat();
        Tymovanicko.TYMOVANICKO.setChat(chat);
        assertEquals(chat, Tymovanicko.TYMOVANICKO.getChat());
    }

    /**
     * testSetChatLog kontroluje nastavení ChatLogu
     */
    @Test
    public void testSetChatLog() {
        ChatLog chatLog = new ChatLog();
        Tymovanicko.TYMOVANICKO.setChatLog(chatLog);
        assertEquals(chatLog, Tymovanicko.TYMOVANICKO.getChatLog());
    }

    /**
     * testGetId kontroluje, zda se vrátí stejné Id
     */
    @Test
    public void testGetId() {
        String expectedId = "123";
        Tymovanicko.TYMOVANICKO.setId(expectedId);
        assertEquals(expectedId, Tymovanicko.TYMOVANICKO.getId());
    }

    /**
     * testSetId kontroluje, zda se nastaví správné Id
     */
    @Test
    public void testSetId() {
        String id = "456";
        Tymovanicko.TYMOVANICKO.setId(id);
        assertEquals(id, Tymovanicko.TYMOVANICKO.getId());
    }

}
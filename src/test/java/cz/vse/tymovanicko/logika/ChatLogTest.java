package cz.vse.tymovanicko.logika;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testovací třída ChatLog je na otestování dané třídy
 *
 * @author ?
 * @version 1.0.0
 */
public class ChatLogTest {

    /**
     * chatLogTest kontroluje, že se do Arraye správně přidává zpráva správně
     */
    @Test
    public void chatLogTest() {
        ChatLog chatLog = new ChatLog();
        ArrayList<String> testMessages = new ArrayList<String>();
        testMessages.add("Testová zpráva 1");
        testMessages.add("Testová zpráva 2");
        chatLog.zpravy = testMessages;
        assertEquals(testMessages, chatLog.getZpravy());
    }
}

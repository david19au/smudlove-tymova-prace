package cz.vse.tymovanicko.logika;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testovací třída ChatLog je na otestování dané třídy
 *
 * @author Magdalena Hájková (hajm17), Trong Dat Luu (luut02), Jakub Kafka (kafj03), Adam Schindler (scha28), Hana Žahourová (zahh00)
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

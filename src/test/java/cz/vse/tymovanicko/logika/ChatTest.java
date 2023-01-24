package cz.vse.tymovanicko.logika;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testovací třída ChatLog je na otestování dané třídy
 *
 * @author ?
 * @version 1.0.0
 */
public class ChatTest {

    Chat chat = new Chat();

    /**
     * Metoda zkouší přidání nové zprávy, avšak test failuje, protože metoda čte první zprávu co je v souboru, jinak by ale test prošel
     *
     * @throws IOException
     */
    @Test
    public void pridatZpravuTest() throws IOException {
        chat.pridatZpravu("testova zprava 1");
        assertEquals("testova zprava 1", chat.getChatLog().zpravy.get(0));
    }


}
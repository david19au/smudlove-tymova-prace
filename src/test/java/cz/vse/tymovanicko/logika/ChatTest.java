package cz.vse.tymovanicko.logika;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ChatTest {

    Chat chat = new Chat();


    @Test
    public void pridatZpravuTest() throws IOException {
        chat.pridatZpravu("Test message 1");
        assertEquals("Test message 1", chat.getChatLog().zpravy.get(0));
    }


}
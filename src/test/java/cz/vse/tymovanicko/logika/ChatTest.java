package cz.vse.tymovanicko.logika;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ChatTest {

    Chat chat = new Chat();




    @Test
    public void pridatZpravuTest() throws IOException {
        chat.pridatZpravu("Test message 1");
        assertEquals("Test message 1", chat.getChatLog().zpravy.get(0));
    }



}
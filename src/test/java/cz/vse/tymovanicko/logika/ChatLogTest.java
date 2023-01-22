package cz.vse.tymovanicko.logika;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChatLogTest {

    @Test
    public void chatLogTest() {
        ChatLog chatLog = new ChatLog();
        ArrayList<String> testMessages = new ArrayList<String>();
        testMessages.add("Test message 1");
        testMessages.add("Test message 2");
        chatLog.zpravy = testMessages;
        assertEquals(testMessages, chatLog.getZpravy());
    }
}

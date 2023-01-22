package cz.vse.tymovanicko.logika;

import java.util.ArrayList;

/**
 * Třída ChatLog - představuje obsah chatu.
 *
 * Tato třída je součástí aplikace Týmováníčko.
 *
 * @author     ?
 * @version    ?
 */
public class ChatLog {
    ArrayList<String> zpravy;

    ChatLog() {
        zpravy = new ArrayList<>();
    }

    public ArrayList<String> getZpravy() {
        return zpravy;
    }
}
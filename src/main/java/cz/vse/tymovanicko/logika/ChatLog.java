package cz.vse.tymovanicko.logika;

import java.util.ArrayList;

/**
 * Třída ChatLog - představuje obsah chatu.
 * Tato třída je součástí aplikace Týmováníčko.
 *
 * @author ?
 * @version ZS2022/23
 */
public class ChatLog {
    ArrayList<String> zpravy;

    /**
     * Konstruktor ChatLog vytváří nový ArrayList, do kterého se budou ukládat zprávy.
     */
    ChatLog() {
        zpravy = new ArrayList<>();
    }

    /**
     * Metodat getZpravy vrací ArrayList se zprávami.
     *
     * @return ArrayList se zprávami
     */
    public ArrayList<String> getZpravy() {
        return zpravy;
    }


}
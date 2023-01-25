package cz.vse.tymovanicko.logika;

import java.util.ArrayList;

/**
 * Třída ChatLog - představuje obsah chatu.
 * Tato třída je součástí aplikace Týmováníčko.
 *
 * @author Magdalena Hájková (hajm17), Trong Dat Luu (luut02), Jakub Kafka (kafj03), Adam Schindler (scha28), Hana Žahourová (zahh00)
 * @version 1.0.0
 */
public class ChatLog {

    // datové atributy
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
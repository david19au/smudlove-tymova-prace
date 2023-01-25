package cz.vse.tymovanicko.logika;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Třída UdalostTest testuje třídu Udalost
 *
 * @author Magdalena Hájková (hajm17), Trong Dat Luu (luut02), Jakub Kafka (kafj03), Adam Schindler (scha28), Hana Žahourová (zahh00)
 * @version 1.0.0
 */
public class UdalostTest {

    /**
     * udalostTest kontroluje, pokud je jméno, datum a lokace události stejné
     */
    @Test
    public void udalostTest() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Udalost udalost = new Udalost("jmeno udalosti", date, "lokace udalosti");
        assertEquals("jmeno udalosti", udalost.getJmenoUdalosti());
        assertEquals(sdf.format(date), udalost.getDatumUdalosti());
        assertEquals("lokace udalosti", udalost.getLokaceUdalosti());
    }

    /**
     * Metoda setSeznamJdeTest kontroluje zda funguje RSVP seznam "jde"
     */
    @Test
    public void setSeznamJdeTest() {
        Udalost udalost = new Udalost("jmeno udalost 2", new Date(), "lokace udalosti 2");
        ArrayList<String> testSeznam = new ArrayList<String>();
        testSeznam.add("Adam Testovy");
        testSeznam.add("Jana Testova");
        udalost.setSeznamJde(testSeznam);
        assertEquals(testSeznam, udalost.getSeznamJde());
    }

    /**
     * Metoda setSeznamNejdeTest kontroluje zda funguje RSVP seznam "nejde"
     */
    @Test
    public void setSeznamNejdeTest() {
        Udalost udalost = new Udalost("jmeno udalost 3", new Date(), "lokace udalosti 3");
        ArrayList<String> testSeznam = new ArrayList<String>();
        testSeznam.add("Adam Testovy");
        testSeznam.add("Jana Testova");
        udalost.setSeznamNejde(testSeznam);
        assertEquals(testSeznam, udalost.getSeznamNejde());
    }
}
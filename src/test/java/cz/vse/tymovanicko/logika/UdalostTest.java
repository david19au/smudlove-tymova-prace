package cz.vse.tymovanicko.logika;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UdalostTest {

    @Test
    public void udalostTest() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Udalost udalost = new Udalost("Event Name", date, "Event Location");
        assertEquals("Event Name", udalost.getJmenoUdalosti());
        assertEquals(sdf.format(date), udalost.getDatumUdalosti());
        assertEquals("Event Location", udalost.getLokaceUdalosti());
    }


    @Test
    public void setSeznamJdeTest() {
        Udalost udalost = new Udalost("Event Name", new Date(), "Event Location");
        ArrayList<String> testSeznam = new ArrayList<String>();
        testSeznam.add("John Doe");
        testSeznam.add("Jane Doe");
        udalost.setSeznamJde(testSeznam);
        assertEquals(testSeznam, udalost.getSeznamJde());
    }
}
package cz.vse.tymovanicko.logika;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Metoda SpravaUdalostiTest kontroluje logiku třídy SpravaUdalosti
 *
 * @author ?
 * @version 1.0.0
 */
public class SpravaUdalostiTest {

    private SpravaUdalosti sprava;
    private Udalost u1, u2, u3;

    /**
     * Metoda setUp vytváří instanci SpravaUdalosti a vytváří 3 nové proměnné pro nové události
     */
    @Before
    public void setUp() {
        sprava = new SpravaUdalosti();
        u1 = new Udalost("Event 1", new Date(), "Lokace 1");
        u2 = new Udalost("Event 2", new Date(), "Lokace 2");
        u3 = new Udalost("Event 3", new Date(), "Lokace 3");
    }

    /**
     * Metoda testVytvorUdalost vytvoří 3 nové události, vloží je a porovnává jejich hodnoty.
     * Tento test nefunguje, jelikož se kouká do pravých dat, takže je tam více událostí a také v jiném pořadí, v teorii by ale fungoval.
     */
    @Test
    public void testVytvorUdalost() {
        sprava.vytvorUdalost("Event 1", new Date(), "Lokace 1");
        sprava.vytvorUdalost("Event 2", new Date(), "Lokace 2");
        sprava.vytvorUdalost("Event 3", new Date(), "lokace 3");

        List<Udalost> udalosti = sprava.getUdalosti();
        assertEquals(3, udalosti.size());
        assertEquals("Event 1", udalosti.get(0).getJmenoUdalosti());
        assertEquals("Lokace 1", udalosti.get(0).getLokaceUdalosti());
        assertEquals("Event 2", udalosti.get(1).getJmenoUdalosti());
        assertEquals("Lokace 2", udalosti.get(1).getLokaceUdalosti());
        assertEquals("Event 3", udalosti.get(2).getJmenoUdalosti());
        assertEquals("Lokace 3", udalosti.get(2).getLokaceUdalosti());
    }

    /**
     * testUlozUdalostDoJSON kontroluje, zda existuje soubor na události
     */
    @Test
    public void testUlozUdalostiDoJSON() {
        File jsonFile = new File("target/udalosti.json");
        assertTrue(jsonFile.exists());
    }
}


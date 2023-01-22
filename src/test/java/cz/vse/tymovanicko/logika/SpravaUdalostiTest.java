package cz.vse.tymovanicko.logika;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpravaUdalostiTest {

    private SpravaUdalosti sprava;
    private Udalost u1, u2, u3;

    @Before
    public void setUp() {
        sprava = new SpravaUdalosti();
        u1 = new Udalost("Event 1", new Date(), "Location 1");
        u2 = new Udalost("Event 2", new Date(), "Location 2");
        u3 = new Udalost("Event 3", new Date(), "Location 3");
    }

    @Test
    public void testVytvorUdalost() {
        sprava.vytvorUdalost("Event 1", new Date(), "Location 1");
        sprava.vytvorUdalost("Event 2", new Date(), "Location 2");
        sprava.vytvorUdalost("Event 3", new Date(), "Location 3");

        List<Udalost> udalosti = sprava.getUdalosti();
        assertEquals(3, udalosti.size());
        assertEquals("Event 1", udalosti.get(0).getJmenoUdalosti());
        assertEquals("Location 1", udalosti.get(0).getLokaceUdalosti());
        assertEquals("Event 2", udalosti.get(1).getJmenoUdalosti());
        assertEquals("Location 2", udalosti.get(1).getLokaceUdalosti());
        assertEquals("Event 3", udalosti.get(2).getJmenoUdalosti());
        assertEquals("Location 3", udalosti.get(2).getLokaceUdalosti());
    }



    @Test
    public void testZmenRSVP() {
        u1.setSeznamJde(new ArrayList<String>());
        u1.setSeznamNejde(new ArrayList<String>());
        u1.setSeznamNeodpovedeli(new ArrayList<String>());
        u1.getSeznamNeodpovedeli().add("John");
        u1.getSeznamNeodpovedeli().add("Mary");
        u1.getSeznamNeodpovedeli().add("Bob");
        sprava.pridatUdalost(u1);

        sprava.zmenRSVP("Event 1", "John", "jdu");
        sprava.zmenRSVP("Event 1", "Mary", "nejdu");
        sprava.zmenRSVP("Event 1", "Bob", "nevim");

        assertEquals(1, u1.getSeznamJde().size());
        assertEquals("John", u1.getSeznamJde().get(0));
        assertEquals(1, u1.getSeznamNejde().size());
        assertEquals("Mary", u1.getSeznamNejde().get(0));
        assertEquals(1, u1.getSeznamNeodpovedeli().size());
        assertTrue(u1.getSeznamNeodpovedeli().contains("Bob"));
    }

    @Test
    public void testUlozUdalostiDoJSON() {
        sprava.pridatUdalost(u1);
        sprava.pridatUdalost(u2);
        sprava.pridatUdalost(u3);

        File jsonFile = new File("target/udalosti.json");
        assertTrue(jsonFile.exists());
    }
}


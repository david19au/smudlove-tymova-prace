package cz.vse.tymovanicko.logika;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeznamUzivateluTest {

    private SeznamUzivatelu seznam;
    private Uzivatel u1, u2, u3;

    @Before
    public void setUp() {
        seznam = new SeznamUzivatelu();
        u1 = new Uzivatel("joe@email.com", "Hana", "Žahourová", "password1");
        u2 = new Uzivatel("sara@email.com", "Klára", "Otrubová", "password2");
        u3 = new Uzivatel("bob@email.com", "David", "Shejbal", "password3");
    }

    @Test
    public void testVlozUzivateleDoSeznamu() {
        seznam.vlozUzivateleDoSeznamu(u1);
        seznam.vlozUzivateleDoSeznamu(u2);
        seznam.vlozUzivateleDoSeznamu(u3);

        assertEquals(3, seznam.getUzivatele().size());
        assertTrue(seznam.getUzivatele().contains(u1));
        assertTrue(seznam.getUzivatele().contains(u2));
        assertTrue(seznam.getUzivatele().contains(u3));
    }


    @Test
    public void testHesloUzivatele() {
        seznam.vlozUzivateleDoSeznamu(u1);
        seznam.vlozUzivateleDoSeznamu(u2);
        seznam.vlozUzivateleDoSeznamu(u3);

        assertEquals("password1", seznam.hesloUzivatele("joe@email.com"));
        assertEquals("password2", seznam.hesloUzivatele("sara@email.com"));
        assertEquals("password3", seznam.hesloUzivatele("bob@email.com"));
    }

}

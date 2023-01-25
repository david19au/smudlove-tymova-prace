package cz.vse.tymovanicko.logika;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testovací třída SeznamUzivateluTest je na otestování dané třídy
 *
 * @author Magdalena Hájková (hajm17), Trong Dat Luu (luut02), Jakub Kafka (kafj03), Adam Schindler (scha28), Hana Žahourová (zahh00)
 * @version 1.0.0
 */
public class SeznamUzivateluTest {

    private SeznamUzivatelu seznam;
    private Uzivatel u1, u2, u3;

    /**
     * Metoda setUp zakládá nový seznam uživatelů a vytváří nové uživatele.
     */
    @Before
    public void setUp() {
        seznam = new SeznamUzivatelu();
        u1 = new Uzivatel("hana@email.com", "Hana", "Žahourová", "password1");
        u2 = new Uzivatel("klara@email.com", "Klára", "Otrubová", "password2");
        u3 = new Uzivatel("david@email.com", "David", "Shejbal", "password3");
    }

    /**
     * Metoda testVlozUzivateleDoSeznamu vkládá uživatele do seznamu, potom kontroluje, že v seznamu jsou jenom 3 uživatelé a ty uživatelé sedí.
     */
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

    /**
     * Metoda testHesloUzivatele vkládá uživatele do seznamu a potom z nich porovnává hesla podle emailů.
     */
    @Test
    public void testHesloUzivatele() {
        seznam.vlozUzivateleDoSeznamu(u1);
        seznam.vlozUzivateleDoSeznamu(u2);
        seznam.vlozUzivateleDoSeznamu(u3);

        assertEquals("password1", seznam.hesloUzivatele("hana@email.com"));
        assertEquals("password2", seznam.hesloUzivatele("klara@email.com"));
        assertEquals("password3", seznam.hesloUzivatele("david@email.com"));
    }

}

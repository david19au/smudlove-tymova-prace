package cz.vse.tymovanicko.logika;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Metoda UzivatelTest kontroluje funkčnost metod této třídy
 *
 * @author ?
 * @version 1.0.0
 */


public class UzivatelTest {
    Uzivatel u = new Uzivatel("user@email.com", "John", "Doe", "password");

    /**
     * Metoda testGetEmail kontroluje, že vrací správně email
     */
    @Test
    public void testGetEmail() {
        assertEquals("adam@email.com", u.getEmail());
    }

    /**
     * Metoda testGetKrestniJmeno kontroluje, že vrací správně křestní jméno
     */
    @Test
    public void testGetKrestniJmeno() {
        assertEquals("Adam", u.getKrestniJmeno());
    }

    /**
     * Metoda testGetPrijmeni kontroluje, že vrací správně příjmení
     */
    @Test
    public void testGetPrijmeni() {
        assertEquals("Zátopek", u.getPrijmeni());
    }

    /**
     * Metoda testGetHeslo kontroluje, že vrací správně heslo (nešifrovaně)
     */
    @Test
    public void testGetHeslo() {
        assertEquals("heslo", u.getHeslo());
    }

    /**
     * Metoda testSetHeslo kontroluje, že nastavuje správně heslo
     */
    @Test
    public void testSetHeslo() {
        u.setHeslo("noveheslo");
        assertEquals("noveheslo", u.getHeslo());
    }

    /**
     * Metoda testSetEmail kontroluje, že nastavuje správně Email
     */
    @Test
    public void testSetEmail() {
        u.setEmail("adamnovy@email.com");
        assertEquals("adamnovy@email.com", u.getEmail());
    }

    /**
     * Metoda testSetKrestniJmeno kontroluje, že nastavuje správně křestní jméno
     */
    @Test
    public void testSetKrestniJmeno() {
        u.setKrestniJmeno("Jana");
        assertEquals("Jana", u.getKrestniJmeno());
    }

    /**
     * Metoda testSetPrijmeni kontroluje, že nastavuje správně příjmení
     */
    @Test
    public void testSetPrijmeni() {
        u.setPrijmeni("Zátopková");
        assertEquals("Zátopková", u.getPrijmeni());
    }
}

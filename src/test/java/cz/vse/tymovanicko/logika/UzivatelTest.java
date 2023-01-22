package cz.vse.tymovanicko.logika;

import org.junit.Test;
import static org.junit.Assert.*;

public class UzivatelTest {
    Uzivatel u = new Uzivatel("user@email.com", "John", "Doe", "password");

    @Test
    public void testGetEmail() {
        assertEquals("user@email.com", u.getEmail());
    }

    @Test
    public void testGetKrestniJmeno() {
        assertEquals("John", u.getKrestniJmeno());
    }

    @Test
    public void testGetPrijmeni() {
        assertEquals("Doe", u.getPrijmeni());
    }

    @Test
    public void testGetHeslo() {
        assertEquals("password", u.getHeslo());
    }

    @Test
    public void testSetHeslo() {
        u.setHeslo("newpassword");
        assertEquals("newpassword", u.getHeslo());
    }

    @Test
    public void testSetEmail() {
        u.setEmail("newuser@email.com");
        assertEquals("newuser@email.com", u.getEmail());
    }

    @Test
    public void testSetKrestniJmeno() {
        u.setKrestniJmeno("Jane");
        assertEquals("Jane", u.getKrestniJmeno());
    }

    @Test
    public void testSetPrijmeni() {
        u.setPrijmeni("Smith");
        assertEquals("Smith", u.getPrijmeni());
    }
}

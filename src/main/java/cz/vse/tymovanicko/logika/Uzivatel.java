package cz.vse.tymovanicko.logika;

/**
 * Třída Uzivatel - představuje uzivatele a jeho stav.
 * Tato třída je součástí aplikace Týmováníčko.
 * "Uzivatel" reprezentuje uzivatele v aplikaci.
 *
 * @author ?
 * @version ZS2022/23
 */
public class Uzivatel {

    private String email;
    private String krestniJmeno;
    private String prijmeni;
    private String heslo;

    /**
     * Konstruktor třídy Uzivatel
     *
     * @param email        email uživatele
     * @param krestniJmeno křestní jméno uživatele
     * @param prijmeni     příjmení uživatele
     * @param heslo        heslo uživatele
     */
    public Uzivatel(String email, String krestniJmeno, String prijmeni, String heslo) {
        this.email = email;
        this.krestniJmeno = krestniJmeno;
        this.prijmeni = prijmeni;
        this.heslo = heslo;
    }

    /**
     * Tato metoda je getter křestního jména uživatele.
     * Vrací jméno uživatele ve Stringu
     *
     * @return String jméno uživatele
     */
    public String getKrestniJmeno() {
        return krestniJmeno;
    }

    /**
     * Metoda nastavuje křestní jméno.
     *
     * @param krestniJmeno nastavované křestní jméno
     */
    public void setKrestniJmeno(String krestniJmeno) {
        this.krestniJmeno = krestniJmeno;
    }

    /**
     * Tato metoda je getter příjmení uživatele.
     * Vrací příjmení uživatele ve Stringu
     *
     * @return String příjmení uživatele
     */
    public String getPrijmeni() {
        return prijmeni;
    }

    /**
     * Metoda nastavuje příjmení.
     *
     * @param prijmeni nastavované příjmení
     */
    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    /**
     * Tato metoda je getter emailu uživatele.
     * Vrací email uživatele ve Stringu
     *
     * @return String emailu uživatele
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metoda nastavuje email.
     *
     * @param email nastavovaný email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Tato metoda je getter hesla uživatele.
     * Vrací heslo uživatele ve Stringu
     *
     * @return String hesla uživatele
     */
    public String getHeslo() {
        return heslo;
    }

    /**
     * Metoda nastavuje heslo.
     *
     * @param heslo nastavované heslo
     */
    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }
}


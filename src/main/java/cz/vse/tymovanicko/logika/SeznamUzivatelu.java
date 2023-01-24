package cz.vse.tymovanicko.logika;

import java.util.ArrayList;

/**
 * Třída SeznamUzivatelu - představuje seznam uživatelů.
 * Tato třída je součástí aplikace Týmováníčko.
 *
 * @author ?
 * @version ZS2022/23
 */
public class SeznamUzivatelu {

    // datové atributy
    private ArrayList<Uzivatel> uzivatele;

    /**
     * Konstruktor SeznamUzivatelu vytvoří nový ArrayList do kterého se ukládají noví uživatelé
     */
    public SeznamUzivatelu() {
        uzivatele = new ArrayList<>();
    }

    /**
     * Metoda vlozUzivateleDoSeznamu vezme uživatele a vloží jej do seznamu s uživateli
     *
     * @param uzivatel
     */
    public void vlozUzivateleDoSeznamu(Uzivatel uzivatel) {
        uzivatele.add(uzivatel);
    }

    /**
     * Metoda emailyUzivatelu prochází emailama uživatelů v seznamu.
     *
     * @return E-maily uživatelů ve String formě
     */
    public String emailyUzivatelu() {
        StringBuilder emaily = new StringBuilder(",");
        for (Uzivatel uzivatel : uzivatele) {
            emaily.append(uzivatel.getEmail()).append(",");
        }
        return emaily.toString();
    }

    /**
     * Metoda vrací zpět saltnuté heslo daného uživatele s určitým emailem.
     *
     * @param email uživatele
     * @return salted heslo uživatele
     */
    public String hesloUzivatele(String email) {
        String heslo = null;
        for (Uzivatel uzivatel : uzivatele) {
            if (uzivatel.getEmail().equals(email)) {
                heslo = uzivatel.getHeslo();
            }
        }
        return heslo;
    }

    /**
     * Metoda getUzivatele vrací seznam uživatelů.
     *
     * @return seznam uživatelů
     */
    public ArrayList<Uzivatel> getUzivatele() {
        return uzivatele;
    }
}

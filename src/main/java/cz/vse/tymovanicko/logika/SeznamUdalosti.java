package cz.vse.tymovanicko.logika;

import java.util.ArrayList;

/**
 * Třída SeznamUdalosti - představuje seznam událostí.
 *
 * Tato třída je součástí aplikace Týmováníčko.
 *
 * @author     ?
 * @version    ?
 */
public class SeznamUdalosti {

    private ArrayList<Udalost> udalosti;

    public SeznamUdalosti() {
        udalosti = new ArrayList<>();
    }

    public void vlozUdalostiDoSeznamu(Udalost udalost) {
        udalosti.add(udalost);
    }

    public void vlozUcastnikaNevi(Uzivatel uzivatel) {
        Tymovanicko.TYMOVANICKO.getUdalost().pridatNevi(uzivatel);
    }

    public void vlozUcastnikaPrijde(Uzivatel uzivatel) {
        Tymovanicko.TYMOVANICKO.getUdalost().pridatPrijde(uzivatel);
    }

    public void vlozUcastnikaNeprijde(Uzivatel uzivatel) {
        Tymovanicko.TYMOVANICKO.getUdalost().pridatNeprijde(uzivatel);
    }


    public ArrayList<Udalost> getUdalosti() {
        return udalosti;
    }


}

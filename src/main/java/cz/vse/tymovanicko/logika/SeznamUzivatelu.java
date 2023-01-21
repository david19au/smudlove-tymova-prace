package cz.vse.tymovanicko.logika;

import java.util.ArrayList;

public class SeznamUzivatelu {

    private ArrayList<Uzivatel> uzivatele;

    public SeznamUzivatelu() {
        uzivatele = new ArrayList<>();
    }

    public void vlozUzivateleDoSeznamu(Uzivatel uzivatel) {
        uzivatele.add(uzivatel);
    }

    public String emailyUzivatelu() {
        String emaily = ",";
        for (Uzivatel uzivatel : uzivatele) {
            emaily += uzivatel.getEmail() + ",";
        }
        return emaily;
    }
}

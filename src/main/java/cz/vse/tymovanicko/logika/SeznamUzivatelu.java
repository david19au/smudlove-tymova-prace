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

    public String hesloUzivatele(String email) {
        String heslo = null;
        for (Uzivatel uzivatel : uzivatele) {
            if (uzivatel.getEmail().equals(email)) {
                heslo = uzivatel.getHeslo();
            }
        }
        return heslo;
    }

    public ArrayList<Uzivatel> getUzivatele() {
        return uzivatele;
    }
}

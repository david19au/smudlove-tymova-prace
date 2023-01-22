package cz.vse.tymovanicko.logika;

import java.util.ArrayList;

public class SeznamUdalosti {

    private ArrayList<Udalost> udalosti;

    public SeznamUdalosti() {
        udalosti = new ArrayList<>();
    }

    public void vlozUdalostiDoSeznamu(Udalost udalost) {
        udalosti.add(udalost);
    }

    public ArrayList<Udalost> getUdalosti() {
        return udalosti;
    }


}

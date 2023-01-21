package cz.vse.tymovanicko.logika;

import java.util.ArrayList;
import java.util.List;

public class Tym {

    private String jmenoTymu;
    private String ucelTymu;
    private List<Uzivatel> seznamClenu = new ArrayList<>();
    private List<Udalost> seznamUdalosti = new ArrayList<>();

    public Tym(String jmenoTymu, String ucelTymu) {
        this.jmenoTymu = jmenoTymu;
        this.ucelTymu = ucelTymu;
    }

    public void pridejClena(Uzivatel clen) {
        seznamClenu.add(clen);
    }

    public List<Uzivatel> getSeznamClenu() {
        return seznamClenu;
    }

    public List<Udalost> getSeznamUdalosti() {
        return seznamUdalosti;
    }
}

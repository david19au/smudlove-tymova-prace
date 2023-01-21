package cz.vse.tymovanicko.logika;

import java.util.ArrayList;
import java.util.List;

public class JsonUdalost {

    private String nazev;
    private String datumKonani;
    private String mistoKonani;
    private List<Uzivatel> seznamUcastniku = new ArrayList<>();

    public JsonUdalost(String nazev, String datumKonani, String mistoKonani) {
        this.nazev = nazev;
        this.datumKonani = datumKonani;
        this.mistoKonani = mistoKonani;
    }

    public void pridejUcasntika(Uzivatel ucastnik) {
        seznamUcastniku.add(ucastnik);
    }


    public List<Uzivatel> getSeznamUcastniku() {
        return seznamUcastniku;
    }

    public String getSeznamUcastnikuVypis() {
        String vypis = "";
        for (Uzivatel uzivatelDemo : seznamUcastniku) {
            vypis += uzivatelDemo.getKrestniJmeno() + ", ";
        }
        return vypis;
    }

    /**
    public int idUcastnika(String ucastnik) {
        for (JsonUzivatel uzivatelDemo : seznamUcastniku) {
            if (Objects.equals(uzivatelDemo.getKrestniJmeno(), ucastnik)) {
                return uzivatelDemo.getId();
            }
        }
        return 0;
    }
     */

    public String getNazev() {
        return nazev;
    }

    public String getMistoKonani() {
        return mistoKonani;
    }

    public String getDatumKonani() {
        return datumKonani;
    }
}


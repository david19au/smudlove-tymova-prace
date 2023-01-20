package cz.vse.tymovanicko.logika;

import java.util.ArrayList;
import java.util.List;

public class JsonTym {

    private String jmenoTymu;
    private String ucelTymu;
    private List<JsonUzivatel> seznamClenu = new ArrayList<>();
    private List<JsonUdalost> seznamUdalosti = new ArrayList<>();

    public JsonTym(String jmenoTymu, String ucelTymu) {
        this.jmenoTymu = jmenoTymu;
        this.ucelTymu = ucelTymu;
    }

    public void pridejClena(JsonUzivatel clen) {
        seznamClenu.add(clen);
    }

    public List<JsonUzivatel> getSeznamClenu () {
        return seznamClenu;
    }

    public List<JsonUdalost> getSeznamUdalosti() {
        return seznamUdalosti;
    }
}

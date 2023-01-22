package cz.vse.tymovanicko.logika;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Třída Udalost - představuje udalost a její stav.
 * Tato třída je součástí aplikace Týmováníčko.
 * "Udalost" reprezentuje událost v aplikaci.
 *
 * @author     ?
 * @version    ?
 */
public class Udalost {
    private String jmenoUdalosti;
    private String datumUdalosti;
    private String lokaceUdalosti;
    private List<String> seznamJde;
    private List<String> seznamNejde;
    private List<String> seznamNeodpovedeli;

    public Udalost(String jmenoUdalosti, Date datumUdalosti, String lokaceUdalosti) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        this.jmenoUdalosti = jmenoUdalosti;
        this.datumUdalosti = sdf.format(datumUdalosti);
        this.lokaceUdalosti = lokaceUdalosti;
    }

    public String getJmenoUdalosti() {
        return jmenoUdalosti;
    }

    public void setJmenoUdalosti(String jmenoUdalosti) {
        this.jmenoUdalosti = jmenoUdalosti;
    }

    public String getDatumUdalosti() {
        return datumUdalosti;
    }

    public void setDatumUdalosti(Date datumUdalosti) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        this.datumUdalosti = sdf.format(datumUdalosti);
    }

    public String getLokaceUdalosti() {
        return lokaceUdalosti;
    }

    public void setLokaceUdalosti(String lokaceUdalosti) {
        this.lokaceUdalosti = lokaceUdalosti;
    }

    public List<String> getSeznamJde() {
        return seznamJde;
    }

    public void setSeznamJde(List<String> seznamJde) {
        this.seznamJde = seznamJde;
    }

    public List<String> getSeznamNejde() {
        return seznamNejde;
    }

    public void setSeznamNejde(List<String> seznamNejde) {
        this.seznamNejde = seznamNejde;
    }

    public List<String> getSeznamNeodpovedeli() {
        return seznamNeodpovedeli;
    }

    public void setSeznamNeodpovedeli(List<String> seznamNeodpovedeli) {
        this.seznamNeodpovedeli = seznamNeodpovedeli;
    }
}

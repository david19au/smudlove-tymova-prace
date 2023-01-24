package cz.vse.tymovanicko.logika;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Třída Udalost - představuje udalost a její stav.
 * Tato třída je součástí aplikace Týmováníčko.
 * "Udalost" reprezentuje událost v aplikaci.
 *
 * @author ?
 * @version ZS2022/23
 */
public class Udalost {
    private String jmenoUdalosti;
    private String datumUdalosti;
    private String lokaceUdalosti;
    private List<String> seznamJde;
    private List<String> seznamNejde;

    /**
     * Konstruktor pro třídu Udalost.
     * Vytváří se tu také instance SimpleDateFormat pro nastavení evropského datového formátu dd.MM.yyyy
     * @param jmenoUdalosti jméno události
     * @param datumUdalosti datum kdy se událost bude konat v dd.MM.yyyy
     * @param lokaceUdalosti kde se událost bude konat
     */
    public Udalost(String jmenoUdalosti, Date datumUdalosti, String lokaceUdalosti) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        this.jmenoUdalosti = jmenoUdalosti;
        this.datumUdalosti = sdf.format(datumUdalosti);
        this.lokaceUdalosti = lokaceUdalosti;
    }
    /**
     * Tato metoda je getter jména události.
     * Vrací jméno události ve Stringu
     * @return String jména události
     */
    public String getJmenoUdalosti() {
        return jmenoUdalosti;
    }
    /**
     * Tato metoda je getter datumu dané události - kdy se událost bude konat.
     * Vrací kdy se bude konat událost v datumu ve Stringu
     * @return String datum konání
     */
    public String getDatumUdalosti() {
        return datumUdalosti;
    }

    /**
     * Setter datumu událostí, kde datum musí být ve formátu dd.MM.yyyy
     * @param datumUdalosti
     */
    public void setDatumUdalosti(Date datumUdalosti) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        this.datumUdalosti = sdf.format(datumUdalosti);
    }
    /**
     * Tato metoda je getter lokace dané události - kde se událost bude konat.
     * Vrací kde se bude konat událost ve Stringu
     * @return String místo konání akce
     */
    public String getLokaceUdalosti() {
        return lokaceUdalosti;
    }

    /**
     * Metoda vrací seznam lidí co jde
     * @return seznam lidí co jde
     */
    public List<String> getSeznamJde() {
        return seznamJde;
    }

    /**
     * Metoda nastavuje seznam lidí co jde.
     * @param seznamJde seznam jdoucích lidí
     */
    public void setSeznamJde(List<String> seznamJde) {
        this.seznamJde = seznamJde;
    }
    /**
     * Metoda vrací seznam lidí co nejde
     * @return seznam lidí co nejde
     */
    public List<String> getSeznamNejde() {
        return seznamNejde;
    }
    /**
     * Metoda nastavuje seznam lidí, kteří ohlásili, že nejdou na akci.
     * @param seznamNejde seznam lidí které se nezúčastní události
     */
    public void setSeznamNejde(List<String> seznamNejde) {
        this.seznamNejde = seznamNejde;
    }

}

package cz.vse.tymovanicko.logika;

import java.util.List;

public class Udalost {

    private String nazev;
    private String datumKonani;
    private String mistoKonani;
    private List<Uzivatel> seznamUcastnikuPrijdou;
    private List<Uzivatel> seznamUcastnikuNeprijdou;
    private List<Uzivatel> seznamUcastnikuNevi;

    public Udalost(String nazev, String datumKonani, String mistoKonani, List<Uzivatel> seznamUcastnikuPrijdou, List<Uzivatel> seznamUcastnikuNeprijdou, List<Uzivatel> seznamUcastnikuNevi) {
        this.nazev = nazev;
        this.datumKonani = datumKonani;
        this.mistoKonani = mistoKonani;
        this.seznamUcastnikuPrijdou = seznamUcastnikuPrijdou;
        this.seznamUcastnikuNeprijdou = seznamUcastnikuNeprijdou;
        this.seznamUcastnikuNevi = seznamUcastnikuNevi;
    }

    public void pridatPrijde(Uzivatel uzivatel) {
        seznamUcastnikuPrijdou.add(uzivatel);
    }

    public void pridatNeprijde(Uzivatel uzivatel) {
        seznamUcastnikuNeprijdou.add(uzivatel);
    }

    public void pridatNevi(Uzivatel uzivatel) {
        seznamUcastnikuNevi.add(uzivatel);
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getDatumKonani() {
        return datumKonani;
    }

    public void setDatumKonani(String datumKonani) {
        this.datumKonani = datumKonani;
    }

    public String getMistoKonani() {
        return mistoKonani;
    }

    public void setMistoKonani(String mistoKonani) {
        this.mistoKonani = mistoKonani;
    }

    public List<Uzivatel> getSeznamUcastnikuPrijdou() {
        return seznamUcastnikuPrijdou;
    }

    public void setSeznamUcastnikuPrijdou(List<Uzivatel> seznamUcastnikuPrijdou) {
        this.seznamUcastnikuPrijdou = seznamUcastnikuPrijdou;
    }

    public List<Uzivatel> getSeznamUcastnikuNeprijdou() {
        return seznamUcastnikuNeprijdou;
    }

    public void setSeznamUcastnikuNeprijdou(List<Uzivatel> seznamUcastnikuNeprijdou) {
        this.seznamUcastnikuNeprijdou = seznamUcastnikuNeprijdou;
    }

    public List<Uzivatel> getSeznamUcastnikuNevi() {
        return seznamUcastnikuNevi;
    }

    public void setSeznamUcastnikuNevi(List<Uzivatel> seznamUcastnikuNevi) {
        this.seznamUcastnikuNevi = seznamUcastnikuNevi;
    }
}


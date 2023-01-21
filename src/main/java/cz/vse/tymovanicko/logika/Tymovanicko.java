package cz.vse.tymovanicko.logika;

public class Tymovanicko {

    private SeznamUzivatelu seznamUzivatelu;
    private Uzivatel uzivatel;

    public Tymovanicko() {
        zalozTymovanicko();
    }

    private void zalozTymovanicko() {
        seznamUzivatelu = new SeznamUzivatelu();
    }

    public SeznamUzivatelu getSeznamUzivatelu() {
        return seznamUzivatelu;
    }
}

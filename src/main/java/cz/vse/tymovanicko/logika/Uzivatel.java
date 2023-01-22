package cz.vse.tymovanicko.logika;

public class Uzivatel {

    private String email;
    private String krestniJmeno;
    private String prijmeni;
    private String heslo;

    public Uzivatel(String email, String krestniJmeno, String prijmeni, String heslo) {
        this.email = email;
        this.krestniJmeno = krestniJmeno;
        this.prijmeni = prijmeni;
        this.heslo = heslo;
    }

    public String getKrestniJmeno() {
        return krestniJmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public String getEmail() {
        return email;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setKrestniJmeno(String krestniJmeno) {
        this.krestniJmeno = krestniJmeno;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }
}


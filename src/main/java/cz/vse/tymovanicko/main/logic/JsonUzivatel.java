package cz.vse.tymovanicko.main.logic;

public class JsonUzivatel {

    private int id;
    private String pohlavi;
    private String krestniJmeno;
    private String prijmeni;
    private int vek;
    private String email;
    private String heslo;
    private String roleVTymu;

    public JsonUzivatel(int id, String pohlavi, String krestniJmeno, String prijmeni, int vek, String email, String heslo, String role) {
        this.id = id;
        this.pohlavi = pohlavi;
        this.krestniJmeno = krestniJmeno;
        this.prijmeni = prijmeni;
        this.vek = vek;
        this.email = email;
        this.heslo = heslo;
        this.roleVTymu = role;
    }

    public int getId() {
        return id;
    }

    public String getPohlavi() {
        return pohlavi;
    }

    public String getKrestniJmeno() {
        return krestniJmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public int getVek() {
        return vek;
    }

    public String getEmail() {
        return email;
    }

    public String getHeslo() {
        return heslo;
    }

    public String getRoleVTymu() {
        return roleVTymu;
    }
}


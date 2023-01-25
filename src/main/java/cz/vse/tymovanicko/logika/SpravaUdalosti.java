package cz.vse.tymovanicko.logika;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Třída SpravaUdalosti spravuje události v programu.
 * Vytváří a zapisuje události, mění RSVP hráčů a zapisuje + načítá události z JSONu
 *
 * @author Magdalena Hájková (hajm17), Trong Dat Luu (luut02), Jakub Kafka (kafj03), Adam Schindler (scha28), Hana Žahourová (zahh00)
 * @version 1.0.0
 */
public class SpravaUdalosti {

    // datové atributy
    private final Gson gson;
    private ArrayList<Udalost> udalosti;

    /**
     * Konstruktor třídy SpravaUdalosti, která vytváří nový ArrayList s událostmi a novou instanci GsonBuilderu.
     * Snaží se přečíst JSON s událostmi a Gson jej populuje.
     */
    public SpravaUdalosti() {
        udalosti = new ArrayList<Udalost>();
        gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader("data/udalosti.json")) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(jsonElement);
            udalosti = gson.fromJson(jsonInString, new TypeToken<List<Udalost>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda vytvorUdalost vytváří novou událost a zpracuje jméno události, kdy se událost koná a kde se koná.
     * Zároveň vytvoří ArrayListy, do kterých se ukládají stringy jmen lidí co na tuto událost jdou.
     * Jako poslední zavolá metoda pridatUdalost a událost se přidá do seznamu.
     *
     * @param jmenoUdalosti
     * @param datumUdalosti
     * @param lokaceUdalosti
     */
    public void vytvorUdalost(String jmenoUdalosti, Date datumUdalosti, String lokaceUdalosti) {
        Udalost udalost = new Udalost(jmenoUdalosti, datumUdalosti, lokaceUdalosti);
        udalost.setSeznamJde(new ArrayList<String>());
        udalost.setSeznamNejde(new ArrayList<String>());
        pridatUdalost(udalost);
    }

    /**
     * Metoda smazUdalost maže událost na bázi jejího jména.
     * For-loop projde události, kde porovnává jména událostí a pokud se nějaká bude rovnat, tak ji smaže ze seznamu.
     * Po smazání se uloží JSON soubor s událostmi a znovu načte, aby se aktualizoval.
     *
     * @param jmenoUdalosti jméno události které se má smazat
     */
    public void smazUdalost(String jmenoUdalosti) {
        for (Udalost udalost : udalosti) {
            if (udalost.getJmenoUdalosti().equals(jmenoUdalosti)) {
                udalosti.remove(udalost);
                ulozUdalostiDoJSON();
                try (Reader reader = new FileReader("data/udalosti.json")) {
                    JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
                    String jsonInString = gson.toJson(jsonElement);
                    udalosti = gson.fromJson(jsonInString, new TypeToken<List<Udalost>>() {
                    }.getType());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }

    /**
     * Metoda přidává událost do seznamu a ukládá jej do JSONu (volá metodu ulozUdalostiDoJSON
     *
     * @param udalost daná událost
     */
    public void pridatUdalost(Udalost udalost) {
        udalosti.add(udalost);
        ulozUdalostiDoJSON();
    }

    /**
     * Tato metoda je getter událostí.
     * Vrací instanci udalosti
     *
     * @return instance udalosti
     */
    public List<Udalost> getUdalosti() {
        return udalosti;
    }

    /**
     * Tato metoda je setter událostí.
     * Nastavuje instanci udalosti
     */
    public void setUdalosti(ArrayList<Udalost> udalosti) {
        this.udalosti = udalosti;
    }

    /**
     * Metoda změň RSVP mění status uživatele na události, zda jde na událost jde, či ne.
     * Přijímá jako parametry jméno události, jméno člena a statusu.
     * For-loop prochází událostmi v seznamu a páruje se se shodným jménem události, pokud se spáruje, porovná status, který uživatel dal a přidá jej do příslušného seznamu.
     *
     * @param jmenoUdalosti jméno události, kde se má změnit RSVP
     * @param jmenoClena    jméno člena, který si chce změnit RSVP
     * @param status        status, který ten člen chce mít
     */
    public void zmenRSVP(Udalost jmenoUdalosti, String jmenoClena, String status) {
        for (Udalost udalost : udalosti) {
            if (udalost.getJmenoUdalosti().equals(jmenoUdalosti.getJmenoUdalosti())) {
                if (udalost.getDatumUdalosti().equals(jmenoUdalosti.getDatumUdalosti())) {
                    if (udalost.getLokaceUdalosti().equals(jmenoUdalosti.getLokaceUdalosti())) {
                        if (status.equals("jdu")) {
                            udalost.getSeznamJde().add(jmenoClena);
                        } else if (status.equals("nejdu")) {
                            udalost.getSeznamNejde().add(jmenoClena);
                        } else {
                            return;
                        }
                        ulozUdalostiDoJSON();
                    }
                }
            }
        }
    }

    /**
     * Metoda ulozUdalostiDoJSON ukládá seznam do JSONu
     * Používá se zde BufferedWriter pro zápis do souboru a try-catch, v případě kdyby došlo k nějaké chybě.
     */
    public void ulozUdalostiDoJSON() {
        String json = gson.toJson(udalosti);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + "udalosti.json"))) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda getJde vrací seznam uživatelů, kteří odpověděli, že jdou na danou událost.
     *
     * @param jmenoUdalosti jméno události
     * @return ArrayList s lidmi co jdou na danou akci
     */
    public Collection<String> getJde(Udalost jmenoUdalosti) {
        for (Udalost udalost : udalosti) {
            if (udalost.getJmenoUdalosti().equals(jmenoUdalosti.getJmenoUdalosti())) {
                if (udalost.getDatumUdalosti().equals(jmenoUdalosti.getDatumUdalosti())) {
                    if (udalost.getLokaceUdalosti().equals(jmenoUdalosti.getLokaceUdalosti())) {
                        return udalost.getSeznamJde();
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * Metoda getNejde vrací seznam uživatelů, kteří odpověděli, že nejdou na danou událost.
     *
     * @param jmenoUdalosti jméno události
     * @return ArrayList s lidmi co nejdou na danou akci
     */
    public Collection<String> getNejde(Udalost jmenoUdalosti) {
        for (Udalost udalost : udalosti) {
            if (udalost.getJmenoUdalosti().equals(jmenoUdalosti.getJmenoUdalosti())) {
                if (udalost.getDatumUdalosti().equals(jmenoUdalosti.getDatumUdalosti())) {
                    if (udalost.getLokaceUdalosti().equals(jmenoUdalosti.getLokaceUdalosti())) {
                        return udalost.getSeznamNejde();
                    }
                }
            }
        }
        return new ArrayList<>();
    }

}

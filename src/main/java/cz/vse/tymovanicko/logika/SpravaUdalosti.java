package cz.vse.tymovanicko.logika;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*******************************************************************************
 * Třída SpravaUdalosti spravuje události v programu.
 * Vytváří a zapisuje události, mění RSVP hráčů a zapisuje + načítá události z JSONu
 *
 * @author ?
 * @version ?
 */
public class SpravaUdalosti {
    private List<Udalost> udalosti;
    private Gson gson;

    /**
     * TODO komentář k tomuhle, ArrayListy povolují jenom String, jelikož tam budeme ukládat jména hráčů.
     *
     * @param jmenoUdalosti
     * @param datumUdalosti
     * @param lokaceUdalosti
     */
    public void vytvorUdalost(String jmenoUdalosti, Date datumUdalosti, String lokaceUdalosti) {
        Udalost udalost = new Udalost(jmenoUdalosti, datumUdalosti, lokaceUdalosti);
        udalost.setSeznamJde(new ArrayList<String>());
        udalost.setSeznamNejde(new ArrayList<String>());
        udalost.setSeznamNeodpovedeli(new ArrayList<String>());
        pridatUdalost(udalost);
    }


    public SpravaUdalosti() {
        udalosti = new ArrayList<Udalost>();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void pridatUdalost(Udalost udalost) {
        udalosti.add(udalost);
        ulozUdalostiDoJSON();
    }

    public List<Udalost> getUdalosti() {
        return udalosti;
    }

    public void zmenRSVP(String jmenoUdalosti, String jmenoClena, String status) {
        for (Udalost udalost : udalosti) {
            if (udalost.getJmenoUdalosti().equals(jmenoUdalosti)) {
                if (status.equals("jdu")) {
                    udalost.getSeznamJde().add(jmenoClena);
                    udalost.getSeznamNeodpovedeli().remove(jmenoClena);
                } else if (status.equals("nejdu")) {
                    udalost.getSeznamNejde().add(jmenoClena);
                    udalost.getSeznamNeodpovedeli().remove(jmenoClena);
                } else {
                    return;
                }
                ulozUdalostiDoJSON();
            }
        }
    }

    public void ulozUdalostiDoJSON() {
        String json = gson.toJson(udalosti);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("target/" + "udalosti.json"))) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Udalost> nactiUdalostiZJSON(String filePath) {
        List<Udalost> events = new ArrayList<Udalost>();
        try (BufferedReader reader = new BufferedReader(new FileReader("target/" + "udalosti.json"))) {
            Udalost[] eventsArray = gson.fromJson(reader, Udalost[].class);
            events = Arrays.asList(eventsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }
}

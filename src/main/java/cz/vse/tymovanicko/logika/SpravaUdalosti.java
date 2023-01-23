package cz.vse.tymovanicko.logika;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
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
    private ArrayList<Udalost> udalosti;
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
        pridatUdalost(udalost);
    }

    public void smazUdalost(String jmenoUdalosti) {
        for (Udalost udalost : udalosti) {
            if (udalost.getJmenoUdalosti().equals(jmenoUdalosti)) {
                udalosti.remove(udalost);
                ulozUdalostiDoJSON();
                try (Reader reader = new FileReader("target/udalosti.json")) {
                    JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
                    String jsonInString = gson.toJson(jsonElement);
                    udalosti = gson.fromJson(jsonInString, new TypeToken<List<Udalost>>() {}.getType());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }


    public SpravaUdalosti() {
        udalosti = new ArrayList<Udalost>();
        gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader("target/udalosti.json")) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(jsonElement);
            udalosti = gson.fromJson(jsonInString, new TypeToken<List<Udalost>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pridatUdalost(Udalost udalost) {
        udalosti.add(udalost);
        ulozUdalostiDoJSON();
    }

    public List<Udalost> getUdalosti() {
        return udalosti;
    }

    public void setUdalosti(ArrayList<Udalost> udalosti) {
        this.udalosti = udalosti;
    }

    public void zmenRSVP(String jmenoUdalosti, String jmenoClena, String status) {
        for (Udalost udalost : udalosti) {
            if (udalost.getJmenoUdalosti().equals(jmenoUdalosti)) {
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

    public void ulozUdalostiDoJSON() {
        String json = gson.toJson(udalosti);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("target/" + "udalosti.json"))) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

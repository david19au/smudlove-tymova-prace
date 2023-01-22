package cz.vse.tymovanicko.logika;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class JsonHandler {

    public static void main(String[] args) {

        /**
        // Gson builder pro lepší vzhled struktury JSONu
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //Vytváří nového uživatele a vytváří JSON s jeho zadanými hodnotami
        JsonUzivatel uzivatelDemo1 = new JsonUzivatel(0001, "muz", "Adam", "Schindler", 21, "scha28@vse.cz", "silneheslo", "trener");
        try (FileWriter writer = new FileWriter("target/uzivatel" + uzivatelDemo1.getKrestniJmeno().toLowerCase() + ".json")) {
            gson.toJson(uzivatelDemo1, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Vytváří novou událost a vytváří JSON s jejími zadanými hodnotami
        JsonUdalost udalostDemo = new JsonUdalost("Setkani", "1.1.2000", "DDM");
        try (FileWriter writer = new FileWriter("target/01_udalost" + udalostDemo.getNazev() + ".json")) {
            gson.toJson(udalostDemo, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Vytváří dalšího uživatele a oba přidává do události
        //Dále zapisuje JSON současného stavu události

        JsonUzivatel uzivatelDemo2 = new JsonUzivatel(0002, "zena", "Fany", "Vařeková", 21, "fanynka@fanynka.cz", "silneheslo2", "kapitan");
        udalostDemo.pridejUcasntika(uzivatelDemo1);
        udalostDemo.pridejUcasntika(uzivatelDemo2);
        try (FileWriter writer = new FileWriter("target/02_udalost" + udalostDemo.getNazev() + ".json")) {
            gson.toJson(udalostDemo, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Přečte soubor uživatele a vytvoří jeho instanci, s jejími hodnotami lze nadále snadno pracovat
        try (Reader reader = new FileReader("target/uzivatel" + uzivatelDemo1.getKrestniJmeno() + ".json")) {

            // Convert JSON File to Java Object
            JsonUzivatel uzivatelDemo1v2 = gson.fromJson(reader, JsonUzivatel.class);

            // print staff object
            System.out.println(uzivatelDemo1v2.getKrestniJmeno() + " " + uzivatelDemo1v2.getPrijmeni());
            //System.out.println(uzivatelDemo1v2.getVek());


        } catch (IOException e) {
            e.printStackTrace();
        }

        //Přečte soubor události a vytvoří její instanci, s jejími hodnotami lze nadále snadno pracovat
        try (Reader reader = new FileReader("target/02_udalost" + udalostDemo.getNazev() + ".json")) {

            JsonUdalost udalostDemo1v2 = gson.fromJson(reader, JsonUdalost.class);

            System.out.println("------------");
            //System.out.println(udalostDemo1v2.idUcastnika("Adam"));
            //System.out.println(udalostDemo1v2.idUcastnika("Fany"));
            //System.out.println(udalostDemo1v2.idUcastnika("Fanyy"));
            //System.out.println(udalostDemo1v2.idUcastnika("fdgdf"));
            System.out.println("------------");

            System.out.println(udalostDemo1v2.getNazev() + " (" + udalostDemo1v2.getDatumKonani() + ")");
            System.out.println(udalostDemo1v2.getSeznamUcastnikuVypis());


        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }
}


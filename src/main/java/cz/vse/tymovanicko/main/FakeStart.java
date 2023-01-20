package cz.vse.tymovanicko.main;

import java.io.IOException;

/**
 * Třída  FakeStart
 *
 * @author ?
 * @version ?
 */
public class FakeStart {

    /**
     * Metoda, která zavolá pravou třídu Main a předá jí případné args.
     *
     * @param args Parametry příkazového řádku / parametry spuštění, které se předají pravému Main
     */
    public static void main(String[] args) throws IOException {
        Start.main(args);
    }
}

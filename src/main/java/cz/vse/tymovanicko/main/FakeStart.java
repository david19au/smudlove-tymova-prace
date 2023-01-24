package cz.vse.tymovanicko.main;

import java.io.IOException;

/**
 * Třída FakeStart je (falešnou) hlavní třídou projektu.
 * Napomáhá ke spuštění fat jaru, aby JRE si myslelo, že se nejedná o JFX aplikaci a spustila se správně při použití s
 * fatjarem.
 *
 * @author Trong Dat Luu
 * @version ZS 2022/23
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

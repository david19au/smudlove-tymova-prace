module cz.vse.tymovanicko {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires jbcrypt;
    requires java.sql;


    opens cz.vse.tymovanicko.main to javafx.fxml, com.google.gson;
    exports cz.vse.tymovanicko.main;
    exports cz.vse.tymovanicko.logika;
    opens cz.vse.tymovanicko.logika to com.google.gson, javafx.fxml;
}
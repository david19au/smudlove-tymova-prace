module cz.vse.tymovanicko {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens cz.vse.tymovanicko.main to javafx.fxml, com.google.gson;
    exports cz.vse.tymovanicko.main;
    exports cz.vse.tymovanicko.main.logic;
    opens cz.vse.tymovanicko.main.logic to com.google.gson, javafx.fxml;
}
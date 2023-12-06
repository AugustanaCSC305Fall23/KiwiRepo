module edu.augustana {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.opencsv;
    requires java.sql;
    requires com.google.gson;

    opens edu.augustana;
    exports edu.augustana;
    exports edu.augustana.cards;
    opens edu.augustana.cards;
    exports edu.augustana.filters;
    opens edu.augustana.filters;
}

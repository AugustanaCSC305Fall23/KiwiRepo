module edu.augustana {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.opencsv;
    requires java.sql;

    opens edu.augustana;
    exports edu.augustana;
}

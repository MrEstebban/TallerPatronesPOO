module com.example.citasmedicas {
    exports Entity;
    opens Entity;

    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires java.sql;
    requires mysql.connector.j;

    opens Control to javafx.fxml;
    exports Control;
}
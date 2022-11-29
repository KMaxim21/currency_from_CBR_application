module com.example.quotations_cbr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.sql;


    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.quotations_cbr to javafx.fxml;
    exports com.example.quotations_cbr;
}
module es.juliogtrenard.jaspermedico {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.juliogtrenard.jaspermedico to javafx.fxml;
    exports es.juliogtrenard.jaspermedico;
    opens es.juliogtrenard.jaspermedico.controller to javafx.fxml;
    exports es.juliogtrenard.jaspermedico.controller;
}
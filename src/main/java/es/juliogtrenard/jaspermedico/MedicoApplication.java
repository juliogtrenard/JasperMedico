package es.juliogtrenard.jaspermedico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase que muestra un informe médico a partir de un formulario
 */
public class MedicoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MedicoApplication.class.getResource("/es/juliogtrenard/jaspermedico/fxml/medico.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 500);
        stage.setTitle("FORMULARIO MÉDICO");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * El punto de entrada de la aplicación
     * @param args argumentos de linea de comandos
     */
    public static void main(String[] args) {
        launch();
    }
}
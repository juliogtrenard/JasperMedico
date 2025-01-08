package es.juliogtrenard.jaspermedico.controller;

import es.juliogtrenard.jaspermedico.MedicoApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que controla la ventana principal
 */
public class MedicoController {
    /**
     * Boton para generar informe
     */
    @FXML
    private Button btnGenerar;

    /**
     * Boton para limpiar los campos
     */
    @FXML
    private Button btnLimpiar;

    /**
     * Boton para salir
     *
     */
    @FXML
    private Button btnSalir;

    /**
     * Codigo del médico
     */
    @FXML
    private TextField txtCodMedico;

    /**
     * Direccion del paciente
     */
    @FXML
    private TextField txtDireccionPaciente;

    /**
     * Especialidad del médico
     */
    @FXML
    private TextField txtEspecialidadMedico;

    /**
     * Nombre del médico
     */
    @FXML
    private TextField txtNombreMedico;

    /**
     * Nombre del paciente
     */
    @FXML
    private TextField txtNombrePaciente;

    /**
     * Numero del paciente
     */
    @FXML
    private TextField txtNumPaciente;

    /**
     * Tratamientos
     */
    @FXML
    private TextArea txtTratamientos;

    /**
     * Generar el informe.
     *
     * @param event evento
     */
    @FXML
    void accionGenerar(ActionEvent event) {
        String errores=validarCampos();

        if(errores.isEmpty()) {
            String informe = "Medico";
            try {
                JasperReport report = (JasperReport) JRLoader.loadObject(MedicoApplication.class.getResource("report/" + informe + ".jasper"));
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("IMAGE_PATH", MedicoApplication.class.getResource("img/").toString());
                parameters.put("NumPaciente",txtNumPaciente.getText().trim());
                parameters.put("NomPaciente", txtNombrePaciente.getText().trim());
                parameters.put("DirPaciente", txtDireccionPaciente.getText().trim());
                parameters.put("CodMedico", txtCodMedico.getText().trim());
                parameters.put("NomMedico", txtNombreMedico.getText().trim());
                parameters.put("EspeMedico", txtEspecialidadMedico.getText().trim());
                parameters.put("Tratamientos", txtTratamientos.getText().trim());
                JasperPrint jprint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
                JasperViewer viewer = new JasperViewer(jprint, false);
                viewer.setVisible(true);
            } catch (JRException e) {
                e.printStackTrace();
            }
        }else {
            Alert al=new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText(errores);
            al.showAndWait();
        }
    }

    /**
     * Valida los campos
     * @return cadena con posibles errores
     */
    private String validarCampos() {
        String errores = "";

        errores += validarCodigoMedico();
        errores += validarDireccionPaciente();
        errores += validarEspecialidadMedico();
        errores += validarNombrePaciente();
        errores += validarNumeroPaciente();
        errores += validarTratamientos();

        return errores;
    }

    /**
     * Valida el codigo del médico
     * @return posible error
     */
    private String validarCodigoMedico() {
        if (txtCodMedico.getText().trim().isEmpty()) {
            return "Ingresa el código del médico\n";
        }
        try {
            int codigo = Integer.parseInt(txtCodMedico.getText());
            if (codigo < 1) {
                return "El código no puede ser negativo\n";
            }
        } catch (NumberFormatException e) {
            return "El código debe ser numérico\n";
        }
        return "";
    }

    /**
     * Valida la direccion del paciente
     * @return posible error
     */
    private String validarDireccionPaciente() {
        if (txtDireccionPaciente.getText().trim().isEmpty()) {
            return "Ingresa la dirección del paciente\n";
        }
        return "";
    }

    /**
     * Valida la especialidad del medico
     * @return posible error
     */
    private String validarEspecialidadMedico() {
        if (txtEspecialidadMedico.getText().trim().isEmpty()) {
            return "Ingresa la especialidad del médico\n";
        }
        return "";
    }

    /**
     * Valida el nombre del paciente
     * @return posible error
     */
    private String validarNombrePaciente() {
        if (txtNombrePaciente.getText().trim().isEmpty()) {
            return "Ingresa el nombre del paciente\n";
        }
        return "";
    }

    /**
     * Valida el número del paciente
     * @return posible error
     */
    private String validarNumeroPaciente() {
        if (txtNumPaciente.getText().trim().isEmpty()) {
            return "Ingresa el número de paciente\n";
        }
        try {
            int numero = Integer.parseInt(txtNumPaciente.getText());
            if (numero < 1) {
                return "El número de paciente no puede ser negativo\n";
            }
        } catch (NumberFormatException e) {
            return "Ingresa un número de paciente correcto\n";
        }
        return "";
    }

    /**
     * Valida los tratamientos
     * @return posible error
     */
    private String validarTratamientos() {
        if (txtTratamientos.getText().trim().isEmpty()) {
            return "Ingresa los tratamientos\n";
        }
        return "";
    }

    /**
     * Limpiar campos.
     *
     * @param event el evento
     */
    @FXML
    void accionLimpiar(ActionEvent event) {
        txtCodMedico.clear();
        txtDireccionPaciente.clear();
        txtEspecialidadMedico.clear();
        txtNombreMedico.clear();
        txtNombrePaciente.clear();
        txtNumPaciente.clear();
        txtTratamientos.clear();
    }

    /**
     * Salir.
     *
     * @param event evento
     */
    @FXML
    void accionSalir(ActionEvent event) {
        Stage stage = (Stage)txtCodMedico.getScene().getWindow();
        stage.close();
    }
}
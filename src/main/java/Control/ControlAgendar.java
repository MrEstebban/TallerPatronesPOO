package Control;

import Entity.CitaMedica;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ControlAgendar {

    @FXML
    private TextField boxCedula;

    @FXML
    private TextField boxApellido;

    @FXML
    private TextField boxEdad;

    @FXML
    private DatePicker boxFecha;

    @FXML
    private ChoiceBox<String> boxHora;

    @FXML
    private TextField boxNombre;

    @FXML
    private Button btnRegistrar;

    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @FXML
    private void initialize() {
        // Inicializa el ChoiceBox con opciones de hora
        boxHora.getItems().addAll("8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM",
                "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM");
    }

    @FXML
    void finalizarRegistro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
            Parent root = loader.load();
            ControlInicio controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            CitaMedica cita = new CitaMedica(boxCedula.getText(), boxApellido.getText(), boxNombre.getText(), Integer.parseInt(boxEdad.getText()), boxFecha.getValue(), boxHora.getValue());

            stage.setOnCloseRequest(e -> controlador.closeWindow());

            Stage myStage = (Stage) this.btnRegistrar.getScene().getWindow();
            myStage.close();

            String fechaFormato = cita.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String carpetaFecha = "Historial_Medico/" + fechaFormato;
            String archivoCedula = carpetaFecha + "/" + cita.getCedula() + ".json";

            File carpetaFechaDir = new File(carpetaFecha);
            if (!carpetaFechaDir.exists()) {
                carpetaFechaDir.mkdirs();
            }

            // Convierte el objeto cita a JSON y escribe en el archivo dentro de la carpeta
            objectMapper.writeValue(new File(archivoCedula), cita);
            System.out.println("Cita registrada en " + archivoCedula);

            System.out.println("Se ha realizado el registro exitosamente");
            System.out.println();

        } catch (IOException e) {
            System.out.println("Error al registrar cita"+e.getMessage());
            //throw new RuntimeException(e);
        }
    }

    public void closeWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
            Parent root = loader.load();
            ControlInicio controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btnRegistrar.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

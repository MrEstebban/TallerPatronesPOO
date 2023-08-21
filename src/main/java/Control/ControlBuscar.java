package Control;

import DAO.DAOCitas;
import Entity.CitaMedica;
import Utilities.UtilidadesAcceso;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlBuscar {

    @FXML
    private DatePicker boxFechaBusqueda;

    @FXML
    private Button btnBuscar;

    @FXML
    private TableView<CitaMedica> tablaMostrar;

    @FXML
    void mostrarFiltro(ActionEvent event) {
        //Limpiar datos viejos de la tabla
        this.tablaMostrar.getColumns().clear();
        this.tablaMostrar.getItems().clear();

        if(boxFechaBusqueda.getValue() != null) {
            //Leer de la BBDD
            DAOCitas daoCitas = new DAOCitas();
            List<CitaMedica> citas = daoCitas.findAllCitasByDate(boxFechaBusqueda.getValue());
            if(citas.size() > 0){
                this.llenarTablaConDatos(citas);
            }else{
                UtilidadesAcceso.abrirVentanaInfo(event, "No se encontraron registros para la fecha seleccionada");
            }

            /*String carpetaFecha = "Historial_Medico/" + boxFechaBusqueda.getValue().toString();
            File carpetaFechaDir = new File(carpetaFecha);
            if (carpetaFechaDir.exists()) {
                List<CitaMedica> citas = this.buscarArchivosCitasEnCarpeta(carpetaFecha);
                this.llenarTablaConDatos(citas);
            }else{
                this.abrirVentanaInfo(event);
            }*/
        }else{
            System.out.println("No se ha seleccionado una fecha");
            UtilidadesAcceso.abrirVentanaInfo(event, "No se ha seleccionado una fecha");
        }
    }

    private void llenarTablaConDatos(List<CitaMedica> citas){
        ObservableList<CitaMedica> citasMedicas = FXCollections.observableArrayList(citas);

        TableColumn<CitaMedica, String> columnaNombre = new TableColumn<>("Cedula");
        columnaNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));

        TableColumn<CitaMedica, String> columnaApellido = new TableColumn<>("Nombres");
        columnaApellido.setCellValueFactory(cellData -> {
            String nombres = cellData.getValue().getNombre() + " " + cellData.getValue().getApellido();
            return new SimpleStringProperty(nombres);
        });

        TableColumn<CitaMedica, String> columnaHora = new TableColumn<>("Hora");
        columnaHora.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHora()));

        //Añadir datos nuevos a la tabla
        this.tablaMostrar.getColumns().addAll(columnaNombre, columnaApellido, columnaHora);
        this.tablaMostrar.getItems().addAll(citas);
    }

    private List<CitaMedica> buscarArchivosCitasEnCarpeta(String ruta){
        ObjectMapper objectMapper = new ObjectMapper();
        //Register the datatype support offered by jsr310 library into you objectmapper object, this can be done by following :
        objectMapper.findAndRegisterModules();
        File folder = new File(ruta);
        File[] files = folder.listFiles();

        List<CitaMedica> citas = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    try {
                        CitaMedica cita = objectMapper.readValue(file, CitaMedica.class);
                        citas.add(cita);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            UtilidadesAcceso.abrirVentanaInfo(new ActionEvent(), "No se encontraron registros para la fecha seleccionada");
        }

        return citas;
    }

    public void closeWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
            Parent root = loader.load();
            ControlInicio controlador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btnBuscar.getScene().getWindow();
            myStage.close();
            System.out.println("Su busqueda ha arrojado los siguientes resultados");
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
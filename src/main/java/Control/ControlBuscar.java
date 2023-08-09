package Control;

import Entity.CitaMedica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;

public class ControlBuscar {

    @FXML
    private DatePicker boxFechaBusqueda;

    @FXML
    private Button btnBuscar;

    @FXML
    private TableView<CitaMedica> tablaMostrar;

    @FXML
    void mostrarFiltro(ActionEvent event) {
        //LOGICA DE FILTRO DE BUSQUEDA
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
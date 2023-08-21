package Utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.InputStream;
import java.util.Properties;

public class UtilidadesAcceso {
    public static Properties loadProperty(String propertiesURL){
        try {
            Properties properties = new Properties();
            InputStream inputStream =  UtilidadesAcceso.class.getClassLoader().getResourceAsStream(propertiesURL);
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    public static void abrirVentanaInfo(ActionEvent event, String mensaje) {

        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Información");
        dialog.setHeaderText(mensaje);
        ButtonType resultado = dialog.showAndWait().orElse(ButtonType.CANCEL);

        if (resultado == ButtonType.OK) {
            System.out.println("Usuario eligió OK");
        }

    }
}

package DAO;

import Entity.CitaMedica;
import FactoryMethodPattern.FactoryBaseDatos;
import FactoryMethodPattern.IntAdaptador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOCitas {
    private IntAdaptador intAdaptador;

    public DAOCitas() {
        this.intAdaptador = FactoryBaseDatos.getDefaultDBAdapter();
    }

    public List<CitaMedica> findAllCitas(){
        Connection connection = this.intAdaptador.getConnection();
        List<CitaMedica> citasList = new ArrayList<>();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT id , cedula, apellido, nombre, edad, fecha, hora FROM cita_medica");
            ResultSet results = statement.executeQuery();
            while(results.next()){
                citasList.add(new CitaMedica(results.getLong(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4),
                        results.getInt(5),
                        results.getDate(6).toLocalDate(),
                        results.getString(7)));
            }
            return citasList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                connection.close();
            } catch (Exception e) {}
        }
    }

    public List<CitaMedica> findAllCitasByDate(LocalDate fecha){
        Connection connection = this.intAdaptador.getConnection();
        List<CitaMedica> citasList = new ArrayList<>();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT id , cedula, apellido, nombre, edad, fecha, hora FROM cita_medica WHERE fecha = '" + fecha.toString() + "'");
            ResultSet results = statement.executeQuery();
            while(results.next()){
                citasList.add(new CitaMedica(results.getLong(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4),
                        results.getInt(5),
                        results.getDate(6).toLocalDate(),
                        results.getString(7)));
            }
            return citasList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                connection.close();
            } catch (Exception e) {}
        }
    }

    public boolean saveCita(CitaMedica cita){
        Connection connection = this.intAdaptador.getConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO cita_medica(id, cedula, apellido, nombre, edad, fecha, hora) VALUES (?,?,?,?,?,?,?)");
            statement.setLong(1, cita.getId());
            statement.setString(2, cita.getCedula());
            statement.setString(3, cita.getApellido());
            statement.setString(4, cita.getNombre());
            statement.setInt(5, cita.getEdad());
            statement.setDate(6, java.sql.Date.valueOf(cita.getFecha()));
            statement.setString(7, cita.getHora());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            try {
                connection.close();
            } catch (Exception e) {}
        }
    }
}

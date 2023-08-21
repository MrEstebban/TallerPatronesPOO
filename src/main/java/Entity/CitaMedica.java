package Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class CitaMedica {

    private Long id;
    private String cedula;
    private String apellido;
    private String nombre;
    private int edad;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;
    private String hora;

    public CitaMedica(Long id, String cedula, String apellido, String nombre, int edad, LocalDate fecha, String hora) {
        this.id = id;
        this.cedula = cedula;
        this.apellido = apellido;
        this.nombre = nombre;
        this.edad = edad;
        this.fecha = fecha;
        this.hora = hora;
    }

    public CitaMedica() {

    }

    public CitaMedica(String text, String text1, String text2, int parseInt, LocalDate value, String value1) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}

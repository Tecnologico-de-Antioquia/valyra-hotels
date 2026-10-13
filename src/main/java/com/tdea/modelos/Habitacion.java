package src.main.java.com.tdea.modelos;

import com.tdea.compartidos.enums.EstadoHabitacion;
import com.tdea.compartidos.enums.TipoHabitacion;

public class Habitacion {
    private Integer numero;
    private TipoHabitacion tipoHabitacion;
    private EstadoHabitacion estado;

    public Habitacion(Integer numero, TipoHabitacion tipoHabitacion, EstadoHabitacion estado){
        this.numero = numero;
        this.tipoHabitacion = tipoHabitacion;
        this.estado = estado;
    }

    public Integer getNumero(){
        return this.numero;
    }

    public TipoHabitacion getTipoHabitacion(){
        return this.tipoHabitacion;
    }

    public EstadoHabitacion getEstadoHabitacion(){
        return this.estado;
    }
}

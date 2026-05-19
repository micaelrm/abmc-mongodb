package modelo;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;


@Entity("test_conexion") 
class PruebaEntidad {
    
    @Id
    private ObjectId id; 
    private String mensaje;
    private int codigoEstado;

    public PruebaEntidad() {}

    public PruebaEntidad(String mensaje, int codigoEstado) {
        this.mensaje = mensaje;
        this.codigoEstado = codigoEstado;
    }

    public ObjectId getId() { return id; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public int getCodigoEstado() { return codigoEstado; }
    public void setCodigoEstado(int codigoEstado) { this.codigoEstado = codigoEstado; }
}

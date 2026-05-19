package modelo;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity("Cliente") 
public class Cliente {
    @Id  
    private ObjectId id;
 
    private String nombre;
    
    public Cliente() { }

    public Cliente(String nombre) {
        this.nombre = nombre;
    }

    public ObjectId getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
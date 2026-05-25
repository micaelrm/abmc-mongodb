package modelo;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexed;
import org.bson.types.ObjectId;

@Entity("Cliente") 
public class Cliente {
    @Id  
    private ObjectId id;
    
    @Indexed(options = @IndexOptions(unique = true))
    private int dni;
 
    private String nombre;
    
    public Cliente() { }

    public Cliente(String nombre, int dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public ObjectId getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
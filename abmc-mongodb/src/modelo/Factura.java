package modelo;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import dev.morphia.annotations.Indexed;
import dev.morphia.annotations.IndexOptions;
import org.bson.types.ObjectId;

@Entity("Factura")
public class Factura {
    @Id
    private ObjectId id;
   
    @Indexed(options = @IndexOptions(unique = true))
    private int nro;
    
    @Reference
    private Cliente cliente; 

    private float importe;

    public Factura() { }
    
    public Factura(int nro, Cliente cliente, float importe) {
        this.nro = nro;
        this.cliente = cliente;
        this.importe = importe;
    }

    public ObjectId getId() { return id; }
    public int getNro() { return nro; }
    public void setNro(int nro) { this.nro = nro; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public double getImporte() { return importe; }
    public void setImporte(float importe) { this.importe = importe; }
}
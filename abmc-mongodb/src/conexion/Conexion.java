package conexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class Conexion {
    
    private static Datastore datastore = null;

    public static Datastore getDataStore(String host, String bd) {
        if (datastore == null) {
            try {
                MongoClient mongoCliente = MongoClients.create(host);
                
                datastore = Morphia.createDatastore(mongoCliente, bd);
                //datastore.getMapper().mapPackage("modelo.Cliente");
                //datastore.getMapper().mapPackage("modelo.Factura");
                datastore.ensureIndexes();
                
                System.out.println("Conexión a MongoDB establecida con exito");
                
            } catch (Exception e) {
                System.err.println("Error al conectar con MongoDB: " + e.getMessage());
            }
        }
        return datastore;
    }
}

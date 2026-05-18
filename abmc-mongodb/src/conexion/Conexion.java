package conexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

// ejemplo: import modelo.Alumno; 

public class Conexion {
    
    private static Datastore datastore = null;

    public static Datastore getConexion() {
        if (datastore == null) {
            try {
                MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
                
                datastore = Morphia.createDatastore(mongoClient, "unlu_bd2");
                
                System.out.println("Conexión a MongoDB establecida con éxito.");
                
            } catch (Exception e) {
                System.err.println("Error crítico al conectar con MongoDB: " + e.getMessage());
            }
        }
        return datastore;
    }
}

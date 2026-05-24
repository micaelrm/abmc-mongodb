package modelo;

import conexion.Conexion;
import static conexion.Conexion.getDataStore;
import dev.morphia.Datastore;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("[!] Iniciando prueba de conexión a MongoDB ");

        Datastore ds = getDataStore("mongodb://localhost:27017", "abmc-mongodb");

        if (ds != null) {
            System.out.println("[!] El objeto Datastore se inicializó correctamente.");
            
            try {
                System.out.println("[!] Intentando registrar la entidad en Morphia...");
                
                ds.getMapper().map(PruebaEntidad.class);

                System.out.println("[!] Creando documento de prueba...");
                
                PruebaEntidad registroTest = new PruebaEntidad("[!] Conexión Exitosa desde Java", 200);

                System.out.println("[!] Guardando en la base de datos...");
                
                ds.save(registroTest);
                
                System.out.println("[!] El dato se guardó correctamente.");
                System.out.println("[!] Abre MongoDB Compass, entra a 'unlu_bd2' y busca la colección 'test_conexion'.");

            } catch (Exception e) {
                System.err.println("\n[ERROR] El motor conectó, pero falló al mapear o guardar el dato.");
                System.err.println("[ERROR] Detalle del error: " + e.getMessage());
                e.printStackTrace();
            }
            
        } else {
            System.err.println("\n[ERROR] El Datastore regresó nulo. Revisa los mensajes de la consola.");
        }
    }
}


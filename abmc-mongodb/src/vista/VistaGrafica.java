package vista;

import static conexion.Conexion.*;
import dev.morphia.Datastore;
import dev.morphia.query.filters.Filters;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;

public class VistaGrafica extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VistaGrafica.class.getName());
    private static Datastore dataStore;
    
    public VistaGrafica() {
        initComponents();
        setLocationRelativeTo(null);
        clases.removeAllItems();
        clases.addItem("modelo.Cliente");
        clases.addItem("modelo.Factura");
        
        dataStore = getDataStore("mongodb://localhost:27017", "abmc-mongodb");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clases = new javax.swing.JComboBox<>();
        btnAlta = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        btnMod = new javax.swing.JButton();
        btnCons = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        clases.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAlta.setText("alta");
        btnAlta.addActionListener(this::btnAltaActionPerformed);

        btnBaja.setText("baja");
        btnBaja.addActionListener(this::btnBajaActionPerformed);

        btnMod.setText("modificar");
        btnMod.addActionListener(this::btnModActionPerformed);

        btnCons.setText("consultar");
        btnCons.addActionListener(this::btnConsActionPerformed);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(clases, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAlta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBaja)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(btnMod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCons))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlta)
                    .addComponent(btnBaja)
                    .addComponent(btnMod)
                    .addComponent(btnCons))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed

        try {
            Class<?> clase = Class.forName(clases.getSelectedItem().toString()); 
            Object nuevoObj = clase.getDeclaredConstructor().newInstance(); 
            
            for (Field atributo : clase.getDeclaredFields()) {
                if (!(atributo.getName().equals("id") && atributo.getType() == ObjectId.class)) {
            
                    String valor = JOptionPane.showInputDialog("Ingrese " + atributo.getName());
                    atributo.setAccessible(true);

                    if (atributo.getType() == int.class || atributo.getType() == Integer.class) {
                        atributo.set(nuevoObj, Integer.parseInt(valor));
                    } else if (atributo.getType() == float.class || atributo.getType() == Float.class) {
                        atributo.set(nuevoObj, Float.parseFloat(valor));
                    } else if (atributo.getType() == String.class) {
                        atributo.set(nuevoObj, valor);
                    } else {
                        Object id = parseId(valor);
                        Object relacion = dataStore.find(atributo.getType()).filter(Filters.eq("_id", id)).first();
                        if (relacion != null) {
                            atributo.set(nuevoObj, relacion);
                        } else {
                            JOptionPane.showMessageDialog(this, "no existe " + atributo.getType().getSimpleName() + " ID: " + valor);
                            return;
                        }
                    }
                }
            }
            
            dataStore.save(nuevoObj);
            JOptionPane.showMessageDialog(this, "objeto guardado");
        } catch (Exception e) { 
            e.printStackTrace();  
        }
    }//GEN-LAST:event_btnAltaActionPerformed
    
    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed

        try {
            Class<?> clase = Class.forName(clases.getSelectedItem().toString());
            String valorPK = JOptionPane.showInputDialog("Ingrese PK a borrar");
            
            //Object id = parseId(valorPK);
            Object objetoBuscado = dataStore.find(clase).filter(Filters.eq("_id", id)).first();

            if (objetoBuscado != null) {
                dataStore.delete(objetoBuscado);
                JOptionPane.showMessageDialog(this, "objeto borrado");
            } else {
                JOptionPane.showMessageDialog(this, "no se encontro el objeto");
            }
        } catch (Exception e) { e.printStackTrace(); } 
    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModActionPerformed
        
        try {
            Class<?> clase = Class.forName(clases.getSelectedItem().toString()); 
            String PKBuscada = JOptionPane.showInputDialog("Ingrese PK a modificar");
            if (PKBuscada == null || PKBuscada.trim().isEmpty()) return;
            
            Object id = parseId(PKBuscada);
            Object objetoBuscado = dataStore.find(clase).filter(Filters.eq("_id", id)).first();

            if (objetoBuscado != null) {
                for (Field atributo : clase.getDeclaredFields()) {
                    atributo.setAccessible(true);
                    Object valorActual = atributo.get(objetoBuscado);
                    String nuevoValor = JOptionPane.showInputDialog("modificar " + atributo.getName(), valorActual);

                    if (nuevoValor != null) {
                        if (atributo.getType() == int.class) atributo.set(objetoBuscado, Integer.parseInt(nuevoValor));
                        else if (atributo.getType() == double.class) atributo.set(objetoBuscado, Double.parseDouble(nuevoValor));
                        else if (atributo.getType() == String.class) atributo.set(objetoBuscado, nuevoValor);
                    }
                }
                dataStore.save(objetoBuscado);
                JOptionPane.showMessageDialog(this, "actualizado");
            }
        } catch (Exception e) { e.printStackTrace(); } 
    }//GEN-LAST:event_btnModActionPerformed

    private void btnConsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsActionPerformed
        
        try {
            Class<?> clase = Class.forName(clases.getSelectedItem().toString());
            String filtro = JOptionPane.showInputDialog("Ingrese PK (vacío para ver todos):");
            
            List<Object> lista = new ArrayList<>();
            if (filtro != null && !filtro.isEmpty()) {
                Object id = parseId(filtro);
                Object objetoBuscado = dataStore.find(clase).filter(Filters.eq("_id", id)).first();
                if (objetoBuscado != null) lista.add(objetoBuscado);
            } else {
                dataStore.find(clase).iterator().forEachRemaining(lista::add);
            }
            tabla.setModel(new Generico(clase, lista)); 
        } catch (Exception e) { e.printStackTrace(); } 
    }//GEN-LAST:event_btnConsActionPerformed
   
    private Object parseId(String valor) {
        if (valor == null || valor.trim().isEmpty()) return null;
        if (ObjectId.isValid(valor)) return new ObjectId(valor);
        return null;
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new VistaGrafica().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnCons;
    private javax.swing.JButton btnMod;
    private javax.swing.JComboBox<String> clases;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}


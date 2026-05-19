
package vista;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.List;

public class Generico<T> extends AbstractTableModel {
    private List<T> datos;
    private Class<T> clase;
    private Field[] atributos;

    public Generico(Class<T> clase, List<T> datos) {
        this.clase = clase;
        this.datos = datos;
        this.atributos = clase.getDeclaredFields();
    }

    @Override
    public int getRowCount() { return datos.size(); }

    @Override
    public int getColumnCount() { return atributos.length; } 
    
    @Override
    public String getColumnName(int col) { return atributos[col].getName(); }

    @Override
    public Object getValueAt(int row, int col) {
        try {
            T obj = datos.get(row);
            atributos[col].setAccessible(true);
            return atributos[col].get(obj); 
        } catch (Exception e) { return null; }
    }
}

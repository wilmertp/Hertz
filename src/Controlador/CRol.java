
package Controlador;

import Modelo.RolBD;
import Modelo.RolMD;
import Vista.VRol;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CRol {
    public static VRol vista;
    
    private RolBD bdRol = new RolBD();

    public CRol(VRol vista) {
        this.vista = vista;
        
        vista.setVisible(true);
        lista();
        
        vista.getBtnNuevo().addActionListener(e -> nuevo());
        vista.getBtnGuardar().addActionListener(e -> guardar());
        vista.getBtnModificar().addActionListener(e -> modificar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        
        vista.getTxtBuscar().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                buscar();
            }        
        });
        
        vista.getTablaRol().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionar();
            }        
        });
        
        vista.getTxtCodigo().setEnabled(false);
        vista.getTxtNombre().setEnabled(false);
        vista.getTxtDescripcion().setEnabled(false);
        vista.getCbEstado().setEnabled(false);
        
        vista.getBtnGuardar().setEnabled(false);
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
        
    }//Fin del constructor
    
    public void nuevo(){
        limpiar();
        vista.getTxtCodigo().setEnabled(true);
        vista.getTxtNombre().setEnabled(true);
        vista.getTxtDescripcion().setEnabled(true);
        vista.getCbEstado().setEnabled(true);
        vista.getBtnGuardar().setEnabled(true);
        vista.getBtnModificar().setEnabled(true);
        vista.getBtnEliminar().setEnabled(true);
    }
    
    public void limpiar(){
        vista.getTxtCodigo().setText("");
        vista.getTxtNombre().setText("");
        vista.getTxtDescripcion().setText("");
        vista.getCbEstado().setSelectedIndex(0);
    }
    
    public void lista(){
        DefaultTableModel modelo;
        modelo = (DefaultTableModel) vista.getTablaRol().getModel();
        
        List<RolMD> lista = bdRol.mostrardatos();
        int columnas = modelo.getColumnCount();
        
//**************** Limpiar en decremento   ************************

        for (int j = vista.getTablaRol().getRowCount()-1; j >= 0; j--){
            modelo.removeRow(j);
        }

//*******************************************************************
        
        for (int i = 0; i < lista.size(); i++) {
            modelo.addRow(new Object[columnas]);
            vista.getTablaRol().setValueAt(lista.get(i).getCodigo(), i, 0);
            vista.getTablaRol().setValueAt(lista.get(i).getNombre(), i, 1);
            vista.getTablaRol().setValueAt(lista.get(i).getDescripcion(), i, 2);
            vista.getTablaRol().setValueAt(lista.get(i).getEstado(), i, 3);
        }
    }//Fin de Lista
    
    public void guardar(){
        
        bdRol.setCodigo(Integer.parseInt(vista.getTxtCodigo().getText()));
        bdRol.setNombre(vista.getTxtNombre().getText());
        bdRol.setDescripcion(vista.getTxtDescripcion().getText());
        bdRol.setEstado(vista.getCbEstado().getSelectedItem().toString());
        
        if (bdRol.insertar()){
            JOptionPane.showMessageDialog(null, "GUARDADO CORRECTAMENTE");
            lista();
            limpiar();
        }
        else{
            JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR");
        }
    }//Fin del Guardar
    
    public void buscar(){
       if(vista.getTxtBuscar().getText().equals("")){
           lista();
       }
       else{
           DefaultTableModel modelo;
           modelo = (DefaultTableModel) vista.getTablaRol().getModel();
        
            List<RolMD> lista = bdRol.obtenerDatos(Integer.parseInt(vista.getTxtBuscar().getText()));
            int columnas = modelo.getColumnCount();

            for (int j = vista.getTablaRol().getRowCount()-1; j >= 0; j--){
                modelo.removeRow(j);
            }

            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[columnas]);
                vista.getTablaRol().setValueAt(lista.get(i).getCodigo(), i, 0);
                vista.getTablaRol().setValueAt(lista.get(i).getNombre(), i, 1);
                vista.getTablaRol().setValueAt(lista.get(i).getDescripcion(), i, 2);
                vista.getTablaRol().setValueAt(lista.get(i).getEstado(), i, 3);
            }
       }
    }//Fin de Buscar
    
    public void modificar(){
        
        bdRol.setCodigo(Integer.parseInt(vista.getTxtCodigo().getText()));
        bdRol.setNombre(vista.getTxtNombre().getText());
        bdRol.setDescripcion(vista.getTxtDescripcion().getText());
        bdRol.setEstado(vista.getCbEstado().getSelectedItem().toString());
        
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de Modificar?");
        if (respuesta == 0){
            if (bdRol.modificar(Integer.parseInt(vista.getTxtCodigo().getText()))){
                JOptionPane.showMessageDialog(null, "Datos Actualizados");
                lista();
                limpiar();
            }
        }        
    }//Fin del modificar
    
    public void seleccionar(){
        vista.getTxtCodigo().setEnabled(true);
        vista.getTxtNombre().setEnabled(true);
        vista.getTxtDescripcion().setEnabled(true);
        vista.getCbEstado().setEnabled(true);
        vista.getBtnGuardar().setEnabled(false);
        vista.getBtnModificar().setEnabled(true);
        vista.getBtnEliminar().setEnabled(true);
        vista.getTxtBuscar().setText("");
        
        DefaultTableModel modelo;
        modelo = (DefaultTableModel) vista.getTablaRol().getModel();
        int codigo = (Integer) modelo.getValueAt(vista.getTablaRol().getSelectedRow(), 0);
        
        List<RolMD> lista = bdRol.obtenerDatos(codigo);
        
        bdRol.setCodigo(lista.get(0).getCodigo());
        vista.getTxtCodigo().setText(String.valueOf(bdRol.getCodigo()));
        
        bdRol.setNombre(lista.get(0).getNombre());
        vista.getTxtNombre().setText(bdRol.getNombre());
        
        bdRol.setDescripcion(lista.get(0).getDescripcion());
        vista.getTxtDescripcion().setText(bdRol.getDescripcion());
        
        bdRol.setEstado(lista.get(0).getEstado());
        vista.getCbEstado().setSelectedItem(bdRol.getEstado());
    }//Fin del seleccionar
    
    public void eliminar(){
        bdRol.setCodigo(Integer.parseInt(vista.getTxtCodigo().getText()));
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este rol " + vista.getTxtCodigo().getText());
        if (respuesta == 0){
            if (bdRol.eliminar(Integer.parseInt(vista.getTxtCodigo().getText()))){
                JOptionPane.showMessageDialog(null, "Datos Actualizados");
                lista();
                limpiar();
            }
        }
    }//Fin de eliminar
}
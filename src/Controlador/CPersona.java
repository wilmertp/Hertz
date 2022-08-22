
package Controlador;

import Modelo.PersonaMD;
import Modelo.PersonaBD;
import Vista.VPersona;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class CPersona {
    public static VPersona vista;
    
    private PersonaBD bdPersona = new PersonaBD();

    public CPersona(VPersona vista) {
        this.vista = vista;
        
        vista.setVisible(true);
 
        lista();
        
        vista.getBtnNuevo().addActionListener(e -> nuevo());
        vista.getBtnGuardar().addActionListener(e -> guardar());
        vista.getBtnModificar().addActionListener(e -> modificar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getBtnFoto().addActionListener(e -> obtieneImagen());
        
        vista.getTxtBuscar().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                buscar();
            }        
        });
        
        vista.getTablaPersona().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionar();
            }        
        });
        
        vista.getTxtCedula().setEnabled(false);
        vista.getTxtNombre().setEnabled(false);
        vista.getTxtDireccion().setEnabled(false);
        vista.getTxtCelular().setEnabled(false);
        vista.getTxtEmail().setEnabled(false);
        vista.getTxtCalendario().setEnabled(false);
        vista.getBtnFoto().setEnabled(false);
        vista.getBtnGuardar().setEnabled(false);
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
        
    }//Fin del constructor
    
    public void nuevo(){
        limpiar();
        vista.getTxtCedula().setEnabled(true);
        vista.getTxtNombre().setEnabled(true);
        vista.getTxtDireccion().setEnabled(true);
        vista.getTxtCelular().setEnabled(true);
        vista.getTxtEmail().setEnabled(true);
        vista.getTxtCalendario().setEnabled(true);
        vista.getBtnFoto().setEnabled(true);
        vista.getBtnGuardar().setEnabled(true);
        vista.getBtnModificar().setEnabled(true);
        vista.getBtnEliminar().setEnabled(true);
    }
    
    public void limpiar(){
        vista.getTxtCedula().setText("");
        vista.getTxtNombre().setText("");
        vista.getTxtDireccion().setText("");
        vista.getTxtCelular().setText("");
        vista.getTxtEmail().setText("");
        vista.getTxtCalendario().setCalendar(null);
        vista.getLbFoto().setIcon(null);
    }
    
    public void lista(){
        DefaultTableModel modelo;
        modelo = (DefaultTableModel) vista.getTablaPersona().getModel();
        
        List<PersonaMD> lista = bdPersona.mostrardatos();
        int columnas = modelo.getColumnCount();
        
//**************** Limpiar en decremento   ************************

        for (int j = vista.getTablaPersona().getRowCount()-1; j >= 0; j--){
            modelo.removeRow(j);
        }

//*******************************************************************
        
        for (int i = 0; i < lista.size(); i++) {
            modelo.addRow(new Object[columnas]);
            vista.getTablaPersona().setValueAt(lista.get(i).getCedula(), i, 0);
            vista.getTablaPersona().setValueAt(lista.get(i).getNombre(), i, 1);
            vista.getTablaPersona().setValueAt(lista.get(i).getDireccion(), i, 2);
            vista.getTablaPersona().setValueAt(lista.get(i).getCelular(), i, 3);
            vista.getTablaPersona().setValueAt(lista.get(i).getEmail(), i, 4);
            vista.getTablaPersona().setValueAt(lista.get(i).getFechaNac(), i, 5);
            vista.getTablaPersona().setValueAt(lista.get(i).getFoto(), i, 6);
        }
    }//Fin de Lista
    
    public void guardar(){
        
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        
        bdPersona.setCedula(vista.getTxtCedula().getText());
        bdPersona.setNombre(vista.getTxtNombre().getText());
        bdPersona.setDireccion(vista.getTxtDireccion().getText());
        bdPersona.setCelular(vista.getTxtCelular().getText());
        bdPersona.setEmail(vista.getTxtEmail().getText());
        bdPersona.setFechaNac(f.format(vista.getTxtCalendario().getDate()));
        ImageIcon ic = (ImageIcon) vista.getLbFoto().getIcon();
        bdPersona.setFoto(ic.getImage());
        
        if (bdPersona.insertar()){
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
           modelo = (DefaultTableModel) vista.getTablaPersona().getModel();
        
            List<PersonaMD> lista = bdPersona.obtenerDatos(vista.getTxtBuscar().getText());
            int columnas = modelo.getColumnCount();

            for (int j = vista.getTablaPersona().getRowCount()-1; j >= 0; j--){
                modelo.removeRow(j);
            }

            for (int i = 0; i < lista.size(); i++) {
                modelo.addRow(new Object[columnas]);
                vista.getTablaPersona().setValueAt(lista.get(i).getCedula(), i, 0);
                vista.getTablaPersona().setValueAt(lista.get(i).getNombre(), i, 1);
                vista.getTablaPersona().setValueAt(lista.get(i).getDireccion(), i, 2);
                vista.getTablaPersona().setValueAt(lista.get(i).getCelular(), i, 3);
                vista.getTablaPersona().setValueAt(lista.get(i).getEmail(), i, 4);
                vista.getTablaPersona().setValueAt(lista.get(i).getFechaNac(), i, 5);
                vista.getTablaPersona().setValueAt(lista.get(i).getFoto(), i, 6);
                //}
        }
       }
    }//Fin de Buscar
    

    
    public void modificar(){
        
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        
        bdPersona.setCedula(vista.getTxtCedula().getText());
        bdPersona.setNombre(vista.getTxtNombre().getText());
        bdPersona.setDireccion(vista.getTxtDireccion().getText());
        bdPersona.setCelular(vista.getTxtCelular().getText());
        bdPersona.setEmail(vista.getTxtEmail().getText());
        bdPersona.setFechaNac(f.format(vista.getTxtCalendario().getDate()));
        ImageIcon ic = (ImageIcon) vista.getLbFoto().getIcon();
        bdPersona.setFoto(ic.getImage());
        
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de Modificar?");
        if (respuesta == 0){
            if (bdPersona.modificar(vista.getTxtCedula().getText())){
                JOptionPane.showMessageDialog(null, "Datos Actualizados");
                lista();
                limpiar();
            }
        }        
    }//Fin del modificar
    
    private void obtieneImagen() {
        vista.getLbFoto().setIcon(null);
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = j.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                Image icono = ImageIO.read(j.getSelectedFile()).getScaledInstance(vista.getLbFoto().getWidth(), vista.getLbFoto().getHeight(), Image.SCALE_DEFAULT);
                vista.getLbFoto().setIcon(new ImageIcon(icono));
                vista.getLbFoto().updateUI();
            } catch (IOException ex) {
                Logger.getLogger(CPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void seleccionar(){
        vista.getTxtCedula().setEnabled(true);
        vista.getTxtNombre().setEnabled(true);
        vista.getTxtDireccion().setEnabled(true);
        vista.getTxtCelular().setEnabled(true);
        vista.getTxtEmail().setEnabled(true);
        vista.getTxtCalendario().setEnabled(true);
        vista.getBtnFoto().setEnabled(true);
        vista.getBtnGuardar().setEnabled(false);
        vista.getBtnModificar().setEnabled(true);
        vista.getBtnEliminar().setEnabled(true);
        vista.getTxtBuscar().setText("");
        
        DefaultTableModel modelo;
        modelo = (DefaultTableModel) vista.getTablaPersona().getModel();
        String cedula = (String) modelo.getValueAt(vista.getTablaPersona().getSelectedRow(), 0);
        
        List<PersonaMD> lista = bdPersona.obtenerDatos(cedula);
        
        bdPersona.setCedula(lista.get(0).getCedula());
        vista.getTxtCedula().setText(bdPersona.getCedula());
        
        bdPersona.setNombre(lista.get(0).getNombre());
        vista.getTxtNombre().setText(bdPersona.getNombre());
        
        bdPersona.setDireccion(lista.get(0).getDireccion());
        vista.getTxtDireccion().setText(bdPersona.getDireccion());
        
        bdPersona.setCelular(lista.get(0).getCelular());
        vista.getTxtCelular().setText(bdPersona.getCelular());
        
        bdPersona.setEmail(lista.get(0).getEmail());
        vista.getTxtEmail().setText(bdPersona.getEmail());
        
        bdPersona.setFechaNac(lista.get(0).getFechaNac());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = null;
        try {
            fecha = format.parse(bdPersona.getFechaNac());
            vista.getTxtCalendario().setDate(fecha);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        Image img = lista.get(0).getFoto();
        if (img != null) {
            Image newimg = img.getScaledInstance(vista.getLbFoto().getWidth(), vista.getLbFoto().getHeight(), java.awt.Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(newimg);
            vista.getLbFoto().setIcon(icon);
        } else {
            vista.getLbFoto().setIcon(null);
        }
    }//Fin del seleccionar
    
    public void eliminar(){
        bdPersona.setCedula(vista.getTxtCedula().getText());
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este uduario " + vista.getTxtCedula().getText() );
        if (respuesta == 0){
            if (bdPersona.eliminar(vista.getTxtCedula().getText())){
                JOptionPane.showMessageDialog(null, "Datos Actualizados");
                lista();
                limpiar();
            }
        }
    }//Fin de eliminar
    
}//Fin de la clase
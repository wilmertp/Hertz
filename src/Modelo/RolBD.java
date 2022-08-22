
package Modelo;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import javax.swing.JOptionPane;

public class RolBD extends RolMD{
     Conect conectar = new Conect();

    public RolBD() {
    }

    public RolBD(int codigo, String nombre, String descripcion, String estado) {
        super(codigo, nombre, descripcion, estado);
    }
    
    public List<RolMD> mostrardatos() {
        
        try {
            List<RolMD> lista = new ArrayList<RolMD>();
            String sql = "select * from rol";
            ResultSet rs = conectar.query(sql);
            while (rs.next()) {
                RolMD rol = new RolMD();
                
                rol.setCodigo(rs.getInt("codigo"));
                rol.setNombre(rs.getString("nombre"));
                rol.setDescripcion(rs.getString("descripcion"));
                rol.setEstado(rs.getString("estado"));
                lista.add(rol);
            }
            
            rs.close();
            return lista;
        }
        catch (SQLException e) {
            Logger.getLogger(RolBD.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public boolean insertar() {
        String sql = "INSERT INTO rol(codigo, nombre, descripcion, estado)" + 
                "VALUES (" + 
                getCodigo() + ",'" + 
                getNombre() + "','" + 
                getDescripcion() + "','" +
                getEstado() + "')"; 

        if (conectar.noQuery(sql) == null) {
            return true;
        } 
        else {
            System.out.println("Error");
            return false;
        }
    }//Fin del insertar
    
    public boolean modificar(Integer codigo){
            String sql = "update rol set " +
                    "nombre = '" + getNombre() + "', " +
                    "descripcion = '" + getDescripcion() + "', " +
                    "estado = '" + getEstado() + "' " +
                    "where codigo = " + codigo;

        if (conectar.noQuery(sql) == null) {
            return true;
        } else {
            System.out.println("error al editar");

            return false;
        }
    }//Fin de modificar
    
    public List<RolMD> obtenerDatos(Integer codigo) {
        
        try {
            List<RolMD> lista = new ArrayList<RolMD>();
            String sql = 
                    "select * from rol " + 
                    "where codigo = " + codigo ;
            ResultSet rs = conectar.query(sql);
            while (rs.next()) {
                RolMD rol = new RolMD();
                rol.setCodigo(rs.getInt("codigo"));
                rol.setNombre(rs.getString("nombre"));
                rol.setDescripcion(rs.getString("descripcion"));
                rol.setEstado(rs.getString("estado"));
                lista.add(rol);
            }
            
            rs.close();
            return lista;
        }
        catch (SQLException e) {
            Logger.getLogger(RolBD.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }//Fin de obteer datos
    
    public boolean eliminar(Integer codigo){
        String nsql = "update rol set " + 
                "nombre = '" + getNombre() + "', " +
                "descripcion = '" + getDescripcion() + "', " +
                "estado = 'Inactivo' " +
                "where codigo = " + codigo;
                
        if(conectar.noQuery(nsql) == null){
            return true;
        }
        else{
            JOptionPane.showConfirmDialog(null, "Error al elimianr");
            return false;
        }
    }//Fin de eliminar
}

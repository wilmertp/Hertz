
package Modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JOptionPane;
import org.postgresql.util.Base64;

public class PersonaBD extends PersonaMD{
    Conect conectar = new Conect();

    public PersonaBD() {
    }

    public PersonaBD(String cedula, String nombre, String direccion, String celular, String email, String fechanac, String buscar, Image foto) {
        super(cedula, nombre, direccion, celular, email, fechanac,buscar, foto);
    }
    
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    
    private Image getImage(byte[] bytes, boolean isThumbnail) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("png");
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; // File or InputStream
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        if (isThumbnail) {
            param.setSourceSubsampling(4, 4, 0, 0);
        }
        return reader.read(0, param);
    }
    
    public List<PersonaMD> mostrardatos() {
        
        try {
            List<PersonaMD> lista = new ArrayList<PersonaMD>();
            String sql = "select * from persona";
            ResultSet rs = conectar.query(sql);
            while (rs.next()) {
                
                PersonaMD persona = new PersonaMD();
                persona.setCedula(rs.getString("cedula"));
                persona.setNombre(rs.getString("nombre"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setCelular(rs.getString("celular"));
                persona.setEmail(rs.getString("email"));
                persona.setFechaNac(rs.getString("fechanac"));
                
                byte[] is;
                is = rs.getBytes("imagen");
                if (is != null) {
                    try {
                        is = Base64.decode(is, 0, rs.getBytes("imagen").length);
//                    BufferedImage bi=Base64.decode( ImageIO.read(is));
                        persona.setFoto(getImage(is, false));
                    } catch (Exception ex) {
                        persona.setFoto(null);
                        Logger.getLogger(PersonaBD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    persona.setFoto(null);
                }
                
                lista.add(persona);   
            }
            
            rs.close();
            return lista;
        }
        catch (SQLException e) {
            Logger.getLogger(PersonaBD.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }//Fin mostrar datos
    
    public boolean insertar() {
        
        String ef = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            BufferedImage img = toBufferedImage(getFoto());
            ImageIO.write(img, "PNG", bos);
            byte[] imgb = bos.toByteArray();
            ef = Base64.encodeBytes(imgb);
        } catch (IOException ex) {
            Logger.getLogger(PersonaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql = "INSERT INTO persona(cedula, nombre, direccion, celular, email, fechaNac, imagen)" + 
                "VALUES ('" + 
                getCedula() + "','" + 
                getNombre().toUpperCase() + "','" + 
                getDireccion().toUpperCase() + "','" + 
                getCelular() + "','" + 
                getEmail().toLowerCase() + "','" + 
                getFechaNac()+ "','" +
                ef + "')"; 

        if (conectar.noQuery(sql) == null) {
            return true;
        } 
        else {
            
            JOptionPane.showMessageDialog(null, "ERROR");
            return false;
        }
    }//Fin de insertar
    
    public boolean modificar(String cedula){
        
        String ef = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            BufferedImage img = toBufferedImage(getFoto());
            ImageIO.write(img, "PNG", bos);
            byte[] imgb = bos.toByteArray();
            ef = Base64.encodeBytes(imgb);
        } catch (IOException ex) {
            Logger.getLogger(PersonaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql = "update persona set " +
                "nombre = '" + getNombre().toUpperCase() + "', " +
                "direccion = '" + getDireccion().toUpperCase() + "', " +
                "celular = '" + getCelular() + "', " +
                "email = '" + getEmail().toLowerCase() + "', " +
                "fechaNac = '" + getFechaNac() + "', " +
                "imagen = '" + ef + "' " +
                "where cedula = '" + cedula + "'";

        if (conectar.noQuery(sql) == null) {
            return true;
        } else {
            System.out.println("error al editar");

            return false;
        }
    }//Fin del modificar
    
    public List<PersonaMD> obtenerDatos(String cedula) {
        
        try {
            List<PersonaMD> lista = new ArrayList<PersonaMD>();
            String sql = 
                    "select * from persona " + 
                    "where cedula ILIKE '%" + cedula + "%'";
            ResultSet rs = conectar.query(sql);
            while (rs.next()) {
                PersonaMD persona = new PersonaMD();
                
                persona.setCedula(rs.getString("cedula"));
                persona.setNombre(rs.getString("nombre"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setCelular(rs.getString("celular"));
                persona.setEmail(rs.getString("email"));
                persona.setFechaNac(rs.getString("fechaNac"));
                
                byte[] is;
                is = rs.getBytes("imagen");
                if (is != null) {
                    try {
                        is = Base64.decode(is, 0, rs.getBytes("imagen").length);
//                    BufferedImage bi=Base64.decode( ImageIO.read(is));
                        persona.setFoto(getImage(is, false));
                    } catch (Exception ex) {
                        persona.setFoto(null);
                        Logger.getLogger(PersonaBD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    persona.setFoto(null);
                }
                
                lista.add(persona);
            }
            
            rs.close();
            return lista;
        }
        catch (SQLException e) {
            Logger.getLogger(PersonaBD.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }//Fin de obtener datos
    
    public boolean eliminar(String cedula){
        String nsql = "Delete from persona where cedula = '" + cedula + "'";
        if(conectar.noQuery(nsql) == null){
            return true;
        }
        else{
            JOptionPane.showConfirmDialog(null, "Error al eliminar");
            return false;
        }
    }//Fin de eliminar
    
}

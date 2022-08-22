
package Modelo;

import java.awt.Image;

public class PersonaMD {
    
    String cedula;
    String nombre;
    String direccion;
    String celular;
    String email;
    String fechaNac;
    String buscar;
    Image foto;

    public PersonaMD() {
    }

    public PersonaMD(String cedula, String nombre, String direccion, String celular, String email, String fechanac, String buscar, Image foto) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
        this.celular = celular;
        this.email = email;
        this.fechaNac = fechanac;
        this.foto = foto;
        this.buscar = buscar;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }
 
}
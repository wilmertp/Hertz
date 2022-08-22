
package Controlador;

import Vista.VPrincipal;
import Vista.VUsuario;
import Vista.VPersona;
import Vista.VRol;
import java.awt.Dimension;
import java.awt.Frame;

public class CPrincipal {
    
    public static VPrincipal vista;
    
    public CPrincipal(VPrincipal vista) {
        this.vista = vista;
        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
        vista.setExtendedState(Frame.MAXIMIZED_BOTH);
    
    //-----------------------   MOSTRAR VENTANAS   -----------------------------
    
        vista.getBtnNuevoPersona().addActionListener(e -> persona());
        vista.getBtnNuevoUsuario().addActionListener(e -> usuario());
        vista.getBtnNuevoRol().addActionListener(e -> rol());
        vista.getBtnSalir().addActionListener(e -> salir());
    }
    
    //*************************     PERSONA     ********************************
    
    private void persona(){
        VPersona vpersona = new VPersona();
        CPersona cpersona = new CPersona(vpersona);

        vista.getEscritorio().add(vpersona);
        Dimension desktopSize = vista.getEscritorio().getSize();
        Dimension FrameSize = vpersona.getSize();
        vpersona.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
    }
    
    //***************************     ROL     **********************************
    
    private void rol(){
        VRol vrol = new VRol();
        CRol crol = new CRol(vrol);

        vista.getEscritorio().add(vrol);
        Dimension desktopSize = vista.getEscritorio().getSize();
        Dimension FrameSize = vrol.getSize();
        vrol.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
    }
    
        //***********************     USUARIO     ******************************
    
    private void usuario(){
        VUsuario vusuario = new VUsuario();
        CUsuario cusuario = new CUsuario(vusuario);

        vista.getEscritorio().add(vusuario);
        Dimension desktopSize = vista.getEscritorio().getSize();
        Dimension FrameSize = vusuario.getSize();
        vusuario.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
    }
    
    //*************************     SALIR     **********************************
    
    private void salir(){
        vista.dispose();
    }
    
}//Fin de la clase
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author damia
 */
public class VPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VPrincipal
     */
    public VPrincipal() {
        initComponents();
    }

    public JMenuItem getBtnNuevoRol() {
        return btnNuevoRol;
    }

    public void setBtnNuevoRol(JMenuItem btnNuevoRol) {
        this.btnNuevoRol = btnNuevoRol;
    }

    public JMenu getMenuRol() {
        return menuRol;
    }

    public void setMenuRol(JMenu menuRol) {
        this.menuRol = menuRol;
    }

    public JDesktopPane getEscritorio() {
        return Escritorio;
    }

    public void setEscritorio(JDesktopPane Escritorio) {
        this.Escritorio = Escritorio;
    }

    public JMenuBar getBarraMenus() {
        return barraMenus;
    }

    public void setBarraMenus(JMenuBar barraMenus) {
        this.barraMenus = barraMenus;
    }

    public JMenuItem getBtnNuevoPersona() {
        return btnNuevoPersona;
    }

    public void setBtnNuevoPersona(JMenuItem btnNuevoPersona) {
        this.btnNuevoPersona = btnNuevoPersona;
    }

    public JMenuItem getBtnNuevoUsuario() {
        return btnNuevoUsuario;
    }

    public void setBtnNuevoUsuario(JMenuItem btnNuevoUsuario) {
        this.btnNuevoUsuario = btnNuevoUsuario;
    }

    public JMenuItem getBtnSalir() {
        return btnSalir;
    }

    public void setBtnSalir(JMenuItem btnSalir) {
        this.btnSalir = btnSalir;
    }

    public JMenu getMenuPersona() {
        return menuPersona;
    }

    public void setMenuPersona(JMenu menuPersona) {
        this.menuPersona = menuPersona;
    }

    public JMenu getMenuSalir() {
        return menuSalir;
    }

    public void setMenuSalir(JMenu menuSalir) {
        this.menuSalir = menuSalir;
    }

    public JMenu getMenuUsuario() {
        return menuUsuario;
    }

    public void setMenuUsuario(JMenu menuUsuario) {
        this.menuUsuario = menuUsuario;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Escritorio = new javax.swing.JDesktopPane();
        barraMenus = new javax.swing.JMenuBar();
        menuPersona = new javax.swing.JMenu();
        btnNuevoPersona = new javax.swing.JMenuItem();
        menuUsuario = new javax.swing.JMenu();
        btnNuevoUsuario = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenu();
        btnSalir = new javax.swing.JMenuItem();
        menuRol = new javax.swing.JMenu();
        btnNuevoRol = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );

        menuPersona.setText("Persona");

        btnNuevoPersona.setText("Nuevo");
        menuPersona.add(btnNuevoPersona);

        barraMenus.add(menuPersona);

        menuUsuario.setText("Usuario");

        btnNuevoUsuario.setText("Nuevo");
        menuUsuario.add(btnNuevoUsuario);

        barraMenus.add(menuUsuario);

        menuSalir.setText("Salir");

        btnSalir.setText("Salir");
        menuSalir.add(btnSalir);

        barraMenus.add(menuSalir);

        menuRol.setText("Rol");

        btnNuevoRol.setText("Nuevo");
        menuRol.add(btnNuevoRol);

        barraMenus.add(menuRol);

        setJMenuBar(barraMenus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Escritorio;
    private javax.swing.JMenuBar barraMenus;
    private javax.swing.JMenuItem btnNuevoPersona;
    private javax.swing.JMenuItem btnNuevoRol;
    private javax.swing.JMenuItem btnNuevoUsuario;
    private javax.swing.JMenuItem btnSalir;
    private javax.swing.JMenu menuPersona;
    private javax.swing.JMenu menuRol;
    private javax.swing.JMenu menuSalir;
    private javax.swing.JMenu menuUsuario;
    // End of variables declaration//GEN-END:variables
}

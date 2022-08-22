
package Main;

import Vista.VPrincipal;
import Controlador.CPrincipal;

public class main {
    public static void main(String[] args) {
        
        VPrincipal vista = new VPrincipal();
        CPrincipal inicio = new CPrincipal(vista);
    }
    
}

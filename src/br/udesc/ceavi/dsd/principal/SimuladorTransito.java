package br.udesc.ceavi.dsd.principal;

import br.udesc.ceavi.dsd.view.FramePrincipal;
import java.awt.EventQueue;

/**
 *
 * @author thiag
 */
public class SimuladorTransito {

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            new FramePrincipal().setVisible(true);
        });
    }
}

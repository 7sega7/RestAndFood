package main;

import com.curso.swing.Ventana;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import modelo.swing.AñadirFrame;
import modelo.swing.EditarFrame;
import modelo.swing.EliminarFrame;
import modelo.swing.NuevoRestaurante;

public class _main_ {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new _main_().startup());
    }
    
    private void startup() {
        
        JButton añadir = new JButton("AÑADIR");
        JButton eliminar = new JButton("ELIMINAR");
        JButton editar = new JButton("EDITAR");
        JButton newRes = new JButton("NUEVO RESTAURANTE");
        
        JPanel panelBoton = new JPanel(new GridLayout(2, 0, 5, 5));
        panelBoton.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        panelBoton.add(añadir);
        panelBoton.add(eliminar);
        panelBoton.add(editar);
        panelBoton.add(newRes);
        
        JFrame mainFrame = Ventana.crear("REST & FOOD", 400, 300, true);
        
        mainFrame.setContentPane(panelBoton);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        añadir.addActionListener(e -> {
            AñadirFrame.añadir();
        });
        
        eliminar.addActionListener(e -> {
            EliminarFrame.eliminarFrame();
        });
        
        editar.addActionListener(e -> {
            EditarFrame.editarFrame();
        });
        
        newRes.addActionListener(e -> {
            NuevoRestaurante.añadirRestaurante();
        });
    }
    
}

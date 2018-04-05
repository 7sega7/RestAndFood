/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.swing;

import com.curso.swing.Ventana;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modelo.dao.SwingController;

/**
 *
 * @author 7sega7
 */
public class MainFrame {
    
    
    
    public static JFrame mainFrame(Integer id_empresa, SwingController controller){
        
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
            AñadirFrame.añadir(id_empresa, controller);
        });
        
        eliminar.addActionListener(e -> {
            EliminarFrame.eliminarFrame(id_empresa, controller);
        });
        
        editar.addActionListener(e -> {
            EditarFrame.editarFrame(id_empresa, controller);
        });
        
        newRes.addActionListener(e -> {
            NuevoRestaurante.añadirRestaurante();
        });
        
        return mainFrame;
    }
    
}

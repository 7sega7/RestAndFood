
package modelo.swing;

import com.curso.swing.Ventana;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import modelo.dao.SwingController;
import modelo.dao.SwingControllerImpl;
import modelo.entidades.Oferta;
import modelo.excepctions.OfertaException;



public class EliminarFrame {
    
    public static JFrame eliminarFrame(){
        
        
        JFrame el = Ventana.crear("", 300, 350, false);
        
        // Creación del panel principal --> titulo principal, panel secundario, boton
        JPanel panelp = new JPanel(new BorderLayout(5, 5));
        panelp.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Creacion del panel secundario --> titulo secundario, lista.
        JPanel panelse = new JPanel(); 
        panelse.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                
        // Titulos de la parte superior
        JLabel principal = new JLabel("ELIMINAR OFERTA");
        
        principal.setFont(new Font("Serif", Font.BOLD, 25));
        
        // Titulo secundario
        JLabel secundario = new JLabel("ELIJA LA OFERTA A ELIMINAR");
        
        // Creacion de la Lista
        
        DefaultListModel<Oferta> modeloLista = new DefaultListModel<>();
        JList<Oferta> lista = new JList<>(modeloLista);
        
        
       
        
        // Añadir los elementos a la lista
        // for (Oferta of : lista) {
        // modeloLista.addElement(of);
        
        // Creacion del boton
        JButton buttonDel = new JButton("Eliminar");
        
        
        
        // Mensaje de advertencia de si quieres eliminar los datos seleccionado.
        
        buttonDel.addActionListener(e -> { 
        
                
                
                SwingController controller = new SwingControllerImpl();
                
                if(JOptionPane.showConfirmDialog(null, "¿Estás seguro?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // yes option
                
                
                buttonDel.addActionListener(del -> {
             
                  
                    try {
                        String title = "";
                        controller.eliminarOferta(title);
                    } catch (OfertaException ex) {
                        Logger.getLogger(EliminarFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                  
                    
                });
                
                
            
                } else {
                // no option
                   
                }
         });
        
        
        
        
        
        /*
        
        // Evento que confirma la eliminacion seleccionada.
        
        
        
        
        buttonDel.addActionListener(e -> {
        
            try{
            
                SwingController controller = new SwingControllerImpl();
                
                
            
            
        } catch (OfertaException ex) {
            
            System.out.println(ex.getMessage());
        } 
        
        
        
        } );
        
        */
        
        
       
        
        
        
        
        // Añadir elementos al panel secundario
        panelse.add(secundario, BorderLayout.NORTH);
        panelse.add(new JScrollPane(lista), BorderLayout.SOUTH);
        
        // Añadir elementos al panel principal
        panelp.add(principal, BorderLayout.NORTH);
        panelp.add(panelse, BorderLayout.CENTER);
        panelp.add(buttonDel, BorderLayout.SOUTH);
        
        // Visibilizacion del panel
        el.setContentPane(panelp);
        el.setVisible(true);
        el.setLocationRelativeTo(null);
        el.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
         
        return el;
    }
}

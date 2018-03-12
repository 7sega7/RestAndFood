
package modelo.swing;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import modelo.entidades.Oferta;



public class EliminarFrame {
    
    public static JFrame eliminarFrame(){
        
        // Titulos de la parte superior
        JLabel principal = new JLabel("ELIMINAR OFERTA");
        
        principal.setSize(20, 20);
        
        JLabel titulo = new JLabel("ELIJA LA OFERTA A ELIMINAR");
        
        
        // Creacion de la Lista
        JList<Oferta> lista = new JList<>();
        
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Creacion del boton eliminar
        JButton buttonDel = new JButton("Eliminar");
        
        JPanel inferior = new JPanel();
        
        inferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inferior.add(buttonDel);
        
        // Evento que confirma la eliminacion seleccionada. buttonDel.addActionListener(al);
        
        panel.add(principal, BorderLayout.NORTH);
        panel.add(titulo, BorderLayout.NORTH);
        panel.add(new JScrollPane(lista), BorderLayout.CENTER );
        panel.add(inferior, BorderLayout.SOUTH);
        
        
         
        
        return null;
    }
}

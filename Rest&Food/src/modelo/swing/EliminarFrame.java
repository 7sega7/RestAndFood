
package modelo.swing;

import com.curso.swing.Ventana;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import modelo.dao.SwingController;
import modelo.dao.SwingControllerImpl;
import modelo.entidades.Oferta;
import modelo.excepctions.OfertaException;



public class EliminarFrame {
    
    public static JFrame eliminarFrame(Integer id_empresa, SwingController controller){
        
        
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
        JLabel secundario = new JLabel("ELIJA LA OFERTA A ELIMINAR", SwingConstants.CENTER);
        
        // Creacion de la lista y su modelo.
        
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        JList<String> lista = new JList<>(); 
        
        // Creacion del JComboBox precargado.
        DefaultComboBoxModel<String> comboxmodel = new DefaultComboBoxModel<>();
        JComboBox<String> comboxeliminar = new JComboBox();
        
        // Creacion del boton
        JButton buttonDel = new JButton("Eliminar");
        
        
        // Ahora precargamos el comboBox
        
        List<Oferta> ofertalist = new ArrayList<>();
        try {
             ofertalist = controller.listarOfertas(id_empresa);
        
            for (Oferta of : ofertalist) {
                comboxmodel.addElement(of.getTitulo());   
            }
            
            comboxeliminar.setModel(comboxmodel);
          
         
        } catch (OfertaException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        
        
        // Añadir elementos al panel secundario
        panelse.add(secundario, BorderLayout.NORTH);
        panelse.add(comboxeliminar, BorderLayout.CENTER);
       
        
        // Añadir elementos al panel principal
        panelp.add(principal, BorderLayout.NORTH);
        panelp.add(panelse, BorderLayout.CENTER);
        panelp.add(buttonDel, BorderLayout.SOUTH);
        
        
         
        // EVENTO DE ELIMINAR
        
        buttonDel.addActionListener(e -> { 
            
            if (lista.getModel().getSize() == 0) {
                
                JOptionPane.showMessageDialog(null, "TIENES QUE SELECCIONAR UNA OFERTA", "AVISO", JOptionPane.WARNING_MESSAGE);
                
            } else {
                
            
                if(JOptionPane.showConfirmDialog(null, "¿Estás seguro?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // yes option
                
                
                buttonDel.addActionListener(del -> {
                 /*
                    try {
                        
                        
                    //    Integer id_oferta = ofertalist.get(comboxeliminar.getSelectedIndex()).getId_oferta();
                        //  comboxmodel.addElement(lista.getSelectedValue());
                        //  modeloLista.removeElement(lista.getSelectedValue());
                        //  lista.setModel(modeloLista);
                        //comboxeliminar.setModel(comboxmodel);
                     //   controller.eliminarOferta(id_oferta);
                    } catch (OfertaException ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                   
                 */ 
                    
                });
                
                
            
                } else {
                // no option
                   
                }
            }
         });
        
    
        
        // Visibilizacion del panel
        el.setContentPane(panelp);
        el.setVisible(true);
        el.setLocationRelativeTo(null);
        el.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
         
        return el;
    }
}

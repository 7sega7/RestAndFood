
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
        
        
        JFrame el = Ventana.crear("ELIMINAR OFERTA", 450, 300, false);
        
        // Creación del panel principal --> titulo principal, panel secundario, boton
        JPanel panelp = new JPanel(new BorderLayout(5, 5));
        
        
        // Creacion del panel secundario --> titulo secundario, lista.
        JPanel panelse = new JPanel(); 
        
        // Creacion del panel que contenga comboBox precargado y los botones de añadir y quitar
        JPanel panelter = new JPanel();
        
        
                
        // Titulos de la parte superior
        JLabel header = new JLabel("ELIMINAR OFERTA", SwingConstants.CENTER);
        header.setFont(new Font("Serif", Font.BOLD, 20));
        
        // Titulo secundario
        JLabel secundario = new JLabel("ELIJA LA OFERTA A ELIMINAR");
        
        // Creacion de la lista y su modelo.
        
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        JList<String> lista = new JList<>(); 
        
        // Creacion del JComboBox precargado.
        DefaultComboBoxModel<String> comboxmodel = new DefaultComboBoxModel<>();
        JComboBox<String> comboxeliminar = new JComboBox();
        
        // Creacion del boton
        JButton buttonDel = new JButton("Eliminar");
        
        // Creación de los botones de aceptar y quitar del ScrollPane
        
        JButton botonadd = new JButton("AÑADIR");
        JButton botonquit = new JButton("QUITAR");
        
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
        panelse.add(header, BorderLayout.NORTH);
        panelse.add(panelter, BorderLayout.CENTER);
        
        // Añadir elementos al panel terciario
        panelter.add(comboxeliminar);
        panelter.add(botonadd);
        panelter.add(botonquit);
       
        
        // Añadir elementos al panel principal
        
        panelp.add(panelse, BorderLayout.NORTH);
        panelp.add(new JScrollPane(lista), BorderLayout.CENTER);
        panelp.add(buttonDel, BorderLayout.SOUTH);
        
        
         
        // EVENTO DE ELIMINAR
        
        Integer id_oferta;
        buttonDel.addActionListener(e -> { 
            
            if (lista.getModel().getSize() == 0) {
                
                JOptionPane.showMessageDialog(null, "NO HAS SELECCIONADO UNA OFERTA", "AVISO", JOptionPane.WARNING_MESSAGE);
                
            } else {
                
            
                if(JOptionPane.showConfirmDialog(null, "¿Estás seguro?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // yes option
                
                /*
                    try {
                        
                        
                       id_oferta = ofertalist.get(comboxeliminar.getSelectedIndex()).getId_oferta();
                       // id_oferta = ofertalist.get(comboxeliminar.getSelectedIndex()).getId_oferta();
                        comboxmodel.addElement(lista.getSelectedValue());
                        modeloLista.removeElement(lista.getSelectedValue());
                        lista.setModel(modeloLista);
                        comboxeliminar.setModel(comboxmodel);
                        controller.eliminarOferta(id_oferta);
                    } catch (OfertaException ex) {
                        System.out.println(ex.getLocalizedMessage());
                    }
                   
                  */   
            
                } else {
                // no option
                   
                }
            }
         });
        
        
        botonadd.addActionListener(e -> {

            if (comboxeliminar.getSelectedItem() != null) {
                modeloLista.addElement((String) comboxeliminar.getSelectedItem());
                lista.setModel(modeloLista);
                comboxeliminar.removeItem(comboxeliminar.getSelectedItem());
            } else {
                JOptionPane.showMessageDialog(null, "Elige una oferta",
                        "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        
        botonquit.addActionListener(e -> {

            if (lista.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "Selecciona una oferta para quitarlo",
                        "ERROR", JOptionPane.WARNING_MESSAGE);
            } else {
                comboxmodel.addElement(lista.getSelectedValue());
                modeloLista.removeElement(lista.getSelectedValue());
                lista.setModel(modeloLista);
                comboxeliminar.setModel(comboxmodel);

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

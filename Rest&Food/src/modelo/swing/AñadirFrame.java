package modelo.swing;

import com.curso.swing.Ventana;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import modelo.dao.SwingController;
import modelo.dao.SwingControllerImpl;

public class AñadirFrame {
    
    public static JFrame añadir(){
        
        JLabel header = new JLabel("AÑADIR NUEVA OFERTA", SwingConstants.CENTER);
        
        JLabel titleLbl = new JLabel("TITULO");
        JLabel descLbl = new JLabel("DESCRIPCION");
        JLabel fechIniLbl = new JLabel("FECHA INICIO");
        JLabel fechFinLbl = new JLabel("FECHA CADUCIDAD");
        JLabel tipoDesLbl = new JLabel("TIPO DESCUENTO");
        
        JTextField titleTxt = new JTextField();
        JTextField descTxt = new JTextField();
        JDateChooser fechaIni = new JDateChooser();
        JDateChooser fechaFin = new JDateChooser();
        JTextField tipoDesTxt = new JTextField();
        JButton aceptarBtn = new JButton("AÑADIR OFERTA");
        
        JPanel panelTxt = new JPanel(new GridLayout(5, 0, 5, 5));
        panelTxt.add(titleLbl);
        panelTxt.add(titleTxt);
        panelTxt.add(descLbl);
        panelTxt.add(descTxt);
        panelTxt.add(fechIniLbl);
        panelTxt.add(fechaIni);
        panelTxt.add(fechFinLbl);
        panelTxt.add(fechaFin);
        panelTxt.add(tipoDesLbl);
        panelTxt.add(tipoDesTxt);
        
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(panelTxt, BorderLayout.CENTER);
        mainPanel.add(aceptarBtn, BorderLayout.SOUTH);
        
        JFrame anadirFrame = Ventana.crear("AÑADA NUEVAS OFERTAS", 400, 250, false);
        anadirFrame.setContentPane(mainPanel);
        anadirFrame.setLocationRelativeTo(null);
        anadirFrame.setVisible(true);
        anadirFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        aceptarBtn.addActionListener(e -> {
            //try {
                SwingController controller = new SwingControllerImpl();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                Date d = fechaIni.getDate();
                
                String datef = dateFormat.format(d);
                System.out.println(datef);
                
                //Oferta of = new Oferta(titleTxt.getText(), descTxt.getText(), 
                        //fechaIni.getSelectedDate().getTime(), 
                        //fechaFin.getSelectedDate().getTime(), tipoDesTxt.getText());
                
                //controller.insertarOferta(of);
                
                //anadirFrame.setVisible(false);
            //} catch(OfertaException ex){
                //System.out.println(ex.getMessage());
            //}
        });
        
        return anadirFrame;
    }
    
}

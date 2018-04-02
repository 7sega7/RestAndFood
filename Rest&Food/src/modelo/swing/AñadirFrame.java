package modelo.swing;

import com.curso.swing.Ventana;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import modelo.dao.SwingController;
import modelo.entidades.Oferta;
import modelo.entidades.Restaurante;
import modelo.excepctions.OfertaException;
import modelo.excepctions.RestauranteException;

public class AñadirFrame {

    public static JFrame añadir(Integer id_empresa, SwingController controller) {

        JLabel header = new JLabel("AÑADIR NUEVA OFERTA", SwingConstants.CENTER);

        JLabel titleLbl = new JLabel("TITULO");
        JLabel descLbl = new JLabel("DESCRIPCION");
        JLabel fechIniLbl = new JLabel("FECHA INICIO");
        JLabel fechFinLbl = new JLabel("FECHA CADUCIDAD");
        JLabel tipoDesLbl = new JLabel("TIPO DESCUENTO");
        JLabel restaurantesLbl = new JLabel("ELIJA LOS RESTAURANTES");

        JTextField titleTxt = new JTextField();
        JTextField descTxt = new JTextField();
        JDateChooser fechaIni = new JDateChooser();
        JDateChooser fechaFin = new JDateChooser();
        JTextField tipoDesTxt = new JTextField();
        DefaultComboBoxModel<Restaurante> modeloCombo = new DefaultComboBoxModel<>();
        JButton añadirResBtn = new JButton("AÑADIR OFERTA AL RESTAURANTE");
        JComboBox restaurantesCbox = new JComboBox();
        JList restList = new JList();

        JButton aceptarBtn = new JButton("CREAR NUEVA OFERTA");

        try {
            List<Restaurante> restaurantesList = controller.listRestaurante(id_empresa);

            for (Restaurante res : restaurantesList) {
                modeloCombo.addElement(res);
            }
        } catch (RestauranteException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

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
            try {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

                Date d = fechaIni.getDate();

                String dateIni = dateFormat.format(d);

                Date r = fechaFin.getDate();

                String dateFin = dateFormat.format(r);

                Oferta of = new Oferta(titleTxt.getText(), descTxt.getText(),
                        dateIni, dateFin, tipoDesTxt.getText());

                controller.insertarOferta(of);

                anadirFrame.setVisible(false);
            } catch (OfertaException ex) {
                System.out.println(ex.getMessage());
            }
        });

        return anadirFrame;
    }

}

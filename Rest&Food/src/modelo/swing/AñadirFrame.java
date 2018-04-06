package modelo.swing;

import com.curso.swing.Ventana;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
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
        titleTxt.setToolTipText("Titulo de la oferta");
        JTextField descTxt = new JTextField();
        descTxt.setToolTipText("Descripcion de la oferta");
        JDateChooser fechaIni = new JDateChooser();
        JDateChooser fechaFin = new JDateChooser();
        JTextField tipoDesTxt = new JTextField();
        tipoDesTxt.setToolTipText("Tipo de descuento");
        DefaultComboBoxModel<String> restaurantesCboxModel = new DefaultComboBoxModel<>();
        JButton añadirResBtn = new JButton("AÑADIR OFERTA AL RESTAURANTE");
        JButton quitarResBtn = new JButton("QUITAR OFERTA DEL RESTAURANTE");
        JComboBox<String> restaurantesCbox = new JComboBox();
        DefaultListModel<String> restListModel = new DefaultListModel<>();
        JList<String> restList = new JList();

        JButton aceptarBtn = new JButton("CREAR NUEVA OFERTA");

        try {
            List<Restaurante> restaurantesList = controller.listRestaurante(id_empresa);

            for (Restaurante res : restaurantesList) {
                restaurantesCboxModel.addElement(res.getNombre());
            }

            restaurantesCbox.setModel(restaurantesCboxModel);
        } catch (RestauranteException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        JPanel panelTxt = new JPanel(new GridLayout(8, 2, 5, 5));
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
        panelTxt.add(restaurantesLbl);
        panelTxt.add(restaurantesCbox);
        panelTxt.add(añadirResBtn);
        panelTxt.add(quitarResBtn);

        JPanel secondPanel = new JPanel(new BorderLayout(5, 5));
        secondPanel.add(header, BorderLayout.NORTH);
        secondPanel.add(panelTxt, BorderLayout.CENTER);
        
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.add(secondPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(restList), BorderLayout.CENTER);
        mainPanel.add(aceptarBtn, BorderLayout.SOUTH);

        JFrame anadirFrame = Ventana.crear("AÑADA NUEVAS OFERTAS", 450, 380, false);
        anadirFrame.setContentPane(mainPanel);
        anadirFrame.setLocationRelativeTo(null);
        anadirFrame.setVisible(true);
        anadirFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        aceptarBtn.addActionListener(e -> {

            if (restList.getModel().getSize() == 0) {
                JOptionPane.showMessageDialog(null,
                        "TIENES QUE AÑADIR ALGUN RESTAURANTE A LA OFERTA", "ERROR", JOptionPane.WARNING_MESSAGE);
            } else {

                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = fechaIni.getDate();
                    String dateIni = dateFormat.format(d);
                    Date r = fechaFin.getDate();
                    String dateFin = dateFormat.format(r);

                    Oferta of = new Oferta(titleTxt.getText(), descTxt.getText(),
                            dateIni, dateFin, tipoDesTxt.getText());

                    String[] restNombre = new String[restListModel.getSize()];

                    for (Integer i = 0; i < restListModel.getSize(); i++) {
                        restNombre[i] = restListModel.getElementAt(i);
                        System.out.println(restListModel.getElementAt(i));
                    }
                    controller.insertarOferta(of, restNombre);

                    anadirFrame.setVisible(false);
                } catch (OfertaException ex) {
                    System.out.println(ex.getLocalizedMessage());
                }
            }

        });

        añadirResBtn.addActionListener(e -> {

            if (restaurantesCbox.getSelectedItem() != null) {
                restListModel.addElement((String) restaurantesCbox.getSelectedItem());
                restList.setModel(restListModel);
                restaurantesCbox.removeItem(restaurantesCbox.getSelectedItem());
            } else {
                JOptionPane.showMessageDialog(null, "Tienes que seleccionar algun restaurante para poder añadirlo",
                        "ERROR", JOptionPane.WARNING_MESSAGE);
            }
        });

        quitarResBtn.addActionListener(e -> {

            if (restList.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "Tienes que seleccionar algun restaurante para poder quitarlo",
                        "ERROR", JOptionPane.WARNING_MESSAGE);
            } else {
                restaurantesCboxModel.addElement(restList.getSelectedValue());
                restListModel.removeElement(restList.getSelectedValue());
                restList.setModel(restListModel);
                restaurantesCbox.setModel(restaurantesCboxModel);

            }

        });

        return anadirFrame;
    }

}

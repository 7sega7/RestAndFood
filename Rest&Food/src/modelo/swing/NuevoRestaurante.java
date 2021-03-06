package modelo.swing;

import com.curso.swing.Ventana;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.dao.SwingController;
import modelo.entidades.Restaurante;
import modelo.excepctions.RestauranteException;

public class NuevoRestaurante {

    public static JFrame añadirRestaurante(Integer id_empresa, SwingController controller) {

        JFrame nuevorestaurante = Ventana.crear("AÑADA NUEVO RESTAURANTE", 425, 350, false);

        JLabel header = new JLabel("AÑADIR NUEVO RESTAURANTE");

        JLabel nombre = new JLabel("NOMBRE");
        JLabel ciudad = new JLabel("CIUDAD");
        JLabel direccion = new JLabel("DIRECCION");
        JLabel codigo_postal = new JLabel("CODIGO POSTAL");

        JTextField txtnombre = new JTextField();
        JTextField txtciudad = new JTextField();
        JTextField txtdireccion = new JTextField();
        JTextField txtpostal = new JTextField();

        JButton nuevores = new JButton("CREAR NUEVO RESTAURANTE");

        JPanel paneltxt = new JPanel(new GridLayout(4, 0, 5, 5));
        paneltxt.add(nombre);
        paneltxt.add(txtnombre);
        paneltxt.add(ciudad);
        paneltxt.add(txtciudad);
        paneltxt.add(direccion);
        paneltxt.add(txtdireccion);
        paneltxt.add(codigo_postal);
        paneltxt.add(txtpostal);

        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(paneltxt, BorderLayout.CENTER);
        mainPanel.add(nuevores, BorderLayout.SOUTH);

        nuevorestaurante.setContentPane(mainPanel);
        nuevorestaurante.setLocationRelativeTo(null);
        nuevorestaurante.setVisible(true);
        nuevorestaurante.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // EVENTO PARA INSERTAR NUEVOS RESTAURANTES.
        nuevores.addActionListener(rs -> {

            String postal = (String) txtpostal.getText();
            Restaurante nrest = new Restaurante(txtdireccion.getText(),
                    txtnombre.getText(),
                    Integer.parseInt(txtpostal.getText()),
                    txtciudad.getText(),
                    id_empresa);

            try {
                controller.insertarRestaurante(nrest);
            } catch (RestauranteException ex) {
                ex.printStackTrace(System.out);
            }

        });

        return nuevorestaurante;
    }
}

package main;

import com.curso.swing.Ventana;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import modelo.swing.AñadirFrame;
import modelo.swing.MainFrame;

public class _main_ {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new _main_().startup());
        
        try {
            /*
            Paso 1.- Instanciar un objeto de la clase de los Drivers de conexion
            a la BBDD
             */
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("No se pueden cargar los drivers de BBDD");
            System.out.println("La aplicación ha finalizado");

            System.exit(0);
        }
    }
    
    private void startup() {
        
        JLabel emailEmpresaLbl = new JLabel("Introduzca su correo electronico: ");
        JLabel passEmpresaLbl = new JLabel("Introduzca su contraseña: ");
        
        JTextField emailEmpresaTxt = new JTextField();
        JTextField passEmpresaTxt = new JTextField();
        
        JPanel flowPanel = new JPanel(new GridLayout(2, 0));
        flowPanel.add(emailEmpresaLbl);
        flowPanel.add(emailEmpresaTxt);
        emailEmpresaTxt.setToolTipText("Correo Electronico");
        flowPanel.add(passEmpresaLbl);
        flowPanel.add(passEmpresaTxt);
        passEmpresaTxt.setToolTipText("Contraseña");
        
        JButton accederBtn = new JButton("ACCEDER");
        
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        mainPanel.add(flowPanel, BorderLayout.NORTH);
        mainPanel.add(accederBtn, BorderLayout.SOUTH);
        
        JFrame empresaFrame = Ventana.crear("REST&FOOD", 450, 125, false);
        empresaFrame.setContentPane(mainPanel);
        empresaFrame.setVisible(true);
        empresaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        empresaFrame.setLocationRelativeTo(null);
        
        accederBtn.addActionListener(e -> {
            MainFrame.mainFrame();
            empresaFrame.setVisible(false);
        });
        
    }
    
}

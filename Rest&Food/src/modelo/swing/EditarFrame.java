package modelo.swing;

import com.curso.swing.Ventana;
import java.awt.BorderLayout;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import modelo.entidades.Oferta;

public class EditarFrame {

    public static JFrame editarFrame() {

        JLabel title = new JLabel("EDITE AQUI SUS OFERTAS");
        title.setSize(20, 20);

        //OfertaTableModel datosOferta = new OfertaTableModel(ofertas);
        JTable ofertas = new JTable();
        //ofertas.setModel(datosOferta);

        JButton editarBtn = new JButton("EDITAR");

        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(ofertas), BorderLayout.CENTER);
        mainPanel.add(editarBtn, BorderLayout.SOUTH);
        
        JFrame editarFrame = Ventana.crear("EDITAR FRAME", 250, 300, false);
        editarFrame.setLocationRelativeTo(null);
        editarFrame.setContentPane(mainPanel);
        editarFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        editarFrame.setVisible(true);
        
        return editarFrame;
    }
}

class OfertaTableModel extends AbstractTableModel {

    private final List<Oferta> filas;

    public OfertaTableModel(List<Oferta> filas) {
        this.filas = filas;
    }

    @Override
    public int getRowCount() {
        return filas.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        switch (columna) {
            case 0:
                return filas.get(fila).getTitulo();
            case 1:
                return filas.get(fila).getDescripcion();
            case 2:
                return filas.get(fila).getFechaInicio();
            case 3:
                return filas.get(fila).getFechaFinal();
            case 4:
                return filas.get(fila).getEmpresa();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columna) {
        switch (columna) {
            case 0:
                return "TITULO";
            case 1:
                return "DESCRIPCION";
            case 2:
                return "FECHA DE INICIO";
            case 3:
                return "FECHA DE CADUCIDAD";
            case 4:
                return "TIPO DE OFERTA";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columna) {
        switch (columna) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Date.class;
            case 3:
                return Date.class;
            case 4:
                return String.class;
            default:
                return null;
        }
    }

}

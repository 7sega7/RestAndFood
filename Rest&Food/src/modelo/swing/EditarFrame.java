package modelo.swing;

import com.curso.swing.Ventana;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import modelo.dao.SwingController;
import modelo.entidades.Oferta;
import modelo.excepctions.OfertaException;

public class EditarFrame {

    public static JFrame editarFrame(Integer id_empresa, SwingController controller) {

        JLabel title = new JLabel("EDITE AQUI SUS OFERTAS", SwingConstants.CENTER);
        title.setSize(20, 20);

        JTable ofertas = new JTable();

        try {
            OfertaTableModel tableModel = new OfertaTableModel(controller.listarOfertas(id_empresa));

            ofertas.setModel(tableModel);
        } catch (OfertaException ex) {
            ex.printStackTrace(System.out);
        }
        ofertas.setAutoCreateRowSorter(true);
        ofertas.getTableHeader().setReorderingAllowed(false);
        ofertas.getTableHeader().setResizingAllowed(false);
        ofertas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //ofertas.getColumnModel().getColumn(5).setPreferredWidth(0);
        ofertas.getColumnModel().getColumn(5).setMaxWidth(0);
        ofertas.getColumnModel().getColumn(5).setMinWidth(0);
        ofertas.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        ofertas.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);

        JButton editarBtn = new JButton("EDITAR");

        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(ofertas), BorderLayout.CENTER);
        mainPanel.add(editarBtn, BorderLayout.SOUTH);

        JFrame editarFrame = Ventana.crear("EDITAR FRAME", 650, 225, false);
        editarFrame.setLocationRelativeTo(null);
        editarFrame.setContentPane(mainPanel);
        editarFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        editarFrame.setVisible(true);

        List<Integer> indiceFilasCambiadas = new ArrayList<>();

        List<Oferta> ofertasACambiar = new ArrayList<>();

        ofertas.getModel().addTableModelListener(ae -> {
            System.out.println("Celula cambiada en: " + ae.getLastRow());
            indiceFilasCambiadas.add(ae.getLastRow());
        });

        editarBtn.addActionListener(ae -> {

            HashSet<Integer> indiceSinRepetidos = new HashSet<>(indiceFilasCambiadas);
            indiceFilasCambiadas.clear();
            indiceFilasCambiadas.addAll(indiceSinRepetidos);

            for (Integer i = 0; i < indiceFilasCambiadas.size(); i++) {

                Oferta of = new Oferta((String) ofertas.getModel().getValueAt(indiceFilasCambiadas.get(i), 0),
                        (String) ofertas.getModel().getValueAt(indiceFilasCambiadas.get(i), 1),
                        (String) ofertas.getModel().getValueAt(indiceFilasCambiadas.get(i), 2),
                        (String) ofertas.getModel().getValueAt(indiceFilasCambiadas.get(i), 3),
                        (String) ofertas.getModel().getValueAt(indiceFilasCambiadas.get(i), 4),
                        (Integer) ofertas.getModel().getValueAt(indiceFilasCambiadas.get(i), 5));
                ofertasACambiar.add(of);
            }
            
            try {
                controller.updateOferta(ofertasACambiar);
            } catch (OfertaException ex) {
                ex.printStackTrace(System.out);
            }
            
            JOptionPane.showMessageDialog(null, "REGISTROS CAMBIADOS CORRECTAMENTE");
            editarFrame.setVisible(false);
            
        });

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
        return 6;
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
                return filas.get(fila).getTipoOferta();
            case 5:
                return filas.get(fila).getId_oferta();
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
            case 5:
                return "ID_OFERTA";
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
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return Integer.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int fila, int columna) {
        return true;
    }

    @Override
    public void setValueAt(Object value, int fila, int columna) {
        Oferta of = (Oferta) filas.get(fila);

        switch (columna) {
            case 0:
                of.setTitulo(value.toString());
                break;
            case 1:
                of.setDescripcion(value.toString());
                break;
            case 2:
                of.setFechaInicio(value.toString());
                break;
            case 3:
                of.setFechaFinal(value.toString());
                break;
            case 4:
                of.setTipoOferta(value.toString());
                break;
        }
        fireTableCellUpdated(fila, columna);

    }

}

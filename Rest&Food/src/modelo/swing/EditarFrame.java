/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.swing;

import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import modelo.entidades.Oferta;

/**
 *
 * @author Grupo 1 Java
 */
public class EditarFrame {

    public static JFrame editarFrame() {

        JLabel title = new JLabel("EDITE AQUI SUS OFERTAS");
        title.setSize(20, 20);
        
        //OfertaTableModel datosOferta = new OfertaTableModel(ofertas);
        
        JTable ofertas = new JTable();
        //ofertas.setModel(datosOferta);
        
        
        
        return null;
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

package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Oferta;
import modelo.excepctions.OfertaException;
import utilities.ConexionBBDD;

public class SwingControllerImpl implements SwingController {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(ConexionBBDD.URL,
                ConexionBBDD.USER, ConexionBBDD.PASSWORD);
    }

    @Override
    public List<Oferta> listarOfertas() throws OfertaException {

        try {

            Connection conexion = getConnection();

            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT titulo, descripcion, fecha_inicio"
                    + " fecha_final, tipo_oferta FROM restandfood.oferta");

            List<Oferta> listaOfertas = new ArrayList<>();

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String descripcion = rs.getString("descripcion");
                String fecha_inicio = rs.getString("fecha_inicio");
                String fecha_final = rs.getString("fecha_final");
                String tipo_oferta = rs.getString("tipo_oferta");

                Oferta of = new Oferta(titulo, descripcion, fecha_inicio,
                        fecha_final, tipo_oferta);

                listaOfertas.add(of);
            }
            conexion.close();
            return listaOfertas;

        } catch (SQLException ex) {
            throw new OfertaException("Error:" + ex.getStackTrace());
        }

    }

    @Override
    public void insertarOferta(Oferta of) throws OfertaException {

        try {
            Connection conexion = getConnection();

            Statement st = conexion.createStatement();
            System.out.println(of.getTitulo() + of.getDescripcion() + of.getFechaInicio() + of.getFechaFinal() + of.getTipoOferta());
            
            st.executeUpdate("INSERT INTO restandfood.oferta(titulo, descripcion, fecha_inicio,"
                    + " fecha_final, tipo_oferta) "
                    + "VALUES ('" + of.getTitulo() + "', '" + of.getDescripcion() + "', " 
                    + of.getFechaInicio() + ", "  + of.getFechaFinal() + ", '"  + of.getTipoOferta() + "')");

            conexion.close();
        } catch (SQLException ex) {
            throw new OfertaException("Error al insertar:" + ex.getLocalizedMessage());
        }

    }

    @Override
    public void eliminarOferta(String titulo) throws OfertaException {

        try {

            Connection conexion = getConnection();

            Statement st = conexion.createStatement();

            st.executeUpdate("DELETE FROM restandfood.oferta "
                    + "WHERE titulo =  " + titulo);

            conexion.close();

        } catch (SQLException ex) {

            throw new OfertaException("Error al eliminar.  Razon: " + ex.getMessage());
        }

    }

    @Override
    public List<Oferta> updateOferta(Oferta of) throws OfertaException {

        try {

            Connection conexion = getConnection();

            Statement st = conexion.createStatement();

            st.executeUpdate("UPDATE restandfood.oferta SET titulo = " + of.getTitulo()
                    + "descripcion = " + of.getDescripcion()
                    + "fecha_inicio = " + of.getFechaInicio()
                    + "fecha_final = " + of.getFechaFinal()
                    + "tipo_oferta = " + of.getTipoOferta());

            return null;
        } catch (SQLException ex) {

            throw new OfertaException("Error al actualizar.  Razon: " + ex.getMessage());

        }
    }

    @Override
    public Integer loginEmpresa(String email, String contraseña) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

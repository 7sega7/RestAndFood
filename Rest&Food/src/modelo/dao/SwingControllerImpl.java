package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.entidades.Oferta;
import modelo.entidades.Restaurante;
import modelo.excepctions.OfertaException;
import modelo.excepctions.RestauranteException;
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

            PreparedStatement ps = conexion.prepareStatement("SELECT titulo, descripcion, fecha_inicio"
                    + " fecha_final, tipo_oferta FROM restandfood.oferta WHERE ");

            ResultSet rs = ps.executeQuery();

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

            PreparedStatement ps = conexion.prepareStatement(
                    "INSERT INTO restandfood.oferta(titulo, descripcion, fecha_inicio,"
                    + " fecha_final, tipo_oferta) VALUES(?,?,?,?,?)");

            ps.setString(1, of.getTitulo());
            ps.setString(2, of.getDescripcion());
            ps.setString(3, of.getFechaInicio());
            ps.setString(4, of.getFechaFinal());
            ps.setString(5, of.getTipoOferta());

            ps.executeUpdate();
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

            PreparedStatement ps = conexion.prepareStatement(
                    "UPDATE restandfood.oferta SET titulo = ?, descripcion = ?, "
                    + "fecha_inicio = ?, fecha_final = ?, tipo_oferta = ? WHERE id_oferta = ?");

            ps.setString(1, of.getTitulo());
            ps.setString(2, of.getDescripcion());
            ps.setString(3, of.getFechaInicio());
            ps.setString(4, of.getFechaFinal());
            ps.setString(5, of.getTipoOferta());
            //ps.setInt(6, );

            return null;
        } catch (SQLException ex) {

            throw new OfertaException("Error al actualizar.  Razon: " + ex.getMessage());

        }
    }

    @Override
    public Integer loginEmpresa(String email, String contraseña) throws OfertaException {

        try {
            Connection conexion = getConnection();

            PreparedStatement ps = conexion.prepareStatement(
                    "SELECT id_empresa FROM restandfood.empresa WHERE correo = ? AND contraseña = ?");

            ps.setString(1, email);
            ps.setString(2, contraseña);

            ResultSet rs = ps.executeQuery();

            rs.next();

            Integer id_empresa = rs.getInt(1);

            return id_empresa;

        } catch (SQLException ex) {
            throw new OfertaException("Error al logearse. Razon: " + ex.getSQLState() + ex.getLocalizedMessage());
        }
    }

    private static List<Restaurante> restaurantes;

    @Override
    public List<Restaurante> listRestaurante(Integer id_empresa) throws RestauranteException {
        if (restaurantes == null) {

            try {
                Connection conexion = getConnection();

                PreparedStatement ps = conexion.prepareStatement(
                        "SELECT direccion, nombre, codigo_postal, ciudad, id_empresa "
                                + "FROM restandfood.restaurante WHERE id_empresa = ?");

                ps.setInt(1, id_empresa);

                ResultSet rs = ps.executeQuery();
            
                while (rs.next()) {
                    String direccion = rs.getString("direccion");
                    String nombre = rs.getString("nombre");
                    Integer cod_postal = rs.getInt("codigo_postal");
                    String ciudad = rs.getString("ciudad");
                    Integer idEmpresa = rs.getInt("id_empresa");
                    Restaurante r = new Restaurante(direccion, nombre, cod_postal, ciudad, idEmpresa);
                    
                    restaurantes.add(r);
                }

                
            } catch (SQLException ex) {
                throw new RestauranteException("Error al recoger los restaurantes. Razon: " + ex.getSQLState() + ex.getLocalizedMessage());
            }
        }
        return restaurantes;
    }

}

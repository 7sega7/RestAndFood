package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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

            ResultSet rs = st.executeQuery("SELECT title, description, start_date"
                    + " end_date, discount_type, empresa FROM sql11227552.ofertas");

            List<Oferta> listaOfertas = new ArrayList<>();

            while (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date start_date = rs.getDate("start_date");
                Date end_date = rs.getDate("end_date");
                String discount_type = rs.getString("discount_type");
                
                Oferta of = new Oferta(title, description, start_date, 
                        end_date, discount_type);
                
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
        
        try{
            Connection conexion = getConnection();
        
            Statement st = conexion.createStatement();
            
            Date fechaIni = new Date(of.getFechaInicio().getTime());
            Date fechaFin = new Date(of.getFechaFinal().getTime());
            
            st.executeUpdate("INSERT INTO sql11227552.ofertas(title, description, start_date"
                    + " end_date, discount_type) "
                    + "VALUES(" + of.getTitulo() + of.getDescripcion() + 
                    fechaIni + fechaFin + of.getTipoOferta() +"')");
        
            conexion.close();
        } catch (SQLException ex){
            throw new OfertaException("Error al insertar:" + ex.getMessage());
        }  
        
    }

    @Override
    public void eliminarOferta(String title) throws OfertaException {
        
        try {
            
          Connection conexion = getConnection();
          
          Statement st = conexion.createStatement();
          
          st.executeUpdate("DELETE FROM sql11227552.ofertas "
                  + "WHERE title =  " + title);
          
          conexion.close();
          
            
            
        } catch(SQLException ex) {
            
            throw new OfertaException("Error al eliminar.  Razon: " + ex.getMessage());
        }
        
        
        
    }

    @Override
    public List<Oferta> updateOferta(Oferta of) throws OfertaException {
        
        
        try {
            
            Connection conexion = getConnection();
            
            Statement st = conexion.createStatement();
            
            st.executeUpdate("UPDATE sql11227552.ofertas SET title = " + of.getTitulo()
                    + "description = " + of.getDescripcion() 
                    + "start_date = " + of.getFechaInicio() 
                    + "end_date = " + of.getFechaFinal() 
                    + "discount_type = " + of.getTipoOferta());
        
        
        
        return null;
        } catch (SQLException ex) {
            
            throw new OfertaException("Error al actualizar.  Razon: " + ex.getMessage());
            
        }
    }

}

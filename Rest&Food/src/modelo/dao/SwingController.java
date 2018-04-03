package modelo.dao;

import java.util.List;
import modelo.entidades.Oferta;
import modelo.entidades.Restaurante;
import modelo.excepctions.OfertaException;
import modelo.excepctions.RestauranteException;

public interface SwingController {

    public abstract List<Oferta> listarOfertas() throws OfertaException;

    public abstract void insertarOferta(Oferta of, String[] restNombres) throws OfertaException;

    public abstract void eliminarOferta(String title) throws OfertaException;

    public abstract List<Oferta> updateOferta(Oferta of) throws OfertaException;
    
    public abstract Integer loginEmpresa(String email, String contraseña) throws OfertaException;
    
    public abstract List<Restaurante> listRestaurante(Integer id_empresa) throws RestauranteException;
}

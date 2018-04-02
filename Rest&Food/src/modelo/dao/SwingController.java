package modelo.dao;

import java.util.List;
import modelo.entidades.Oferta;
import modelo.excepctions.OfertaException;

public interface SwingController {

    public abstract List<Oferta> listarOfertas() throws OfertaException;

    public abstract void insertarOferta(Oferta of) throws OfertaException;

    public abstract void eliminarOferta(String title) throws OfertaException;

    public abstract List<Oferta> updateOferta(Oferta of) throws OfertaException;
    
    public abstract Integer loginEmpresa(String email, String contraseña);
}

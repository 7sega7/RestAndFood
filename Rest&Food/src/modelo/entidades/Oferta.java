package modelo.entidades;

import java.util.Date;

public class Oferta {

    private final String titulo;
    private final String descripcion;
    private final Date fechaInicio;
    private final Date fechaFinal;
    private final String tipoOferta;
    private final String empresa;

    public Oferta(String titulo, String descripcion, Date fechaInicio,
            Date fechaFinal, String tipoOferta, String empresa) {
        
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.tipoOferta = tipoOferta;
        this.empresa = empresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public String getTipoOferta() {
        return tipoOferta;
    }

    public String getEmpresa() {
        return empresa;
    }

}

package modelo.entidades;

public class Oferta {

    private final String titulo;
    private final String descripcion;
    private final String fechaInicio;
    private final String fechaFinal;
    private final String tipoOferta;

    public Oferta(String titulo, String descripcion, String fechaInicio,
            String fechaFinal, String tipoOferta) {
        
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.tipoOferta = tipoOferta;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public String getTipoOferta() {
        return tipoOferta;
    }

}

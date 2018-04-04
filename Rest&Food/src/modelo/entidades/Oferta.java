package modelo.entidades;

public class Oferta {

    private String titulo;
    private String descripcion;
    private String fechaInicio;
    private String fechaFinal;
    private String tipoOferta;
    private Integer id_oferta;

    public Oferta(String titulo, String descripcion, String fechaInicio,
            String fechaFinal, String tipoOferta) {
        
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.tipoOferta = tipoOferta;
    }

    public Oferta(String titulo, String descripcion, String fechaInicio, String fechaFinal, String tipoOferta, Integer id_oferta) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.tipoOferta = tipoOferta;
        this.id_oferta = id_oferta;
    }

    public Integer getId_oferta() {
        return id_oferta;
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public void setTipoOferta(String tipoOferta) {
        this.tipoOferta = tipoOferta;
    }

}

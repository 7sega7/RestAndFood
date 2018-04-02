package modelo.entidades;

public class Restaurante {
    
    private final String direccion;
    private final String nombre;
    private final Integer cod_postal;
    private final String ciudad;
    private final Integer id_empresa;

    public Restaurante(String direccion, String nombre, Integer cod_postal, String ciudad, Integer id_empresa) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.cod_postal = cod_postal;
        this.ciudad = ciudad;
        this.id_empresa = id_empresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getCod_postal() {
        return cod_postal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public Integer getId_empresa() {
        return id_empresa;
    }
    
    
}

package clases;

public class Procesador {
    String id;
    String codigo;
    Boolean refrigerado;
    Integer anio;

    public Procesador(String id, String codigo, Boolean refrigerado, Integer anio) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anio = anio;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Boolean getRefrigerado() {
        return this.refrigerado;
    }

    public void setRefrigerado(Boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    public Integer getAnio() {
        return this.anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }    
}

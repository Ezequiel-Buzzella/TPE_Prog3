package clases;

public class Tarea {
    String id;
    String nombre;
    int tiempo;
    boolean critica;
    int prioridad;

    public Tarea(String id, String nombre, int tiempo, boolean critica, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.critica = critica;
        this.prioridad = prioridad;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return this.tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public boolean esCritica() {
        return this.critica;
    }

    public void setCritica(boolean critica) {
        this.critica = critica;
    }

    public int getPrioridad() {
        return this.prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Tarea " + this.getId() + 
                ": Nombre " + this.getNombre() + 
                "; tiempo: " + this.getTiempo() + 
                "; critica: " + this.esCritica() + 
                "; prioridad: " + this.getPrioridad();
    }

}
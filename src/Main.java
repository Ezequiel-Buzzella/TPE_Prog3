import java.util.List;

import clases.Servicios;
import clases.Tarea;

public class Main {
    public static void main(String[] args) {
        String tareasPath = "src/datasets/Tareas.csv";
        String procesadoresPath = "src/datasets/Procesadores.csv";
        Servicios servicios = new Servicios(procesadoresPath, tareasPath);
        
        System.out.println("------------------------------Servicio 1------------------------------");
        Tarea tarea = servicios.servicio1("T2");
        System.out.println(tarea);

        System.out.println("------------------------------Servicio 2------------------------------");
        List<Tarea> tareas1 = servicios.servicio2(false);
        System.out.println(tareas1);

        System.out.println("------------------------------Servicio 3------------------------------");
        List<Tarea> tareas2 = servicios.servicio3(31, 71);
        System.out.println(tareas2);
    }
}

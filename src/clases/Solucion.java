package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solucion {

    private Map<Procesador, List<Tarea>> asignacion;
    private int tiempoMaximo;

    public Solucion(Map<Procesador, List<Tarea>> asignacion, int tiempoMaximo) {
        this.asignacion = asignacion;
        this.tiempoMaximo = tiempoMaximo;
    }

    public Solucion Greedy(List<Procesador> procesadores, List<Tarea> tareas, int tiempoMaximoPermitido) {
        Collections.sort(tareas, new Comparator<Tarea>() {
            public int compare(Tarea t1, Tarea t2) {
                return Integer.compare(t2.getTiempo(), t1.getTiempo());
            }
        });

        Map<Procesador, List<Tarea>> asignacion = new HashMap();

        for (Procesador p : procesadores) {
            asignacion.put(p, new ArrayList<>());
        }

        for (Tarea tarea : tareas) {
            Procesador mejorProcesador = null;
            int menorTiempo = Integer.MAX_VALUE;// Se coloca el maximo vaLor que puede tomar un int

            for (Procesador procesador : procesadores) {
                if (puedeAsignarse(procesador, tarea, asignacion, tiempoMaximoPermitido)) {
                    int timepoActual = calcularTiempoEjecucion(procesador, asignacion);
                    if (timepoActual < menorTiempo) {
                        menorTiempo = timepoActual;
                        mejorProcesador = procesador;
                    }
                }
            }
            if (mejorProcesador != null) {
                asignacion.get(mejorProcesador).add(tarea);
            }
        }
        int tiempoMaximo = calcularTiempoMaximo(asignacion);
        return new Solucion(asignacion, tiempoMaximo);
    }

    private boolean puedeAsignarse(Procesador procesador, Tarea tarea, Map<Procesador, List<Tarea>> asignacion,
            int tiempoMaximoPermitido) {
        List<Tarea> tareaAsignada = asignacion.get(procesador);
        int tareasCriticas = 0;
        int tiempoTotal = 0;

        for (Tarea t : tareaAsignada) {
            if (t.esCritica()) {
                tareasCriticas++;
            }
            tiempoTotal += t.getTiempo();
        }
        if (tarea.esCritica() && tareasCriticas >= 2) {
            return false;
        }
        if (!procesador.getRefrigerado() && tiempoTotal + tarea.getTiempo() > tiempoMaximoPermitido) {
            return false;
        }
        return true;
    }

    private int calcularTiempoEjecucion(Procesador procesador, Map<Procesador, List<Tarea>> asignacion) {
        int tiempo = 0;
        for (Tarea tarea : asignacion.get(procesador)) {
            tiempo += tarea.getTiempo();
        }
        return tiempo;
    }

    private int calcularTiempoMaximo(Map<Procesador, List<Tarea>> asignacion) {
        int tiempoMaximo = 0;
        for (Map.Entry<Procesador, List<Tarea>> entry : asignacion.entrySet()) {
            int tiempoProcesador = 0;
            for (Tarea tarea : entry.getValue()) {
                tiempoProcesador += tarea.getTiempo();
            }
            tiempoMaximo = Math.max(tiempoMaximo, tiempoProcesador);
        }
        return tiempoMaximo;
    }
}
